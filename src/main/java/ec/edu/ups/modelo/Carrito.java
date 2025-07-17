package ec.edu.ups.modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public class Carrito {

    private final double IVA = 0.12;
    private static int contador = 1;
    private int codigo;
    private GregorianCalendar fechaCreacion;
    private List<ItemCarrito> items;
    private Usuario usuario;

    public Carrito(Usuario usuario) {
        codigo = contador++;
        items = new ArrayList<>();
        fechaCreacion = new GregorianCalendar();
        this.usuario = usuario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public GregorianCalendar getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(GregorianCalendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        items.add(new ItemCarrito(producto, cantidad));
    }

    public void eliminarProducto(int codigoProducto) {
        Iterator<ItemCarrito> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getProducto().getCodigo() == codigoProducto) {
                it.remove();
                break;
            }
        }
    }

    public void vaciarCarrito() {
        items.clear();
    }

    public List<ItemCarrito> obtenerItems() {
        return items;
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemCarrito item : items) {
            subtotal += item.getProducto().getPrecio() * item.getCantidad();
        }
        return subtotal;
    }

    public double calcularIVA() {
        double subtotal = calcularSubtotal();
        return subtotal * IVA;
    }

    public double calcularTotal() {
        return calcularSubtotal() + calcularIVA();
    }


    @Override
    public String toString() {
        return  codigo +","+ IVA+"," + fechaCreacion +"," + items;
    }

    public String toArchivo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return codigo + "," +
                usuario.getUsername() + "," +
                sdf.format(fechaCreacion.getTime());
    }

    public static Carrito fromArchivo(String linea, Usuario usuario) {
        try {
            String[] datos = linea.split(",");
            int codigo = Integer.parseInt(datos[0]);
            String username = datos[1];
            GregorianCalendar fecha = new GregorianCalendar();
            fecha.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(datos[2]));
            Carrito carrito = new Carrito(usuario);
            carrito.setCodigo(codigo);
            carrito.setFechaCreacion(fecha);
            return carrito;
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir línea en Carrito: " + linea, e);
        }
    }

}

