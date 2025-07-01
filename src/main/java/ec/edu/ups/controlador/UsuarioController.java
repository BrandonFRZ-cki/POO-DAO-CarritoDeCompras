package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.*;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
    private final UsuarioDAO usuarioDAO;
    private LoginView loginView;
    private Usuario usuario;
    private UsuarioAnadirView usuarioAnadirView;
    private UsuarioListaView usuarioListaView;
    private UsuarioEliminarView usuarioEliminarView;
    private UsuarioActualizarView usuarioActualizarView;

    public UsuarioController(UsuarioDAO usuarioDAO, LoginView loginView, UsuarioAnadirView usuarioAnadirView, UsuarioListaView usuarioListaView, UsuarioEliminarView usuarioEliminarView, UsuarioActualizarView usuarioActualizarView) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.usuarioAnadirView = usuarioAnadirView;
        this.usuarioListaView = usuarioListaView;
        this.usuarioActualizarView = usuarioActualizarView;
        this.usuarioEliminarView = usuarioEliminarView;
        this.usuario = null;
        configurarEventosEnVistas();
    }
    private void configurarEventosEnVistas() {
        /**
         * ╔═════════════════════════════╗
         * ║         🧍 USUARIO          ║
         * ╚═════════════════════════════╝
         */
        loginView.getIniciarSesiónButton().addActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });

        loginView.getRegistrarceButton().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrar();
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

    }
    private void autenticar() {
        String username = loginView.getTxtUsername().getText();
        String contrasena = loginView.getTxtContrasena().getText();

        usuario = usuarioDAO.autenticar(username,contrasena);
        if (usuario == null) {
            loginView.mostrarMensaje("Usuario o contrasena incorrectos","Datos Incorrectos","error");
        }else{

            loginView.dispose();
        }
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
}
