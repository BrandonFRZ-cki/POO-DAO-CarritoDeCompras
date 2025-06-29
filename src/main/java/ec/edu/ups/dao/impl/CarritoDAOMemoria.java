package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarritoDAOMemoria implements CarritoDAO {

    private List<Carrito> carritos;

    public CarritoDAOMemoria() {
        this.carritos = new ArrayList<>();

        // Crear usuarios temporales para asociarlos con los carritos
        Usuario tempAdmin = new Usuario("tempAdmin", "12345", Rol.ADMINISTRADOR);
        Usuario tempUser = new Usuario("tempUser", "12345", Rol.USUARIO);

        // Carrito 1 – Admin: hogar y cocina
        Carrito c1 = new Carrito(tempAdmin);
        c1.agregarProducto(new Producto(1001, "Cafetera eléctrica", 45.90), 1);
        c1.agregarProducto(new Producto(4002, "Sartén antiadherente", 28.30), 2);
        carritos.add(c1);

        // Carrito 2 – Admin: juguetes
        Carrito c2 = new Carrito(tempAdmin);
        c2.agregarProducto(new Producto(2001, "Rompecabezas 1000 piezas", 14.99), 3);
        c2.agregarProducto(new Producto(2003, "Muñeca interactiva", 39.99), 1);
        carritos.add(c2);

        // Carrito 3 – User: hogar y tecnología
        Carrito c3 = new Carrito(tempUser);
        c3.agregarProducto(new Producto(1002, "Lámpara de escritorio LED", 29.50), 1);
        c3.agregarProducto(new Producto(3001, "Mouse ergonómico", 19.95), 2);
        carritos.add(c3);

        // Carrito 4 – User: cocina
        Carrito c4 = new Carrito(tempUser);
        c4.agregarProducto(new Producto(4001, "Set de cuchillos de cocina", 49.99), 1);
        carritos.add(c4);
    }

    @Override
    public void crear(Carrito carrito) {
        carritos.add(carrito);
    }

    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito carrito : carritos) {
            if (carrito.getCodigo() == codigo) {
                return carrito;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Carrito carrito) {
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                break;
            }
        }
    }

    @Override
    public void eliminar(int codigo) {
        Iterator<Carrito> iterator = carritos.iterator();
        while (iterator.hasNext()) {
            Carrito carrito = iterator.next();
            if (carrito.getCodigo() == codigo) {
                iterator.remove();
            }
        }
    }

    @Override
    public List<Carrito> listarTodos() {
        return carritos;
    }
}
