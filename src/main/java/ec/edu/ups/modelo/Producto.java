package ec.edu.ups.modelo;

/**
 * Representa un producto que puede ser añadido al carrito de compras.
 * Contiene información básica como código, nombre y precio.
 *
 * @author Brandon
 * @version 1.0
 */
public class Producto {

    /** Código identificador único del producto */
    private int codigo;

    /** Nombre del producto */
    private String nombre;

    /** Precio unitario del producto */
    private double precio;

    /**
     * Constructor vacío requerido por algunas librerías o frameworks.
     */
    public Producto() {
    }

    /**
     * Constructor que inicializa un producto con sus atributos básicos.
     *
     * @param codigo Código identificador del producto.
     * @param nombre Nombre del producto.
     * @param precio Precio unitario del producto.
     */
    public Producto(int codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Establece el código del producto.
     *
     * @param codigo Código único a asignar.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nombre Texto descriptivo del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el precio del producto.
     *
     * @param precio Valor monetario del producto.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el código del producto.
     *
     * @return Código identificador.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return Nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return Precio unitario.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Devuelve una representación en texto del producto,
     * incluyendo su nombre y precio formateado.
     *
     * @return Cadena descriptiva del producto.
     */
    @Override
    public String toString() {
        return nombre + " - $" + precio;
    }
}
