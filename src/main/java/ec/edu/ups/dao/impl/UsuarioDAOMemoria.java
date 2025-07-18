package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementación en memoria del DAO para la entidad {@link Usuario}.
 * Los datos se almacenan en una lista sin persistencia en archivos ni bases de datos.
 *
 * <p>Se crean dos usuarios de ejemplo al instanciar esta clase: uno administrador y uno usuario común.</p>
 *
 * @author Brandon
 * @version 1.0
 */
public class UsuarioDAOMemoria implements UsuarioDAO {

    private List<Usuario> usuarios;

    /**
     * Constructor que inicializa la lista de usuarios
     * y agrega un administrador y un usuario de prueba.
     */
    public UsuarioDAOMemoria() {
        usuarios = new ArrayList<Usuario>();
        Usuario admin = new Usuario("0107233710", "Bran@1", Rol.ADMINISTRADOR, "Brandon", "Rivera", "admin@tienda.com", "0999999999");
        Usuario user = new Usuario("0103176194", "Bran@1", Rol.USUARIO, "Cecilia", "Zambrano", "user@tienda.com", "0888888888");
        crear(admin);
        crear(user);
    }

    /**
     * Autentica un usuario comparando username y contraseña.
     *
     * @param username    Cédula (username) del usuario.
     * @param contrasenia Contraseña del usuario.
     * @return El usuario autenticado si existe, o {@code null} si las credenciales no coinciden.
     */
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getContrasenia().equals(contrasenia)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Agrega un nuevo usuario a la lista.
     *
     * @param usuario Usuario a registrar.
     */
    @Override
    public void crear(Usuario usuario) {
        usuarios.add(usuario);
    }

    /**
     * Busca un usuario por su username (cédula).
     *
     * @param username Cédula del usuario.
     * @return Usuario encontrado o {@code null} si no existe.
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Elimina un usuario según su username.
     *
     * @param username Cédula del usuario a eliminar.
     */
    @Override
    public void eliminar(String username) {
        Iterator<Usuario> iterator = usuarios.iterator();
        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            if (usuario.getUsername().equals(username)) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Actualiza la información de un usuario.
     *
     * @param usuario Usuario con la información actualizada.
     */
    @Override
    public void actualizar(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuarioAux = usuarios.get(i);
            if (usuarioAux.getUsername().equals(usuario.getUsername())) {
                usuarios.set(i, usuario);
                break;
            }
        }
    }

    /**
     * Lista todos los usuarios almacenados en memoria.
     *
     * @return Lista completa de usuarios.
     */
    @Override
    public List<Usuario> listarTodos() {
        return usuarios;
    }

    /**
     * Lista los usuarios filtrados por su rol.
     *
     * @param rol Rol del usuario (ADMINISTRADOR o USUARIO).
     * @return Lista de usuarios con el rol especificado.
     */
    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> usuariosEncontrados = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario.getRol().equals(rol)) {
                usuariosEncontrados.add(usuario);
            }
        }

        return usuariosEncontrados;
    }
}
