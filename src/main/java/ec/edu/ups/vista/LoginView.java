package ec.edu.ups.vista;

import javax.swing.*;

/**
 * Vista principal del sistema que permite el inicio de sesión del usuario.
 * También proporciona accesos a registro, recuperación de contraseña y cambio de idioma.
 *
 * @author Brandon
 * @version 1.0
 */
public class LoginView extends JFrame {

    private JPanel panelPrincipal;
    private JTextField txtUsername;
    private JPasswordField txtContrasena;
    private JButton btnIniciarSesion;
    private JButton registrarceButton;
    private JButton btnRecuperarContrasenia;
    private JButton btFR;
    private JButton btSP;
    private JButton btEN;
    private JLabel lbTitulo;
    private JLabel lbUsername;
    private JLabel lbContrasena;

    /**
     * Constructor que configura los componentes iniciales de la ventana de inicio de sesión.
     */
    public LoginView() {
        add(panelPrincipal);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // Getters y Setters

    /** @return Panel principal del formulario de inicio de sesión. */
    public JPanel getPanel1() {
        return panelPrincipal;
    }

    /** @param panel1 Panel principal a establecer. */
    public void setPanel1(JPanel panel1) {
        this.panelPrincipal = panel1;
    }

    /** @return Campo de texto para ingresar el nombre de usuario. */
    public JTextField getTxtUsername() {
        return txtUsername;
    }

    /** @param textField1 Campo de texto para el nombre de usuario. */
    public void setTxtUsername(JTextField textField1) {
        this.txtUsername = textField1;
    }

    /** @return Campo de contraseña para ingresar la clave. */
    public JPasswordField getTxtContrasena() {
        return txtContrasena;
    }

    /** @param txtContrasena Campo de contraseña a establecer. */
    public void setTxtContrasena(JPasswordField txtContrasena) {
        this.txtContrasena = txtContrasena;
    }

    /** @return Botón para iniciar sesión. */
    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    /** @param btnIniciarSesion Botón de inicio de sesión a establecer. */
    public void setBtnIniciarSesion(JButton btnIniciarSesion) {
        this.btnIniciarSesion = btnIniciarSesion;
    }

    /** @return Botón para registrarse. */
    public JButton getRegistrarceButton() {
        return registrarceButton;
    }

    /** @param registrarceButton Botón de registro a establecer. */
    public void setRegistrarceButton(JButton registrarceButton) {
        this.registrarceButton = registrarceButton;
    }

    /** @return Botón para recuperar la contraseña olvidada. */
    public JButton getBtnRecuperarContrasenia() {
        return btnRecuperarContrasenia;
    }

    /** @return Botón para cambiar idioma a francés. */
    public JButton getBtFR() {
        return btFR;
    }

    /** @return Botón para cambiar idioma a español. */
    public JButton getBtSP() {
        return btSP;
    }

    /** @return Botón para cambiar idioma a inglés. */
    public JButton getBtEN() {
        return btEN;
    }

    /** @return Etiqueta del título de la vista. */
    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    /** @return Etiqueta para el campo de nombre de usuario. */
    public JLabel getLbUsername() {
        return lbUsername;
    }

    /** @return Etiqueta para el campo de contraseña. */
    public JLabel getLbContrasena() {
        return lbContrasena;
    }

    /**
     * Muestra un cuadro de diálogo con un mensaje según el tipo indicado.
     *
     * @param mensaje Contenido del mensaje.
     * @param titulo  Título del cuadro de mensaje.
     * @param tipo    Tipo de mensaje: "error", "info" o "warning".
     */
    public void mostrarMensaje(String mensaje, String titulo, String tipo) {
        if (tipo.equals("error")) {
            JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
        } else if (tipo.equals("info")) {
            JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
        } else if (tipo.equals("warning")) {
            JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.WARNING_MESSAGE);
        }
    }
}
