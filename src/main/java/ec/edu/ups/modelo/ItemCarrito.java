package ec.edu.ups.modelo;

/**
 * Representa un ítem dentro del carrito de compras.
 * Contiene un producto y la cantidad seleccionada del mismo.
 * Se encarga también de calcular el subtotal de dicho producto.
 *
 * Este objeto se serializa en texto con el siguiente formato:
 * <pre>
 * codigoProducto,nombreProducto,precio,cantidad
 * </pre>
 *
 * @author Brandon
 * @version 1.2
 */
public class ItemCarrito {

    /** Producto asociado al ítem */
    private Producto producto;

    /** Cantidad de unidades del producto */
    private int cantidad;

    /**
     * Constructor vacío requerido por algunas librerías o frameworks.
     */
    public ItemCarrito() {
    }

    /**
     * Constructor completo.
     *
     * @param producto El producto a agregar al carrito.
     * @param cantidad La cantidad deseada del producto.
     */
    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    /**
     * Establece el producto de este ítem.
     *
     * @param producto El producto que se desea asignar.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Establece la cantidad del producto.
     *
     * @param cantidad Número de unidades deseadas.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el producto de este ítem.
     *
     * @return El producto asignado.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Obtiene la cantidad asignada del producto.
     *
     * @return Número de unidades.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Calcula el subtotal de este ítem (precio × cantidad).
     *
     * @return El subtotal. Retorna 0.0 si el producto es nulo.
     */
    public double getSubtotal() {
        if (producto == null) {
            return 0.0;
        }
        return producto.getPrecio() * cantidad;
    }

    /**
     * Retorna una representación en texto del ítem,
     * útil para almacenamiento en archivos.
     * Formato: codigo,nombre,precio,cantidad
     *
     * @return Cadena serializada del ítem.
     */
    @Override
    public String toString() {
        if (producto == null) {
            return "0,ProductoNulo,0.0," + cantidad;
        }
        return producto.getCodigo() + "," +
                producto.getNombre() + "," +
                producto.getPrecio() + "," +
                cantidad;
    }
}
