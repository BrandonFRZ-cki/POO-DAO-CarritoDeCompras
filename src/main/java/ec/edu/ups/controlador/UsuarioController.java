package ec.edu.ups.controlador;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Pregunta;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


public class UsuarioController {
    private final UsuarioDAO usuarioDAO;
    private LoginView loginView;
    private Usuario usuario;
    private UsuarioAnadirView usuarioAnadirView;
    private UsuarioListaView usuarioListaView;
    private UsuarioEliminarView usuarioEliminarView;
    private UsuarioActualizarView usuarioActualizarView;
    private RegistrarView registrarView;
    private ResponderPreguntas responderPreguntasView;
    private PreguntaDAO preguntaDAO;

    public UsuarioController(UsuarioDAO usuarioDAO, LoginView loginView, UsuarioAnadirView usuarioAnadirView,
                             UsuarioListaView usuarioListaView, UsuarioEliminarView usuarioEliminarView,
                             UsuarioActualizarView usuarioActualizarView, RegistrarView registrarView, ResponderPreguntas responderPreguntasView, PreguntaDAO preguntaDAO) {
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
        configurarEventosEnVistas();
    }
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
                registrarNuevoUsuario();
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
                anadirUsuario();
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
                actualizarUsuario();
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

    private void autenticar() {
        String username = loginView.getTxtUsername().getText();
        String contrasena = new String(loginView.getTxtContrasena().getPassword());

        usuario = usuarioDAO.autenticar(username, contrasena);


        if (usuario == null) {
            loginView.mostrarMensaje("Usuario o contraseña incorrectos", "Error de autenticación", "error");
            return;
        }


        boolean esAdmin = usuario.getRol().equals(Rol.ADMINISTRADOR);

        boolean tieneMinimoPreguntas = usuario.getPreguntasRespondidas() != null && usuario.getPreguntasRespondidas().size() >= 3;
        boolean tieneFecha = usuario.getFechaNacimiento() != null;

        if (!esAdmin && (!tieneMinimoPreguntas || !tieneFecha)) {
            responderPreguntasView.setVisible(true);
            return;
        }

        loginView.dispose();
    }
    private void configurarResponderPreguntas(Usuario usuario) {
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
    }
    private void guardarPreguntasYFecha(Usuario usuario) {
        List<Pregunta> preguntasRespondidas = new ArrayList<>();
        JTextField[] campos = responderPreguntasView.getCamposRespuestas();

        int respuestasValidas = 0;
        for (int i = 0; i < campos.length; i++) {
            String texto = campos[i].getText().trim();
            if (!texto.isEmpty()) {
                preguntasRespondidas.add(new Pregunta(i + 1, preguntaDAO.buscarPorCodigo(i + 1).getPregunta()));
                preguntasRespondidas.get(preguntasRespondidas.size() - 1).setRespuesta(texto);
                respuestasValidas++;
            }
        }

        if (respuestasValidas < 3) {
            JOptionPane.showMessageDialog(responderPreguntasView, "Debe responder al menos 3 preguntas", "Respuestas insuficientes", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int dia = Integer.parseInt(responderPreguntasView.getTxtDia().getText().trim());
            int mes = responderPreguntasView.getCbxMes().getSelectedIndex(); // enero=0
            int anio = Integer.parseInt(responderPreguntasView.getTxtAnio().getText().trim());

            if (String.valueOf(anio).length() != 4 || dia < 1 || dia > 31) {
                throw new NumberFormatException();
            }

            usuario.setFechaNacimiento(new GregorianCalendar(anio, mes, dia));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(responderPreguntasView, "Fecha inválida. Verifica el día y año (ej: 1999)", "Error de fecha", JOptionPane.ERROR_MESSAGE);
            return;
        }

        usuario.setPreguntasRespondidas(preguntasRespondidas);
        JOptionPane.showMessageDialog(responderPreguntasView, "Preguntas guardadas con éxito", "¡Listo!", JOptionPane.INFORMATION_MESSAGE);
        responderPreguntasView.dispose();
    }
    public Usuario getUsuarioAutenticado() {
        return usuario;
    }
    private void registrar() {
        if(loginView.getTxtUsername().getText().isEmpty() || loginView.getTxtContrasena().getText().isEmpty()){
            loginView.mostrarMensaje("Llena los campos para crear tu usuario","Campos Vacios","error");
        }else{
            Usuario usuarioRegistrado = new Usuario();
            usuarioRegistrado.setUsername(loginView.getTxtUsername().getText());
            usuarioRegistrado.setContrasenia(loginView.getTxtContrasena().getText());
            usuarioRegistrado.setRol(Rol.USUARIO);
            usuarioDAO.crear(usuarioRegistrado);
            loginView.mostrarMensaje("Usuario registrado con exito","Datos Creados","warning");
        }
    }
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
            usuarioAnadirView.mostrarMensaje("Todos los campos son obligatorios", "Campos vacíos", "warning");
            return;
        }

        if (!contrasena.equals(verificarContrasena)) {
            usuarioAnadirView.mostrarMensaje("Las contraseñas no coinciden", "Verificación fallida", "error");
            return;
        }

        if (usuarioDAO.buscarPorUsername(username) != null) {
            usuarioAnadirView.mostrarMensaje("Ya existe un usuario con ese nombre", "Usuario duplicado", "error");
            return;
        }

        Usuario nuevo = new Usuario(username, contrasena, Rol.USUARIO, nombre, apellido, correo, telefono);
        usuarioDAO.crear(nuevo);

        usuarioAnadirView.mostrarMensaje("Usuario registrado con éxito", "Registro completo", "info");
        usuarioAnadirView.mostrarMensaje("EL Usuario debe llenar sus preguntas de recuperacion posteriormente", "Registro completo", "warning");
        usuarioAnadirView.limparCampos();
    }
    private void buscarUsuarioPorUserName() {
        String texto = usuarioListaView.getTxtBuscar().getText().toLowerCase().trim();

        if (texto.isEmpty()) {
            usuarioListaView.mostrarMensaje("Ingrese un nombre de usuario para buscar", "Campo vacío", "warning");
            return;
        }

        List<Usuario> usuariosCoincidentes = new ArrayList<>();

        List<Usuario> listaUsuarios = usuarioDAO.listarTodos();
        for (int i = 0; i < listaUsuarios.size(); i++) {
            Usuario u = listaUsuarios.get(i);
            if (u.getUsername().toLowerCase().contains(texto)) {
                usuariosCoincidentes.add(u);
            }
        }

        if (usuariosCoincidentes.isEmpty()) {
            usuarioListaView.mostrarMensaje("No se encontraron usuarios con ese username", "Sin resultados", "info");
        }

        cargarEnTabla(usuariosCoincidentes);
    }
    private void listarUsuarios() {
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        cargarEnTabla(usuarios);
    }
    private void cargarEnTabla(List<Usuario> usuarios) {
        DefaultTableModel modelo = usuarioListaView.getModelo();
        modelo.setRowCount(0); // Limpia la tabla

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            modelo.addRow(new Object[]{
                    u.getUsername(),
                    u.getNombre(),
                    u.getApellido(),
                    u.getContrasenia(),
                    u.getEmail(),
                    u.getTelefono()
            });
        }
    }
    private void actualizarUsuario() {
        String username = usuarioActualizarView.getTxtUsername().getText();
        Usuario usuario = usuarioDAO.buscarPorUsername(username);

        if (usuario == null) {
            usuarioActualizarView.mostrarMensaje("Usuario no encontrado", "Error", "error");
            return;
        }

        Object[] opciones = {
                "Nombre",
                "Apellido",
                "Correo",
                "Teléfono",
                "Contraseña"
        };

        String opcion = (String) JOptionPane.showInputDialog(
                usuarioActualizarView,
                "¿Qué deseas actualizar?",
                "Actualizar usuario",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (opcion == null) return; // Canceló

        switch (opcion) {
            case "Nombre":
                String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:", usuario.getNombre());
                if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                    usuario.setNombre(nuevoNombre);
                }
                break;

            case "Apellido":
                String nuevoApellido = JOptionPane.showInputDialog("Nuevo apellido:", usuario.getApellido());
                if (nuevoApellido != null && !nuevoApellido.trim().isEmpty()) {
                    usuario.setApellido(nuevoApellido);
                }
                break;

            case "Correo":
                String nuevoCorreo = JOptionPane.showInputDialog("Nuevo correo electrónico:", usuario.getEmail());
                if (nuevoCorreo != null && !nuevoCorreo.trim().isEmpty()) {
                    usuario.setEmail(nuevoCorreo);
                }
                break;

            case "Teléfono":
                String nuevoTelefono = JOptionPane.showInputDialog("Nuevo número de teléfono:", usuario.getTelefono());
                if (nuevoTelefono != null && !nuevoTelefono.trim().isEmpty()) {
                    usuario.setTelefono(nuevoTelefono);
                }
                break;

            case "Contraseña":
                String nuevaContrasena = JOptionPane.showInputDialog("Nueva contraseña:");
                if (nuevaContrasena != null && !nuevaContrasena.trim().isEmpty()) {
                    usuario.setContrasenia(nuevaContrasena);
                }
                break;
        }

        usuarioDAO.actualizar(usuario);
        usuarioActualizarView.mostrarMensaje("Datos actualizados correctamente", "Éxito", "info");



        // Refrescar los campos
        usuarioActualizarView.getTxtUserName().setText(usuario.getUsername());
        usuarioActualizarView.getTxtNombre().setText(usuario.getNombre());
        usuarioActualizarView.getTxtApellido().setText(usuario.getApellido());
        usuarioActualizarView.getTxtCorreo().setText(usuario.getEmail());
        usuarioActualizarView.getTxtTelefono().setText(usuario.getTelefono());
        usuarioActualizarView.getTxtContrasena().setText(usuario.getContrasenia());
    }
    public void buscarParaActualizar() {
        String username = usuarioActualizarView.getTxtUsername().getText().trim();

        if (username.isEmpty()) {
            usuarioActualizarView.mostrarMensaje("Ingresa un nombre de usuario para buscar", "Campo vacío", "warning");
            return;
        }

        Usuario usuario = usuarioDAO.buscarPorUsername(username);

        if (usuario == null) {
            usuarioActualizarView.mostrarMensaje("Usuario no encontrado", "Sin coincidencias", "error");
            return;
        }

        // Mostrar información del usuario en campos no editables
        usuarioActualizarView.getTxtUserName().setText(usuario.getUsername());
        usuarioActualizarView.getTxtNombre().setText(usuario.getNombre());
        usuarioActualizarView.getTxtApellido().setText(usuario.getApellido());
        usuarioActualizarView.getTxtTelefono().setText(usuario.getTelefono());
        usuarioActualizarView.getTxtCorreo().setText(usuario.getEmail());
        usuarioActualizarView.getTxtContrasena().setText(usuario.getContrasenia());

        usuarioActualizarView.mostrarMensaje("Usuario encontrado. Datos cargados.", "Éxito", "info");

    }

    public void buscarParaEliminar() {
        String username = usuarioEliminarView.getTxtUsername().getText().trim();

        if (username.isEmpty()) {
            usuarioEliminarView.mostrarMensaje("Ingrese el nombre de usuario", "Campo vacío", "warning");
            return;
        }

        Usuario usuario = usuarioDAO.buscarPorUsername(username);

        if (usuario != null) {
            usuarioEliminarView.getTxtNombre().setText(usuario.getNombre());
            usuarioEliminarView.getTxtApellido().setText(usuario.getApellido());
        } else {
            usuarioEliminarView.getTxtNombre().setText(" - ");
            usuarioEliminarView.getTxtApellido().setText(" - ");
            usuarioEliminarView.mostrarMensaje("Usuario no encontrado", "Error", "error");
        }
    }
    public void eliminar() {
        String username = usuarioEliminarView.getTxtUsername().getText().trim();

        Usuario usuario = usuarioDAO.buscarPorUsername(username);
        if (usuario == null) {
            usuarioEliminarView.mostrarMensaje("Debe buscar un usuario válido primero", "Advertencia", "warning");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                usuarioEliminarView,
                "¿Estás seguro de eliminar al usuario \"" + username + "\"?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            usuarioDAO.eliminar(username);
            usuarioEliminarView.mostrarMensaje("Usuario eliminado correctamente", "Éxito", "info");

            // Limpiar campos
            usuarioEliminarView.getTxtUsername().setText("");
            usuarioEliminarView.getTxtNombre().setText("");
            usuarioEliminarView.getTxtApellido().setText("");
        }
    }

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
            registrarView.mostrarMensaje("Completa todos los campos", "Campos vacíos", "warning");
            return;
        }

        if (!contrasena.equals(verificar)) {
            registrarView.mostrarMensaje("Las contraseñas no coinciden", "Verificación fallida", "error");
            return;
        }

        if (usuarioDAO.buscarPorUsername(username) != null) {
            registrarView.mostrarMensaje("Ya existe un usuario con ese nombre", "Duplicado", "error");
            return;
        }

        Usuario nuevo = new Usuario(username, contrasena, Rol.USUARIO, nombre, apellido, correo, telefono);
        usuarioDAO.crear(nuevo);

        registrarView.mostrarMensaje("Usuario registrado con éxito", "Registro completado", "info");
        registrarView.limpiarCampos();
        registrarView.setVisible(false);
        responderPreguntasView.limpiarCampos();
        responderPreguntasView.setVisible(true);

    }
    private void recuperarContrasena() {
        String username = JOptionPane.showInputDialog(loginView, "Ingresa tu nombre de usuario:");
        Usuario usuario = usuarioDAO.buscarPorUsername(username);

        if (usuario == null) {
            loginView.mostrarMensaje("Usuario no encontrado", "Error", "error");
            return;
        }

        List<Pregunta> respondidas = usuario.getPreguntasRespondidas();
        if (respondidas == null || respondidas.size() < 3) {
            loginView.mostrarMensaje("No tienes suficientes preguntas registradas", "Acceso denegado", "warning");
            return;
        }

        // Selección aleatoria
        int aleatoria = (int) (Math.random() * respondidas.size());
        Pregunta seleccionada = respondidas.get(aleatoria);

        String respuesta = JOptionPane.showInputDialog(loginView, seleccionada.getPregunta());
        if (respuesta == null || respuesta.trim().isEmpty()) return;

        if (seleccionada.getRespuesta().equalsIgnoreCase(respuesta.trim())) {
            loginView.mostrarMensaje("Tu contraseña es: " + usuario.getContrasenia(), "Recuperación exitosa", "info");
        } else {
            loginView.mostrarMensaje("Respuesta incorrecta", "Recuperación fallida", "error");
        }
    }
}
