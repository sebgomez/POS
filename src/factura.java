public class factura {
    private float precioTotal;

    public factura() {
        this.precioTotal = precioTotal;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    void agregarProducto(int pid, int cantidad) {
        float valor = inventario.buscarProducto(pid).getPrecio();
        inventario.eliminarProducto(pid, cantidad);
        this.precioTotal += (valor * cantidad);
    }

    float facturacion(float pago) {
        return pago - precioTotal;
    }
}
