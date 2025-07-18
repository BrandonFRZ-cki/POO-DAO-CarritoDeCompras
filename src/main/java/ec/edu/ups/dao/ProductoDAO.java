package ec.edu.ups.dao;

import ec.edu.ups.modelo.Producto;

import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad {@link Producto}.
 * Permite desacoplar la lógica de negocio del mecanismo de persistencia (memoria, archivo, base de datos, etc.).
 *
 * <p>Responsabilidades:
 * <ul>
 *     <li>Crear productos</li>
 *     <li>Buscar productos por código o nombre</li>
 *     <li>Actualizar productos existentes</li>
 *     <li>Eliminar productos por código</li>
 *     <li>Listar todos los productos disponibles</li>
 * </ul>
 * </p>
 *
 * @author Brandon
 * @version 1.0
 */
public interface ProductoDAO {

    /**
     * Guarda un nuevo producto en el sistema.
     * @param producto Producto a registrar.
     */
    void crear(Producto producto);

    /**
     * Busca un producto por su código único.
     * @param codigo Código del producto.
     * @return Producto encontrado o {@code null} si no existe.
     */
    Producto buscarPorCodigo(int codigo);

    /**
     * Busca productos cuyo nombre comience con el texto especificado.
     * @param nombre Prefijo del nombre del producto.
     * @return Lista de productos que coinciden con el criterio.
     */
    List<Producto> buscarPorNombre(String nombre);

    /**
     * Actualiza los datos de un producto existente.
     * @param producto Producto con los datos actualizados.
     */
    void actualizar(Producto producto);

    /**
     * Elimina un producto del sistema según su código.
     * @param codigo Código del producto a eliminar.
     */
    void eliminar(int codigo);

    /**
     * Retorna la lista completa de productos almacenados.
     * @return Lista de productos.
     */
    List<Producto> listarTodos();
}
