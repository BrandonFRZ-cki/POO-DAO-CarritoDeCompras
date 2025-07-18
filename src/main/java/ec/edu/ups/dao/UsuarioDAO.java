package ec.edu.ups.dao;

import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad {@link Usuario}.
 * Facilita la implementación de diferentes mecanismos de persistencia (memoria, archivos, etc.)
 * para el manejo de usuarios del sistema.
 *
 * <p>Responsabilidades:
 * <ul>
 *     <li>Autenticación de usuarios</li>
 *     <li>CRUD completo de usuarios</li>
 *     <li>Filtrado por rol</li>
 * </ul>
 * </p>
 *
 * @author Brandon
 * @version 1.0
 */
public interface UsuarioDAO {

    /**
     * Autentica un usuario con sus credenciales.
     *
     * @param username Nombre de usuario (normalmente cédula).
     * @param contrasenia Contraseña del usuario.
     * @return Usuario autenticado o {@code null} si las credenciales no coinciden.
     */
    Usuario autenticar(String username, String contrasenia);

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param usuario Objeto usuario con todos sus datos.
     */
    void crear(Usuario usuario);

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario (único).
     * @return Usuario encontrado o {@code null} si no existe.
     */
    Usuario buscarPorUsername(String username);

    /**
     * Elimina un usuario del sistema por su nombre de usuario.
     *
     * @param username Nombre de usuario del usuario a eliminar.
     */
    void eliminar(String username);

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param usuario Usuario con los datos actualizados.
     */
    void actualizar(Usuario usuario);

    /**
     * Lista todos los usuarios registrados en el sistema.
     *
     * @return Lista de todos los usuarios.
     */
    List<Usuario> listarTodos();

    /**
     * Lista los usuarios filtrados por su rol (ADMINISTRADOR o USUARIO).
     *
     * @param rol Rol a filtrar.
     * @return Lista de usuarios con el rol especificado.
     */
    List<Usuario> listarPorRol(Rol rol);
}
