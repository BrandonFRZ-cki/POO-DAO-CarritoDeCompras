package ec.edu.ups.vista;

import javax.swing.*;

public class UsuarioAnadirView extends JInternalFrame{
    private JTextField txtUserName;
    private JTextField txtContrasena;
    private JButton btnLimpiar;
    private JButton btnAceptar;
    private JPanel panelPrincipal;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCorreo;
    private JTextField txtTelefono;
    private JPasswordField txtVerificaContrasena;
    private JLabel lbTitulo;
    private JLabel lbNombreDeUsuario;
    private JLabel lbNombre;
    private JLabel lbApellido;
    private JLabel lbCorreo;
    private JLabel lbTelefono;
    private JLabel lbContrasena;
    private JLabel lbVerificaContrasena;

    public UsuarioAnadirView(){
        setContentPane(panelPrincipal);
        setTitle("Datos del Usuario");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
    }

    public JTextField getTxtUserName() {
        return txtUserName;
    }

    public JTextField getTxtContrasena() {
        return txtContrasena;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtApellido() {
        return txtApellido;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JPasswordField getTxtVerificaContrasena() {
        return txtVerificaContrasena;
    }

    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    public JLabel getLbNombreDeUsuario() {
        return lbNombreDeUsuario;
    }

    public JLabel getLbNombre() {
        return lbNombre;
    }

    public JLabel getLbApellido() {
        return lbApellido;
    }

    public JLabel getLbCorreo() {
        return lbCorreo;
    }

    public JLabel getLbTelefono() {
        return lbTelefono;
    }

    public JLabel getLbContrasena() {
        return lbContrasena;
    }

    public JLabel getLbVerificaContrasena() {
        return lbVerificaContrasena;
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
    public void limparCampos() {
        txtUserName.setText("");
        txtContrasena.setText("");
        txtVerificaContrasena.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
    }
}
