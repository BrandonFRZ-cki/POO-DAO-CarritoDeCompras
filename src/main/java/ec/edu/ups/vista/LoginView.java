package ec.edu.ups.vista;

import javax.swing.*;

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

    public LoginView() {
        add(panelPrincipal);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public JPanel getPanel1() {
        return panelPrincipal;
    }

    public void setPanel1(JPanel panel1) {
        this.panelPrincipal = panel1;
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

    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    public void setBtnIniciarSesion(JButton btnIniciarSesion) {
        this.btnIniciarSesion = btnIniciarSesion;
    }

    public JButton getRegistrarceButton() {
        return registrarceButton;
    }

    public void setRegistrarceButton(JButton registrarceButton) {
        this.registrarceButton = registrarceButton;
    }

    public JButton getBtnRecuperarContrasenia() {
        return btnRecuperarContrasenia;
    }

    public JButton getBtFR() {
        return btFR;
    }

    public JButton getBtSP() {
        return btSP;
    }

    public JButton getBtEN() {
        return btEN;
    }

    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    public JLabel getLbUsername() {
        return lbUsername;
    }

    public JLabel getLbContrasena() {
        return lbContrasena;
    }


    public void mostrarMensaje(String mensaje, String titulo, String tipo) {
        if (tipo.equals("error")) {
            JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
        }else if (tipo.equals("info")) {
            JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
        }else if (tipo.equals("warning")) {
            JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.WARNING_MESSAGE);
        }


    }

}
