package ec.edu.ups.vista;

import javax.swing.*;

public class LoginView extends JFrame {
    private JPanel panel1;
    private JTextField txtUsername;
    private JPasswordField txtContrasena;
    private JButton iniciarSesiónButton;
    private JButton registrarceButton;

    public LoginView() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public void setTxtUsername(JTextField textField1) {
        this.txtUsername = textField1;
    }

    public JPasswordField getTxtContrasena() {
        return txtContrasena;
    }

    public void setTxtContrasena(JPasswordField txtContrasena) {
        this.txtContrasena = txtContrasena;
    }

    public JButton getIniciarSesiónButton() {
        return iniciarSesiónButton;
    }

    public void setIniciarSesiónButton(JButton iniciarSesiónButton) {
        this.iniciarSesiónButton = iniciarSesiónButton;
    }

    public JButton getRegistrarceButton() {
        return registrarceButton;
    }

    public void setRegistrarceButton(JButton registrarceButton) {
        this.registrarceButton = registrarceButton;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
