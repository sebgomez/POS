import org.junit.jupiter.api.*;

import java.util.Date;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

//import org.junit.jupiter.api.Timeout;

class inventarioTest {

    producto p;
    inventario i;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("@BeforeAll");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println("@AfterAll");
    }

    @BeforeEach
    void setUp() {
        p = new producto(1, "leche", 2500, new Date());
        i = new inventario();
        System.out.println("Set Up @BeforeEach");
    }

    @AfterEach
    void tearDown() throws Exception {
        System.out.println("Tear Down @AfterEach");
    }

    @Test
    @DisplayName("Test de agregar producto")
    void testAgregarProducto() {
        int cantidad = 10;
        inventario.agregarProducto(p, cantidad);
        int cantidadReal = i.getCantidades().get(p.getId());
        assertEquals(11, cantidadReal);
    }

    //@Disabled
    @Test
    void testEliminarProducto() {
        int cantidad = 10;
        int cantidadEliminar = 5;
        inventario.agregarProducto(p, cantidad);
        inventario.eliminarProducto(1, 5);
        int cantidadReal = i.getCantidades().get(p.getId());
        assertEquals(cantidadEliminar, cantidadReal);
    }

}