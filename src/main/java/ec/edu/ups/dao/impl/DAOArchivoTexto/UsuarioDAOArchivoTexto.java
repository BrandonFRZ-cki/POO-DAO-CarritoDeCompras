package ec.edu.ups.dao.impl.DAOArchivoTexto;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del DAO para la entidad Usuario utilizando archivos de texto.
 * Cada usuario se representa como una línea en el archivo, con atributos separados por comas.
 */
public class UsuarioDAOArchivoTexto implements UsuarioDAO {

    private final String ruta;

    /**
     * Constructor del DAO que recibe la ruta del archivo donde se almacenarán los usuarios.
     * Si el archivo no existe, se crea automáticamente.
     *
     * @param ruta Ruta del archivo de almacenamiento.
     */
    public UsuarioDAOArchivoTexto(String ruta) {
        this.ruta = ruta;
        crear(new Usuario("0107233710", "12345", Rol.ADMINISTRADOR, "Brandon", "Rivera", "admin@tienda.com", "0999999999"));
        crear(new Usuario("0103176194", "12345", Rol.USUARIO,"Cecilia", "Zambrano", "user@tienda.com", "0888888888"));
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("No se pudo crear el archivo: " + ruta, e);
            }
        }
    }

    /**
     * Guarda un nuevo usuario en el archivo.
     *
     * @param usuario Objeto Usuario a guardar.
     */
    @Override
    public void crear(Usuario usuario) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            bw.write(usuario.toArchivo() + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar usuario", e);
        }
    }

    /**
     * Busca un usuario por su nombre de usuario (cédula).
     *
     * @param username Nombre de usuario (cédula).
     * @return Usuario encontrado o null si no existe.
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario usuario : listarTodos()) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Elimina un usuario del archivo según su cédula.
     *
     * @param username Cédula del usuario a eliminar.
     */
    @Override
    public void eliminar(String username) {
        List<Usuario> usuarios = listarTodos();
        usuarios.removeIf(u -> u.getUsername().equals(username));
        sobrescribirArchivo(usuarios);
    }

    /**
     * Actualiza los datos de un usuario ya existente.
     *
     * @param usuario Usuario con los datos modificados.
     */
    @Override
    public void actualizar(Usuario usuario) {
        List<Usuario> usuarios = listarTodos();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsername().equals(usuario.getUsername())) {
                usuarios.set(i, usuario);
                break;
            }
        }
        sobrescribirArchivo(usuarios);
    }

    /**
     * Lista todos los usuarios almacenados en el archivo.
     *
     * @return Lista de usuarios.
     */
    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lista.add(Usuario.fromArchivo(linea));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer usuarios del archivo", e);
        }
        return lista;
    }

    /**
     * Lista todos los usuarios que tienen un rol específico.
     *
     * @param rol Rol a filtrar.
     * @return Lista de usuarios con el rol indicado.
     */
    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> resultado = new ArrayList<>();
        for (Usuario usuario : listarTodos()) {
            if (usuario.getRol().equals(rol)) {
                resultado.add(usuario);
            }
        }
        return resultado;
    }

    /**
     * Autentica un usuario comparando cédula y contraseña.
     *
     * @param username Cédula del usuario.
     * @param contrasenia Contraseña del usuario.
     * @return Usuario autenticado o null si no coincide.
     */
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        Usuario usuario = buscarPorUsername(username);
        if (usuario != null && usuario.getContrasenia().equals(contrasenia)) {
            return usuario;
        }
        return null;
    }

    /**
     * Método auxiliar para sobrescribir el archivo completo con una nueva lista de usuarios.
     *
     * @param usuarios Lista de usuarios a guardar.
     */
    private void sobrescribirArchivo(List<Usuario> usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (Usuario u : usuarios) {
                bw.write(u.toArchivo() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al sobrescribir archivo de usuarios", e);
        }
    }
}
