package ec.edu.ups.modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

/**
 * Representa un carrito de compras que pertenece a un usuario y contiene varios productos.
 * Permite calcular subtotal, IVA y total, así como manipular los ítems agregados.
 *
 * @author Brandon
 * @version 1.0
 */
public class Carrito {

    /** Porcentaje de IVA aplicado al total de la compra */
    private final double IVA = 0.12;

    /** Contador estático para generar códigos únicos */
    private static int contador = 1;

    /** Código único del carrito */
    private int codigo;

    /** Fecha de creación del carrito */
    private GregorianCalendar fechaCreacion;

    /** Lista de ítems que contiene el carrito */
    private List<ItemCarrito> items;

    /** Usuario propietario del carrito */
    private Usuario usuario;

    /**
     * Constructor que inicializa un nuevo carrito con un usuario.
     *
     * @param usuario Usuario al que pertenece el carrito.
     */
    public Carrito(Usuario usuario) {
        codigo = contador++;
        items = new ArrayList<>();
        fechaCreacion = new GregorianCalendar();
        this.usuario = usuario;
    }

    /**
     * Obtiene el código único del carrito.
     *
     * @return Código del carrito.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Establece un nuevo código para el carrito (usado en persistencia).
     *
     * @param codigo Código deseado.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el usuario asociado al carrito.
     *
     * @return Usuario del carrito.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Asigna un nuevo usuario al carrito.
     *
     * @param usuario Usuario que será propietario del carrito.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la fecha en que se creó el carrito.
     *
     * @return Fecha de creación.
     */
    public GregorianCalendar getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece una nueva fecha de creación (usado en carga de datos).
     *
     * @param fechaCreacion Fecha deseada.
     */
    public void setFechaCreacion(GregorianCalendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Agrega un producto con cantidad específica al carrito.
     *
     * @param producto Producto a añadir.
     * @param cantidad Cantidad deseada.
     */
    public void agregarProducto(Producto producto, int cantidad) {
        items.add(new ItemCarrito(producto, cantidad));
    }

    /**
     * Elimina un producto del carrito según su código.
     *
     * @param codigoProducto Código del producto a eliminar.
     */
    public void eliminarProducto(int codigoProducto) {
        Iterator<ItemCarrito> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getProducto().getCodigo() == codigoProducto) {
                it.remove();
                break;
            }
        }
    }

    /**
     * Elimina todos los productos del carrito.
     */
    public void vaciarCarrito() {
        items.clear();
    }

    /**
     * Retorna todos los ítems agregados al carrito.
     *
     * @return Lista de ítems.
     */
    public List<ItemCarrito> obtenerItems() {
        return items;
    }

    /**
     * Verifica si el carrito está vacío.
     *
     * @return {@code true} si no hay ítems, {@code false} si contiene productos.
     */
    public boolean estaVacio() {
        return items.isEmpty();
    }

    /**
     * Calcula el subtotal de la compra (sin IVA).
     *
     * @return Valor subtotal.
     */
    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemCarrito item : items) {
            subtotal += item.getProducto().getPrecio() * item.getCantidad();
        }
        return subtotal;
    }

    /**
     * Calcula el valor del IVA sobre el subtotal.
     *
     * @return Valor de IVA.
     */
    public double calcularIVA() {
        double subtotal = calcularSubtotal();
        return subtotal * IVA;
    }

    /**
     * Calcula el total a pagar (subtotal + IVA).
     *
     * @return Valor total de la compra.
     */
    public double calcularTotal() {
        return calcularSubtotal() + calcularIVA();
    }

    /**
     * Representación en texto del carrito para persistencia.
     * Incluye código, IVA, fecha, total, usuario y lista de ítems.
     *
     * @return Cadena con información del carrito.
     */
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return codigo + "," +
                IVA + "," +
                sdf.format(fechaCreacion.getTime()) + "," +
                calcularTotal() + "," +
                (usuario != null ? usuario.getUsername() : "null") + "," +
                items;
    }
}
