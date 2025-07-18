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
     * Constructor que inicializa la ruta del archivo y crea dos usuarios predeterminados si no existen.
     * @param ruta Ruta del archivo de almacenamiento.
     */
    public UsuarioDAOArchivoTexto(String ruta) {
        this.ruta = ruta;
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("No se pudo crear el archivo: " + ruta, e);
            }
        }
        crear(new Usuario("0107233710", "12345", Rol.ADMINISTRADOR, "Brandon", "Rivera", "admin@tienda.com", "0999999999"));
        crear(new Usuario("0103176194", "12345", Rol.USUARIO,"Cecilia", "Zambrano", "user@tienda.com", "0888888888"));
    }

    /**
     * Guarda un nuevo usuario en el archivo.
     * @param usuario Usuario a guardar.
     */
    @Override
    public void crear(Usuario usuario) {
        if (buscarPorUsername(usuario.getUsername()) == null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
                bw.write(usuario.toString() + "\n");
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar usuario", e);
            }
        }
    }


    /**
     * Autentica un usuario con username y contraseña.
     * @param username Nombre de usuario.
     * @param contrasenia Contraseña.
     * @return Usuario autenticado o null si no coincide.
     */
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        Usuario usuario = buscarPorUsername(username);
        if(usuario != null && usuario.getUsername().equals(username) && usuario.getContrasenia().equals(contrasenia)){
            return usuario;
        }
        return null;
    }

    /**
     * Busca un usuario por su nombre de usuario.
     * @param username Nombre de usuario.
     * @return Usuario encontrado o null si no existe.
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        try {
            FileReader archivoLectura = new FileReader(ruta);
            BufferedReader lectura = new BufferedReader(archivoLectura);
            String linea = lectura.readLine();
            while (linea != null) {
                Usuario usuario = convertirLineaEnUsuario(linea);
                if (usuario != null && usuario.getUsername().equals(username)) {
                    lectura.close();
                    archivoLectura.close();
                    return usuario;
                }
                linea = lectura.readLine();
            }
            lectura.close();
            archivoLectura.close();
        } catch (IOException e) {
            throw new RuntimeException("Error al buscar usuario", e);
        }
        return null;
    }

    /**
     * Elimina un usuario del archivo por su username.
     * @param username Username del usuario a eliminar.
     */
    @Override
    public void eliminar(String username) {
        List<Usuario> usuarios = listarTodos();
        List<Usuario> nuevos = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (!u.getUsername().equals(username)) {
                nuevos.add(u);
            }
        }
        sobrescribirArchivo(nuevos);
    }

    /**
     * Actualiza un usuario en el archivo.
     * @param usuario Usuario con datos actualizados.
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
     * Devuelve una lista de todos los usuarios almacenados en el archivo.
     * @return Lista de usuarios.
     */
    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        try {
            FileReader archivoLectura = new FileReader(ruta);
            BufferedReader lectura = new BufferedReader(archivoLectura);
            String linea = lectura.readLine();
            while (linea != null) {
                Usuario usuario = convertirLineaEnUsuario(linea);
                if (usuario != null) {
                    lista.add(usuario);
                }
                linea = lectura.readLine();
            }
            lectura.close();
            archivoLectura.close();
        } catch (IOException e) {
            throw new RuntimeException("Error al leer usuarios", e);
        }
        return lista;
    }

    /**
     * Devuelve una lista de usuarios que tienen el rol especificado.
     * @param rol Rol a buscar.
     * @return Lista de usuarios con ese rol.
     */
    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> filtrados = new ArrayList<>();
        for (Usuario u : listarTodos()) {
            if (u.getRol().equals(rol)) {
                filtrados.add(u);
            }
        }
        return filtrados;
    }

    /**
     * Convierte una línea del archivo en un objeto Usuario.
     * @param linea Línea leída del archivo.
     * @return Objeto Usuario o null si el formato es inválido.
     */
    private Usuario convertirLineaEnUsuario(String linea) {
        if (linea.contains("<")) {
            linea = linea.substring(0, linea.indexOf("<"));
        }
        String[] partes = linea.split(",");
        if (partes.length >= 7) {
            return new Usuario(
                    partes[0],
                    partes[1],
                    Rol.valueOf(partes[2]),
                    partes[3],
                    partes[4],
                    partes[5],
                    partes[6]
            );
        }
        return null;
    }

    /**
     * Sobrescribe completamente el archivo con una nueva lista de usuarios.
     * @param lista Lista de usuarios a escribir.
     */
    private void sobrescribirArchivo(List<Usuario> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, false))) {
            for (Usuario u : lista) {
                bw.write(u.toString() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al sobrescribir archivo", e);
        }
    }
}
