package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementación en memoria del DAO para la entidad {@link Carrito}.
 * Almacena los carritos en una lista dentro de la aplicación, útil para pruebas y modo memoria.
 *
 * @author Brandon
 * @version 1.0
 */
public class CarritoDAOMemoria implements CarritoDAO {

    /** Lista que almacena los carritos en memoria */
    private List<Carrito> carritos;

    /**
     * Constructor que inicializa la lista de carritos.
     */
    public CarritoDAOMemoria() {
        this.carritos = new ArrayList<>();
    }

    /**
     * Agrega un nuevo carrito a la lista.
     *
     * @param carrito El carrito a ser guardado.
     */
    @Override
    public void crear(Carrito carrito) {
        carritos.add(carrito);
    }

    /**
     * Busca un carrito por su código único.
     *
     * @param codigo Código del carrito a buscar.
     * @return El carrito encontrado o {@code null} si no existe.
     */
    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito carrito : carritos) {
            if (carrito.getCodigo() == codigo) {
                return carrito;
            }
        }
        return null;
    }

    /**
     * Actualiza un carrito existente en la lista.
     *
     * @param carrito Carrito con los nuevos datos a actualizar.
     */
    @Override
    public void actualizar(Carrito carrito) {
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                break;
            }
        }
    }

    /**
     * Elimina un carrito según su código.
     *
     * @param codigo Código del carrito a eliminar.
     */
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

    /**
     * Devuelve todos los carritos almacenados en memoria.
     *
     * @return Lista completa de carritos.
     */
    @Override
    public List<Carrito> listarTodos() {
        return carritos;
    }
}
