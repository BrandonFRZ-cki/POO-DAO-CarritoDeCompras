package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.LoginView;
import ec.edu.ups.vista.UsuarioAnadirView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioController {
    private final UsuarioDAO usuarioDAO;
    private LoginView loginView;
    private Usuario usuario;
    private UsuarioAnadirView usuarioAnadirView;


    public UsuarioController(UsuarioDAO usuarioDAO, LoginView loginView, UsuarioAnadirView usuarioAnadirView) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.usuarioAnadirView = usuarioAnadirView;
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

        if (username.isEmpty() || contrasena.isEmpty()) {
            usuarioAnadirView.mostrarMensaje("Debe llenar todos los campos", "Campos vacíos", "warning");
            return;
        }

        if (usuarioDAO.buscarPorUsername(username) != null) {
            usuarioAnadirView.mostrarMensaje("Ya existe un usuario con ese nombre", "Nombre duplicado", "error");
            return;
        }

        Usuario nuevo = new Usuario(username, contrasena, Rol.USUARIO);
        usuarioDAO.crear(nuevo);
        usuarioAnadirView.mostrarMensaje("Usuario añadido con éxito", "Registrado", "info");
        usuarioAnadirView.limparCampos();
    }
}
