import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class facturaTest {

    private producto p1, p2;
    private inventario i;
    private factura f;

    enum Cantidades {
        LECHE,
        DOG;
    }

    @BeforeEach
    void init() {
        p1 = new producto(1, "leche", 2500, new Date());
        p2 = new producto(2, "huevos", 12000, new Date());

        i = new inventario();
        inventario.agregarProducto(p1, 10);
        inventario.agregarProducto(p2, 8);
        f = new factura();
    }

    //assertAll
    @Test
    @Order(1)
    void TestAgregarProducto() {
        f.agregarProducto(1, 3);
        f.agregarProducto(2, 2);
        float expected = 31500;
        float cantidadReal = f.getPrecioTotal();

        assertAll("agregar",
                () -> assertEquals(expected, cantidadReal),
                () -> assertEquals(7, i.getCantidades().get(1)),
                () -> assertEquals(6, i.getCantidades().get(2))
        );
    }

    //assertTrue
    @Test
    @Order(2)
    void testFacturacion() {
        f.agregarProducto(1, 3);
        f.agregarProducto(2, 2);
        float expected = 1500;
        float pago = 30000;
        float cantidadReal = f.facturacion(pago);
        assertTrue(cantidadReal > 0);
    }

    //assertNotNull
    @Test
    @Order(3)
    void testInventarioNotNull() {
        inventario i1 = new inventario();
        assertNotNull(i1.getProductos());
        //fail("fallo no nulo", Exception.get);
    }

    //assertNotSame
    @Test
    @Order(3)
    void testInventarioNotSame() {
        assertNotSame(i.getProductos().get(0), i.getProductos().get(1));
    }

    //assertTimeout
    @Test
    @Order(4)
    void timeoutNotExceeded() {
        //The following assertion succeeds.
        assertTimeout(ofMinutes(2), () -> {
            // Perform task that takes less than 2 minutes.
        });
    }

    @Test
    @Order(5)
    void timeoutExceeded() {
        // The following assertion fails with an error message similar to:
        // execution exceeded timeout of 10 ms by 91 ms
        assertTimeout(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(100);
        });
    }

    @Test
    @Order(6)
    void timeoutExceededWithPreemptiveTermination() {
        // The following assertion fails with an error message similar to:
        // execution timed out after 10 ms
        assertTimeoutPreemptively(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            new CountDownLatch(1).await();
        });
    }

    //Tests parametrizados

    //CsvSource
    @ParameterizedTest(name = "{0} - 31500 = {1}")
    @Order(7)
    @CsvSource({
            "50000,    18500",
            "31500,    0",
            "30000,  -1500",
            "35000, 3500"
    })
    void testFacturacionCsvSource(int pago, int expected) {
        f.agregarProducto(1, 3);
        f.agregarProducto(2, 2);
        assertEquals(expected, f.facturacion(pago),
                () -> f.getPrecioTotal() + " - 33000" + " debería ser igual a " + expected);
    }

    //CsvFileSource
    @ParameterizedTest(name = "{0} - 31500 = {1}")
    @Order(8)
    @CsvFileSource(resources = "/test-data.csv")
    void testFacturacionCsvFileSource(int pago, int expected) {
        f.agregarProducto(1, 3);
        f.agregarProducto(2, 2);
        assertEquals(expected, f.facturacion(pago),
                () -> f.getPrecioTotal() + " - 33000" + " debería ser igual a " + expected);
    }

    //ValueSource
    @ParameterizedTest(name = "agregar {0} productos con id 1")
    @Order(9)
    @ValueSource(ints = {3, 6, 9, 12})
    void testAgregarProductoValueSource(int cantidad) {
        f.agregarProducto(1, cantidad);
        float esperado = 2500 * cantidad;
        float real = f.getPrecioTotal();
        assertEquals(esperado, real,
                () -> cantidad + " * 2500" + " debería ser igual a " + esperado);
    }

    @Disabled
    //EnumSource
    @ParameterizedTest
    @Order(10)
    @EnumSource(ChronoUnit.class)
    void testWithEnumSource(TemporalUnit unit) {
        assertNotNull(unit);
    }

    //fail
    @Test
    @Order(11)
    @DisplayName("este test fallará")
    void testFail() {
        fail();
        fail("sin implementar todavía");
        fail(() -> {
            return "sin implementar todavía";
        });
        fail("sin implementar todavía", new RuntimeException());
        fail(new RuntimeException());
    }

    //assumeTrue
    @ParameterizedTest(name = "test assumeTrue, índice: {index} parámetro: {0}")
    @Order(12)
    @ValueSource(ints = {50000, 21000, 40000, 2000})
    void assumeTest(int pago) {
        f.agregarProducto(1, 3);
        f.agregarProducto(2, 2);
        assumeTrue(f.facturacion(pago) >= 0);

        /*assumingThat(f.facturacion(pago)>0, () -> {
            System.out.println("el pago es mayor al precio total");
        });*/
    }

    @RepeatedTest(value = 10, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("Repeat!")
    void customDisplayName(TestInfo testInfo) {
        p1 = new producto(2, "huevos", 12000, new Date());
        inventario.agregarProducto(p1, 10);
        assertEquals("Repeat! 1/10", testInfo.getDisplayName());
        //assertEquals("Repeat! 1/10", testInfo.getDisplayName());
    }
}