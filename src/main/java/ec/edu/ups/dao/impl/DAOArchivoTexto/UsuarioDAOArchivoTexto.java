package ec.edu.ups.dao.impl.DAOArchivoTexto;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.*;

import java.io.*;
import java.util.*;

/**
 * Implementación de UsuarioDAO que utiliza un archivo de texto plano para persistir los datos.
 * Cada usuario se representa en una línea, incluyendo sus datos, preguntas respondidas y (opcionalmente) carritos.
 */
public class UsuarioDAOArchivoTexto implements UsuarioDAO {

    private final String ruta;
    private List<Usuario> usuarios;

    /**
     * Constructor que inicializa la lista de usuarios desde el archivo.
     * Si el archivo no existe, se crea uno nuevo.
     * Además, si está vacío, se crean usuarios por defecto: un administrador y un usuario normal.
     *
     * @param ruta Ruta del archivo de texto donde se almacenarán los usuarios.
     */
    public UsuarioDAOArchivoTexto(String ruta) {
        this.ruta = ruta;
        this.usuarios = new ArrayList<>();

        File archivo = new File(ruta);
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el archivo: " + ruta, e);
        }

        // Leer usuarios existentes
        this.usuarios = leerUsuariosDesdeArchivo();

        // Si no hay usuarios, crear los de prueba por defecto
        if (usuarios.isEmpty()) {
            Usuario admin = new Usuario("0107233710", "Bran@1", Rol.ADMINISTRADOR, "Brandon", "Rivera", "admin@tienda.com", "0999999999");
            Usuario user = new Usuario("0103176194", "Bran@1", Rol.USUARIO, "Cecilia", "Zambrano", "user@tienda.com", "0888888888");
            usuarios.add(admin);
            usuarios.add(user);
            escribirUsuariosEnArchivo();
        }
    }

    @Override
    public void crear(Usuario usuario) {
        usuarios.add(usuario);
        escribirUsuariosEnArchivo();
    }

    @Override
    public Usuario autenticar(String username, String contrasenia) {
        Usuario usuario = buscarPorUsername(username);
        if (usuario != null && usuario.getContrasenia().equals(contrasenia)) {
            return usuario;
        }
        return null;
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void eliminar(String username) {
        usuarios.removeIf(u -> u.getUsername().equals(username));
        escribirUsuariosEnArchivo();
    }

    @Override
    public void actualizar(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsername().equals(usuario.getUsername())) {
                usuarios.set(i, usuario);
                break;
            }
        }
        escribirUsuariosEnArchivo();
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarios;
    }

    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> filtrados = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (u.getRol().equals(rol)) {
                filtrados.add(u);
            }
        }
        return filtrados;
    }

    /**
     * Lee todos los usuarios desde el archivo de texto.
     * Cada línea representa un usuario serializado por el método toString().
     *
     * @return Lista de objetos Usuario.
     */
    private List<Usuario> leerUsuariosDesdeArchivo() {
        List<Usuario> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Usuario usuario = fromArchivo(linea);
                if (usuario != null) {
                    lista.add(usuario);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer usuarios desde archivo: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Escribe todos los usuarios actuales al archivo de texto, sobrescribiéndolo completamente.
     */
    private void escribirUsuariosEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, false))) {
            for (Usuario u : usuarios) {
                bw.write(u.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir usuarios en archivo: " + e.getMessage());
        }
    }

    /**
     * Reconstruye un objeto Usuario desde una línea de texto del archivo.
     * Espera un formato como: username,contraseña,rol,nombre,apellido,email,teléfono<preguntas><carritos>
     *
     * @param linea Línea leída del archivo.
     * @return Objeto Usuario reconstruido o null si hubo error.
     */
    private Usuario fromArchivo(String linea) {
        try {
            // Separar en secciones <preguntas> y <carritos>
            String[] partes = linea.split("<");
            String[] datos = partes[0].split(",");

            Usuario u = new Usuario();
            u.setUsername(datos[0]);
            u.setContrasenia(datos[1]);
            u.setRol(Rol.valueOf(datos[2]));
            u.setNombre(datos[3]);
            u.setApellido(datos[4]);
            u.setEmail(datos[5]);
            u.setTelefono(datos[6]);

            // Procesar preguntas respondidas (si existen)
            if (partes.length >= 3 && partes[2].contains(">")) {
                String preguntasStr = partes[2].split(">")[0];
                if (!preguntasStr.equals("sin preguntas")) {
                    String[] preguntas = preguntasStr.split("\\|"); // separador de preguntas
                    for (String p : preguntas) {
                        if (!p.trim().isEmpty()) {
                            String[] datosPregunta = p.split(":");
                            int cod = Integer.parseInt(datosPregunta[0]);
                            String resp = datosPregunta[1];
                            Pregunta pregunta = new Pregunta(cod);
                            pregunta.setRespuesta(resp);
                            u.agregarPregunta(pregunta);
                        }
                    }
                }
            }

            return u;
        } catch (Exception e) {
            System.err.println("Error al reconstruir usuario desde archivo: " + e.getMessage());
            return null;
        }
    }
}
