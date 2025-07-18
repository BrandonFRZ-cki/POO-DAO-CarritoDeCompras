package ec.edu.ups.dao;

import ec.edu.ups.modelo.Carrito;

import java.util.List;

/**
 * Interfaz que define las operaciones básicas (CRUD) para la entidad {@link Carrito}.
 * Su implementación puede variar según el tipo de almacenamiento (memoria, archivo de texto, archivo binario, base de datos, etc.).
 *
 * <p>Responsabilidades:
 * <ul>
 *     <li>Persistencia de carritos</li>
 *     <li>Búsqueda por código</li>
 *     <li>Modificación y eliminación</li>
 *     <li>Listado completo</li>
 * </ul>
 * </p>
 *
 * @author Brandon
 * @version 1.0
 */
public interface CarritoDAO {

    /**
     * Crea y guarda un nuevo carrito.
     * @param carrito Carrito a guardar.
     */
    void crear(Carrito carrito);

    /**
     * Busca un carrito específico por su código único.
     * @param codigo Código del carrito a buscar.
     * @return El carrito encontrado o {@code null} si no existe.
     */
    Carrito buscarPorCodigo(int codigo);

    /**
     * Actualiza un carrito existente.
     * @param carrito Carrito con los datos actualizados.
     */
    void actualizar(Carrito carrito);

    /**
     * Elimina un carrito según su código.
     * @param codigo Código del carrito a eliminar.
     */
    void eliminar(int codigo);

    /**
     * Retorna una lista de todos los carritos almacenados.
     * @return Lista de carritos.
     */
    List<Carrito> listarTodos();
}
