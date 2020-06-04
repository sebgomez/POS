import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class inventario {
    private static ArrayList<producto> productos;
    private static Map<Integer, Integer> cantidades;

    public inventario(ArrayList<producto> productos, Map<Integer, Integer> cantidades) {
        this.productos = productos;
        this.cantidades = cantidades;
    }

    public inventario() {
        this.productos = new ArrayList<>();
        this.cantidades = new HashMap<Integer, Integer>();
    }

    public ArrayList<producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<producto> productos) {
        this.productos = productos;
    }

    public Map<Integer, Integer> getCantidades() {
        return cantidades;
    }

    public void setCantidades(Map<Integer, Integer> cantidades) {
        this.cantidades = cantidades;
    }

    static void agregarProducto(producto p) {
        if (cantidades.get(p.getId()) == null) {
            productos.add(p);
            cantidades.put(p.getId(), 1);
        } else {
            int cantidadPrevia = cantidades.get(p.getId());
            cantidades.put(p.getId(), cantidadPrevia+1);
        }
    }

    static void agregarProducto(producto p, int cantidad) {
        if (cantidades.get(p.getId()) == null) {
            productos.add(p);
            cantidades.put(p.getId(), cantidad);
        } else {
            int cantidadPrevia = cantidades.get(p.getId());
            cantidades.put(p.getId(), cantidadPrevia + cantidad);
        }
    }

    static void eliminarProducto(int pid, int cantidad) {
        if (cantidades.get(pid) != null) {
            int nuevaCantidad = cantidades.get(pid) - cantidad;
            if (nuevaCantidad < 0) {
                nuevaCantidad = 0;
            }
            cantidades.put(pid, nuevaCantidad);
        }
    }

    static producto buscarProducto(int pid) {
        for (producto producto : productos) {
            if (producto.getId() == pid) {
                return producto;
            }
        }
        return null;
    }
}
