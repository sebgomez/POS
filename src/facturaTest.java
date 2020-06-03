import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class facturaTest {

    @Test
    void TestAgregarProducto() {
        producto p1 = new producto(1, "leche", 2500, new Date());
        producto p2 = new producto(2, "huevos", 12000, new Date());

        inventario i = new inventario();
        i.agregarProducto(p1, 10);
        i.agregarProducto(p2, 8);

        factura f = new factura();
        f.agregarProducto(1,3);
        f.agregarProducto(2,2);

        float cantidadReal = f.getPrecioTotal();
        float expected = 31500;

        assertEquals(expected, cantidadReal);
        assertEquals(7, i.getCantidades().get(1));
        assertEquals(6, i.getCantidades().get(2));
    }

    @Test
    void TestFacturacion() {
    }
}