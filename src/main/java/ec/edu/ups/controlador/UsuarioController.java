package ec.edu.ups.controlador;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Pregunta;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.exceptions.CedulaValidationException;
import ec.edu.ups.util.exceptions.CorreoValidationException;
import ec.edu.ups.util.exceptions.PasswordException;
import ec.edu.ups.util.exceptions.TelefonoValidationException;
import ec.edu.ups.vista.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;


/**
 * Controlador que gestiona la lógica relacionada con la administración de usuarios,
 * incluyendo creación, eliminación, actualización, autenticación, y recuperación de contraseña.
 * También gestiona el cambio dinámico de idioma y las preguntas de seguridad.
 *
 * @author Brandon
 * @version 1.0
 */
public class UsuarioController {

    private final LoginView loginView;
    private final UsuarioAnadirView usuarioAnadirView;
    private final UsuarioListaView usuarioListaView;
    private final UsuarioEliminarView usuarioEliminarView;
    private final UsuarioActualizarView usuarioActualizarView;
    private final RegistrarView registrarView;
    private final ResponderPreguntas responderPreguntasView;
    private final UsuarioDAO usuarioDAO;
    private final PreguntaDAO preguntaDAO;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;
    private Usuario usuario;
    private Locale locale;
    /**
     * Crea una nueva instancia del controlador de usuarios.
     *
     * @param usuarioDAO DAO para operaciones CRUD de usuario.
     * @param loginView Vista de inicio de sesión.
     * @param usuarioAnadirView Vista para añadir usuarios.
     * @param usuarioListaView Vista para listar usuarios.
     * @param usuarioEliminarView Vista para eliminar usuarios.
     * @param usuarioActualizarView Vista para actualizar usuarios.
     * @param registrarView Vista para registrar nuevos usuarios.
     * @param responderPreguntasView Vista para responder preguntas de seguridad.
     * @param preguntaDAO DAO de preguntas disponibles.
     * @param mensajeInternacionalizacionHandler Manejador de mensajes internacionalizados.
     */
    public UsuarioController(UsuarioDAO usuarioDAO, LoginView loginView, UsuarioAnadirView usuarioAnadirView,
                             UsuarioListaView usuarioListaView, UsuarioEliminarView usuarioEliminarView,
                             UsuarioActualizarView usuarioActualizarView, RegistrarView registrarView,
                             ResponderPreguntas responderPreguntasView, PreguntaDAO preguntaDAO,MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.usuarioAnadirView = usuarioAnadirView;
        this.usuarioListaView = usuarioListaView;
        this.usuarioEliminarView = usuarioEliminarView;
        this.usuarioActualizarView = usuarioActualizarView;
        this.registrarView = registrarView;
        this.responderPreguntasView = responderPreguntasView;
        this.preguntaDAO = preguntaDAO;
        this.usuario = null;
        this.mensajeInternacionalizacionHandler = mensajeInternacionalizacionHandler;
        locale = mensajeInternacionalizacionHandler.getLocale();
        configurarEventosEnVistas();

    }
    /**
     * Configura los eventos de acción para todas las vistas asociadas al controlador de usuario.
     * Incluye los botones de login, registro, añadir, listar, actualizar, eliminar y recuperación
     * de contraseña, así como la validación de preguntas de seguridad.
     *
     * Este método centraliza el enlace entre la lógica del controlador y las interfaces gráficas,
     * registrando listeners para cada botón relevante del sistema.
     */
    private void configurarEventosEnVistas() {
        /**
         * ╔═════════════════════════════╗
         * ║         🧍 USUARIO          ║
         * ╚═════════════════════════════╝
         */
        loginView.getBtnIniciarSesion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });
        loginView.getRegistrarceButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarView.setVisible(true);
            }
        });

        registrarView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registrarNuevoUsuario();
                } catch (CedulaValidationException e1) {
                    usuarioActualizarView.mostrarMensaje(
                            mensajes(Integer.parseInt(e1.getMessage())),
                            titulosMensajes(Integer.parseInt(e1.getMessage())),
                            "error"
                    );
                } catch (PasswordException e2) {
                    usuarioActualizarView.mostrarMensaje(
                            mensajes(Integer.parseInt(e2.getMessage())),
                            titulosMensajes(Integer.parseInt(e2.getMessage())),
                            "error"
                    );
                } catch (CorreoValidationException e3) {
                    usuarioActualizarView.mostrarMensaje(
                            mensajes(Integer.parseInt(e3.getMessage())),
                            titulosMensajes(Integer.parseInt(e3.getMessage())),
                            "error"
                    );
                } catch (TelefonoValidationException e4) {
                    usuarioActualizarView.mostrarMensaje(
                            mensajes(Integer.parseInt(e4.getMessage())),
                            titulosMensajes(Integer.parseInt(e4.getMessage())),
                            "error"
                    );
                }
            }
        });

        registrarView.getBtnLimpiar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarView.limpiarCampos();
            }
        });
        responderPreguntasView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPreguntasYFecha(usuario);
            }
        });
        responderPreguntasView.getBtnLimpiar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                responderPreguntasView.limpiarCampos();
            }
        });


        /**
         * ╔════════════════════════════════════╗
         * ║          ➕ CREAR                  ║
         * ╚════════════════════════════════════╝
         */
        usuarioAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    anadirUsuario();
                } catch (CedulaValidationException e1) {
                    usuarioAnadirView.mostrarMensaje(
                            mensajes(Integer.parseInt(e1.getMessage())),
                            titulosMensajes(Integer.parseInt(e1.getMessage())),
                            "error"
                    );
                }catch (PasswordException e2) {
                    usuarioAnadirView.mostrarMensaje(
                            mensajes(Integer.parseInt(e2.getMessage())),
                            titulosMensajes(Integer.parseInt(e2.getMessage())),
                            "error"
                    );
                }
                catch (CorreoValidationException e2) {
                    usuarioAnadirView.mostrarMensaje(
                            mensajes(Integer.parseInt(e2.getMessage())),
                            titulosMensajes(Integer.parseInt(e2.getMessage())),
                            "error"
                    );
                } catch (TelefonoValidationException e3) {
                    usuarioAnadirView.mostrarMensaje(
                            mensajes(Integer.parseInt(e3.getMessage())),
                            titulosMensajes(Integer.parseInt(e3.getMessage())),
                            "error"
                    );
                }
            }
        });

        usuarioAnadirView.getBtnLimpiar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioAnadirView.limparCampos();
            }
        });
        /**
         * ╔════════════════════════════════════╗
         * ║         🔍 LISTAR                  ║
         * ╚════════════════════════════════════╝
         */
        usuarioListaView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuarioPorUserName();
            }
        });
        usuarioListaView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarUsuarios();
            }
        });
        /**
         * ╔════════════════════════════════════╗
         * ║         📝 ACTUALIZAR              ║
         * ╚════════════════════════════════════╝
         */
        usuarioActualizarView.getBtnActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizarUsuario();
                } catch (CedulaValidationException e1) {
                    usuarioActualizarView.mostrarMensaje(
                            mensajes(Integer.parseInt(e1.getMessage())),
                            titulosMensajes(Integer.parseInt(e1.getMessage())),
                            "error"
                    );
                } catch (PasswordException e2) {
                    usuarioActualizarView.mostrarMensaje(
                            mensajes(Integer.parseInt(e2.getMessage())),
                            titulosMensajes(Integer.parseInt(e2.getMessage())),
                            "error"
                    );
                } catch (CorreoValidationException e3) {
                    usuarioActualizarView.mostrarMensaje(
                            mensajes(Integer.parseInt(e3.getMessage())),
                            titulosMensajes(Integer.parseInt(e3.getMessage())),
                            "error"
                    );
                } catch (TelefonoValidationException e4) {
                    usuarioActualizarView.mostrarMensaje(
                            mensajes(Integer.parseInt(e4.getMessage())),
                            titulosMensajes(Integer.parseInt(e4.getMessage())),
                            "error"
                    );
                }
            }
        });

        usuarioActualizarView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarParaActualizar();
            }
        });
        /**
         * ╔════════════════════════════════════╗
         * ║         ❌ ELIMINAR                ║
         * ╚════════════════════════════════════╝
         */
        usuarioEliminarView.getBtBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarParaEliminar();

            }
        });
        usuarioEliminarView.getBtEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar();
            }
        });
        usuarioEliminarView.getBtLimpiar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioEliminarView.limpiarCampos();
            }
        });
        loginView.getBtnRecuperarContrasenia().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recuperarContrasena();
            }
        });
    }
    /**
     * Autentica al usuario según el nombre de usuario y contraseña ingresados en la vista de login.
     * Verifica si el usuario tiene preguntas de seguridad respondidas y fecha de nacimiento.
     * Si no cumple con los requisitos, se abre la vista para completar datos faltantes.
     */
    private void autenticar() {
        String username = loginView.getTxtUsername().getText();
        String contrasena = new String(loginView.getTxtContrasena().getPassword());

        usuario = usuarioDAO.autenticar(username, contrasena);

        if (usuario == null) {
            loginView.mostrarMensaje(mensajes(0), titulosMensajes(0), "error");
            return;
        }

        boolean esAdmin = usuario.getRol().equals(Rol.ADMINISTRADOR);
        boolean tieneMinimoPreguntas = usuario.getPreguntasRespondidas() != null &&
                usuario.getPreguntasRespondidas().size() >= 3;
        boolean tieneFecha = usuario.getFechaNacimiento() != null;

        if (!esAdmin && (!tieneMinimoPreguntas || !tieneFecha)) {
            loginView.mostrarMensaje(mensajes(2), titulosMensajes(0), "warning");
            responderPreguntasView.setVisible(true);
            return;
        }

        loginView.dispose();
    }
    private void guardarPreguntasYFecha(Usuario usuario) {
        List<Pregunta> preguntasRespondidas = new ArrayList<>();
        JTextField[] campos = responderPreguntasView.getCamposRespuestas();

        int respuestasValidas = 0;
        for (int i = 0; i < campos.length; i++) {
            String texto = campos[i].getText().trim();
            if (!texto.isEmpty()) {
                Pregunta pregunta = new Pregunta(i + 1);
                pregunta.setRespuesta(texto);
                preguntasRespondidas.add(pregunta);
                preguntasRespondidas.get(preguntasRespondidas.size() - 1).setRespuesta(texto);
                respuestasValidas++;
            }
        }

        if (respuestasValidas < 3) {
            JOptionPane.showMessageDialog(responderPreguntasView, mensajes(3), titulosMensajes(1), JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int dia = Integer.parseInt(responderPreguntasView.getTxtDia().getText().trim());
            int mes = responderPreguntasView.getCbxMes().getSelectedIndex();
            int anio = Integer.parseInt(responderPreguntasView.getTxtAnio().getText().trim());

            if (String.valueOf(anio).length() != 4 || dia < 1 || dia > 31) {
                throw new NumberFormatException();
            }

            usuario.setFechaNacimiento(new GregorianCalendar(anio, mes, dia));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(responderPreguntasView, mensajes(5), titulosMensajes(2), JOptionPane.ERROR_MESSAGE);
            return;
        }

        usuario.setPreguntasRespondidas(preguntasRespondidas);
        JOptionPane.showMessageDialog(responderPreguntasView, mensajes(7), titulosMensajes(3), JOptionPane.INFORMATION_MESSAGE);
        responderPreguntasView.dispose();
    }

    /**
     * Devuelve el usuario actualmente autenticado.
     *
     * @return Usuario autenticado o null si no se ha autenticado aún.
     */
    public Usuario getUsuarioAutenticado() {
        return usuario;
    }
    /**
     * Añade un nuevo usuario desde la vista de administrador.
     * Realiza validaciones de campos vacíos, coincidencia de contraseñas y existencia previa del username.
     * Si todo es válido, crea el usuario y lo guarda en el DAO.
     */
    private void anadirUsuario() {
        String username = usuarioAnadirView.getTxtUserName().getText();
        String contrasena = usuarioAnadirView.getTxtContrasena().getText();
        String verificarContrasena = new String(usuarioAnadirView.getTxtVerificaContrasena().getPassword());
        String nombre = usuarioAnadirView.getTxtNombre().getText();
        String apellido = usuarioAnadirView.getTxtApellido().getText();
        String correo = usuarioAnadirView.getTxtCorreo().getText();
        String telefono = usuarioAnadirView.getTxtTelefono().getText();

        if (username.isEmpty() || contrasena.isEmpty() || verificarContrasena.isEmpty() ||
                nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
            usuarioAnadirView.mostrarMensaje(mensajes(9), titulosMensajes(4), "warning");
            return;
        }

        if (!contrasena.equals(verificarContrasena)) {
            usuarioAnadirView.mostrarMensaje(mensajes(11), titulosMensajes(5), "error");
            return;
        }

        if (usuarioDAO.buscarPorUsername(username) != null) {
            usuarioAnadirView.mostrarMensaje(mensajes(13), titulosMensajes(6), "error");
            return;
        }

        Usuario nuevo = new Usuario(username, contrasena, Rol.USUARIO, nombre, apellido, correo, telefono);
        usuarioDAO.crear(nuevo);

        usuarioAnadirView.mostrarMensaje(mensajes(15), titulosMensajes(7), "info");
        usuarioAnadirView.mostrarMensaje(mensajes(16), titulosMensajes(7), "warning");
        usuarioAnadirView.limparCampos();
    }
    /**
     * Busca usuarios por coincidencia parcial en el nombre de usuario ingresado.
     * Si encuentra coincidencias, las carga en la tabla de la vista; si no, muestra un mensaje informativo.
     */
    private void buscarUsuarioPorUserName() {
        String texto = usuarioListaView.getTxtBuscar().getText().toLowerCase().trim();

        if (texto.isEmpty()) {
            usuarioListaView.mostrarMensaje(mensajes(18), titulosMensajes(8), "warning");
            return;
        }

        List<Usuario> usuariosCoincidentes = new ArrayList<>();
        List<Usuario> listaUsuarios = usuarioDAO.listarTodos();

        for (Usuario u : listaUsuarios) {
            if (u.getUsername().toLowerCase().contains(texto)) {
                usuariosCoincidentes.add(u);
            }
        }

        if (usuariosCoincidentes.isEmpty()) {
            usuarioListaView.mostrarMensaje(mensajes(20), titulosMensajes(9), "info");
        }

        cargarEnTabla(usuariosCoincidentes);
    }
    /**
     * Lista todos los usuarios registrados y los carga en la tabla de la vista.
     */
    private void listarUsuarios() {
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        cargarEnTabla(usuarios);
    }
    /**
     * Carga una lista de usuarios en el modelo de tabla de la vista de listado.
     *
     * @param usuarios Lista de usuarios a mostrar en la tabla.
     */
    private void cargarEnTabla(List<Usuario> usuarios) {
        DefaultTableModel modelo = usuarioListaView.getModelo();
        modelo.setRowCount(0); // Limpia la tabla

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            modelo.addRow(new Object[]{
                    u.getUsername(),
                    u.getNombre(),
                    u.getApellido(),
                    u.getEmail(),
                    u.getTelefono(),
                    FormateadorUtils.formatearFecha(u.getFechaNacimiento().getTime(),locale)
            });
        }
    }
    /**
     * Actualiza los datos de un usuario según la opción seleccionada por el administrador.
     * Permite modificar nombre, apellido, correo, teléfono o contraseña.
     */
    private void actualizarUsuario() {
        String username = usuarioActualizarView.getTxtUsername().getText();
        Usuario usuario = usuarioDAO.buscarPorUsername(username);

        if (usuario == null) {
            usuarioActualizarView.mostrarMensaje(mensajes(22), titulosMensajes(10), "error");
            return;
        }

        Object[] opciones = {
                mensajes(25), mensajes(26), mensajes(27), mensajes(28), mensajes(29)
        };

        String opcion = (String) JOptionPane.showInputDialog(
                usuarioActualizarView,
                mensajes(23),
                mensajes(24),
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (opcion == null) return;

        if (opcion.equals(mensajes(25))) {
            String nuevoNombre = JOptionPane.showInputDialog(mensajes(30), usuario.getNombre());
            if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                usuario.setNombre(nuevoNombre);
            }
        } else if (opcion.equals(mensajes(26))) {
            String nuevoApellido = JOptionPane.showInputDialog(mensajes(31), usuario.getApellido());
            if (nuevoApellido != null && !nuevoApellido.trim().isEmpty()) {
                usuario.setApellido(nuevoApellido);
            }
        } else if (opcion.equals(mensajes(27))) {
            String nuevoCorreo = JOptionPane.showInputDialog(mensajes(32), usuario.getEmail());
            if (nuevoCorreo != null && !nuevoCorreo.trim().isEmpty()) {
                usuario.setEmail(nuevoCorreo);
            }
        } else if (opcion.equals(mensajes(28))) {
            String nuevoTelefono = JOptionPane.showInputDialog(mensajes(33), usuario.getTelefono());
            if (nuevoTelefono != null && !nuevoTelefono.trim().isEmpty()) {
                usuario.setTelefono(nuevoTelefono);
            }
        } else if (opcion.equals(mensajes(29))) {
            String nuevaContrasena = JOptionPane.showInputDialog(mensajes(34));
            if (nuevaContrasena != null && !nuevaContrasena.trim().isEmpty()) {
                usuario.setContrasenia(nuevaContrasena);
            }
        }

        usuarioDAO.actualizar(usuario);
        usuarioActualizarView.mostrarMensaje(mensajes(35), titulosMensajes(11), "info");

        usuarioActualizarView.getTxtUserName().setText(usuario.getUsername());
        usuarioActualizarView.getTxtNombre().setText(usuario.getNombre());
        usuarioActualizarView.getTxtApellido().setText(usuario.getApellido());
        usuarioActualizarView.getTxtCorreo().setText(usuario.getEmail());
        usuarioActualizarView.getTxtTelefono().setText(usuario.getTelefono());
        usuarioActualizarView.getTxtContrasena().setText(usuario.getContrasenia());
    }
    /**
     * Busca un usuario por su username y llena los campos de la vista de actualización con su información.
     * Muestra un mensaje si el campo está vacío o si no se encuentra el usuario.
     */
    public void buscarParaActualizar() {
        String username = usuarioActualizarView.getTxtUsername().getText().trim();

        if (username.isEmpty()) {
            usuarioActualizarView.mostrarMensaje(mensajes(36), titulosMensajes(12), "warning");
            return;
        }

        Usuario usuario = usuarioDAO.buscarPorUsername(username);

        if (usuario == null) {
            usuarioActualizarView.mostrarMensaje(mensajes(38), titulosMensajes(13), "error");
            return;
        }

        usuarioActualizarView.getTxtUserName().setText(usuario.getUsername());
        usuarioActualizarView.getTxtNombre().setText(usuario.getNombre());
        usuarioActualizarView.getTxtApellido().setText(usuario.getApellido());
        usuarioActualizarView.getTxtTelefono().setText(usuario.getTelefono());
        usuarioActualizarView.getTxtCorreo().setText(usuario.getEmail());
        usuarioActualizarView.getTxtContrasena().setText(usuario.getContrasenia());

        usuarioActualizarView.mostrarMensaje(mensajes(40), titulosMensajes(14), "info");
    }
    /**
     * Busca un usuario por su username en la vista de eliminación.
     * Si existe, muestra su nombre y apellido; si no, muestra mensajes de error y limpia los campos.
     */
    public void buscarParaEliminar() {
        String username = usuarioEliminarView.getTxtUsername().getText().trim();

        if (username.isEmpty()) {
            usuarioEliminarView.mostrarMensaje(mensajes(42), titulosMensajes(15), "warning");
            return;
        }

        Usuario usuario = usuarioDAO.buscarPorUsername(username);

        if (usuario != null) {
            usuarioEliminarView.getTxtNombre().setText(usuario.getNombre());
            usuarioEliminarView.getTxtApellido().setText(usuario.getApellido());
        } else {
            usuarioEliminarView.getTxtNombre().setText(" - ");
            usuarioEliminarView.getTxtApellido().setText(" - ");
            usuarioEliminarView.mostrarMensaje(mensajes(43), titulosMensajes(16), "error");
        }
    }
    /**
     * Elimina un usuario luego de confirmar la acción con un cuadro de diálogo.
     * Muestra mensajes de éxito o advertencia según el resultado.
     */
    public void eliminar() {
        String username = usuarioEliminarView.getTxtUsername().getText().trim();

        Usuario usuario = usuarioDAO.buscarPorUsername(username);
        if (usuario == null) {
            usuarioEliminarView.mostrarMensaje(mensajes(46), titulosMensajes(17), "warning");
            return;
        }

        String mensajeConfirmacion = mensajes(48).replace("{0}", username);

        int confirmacion = JOptionPane.showConfirmDialog(
                usuarioEliminarView,
                mensajeConfirmacion,
                titulosMensajes(18),
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            usuarioDAO.eliminar(username);
            usuarioEliminarView.mostrarMensaje(mensajes(50), titulosMensajes(19), "info");

            usuarioEliminarView.getTxtUsername().setText("");
            usuarioEliminarView.getTxtNombre().setText("");
            usuarioEliminarView.getTxtApellido().setText("");
        }
    }
    /**
     * Registra un nuevo usuario desde la vista de registro público.
     * Valida los campos obligatorios, verifica coincidencia de contraseñas
     * y la existencia previa del username. Si es exitoso, pasa a la vista de preguntas de seguridad.
     */
    private void registrarNuevoUsuario() {
        String username = registrarView.getTxtUserName().getText().trim();
        String nombre = registrarView.getTxtNombre().getText().trim();
        String apellido = registrarView.getTxtApellido().getText().trim();
        String telefono = registrarView.getTxtTelefono().getText().trim();
        String correo = registrarView.getTxtCorreo().getText().trim();
        String contrasena = new String(registrarView.getTxtContrasena().getPassword()).trim();
        String verificar = new String(registrarView.getTxtVerificaContrasena().getPassword()).trim();

        if (username.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() ||
                correo.isEmpty() || contrasena.isEmpty() || verificar.isEmpty()) {
            registrarView.mostrarMensaje(mensajes(51), titulosMensajes(20), "warning");
            return;
        }

        if (!contrasena.equals(verificar)) {
            registrarView.mostrarMensaje(mensajes(53), titulosMensajes(21), "error");
            return;
        }

        if (usuarioDAO.buscarPorUsername(username) != null) {
            registrarView.mostrarMensaje(mensajes(55), titulosMensajes(22), "error");
            return;
        }

        usuario = new Usuario(username, contrasena, Rol.USUARIO, nombre, apellido, correo, telefono);
        usuarioDAO.crear(usuario);

        registrarView.mostrarMensaje(mensajes(57), titulosMensajes(23), "info");
        registrarView.limpiarCampos();
        registrarView.setVisible(false);
        responderPreguntasView.limpiarCampos();
        responderPreguntasView.setVisible(true);
    }
    /**
     * Recupera la contraseña de un usuario autenticando su identidad con una pregunta de seguridad aleatoria.
     * Si la respuesta es correcta, permite definir una nueva contraseña.
     */
    private void recuperarContrasena() {
        String username = JOptionPane.showInputDialog(loginView, mensajes(68));
        Usuario usuario = usuarioDAO.buscarPorUsername(username);

        if (usuario == null) {
            loginView.mostrarMensaje(mensajes(69), titulosMensajes(28), "error");
            return;
        }

        List<Pregunta> respondidas = usuario.getPreguntasRespondidas();
        if (respondidas == null || respondidas.size() < 3) {
            loginView.mostrarMensaje(mensajes(71), titulosMensajes(29), "warning");
            return;
        }

        int aleatoria = (int) (Math.random() * respondidas.size());
        Pregunta seleccionada = respondidas.get(aleatoria);

        String textoPregunta = seleccionada.getPregunta(mensajeInternacionalizacionHandler);
        String respuesta = JOptionPane.showInputDialog(loginView, textoPregunta);

        if (respuesta == null || respuesta.trim().isEmpty()) return;

        if (seleccionada.getRespuesta().equalsIgnoreCase(respuesta.trim())) {
            JPasswordField campoNuevaContra = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(
                    loginView,
                    campoNuevaContra,
                    mensajes(73),
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (option == JOptionPane.OK_OPTION) {
                String nuevaContra = new String(campoNuevaContra.getPassword());
                if (!nuevaContra.trim().isEmpty()) {
                    usuario.setContrasenia(nuevaContra);
                    usuarioDAO.actualizar(usuario);
                    loginView.mostrarMensaje(mensajes(74), titulosMensajes(30), "info");
                }
            }

        } else {
            loginView.mostrarMensaje(mensajes(76), titulosMensajes(31), "error");
        }
    }
    /**
     * Cambia el idioma de la interfaz gráfica, actualizando los textos de todas las vistas.
     *
     * @param lenguaje Código ISO del idioma (por ejemplo, "es" para español).
     * @param pais Código ISO del país (por ejemplo, "EC" para Ecuador).
     */
    public void cambiarIdioma(String lenguaje, String pais) {
        locale = mensajeInternacionalizacionHandler.getLocale();
        // Login
        loginView.getLbContrasena().setText(mensajeInternacionalizacionHandler.get("contrasena"));
        loginView.getLbTitulo().setText(mensajeInternacionalizacionHandler.get("inicio.sesion")+" 👤");
        loginView.getBtnIniciarSesion().setText(mensajeInternacionalizacionHandler.get("usuario.login"));
        loginView.getLbUsername().setText(mensajeInternacionalizacionHandler.get("usuario"));
        loginView.getBtnRecuperarContrasenia().setText(mensajeInternacionalizacionHandler.get("olvidemicontrasena"));
        loginView.getRegistrarceButton().setText(mensajeInternacionalizacionHandler.get("registrarse"));

        responderPreguntasView.getLbTitulo().setText(mensajeInternacionalizacionHandler.get("registro"));
        responderPreguntasView.getLbAnio().setText(mensajeInternacionalizacionHandler.get("anio"));
        responderPreguntasView.getLbDia().setText(mensajeInternacionalizacionHandler.get("dia"));
        responderPreguntasView.getLbMes().setText(mensajeInternacionalizacionHandler.get("mes"));
        responderPreguntasView.getLbPregunta1().setText(mensajeInternacionalizacionHandler.get("pregunta.1"));
        responderPreguntasView.getLbPregunta2().setText(mensajeInternacionalizacionHandler.get("pregunta.2"));
        responderPreguntasView.getLbPregunta3().setText(mensajeInternacionalizacionHandler.get("pregunta.3"));
        responderPreguntasView.getLbPregunta4().setText(mensajeInternacionalizacionHandler.get("pregunta.4"));
        responderPreguntasView.getLbPregunta5().setText(mensajeInternacionalizacionHandler.get("pregunta.5"));
        responderPreguntasView.getLbPregunta6().setText(mensajeInternacionalizacionHandler.get("pregunta.6"));
        responderPreguntasView.getLbPregunta7().setText(mensajeInternacionalizacionHandler.get("pregunta.7"));
        responderPreguntasView.getLbPregunta8().setText(mensajeInternacionalizacionHandler.get("pregunta.8"));
        responderPreguntasView.getLbPregunta9().setText(mensajeInternacionalizacionHandler.get("pregunta.9"));
        responderPreguntasView.getCbxMes().removeAllItems();
        for (int i = 1; i <= 12; i++) {
            responderPreguntasView.getCbxMes().addItem(mensajeInternacionalizacionHandler.get("mes." + i));
        }
        responderPreguntasView.getBtnAceptar().setText(mensajeInternacionalizacionHandler.get("aceptar"));
        responderPreguntasView.getBtnLimpiar().setText(mensajeInternacionalizacionHandler.get("limpiar"));

        registrarView.setTitle(mensajeInternacionalizacionHandler.get("registrarse"));
        registrarView.getLbTitulo().setText(mensajeInternacionalizacionHandler.get("registrarse")+" 👤");
        registrarView.getLbApellido().setText(mensajeInternacionalizacionHandler.get("apellido"));
        registrarView.getLbNombre().setText(mensajeInternacionalizacionHandler.get("nombre"));
        registrarView.getLbContresenia().setText(mensajeInternacionalizacionHandler.get("contrasena"));
        registrarView.getLbNombreDeUsuario().setText(mensajeInternacionalizacionHandler.get("nombreusuario"));
        registrarView.getLbTelefono().setText(mensajeInternacionalizacionHandler.get("telefono"));
        registrarView.getLbCorreo().setText(mensajeInternacionalizacionHandler.get("correo"));
        registrarView.getLbVerificaContrasenia().setText(mensajeInternacionalizacionHandler.get("validarcontrasena"));
        registrarView.getBtnAceptar().setText(mensajeInternacionalizacionHandler.get("aceptar"));
        registrarView.getBtnLimpiar().setText(mensajeInternacionalizacionHandler.get("limpiar"));


        /**
         * ╔════════════════════════════════════╗
         * ║          ➕ CREAR                  ║
         * ╚════════════════════════════════════╝
         */
        usuarioAnadirView.setTitle(mensajeInternacionalizacionHandler.get("usuario.crear"));
        usuarioAnadirView.getLbTitulo().setText(mensajeInternacionalizacionHandler.get("usuario.crear") + " ➕");
        usuarioAnadirView.getLbNombre().setText(mensajeInternacionalizacionHandler.get("nombre"));
        usuarioAnadirView.getLbNombreDeUsuario().setText(mensajeInternacionalizacionHandler.get("nombreusuario"));
        usuarioAnadirView.getBtnLimpiar().setText(mensajeInternacionalizacionHandler.get("limpiar"));
        usuarioAnadirView.getLbContrasena().setText(mensajeInternacionalizacionHandler.get("contrasena"));
        usuarioAnadirView.getBtnAceptar().setText(mensajeInternacionalizacionHandler.get("aceptar"));
        usuarioAnadirView.getLbVerificaContrasena().setText(mensajeInternacionalizacionHandler.get("validarcontrasena"));
        usuarioAnadirView.getLbApellido().setText(mensajeInternacionalizacionHandler.get("apellido"));
        usuarioAnadirView.getLbCorreo().setText(mensajeInternacionalizacionHandler.get("correo"));
        usuarioAnadirView.getLbTelefono().setText(mensajeInternacionalizacionHandler.get("telefono"));
        /**
         * ╔════════════════════════════════════╗
         * ║         ❌ ELIMINAR                ║
         * ╚════════════════════════════════════╝
         */
        usuarioEliminarView.setTitle(mensajeInternacionalizacionHandler.get("usuario.eliminar"));
        usuarioEliminarView.getLbTitulo().setText(mensajeInternacionalizacionHandler.get("usuario.eliminar")+" 🗑️");
        usuarioEliminarView.getBtLimpiar().setText(mensajeInternacionalizacionHandler.get("limpiar"));
        usuarioEliminarView.getBtEliminar().setText(mensajeInternacionalizacionHandler.get("eliminar"));
        usuarioEliminarView.getLbUsername().setText(mensajeInternacionalizacionHandler.get("nombreusuario"));
        /**
         * ╔════════════════════════════════════╗
         * ║         📝 ACTUALIZAR              ║
         * ╚════════════════════════════════════╝
         */
        usuarioActualizarView.setTitle(mensajeInternacionalizacionHandler.get("usuario.actualizar"));
        usuarioActualizarView.getLbTitulo().setText(mensajeInternacionalizacionHandler.get("usuario.actualizar"));
        usuarioActualizarView.getLbNombre().setText(mensajeInternacionalizacionHandler.get("nombre"));
        usuarioActualizarView.getLbApellido().setText(mensajeInternacionalizacionHandler.get("apellido"));
        usuarioActualizarView.getLbContrasena().setText(mensajeInternacionalizacionHandler.get("contrasena"));
        usuarioActualizarView.getLbTelefono().setText(mensajeInternacionalizacionHandler.get("telefono"));
        usuarioActualizarView.getLbUser().setText(mensajeInternacionalizacionHandler.get("usuario"));
        usuarioActualizarView.getTbCorreo().setText(mensajeInternacionalizacionHandler.get("correo"));
        usuarioActualizarView.getLbUserName().setText(mensajeInternacionalizacionHandler.get("nombreusuario"));
        usuarioActualizarView.getBtnActualizar().setText(mensajeInternacionalizacionHandler.get("actualizar"));
        /**
         * ╔════════════════════════════════════╗
         * ║         🔍 LISTAR                  ║
         * ╚════════════════════════════════════╝
         */
        usuarioListaView.setTitle(mensajeInternacionalizacionHandler.get("usuario.buscar"));
        usuarioListaView.getLbTitulo().setText(mensajeInternacionalizacionHandler.get("usuario.buscar")+" 👤");
        usuarioListaView.getLbUsername().setText(mensajeInternacionalizacionHandler.get("nombreusuario"));
        usuarioListaView.getBtnListar().setText(mensajeInternacionalizacionHandler.get("listar"));
        Object[] columnasUsers = {mensajeInternacionalizacionHandler.get("nombreusuario"), mensajeInternacionalizacionHandler.get("nombre"), mensajeInternacionalizacionHandler.get("apellido"), mensajeInternacionalizacionHandler.get("correo"), mensajeInternacionalizacionHandler.get("telefono"),mensajeInternacionalizacionHandler.get("fechanacimiento")};
        usuarioListaView.getModelo().setColumnIdentifiers(columnasUsers);
   }
    /**
     * Retorna un mensaje internacionalizado correspondiente al código proporcionado.
     * Este método utiliza el manejador de internacionalización para obtener el texto
     * adecuado en el idioma actual del sistema.
     *
     * @param cod Código numérico del mensaje.
     * @return Cadena con el mensaje correspondiente al código dado.
     */
    private String mensajes(int cod) {
        return switch (cod) {
            case 0 -> mensajeInternacionalizacionHandler.get("mensaje.47");
            case 1 -> mensajeInternacionalizacionHandler.get("mensaje.48");
            case 2 -> mensajeInternacionalizacionHandler.get("mensaje.49");
            case 3 -> mensajeInternacionalizacionHandler.get("mensaje.50");
            case 4 -> mensajeInternacionalizacionHandler.get("mensaje.51");
            case 5 -> mensajeInternacionalizacionHandler.get("mensaje.52");
            case 6 -> mensajeInternacionalizacionHandler.get("mensaje.53");
            case 7 -> mensajeInternacionalizacionHandler.get("mensaje.54");
            case 8 -> mensajeInternacionalizacionHandler.get("mensaje.55");
            case 9 -> mensajeInternacionalizacionHandler.get("mensaje.56");
            case 10 -> mensajeInternacionalizacionHandler.get("mensaje.57");
            case 11 -> mensajeInternacionalizacionHandler.get("mensaje.58");
            case 12 -> mensajeInternacionalizacionHandler.get("mensaje.59");
            case 13 -> mensajeInternacionalizacionHandler.get("mensaje.60");
            case 14 -> mensajeInternacionalizacionHandler.get("mensaje.61");
            case 15 -> mensajeInternacionalizacionHandler.get("mensaje.62");
            case 16 -> mensajeInternacionalizacionHandler.get("mensaje.63");
            case 17 -> mensajeInternacionalizacionHandler.get("mensaje.64");
            case 18 -> mensajeInternacionalizacionHandler.get("mensaje.65");
            case 19 -> mensajeInternacionalizacionHandler.get("mensaje.66");
            case 20 -> mensajeInternacionalizacionHandler.get("mensaje.67");
            case 21 -> mensajeInternacionalizacionHandler.get("mensaje.68");
            case 22 -> mensajeInternacionalizacionHandler.get("mensaje.69");
            case 23 -> mensajeInternacionalizacionHandler.get("mensaje.70");
            case 24 -> mensajeInternacionalizacionHandler.get("mensaje.71");
            case 25 -> mensajeInternacionalizacionHandler.get("mensaje.72");
            case 26 -> mensajeInternacionalizacionHandler.get("mensaje.73");
            case 27 -> mensajeInternacionalizacionHandler.get("mensaje.74");
            case 28 -> mensajeInternacionalizacionHandler.get("mensaje.75");
            case 29 -> mensajeInternacionalizacionHandler.get("mensaje.76");
            case 30 -> mensajeInternacionalizacionHandler.get("mensaje.77");
            case 31 -> mensajeInternacionalizacionHandler.get("mensaje.78");
            case 32 -> mensajeInternacionalizacionHandler.get("mensaje.79");
            case 33 -> mensajeInternacionalizacionHandler.get("mensaje.80");
            case 34 -> mensajeInternacionalizacionHandler.get("mensaje.81");
            case 35 -> mensajeInternacionalizacionHandler.get("mensaje.82");
            case 36 -> mensajeInternacionalizacionHandler.get("mensaje.83");
            case 37 -> mensajeInternacionalizacionHandler.get("mensaje.84");
            case 38 -> mensajeInternacionalizacionHandler.get("mensaje.85");
            case 39 -> mensajeInternacionalizacionHandler.get("mensaje.86");
            case 40 -> mensajeInternacionalizacionHandler.get("mensaje.87");
            case 41 -> mensajeInternacionalizacionHandler.get("mensaje.88");
            case 42 -> mensajeInternacionalizacionHandler.get("mensaje.89");
            case 43 -> mensajeInternacionalizacionHandler.get("mensaje.90");
            case 44 -> mensajeInternacionalizacionHandler.get("mensaje.91");
            case 45 -> mensajeInternacionalizacionHandler.get("mensaje.92");
            case 46 -> mensajeInternacionalizacionHandler.get("mensaje.93");
            case 47 -> mensajeInternacionalizacionHandler.get("mensaje.94");
            case 48 -> mensajeInternacionalizacionHandler.get("mensaje.95");
            case 49 -> mensajeInternacionalizacionHandler.get("mensaje.96");
            case 50 -> mensajeInternacionalizacionHandler.get("mensaje.97");
            case 51 -> mensajeInternacionalizacionHandler.get("mensaje.98");
            case 52 -> mensajeInternacionalizacionHandler.get("mensaje.99");
            case 53 -> mensajeInternacionalizacionHandler.get("mensaje.100");
            case 54 -> mensajeInternacionalizacionHandler.get("mensaje.101");
            case 55 -> mensajeInternacionalizacionHandler.get("mensaje.102");
            case 56 -> mensajeInternacionalizacionHandler.get("mensaje.103");
            case 57 -> mensajeInternacionalizacionHandler.get("mensaje.104");
            case 58 -> mensajeInternacionalizacionHandler.get("mensaje.105");
            case 59 -> mensajeInternacionalizacionHandler.get("mensaje.106");
            case 60 -> mensajeInternacionalizacionHandler.get("mensaje.107");
            case 61 -> mensajeInternacionalizacionHandler.get("mensaje.108");
            case 62 -> mensajeInternacionalizacionHandler.get("mensaje.109");
            case 63 -> mensajeInternacionalizacionHandler.get("mensaje.110");
            case 64 -> mensajeInternacionalizacionHandler.get("mensaje.111");
            case 65 -> mensajeInternacionalizacionHandler.get("mensaje.112");
            case 66 -> mensajeInternacionalizacionHandler.get("mensaje.113");
            case 67 -> mensajeInternacionalizacionHandler.get("mensaje.114");
            case 68 -> mensajeInternacionalizacionHandler.get("mensaje.115");
            case 69 -> mensajeInternacionalizacionHandler.get("mensaje.116");
            case 70 -> mensajeInternacionalizacionHandler.get("mensaje.117");
            case 71 -> mensajeInternacionalizacionHandler.get("mensaje.118");
            case 72 -> mensajeInternacionalizacionHandler.get("mensaje.119");
            case 73 -> mensajeInternacionalizacionHandler.get("mensaje.120");
            case 74 -> mensajeInternacionalizacionHandler.get("mensaje.121");
            case 75 -> mensajeInternacionalizacionHandler.get("mensaje.122");
            case 76 -> mensajeInternacionalizacionHandler.get("mensaje.123");
            case 77 -> mensajeInternacionalizacionHandler.get("mensaje.124");
            case 78 -> mensajeInternacionalizacionHandler.get("mensaje.125");
            case 79 -> mensajeInternacionalizacionHandler.get("mensaje.126");
            case 80 -> mensajeInternacionalizacionHandler.get("mensaje.127");
            case 81 -> mensajeInternacionalizacionHandler.get("mensaje.128");
            case 82 -> mensajeInternacionalizacionHandler.get("mensaje.129");
            case 83 -> mensajeInternacionalizacionHandler.get("mensaje.130");
            case 84 -> mensajeInternacionalizacionHandler.get("mensaje.131");
            case 85 -> mensajeInternacionalizacionHandler.get("mensaje.132");
            case 86 -> mensajeInternacionalizacionHandler.get("mensaje.133");
            case 87 -> mensajeInternacionalizacionHandler.get("mensaje.134"); // Correo sin '@'
            case 88 -> mensajeInternacionalizacionHandler.get("mensaje.135"); // Correo sin '.'
            case 89 -> mensajeInternacionalizacionHandler.get("mensaje.136"); // Teléfono no numérico
            case 90 -> mensajeInternacionalizacionHandler.get("mensaje.137"); // Teléfono no tiene 10 dígitos

            default -> "";
        };
    }
    /**
     * Retorna un título de mensaje internacionalizado correspondiente al código proporcionado.
     * Usado principalmente en cuadros de diálogo (JOptionPane) como encabezado del mensaje.
     *
     * @param cod Código numérico del título.
     * @return Cadena con el título correspondiente al código dado.
     */
    private String titulosMensajes(int cod) {
        return switch (cod) {
            case 0 -> mensajeInternacionalizacionHandler.get("mensaje.48");
            case 1 -> mensajeInternacionalizacionHandler.get("mensaje.51");
            case 2 -> mensajeInternacionalizacionHandler.get("mensaje.53");
            case 3 -> mensajeInternacionalizacionHandler.get("mensaje.55");
            case 4 -> mensajeInternacionalizacionHandler.get("mensaje.57"); // Campos vacíos
            case 5 -> mensajeInternacionalizacionHandler.get("mensaje.59"); // Verificación fallida
            case 6 -> mensajeInternacionalizacionHandler.get("mensaje.61"); // Usuario duplicado
            case 7 -> mensajeInternacionalizacionHandler.get("mensaje.64"); // Registro completo
            case 8 -> mensajeInternacionalizacionHandler.get("mensaje.66"); // Campo vacío
            case 9 -> mensajeInternacionalizacionHandler.get("mensaje.68"); // Sin resultados
            case 10 -> mensajeInternacionalizacionHandler.get("titulo.30");
            case 11 -> mensajeInternacionalizacionHandler.get("titulo.31");
            case 12 -> mensajeInternacionalizacionHandler.get("mensaje.84"); // Campo vacío
            case 13 -> mensajeInternacionalizacionHandler.get("mensaje.86"); // Sin coincidencias
            case 14 -> mensajeInternacionalizacionHandler.get("mensaje.88"); // Éxito
            case 15 -> mensajeInternacionalizacionHandler.get("mensaje.91"); // Campo vacío
            case 16 -> mensajeInternacionalizacionHandler.get("mensaje.92"); // Error
            case 17 -> mensajeInternacionalizacionHandler.get("titulo.33"); // Advertencia
            case 18 -> mensajeInternacionalizacionHandler.get("titulo.34"); // Confirmar eliminación
            case 19 -> mensajeInternacionalizacionHandler.get("titulo.35"); // Éxito
            case 20 -> mensajeInternacionalizacionHandler.get("mensaje.99");  // Campos vacíos
            case 21 -> mensajeInternacionalizacionHandler.get("mensaje.101"); // Verificación fallida
            case 22 -> mensajeInternacionalizacionHandler.get("mensaje.103"); // Duplicado
            case 23 -> mensajeInternacionalizacionHandler.get("mensaje.105"); // Registro completado
            case 24 -> mensajeInternacionalizacionHandler.get("mensaje.108"); // Error
            case 25 -> mensajeInternacionalizacionHandler.get("mensaje.110"); // Acceso denegado
            case 26 -> mensajeInternacionalizacionHandler.get("mensaje.112"); // Recuperación fallida
            case 27 -> mensajeInternacionalizacionHandler.get("mensaje.114"); // Recuperación exitosa
            case 28 -> mensajeInternacionalizacionHandler.get("mensaje.117"); // Usuario no encontrado
            case 29 -> mensajeInternacionalizacionHandler.get("mensaje.119"); // Preguntas insuficientes
            case 30 -> mensajeInternacionalizacionHandler.get("mensaje.122"); // Contraseña actualizada
            case 31 -> mensajeInternacionalizacionHandler.get("mensaje.124"); // Respuesta incorrecta
            case 78, 79, 80, 81, 82 -> mensajeInternacionalizacionHandler.get("titulo.36");
            case 83, 84, 85, 86 -> mensajeInternacionalizacionHandler.get("titulo.37");
            case 87, 88 -> mensajeInternacionalizacionHandler.get("titulo.38"); // Correo inválido
            case 89, 90 -> mensajeInternacionalizacionHandler.get("titulo.39"); // Teléfono inválido

            default -> " ";
        };
    }
}
