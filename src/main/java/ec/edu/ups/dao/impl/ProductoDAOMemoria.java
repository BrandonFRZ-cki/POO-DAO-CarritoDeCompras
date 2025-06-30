package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductoDAOMemoria implements ProductoDAO {

    private List<Producto> productos;

    public ProductoDAOMemoria() {
        productos = new ArrayList<>();

        crear(new Producto(2001, "Rompecabezas 1000 piezas", 14.99));
        crear(new Producto(2002, "Set de bloques magnéticos", 23.40));
        crear(new Producto(2003, "Muñeca interactiva", 39.99));
        crear(new Producto(2004, "Carro de control remoto", 27.80));
        crear(new Producto(3001, "Mouse ergonómico", 19.95));
        crear(new Producto(3002, "Teclado inalámbrico", 34.60));
        crear(new Producto(3003, "Soporte para laptop", 21.10));
        crear(new Producto(4001, "Set de cuchillos de cocina", 49.99));
        crear(new Producto(4002, "Sartén antiadherente", 28.30));
        crear(new Producto(4003, "Batidora de mano", 37.75));
    }

    @Override
    public void crear(Producto producto) {
        productos.add(producto);
    }

    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> productosEncontrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getNombre().startsWith(nombre)) {
                productosEncontrados.add(producto);
            }
        }
        return productosEncontrados;
    }

    @Override
    public void actualizar(Producto producto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == producto.getCodigo()) {
                productos.set(i, producto);
            }
        }
    }

    @Override
    public void eliminar(int codigo) {
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            if (producto.getCodigo() == codigo) {
                iterator.remove();
            }
        }
    }

    @Override
    public List<Producto> listarTodos() {
        return productos;
    }
}
