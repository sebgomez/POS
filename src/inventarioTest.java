import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;

import org.junit.rules.Timeout;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.jupiter.api.Timeout;

class inventarioTest {

    producto p;
    inventario i;

/*    @BeforeAll
    void setUp() {

    }*/

    @Test
    void testAgregarProducto() {
        p = new producto(1, "leche", 2500, new Date());
        i = new inventario();
        int cantidad = 10;
        i.agregarProducto(p, cantidad);
        int cantidadReal = i.getCantidades().get(p.getId());

        assertEquals(cantidad, cantidadReal);
    }

    @Test
    @DisplayName("╯°□°）╯")
    void eliminarProducto() {
        p = new producto(1, "leche", 2500, new Date());
        i = new inventario();
        int cantidad = 10;
        int cantidadEliminar = 5;
        i.agregarProducto(p, cantidad);
        i.eliminarProducto(1, 5);
        int cantidadReal = i.getCantidades().get(p.getId());
        assertEquals(cantidadEliminar, cantidadReal);
    }

    @Test
    void timeoutNotExceeded() {
        //The following assertion succeeds.
        assertTimeout(ofMinutes(2), () -> {
            // Perform task that takes less than 2 minutes.
        });
    }

    @Test
    void timeoutExceeded() {
        // The following assertion fails with an error message similar to:
        // execution exceeded timeout of 10 ms by 91 ms
        assertTimeout(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(100);
        });
    }
}