package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioController {
    private final UsuarioDAO usuarioDAO;
    private LoginView loginView;
    private Usuario usuario;


    public UsuarioController(UsuarioDAO usuarioDAO, LoginView loginView) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.usuario = null;
        configurarEventosEnVistas();
    }
    private void configurarEventosEnVistas() {
        loginView.getIniciarSesiónButton().addActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });

    }
    private void autenticar() {
        String username = loginView.getTxtUsername().getText();
        String contrasena = loginView.getTxtContrasena().getText();

        usuario = usuarioDAO.autenticar(username,contrasena);
        if (usuario == null) {
            loginView.mostrarMensaje("Usuario o contrasena incorrectos");
        }else{

            loginView.dispose();
        }
    }
    public Usuario getUsuarioAutenticado() {
        return usuario;
    }
}
