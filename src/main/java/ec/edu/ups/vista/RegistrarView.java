package ec.edu.ups.vista;

import javax.swing.*;

public class RegistrarView extends JFrame {
    private JPanel panelPrincipal;
    private JTextField txtUserName;
    private JPasswordField txtVerificaContrasena;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JButton btnLimpiar;
    private JButton btnAceptar;
    private JLabel lbNombreDeUsuario;
    private JLabel lbNombre;
    private JLabel lbApellido;
    private JLabel lbTelefono;
    private JLabel lbCorreo;
    private JLabel lbContresenia;
    private JLabel lbVerificaContrasenia;



    public RegistrarView() {
        setContentPane(panelPrincipal);
        setTitle("Registro");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600);
        setResizable(true);
        setLocationRelativeTo(null);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtUserName() {
        return txtUserName;
    }

    public void setTxtUserName(JTextField txtUserName) {
        this.txtUserName = txtUserName;
    }

    public JPasswordField getTxtVerificaContrasena() {
        return txtVerificaContrasena;
    }

    public void setTxtVerificaContrasena(JPasswordField txtVerificaContrasena) {
        this.txtVerificaContrasena = txtVerificaContrasena;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtApellido() {
        return txtApellido;
    }

    public void setTxtApellido(JTextField txtApellido) {
        this.txtApellido = txtApellido;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public void setTxtTelefono(JTextField txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public void setTxtCorreo(JTextField txtCorreo) {
        this.txtCorreo = txtCorreo;
    }

    public JPasswordField getTxtContrasena() {
        return txtContrasena;
    }

    public void setTxtContrasena(JPasswordField txtContrasena) {
        this.txtContrasena = txtContrasena;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public void setBtnAceptar(JButton btnAceptar) {
        this.btnAceptar = btnAceptar;
    }

    public JLabel getLbNombreDeUsuario() {
        return lbNombreDeUsuario;
    }

    public void setLbNombreDeUsuario(JLabel lbNombreDeUsuario) {
        this.lbNombreDeUsuario = lbNombreDeUsuario;
    }

    public JLabel getLbNombre() {
        return lbNombre;
    }

    public void setLbNombre(JLabel lbNombre) {
        this.lbNombre = lbNombre;
    }

    public JLabel getLbApellido() {
        return lbApellido;
    }

    public void setLbApellido(JLabel lbApellido) {
        this.lbApellido = lbApellido;
    }

    public JLabel getLbTelefono() {
        return lbTelefono;
    }

    public void setLbTelefono(JLabel lbTelefono) {
        this.lbTelefono = lbTelefono;
    }

    public JLabel getLbCorreo() {
        return lbCorreo;
    }

    public void setLbCorreo(JLabel lbCorreo) {
        this.lbCorreo = lbCorreo;
    }

    public JLabel getLbContresenia() {
        return lbContresenia;
    }

    public void setLbContresenia(JLabel lbContresenia) {
        this.lbContresenia = lbContresenia;
    }

    public JLabel getLbVerificaContrasenia() {
        return lbVerificaContrasenia;
    }

    public void setLbVerificaContrasenia(JLabel lbVerificaContrasenia) {
        this.lbVerificaContrasenia = lbVerificaContrasenia;
    }
    public void mostrarMensaje(String mensaje, String titulo, String tipo) {
        if (tipo.equalsIgnoreCase("error")) {
            JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
        } else if (tipo.equalsIgnoreCase("info")) {
            JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
        } else if (tipo.equalsIgnoreCase("warning")) {
            JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.WARNING_MESSAGE);
        }
    }
    public void limpiarCampos() {
        txtUserName.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtContrasena.setText("");
        txtVerificaContrasena.setText("");
    }
}
