package ec.edu.ups.vista;

import javax.swing.*;

/**
 * Vista principal para registrar un nuevo usuario en el sistema.
 * Incluye campos para cédula (username), nombre, apellido, teléfono,
 * correo, contraseña y su confirmación.
 * También permite limpiar los campos y aceptar el registro.
 *
 * @author Brandon
 * @version 1.0
 */
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
    private JLabel lbTitulo;

    /**
     * Constructor que inicializa la vista de registro.
     */
    public RegistrarView() {
        setContentPane(panelPrincipal);
        setTitle("Registro");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600);
        setResizable(true);
        setLocationRelativeTo(null);
    }

    /** @return Panel principal del formulario. */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /** @return Campo para ingresar el nombre de usuario (cédula). */
    public JTextField getTxtUserName() {
        return txtUserName;
    }

    public void setTxtUserName(JTextField txtUserName) {
        this.txtUserName = txtUserName;
    }

    /** @return Campo para confirmar la contraseña. */
    public JPasswordField getTxtVerificaContrasena() {
        return txtVerificaContrasena;
    }

    public void setTxtVerificaContrasena(JPasswordField txtVerificaContrasena) {
        this.txtVerificaContrasena = txtVerificaContrasena;
    }

    /** @return Campo para ingresar el nombre del usuario. */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    /** @return Campo para ingresar el apellido del usuario. */
    public JTextField getTxtApellido() {
        return txtApellido;
    }

    public void setTxtApellido(JTextField txtApellido) {
        this.txtApellido = txtApellido;
    }

    /** @return Campo para ingresar el número de teléfono. */
    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public void setTxtTelefono(JTextField txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    /** @return Campo para ingresar el correo electrónico. */
    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public void setTxtCorreo(JTextField txtCorreo) {
        this.txtCorreo = txtCorreo;
    }

    /** @return Campo para ingresar la contraseña. */
    public JPasswordField getTxtContrasena() {
        return txtContrasena;
    }

    public void setTxtContrasena(JPasswordField txtContrasena) {
        this.txtContrasena = txtContrasena;
    }

    /** @return Botón para limpiar todos los campos del formulario. */
    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    /** @return Botón para aceptar y registrar el usuario. */
    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public void setBtnAceptar(JButton btnAceptar) {
        this.btnAceptar = btnAceptar;
    }

    /** @return Etiqueta del campo nombre de usuario. */
    public JLabel getLbNombreDeUsuario() {
        return lbNombreDeUsuario;
    }

    public void setLbNombreDeUsuario(JLabel lbNombreDeUsuario) {
        this.lbNombreDeUsuario = lbNombreDeUsuario;
    }

    /** @return Etiqueta del campo nombre. */
    public JLabel getLbNombre() {
        return lbNombre;
    }

    public void setLbNombre(JLabel lbNombre) {
        this.lbNombre = lbNombre;
    }

    /** @return Etiqueta del campo apellido. */
    public JLabel getLbApellido() {
        return lbApellido;
    }

    public void setLbApellido(JLabel lbApellido) {
        this.lbApellido = lbApellido;
    }

    /** @return Etiqueta del campo teléfono. */
    public JLabel getLbTelefono() {
        return lbTelefono;
    }

    public void setLbTelefono(JLabel lbTelefono) {
        this.lbTelefono = lbTelefono;
    }

    /** @return Etiqueta del campo correo electrónico. */
    public JLabel getLbCorreo() {
        return lbCorreo;
    }

    public void setLbCorreo(JLabel lbCorreo) {
        this.lbCorreo = lbCorreo;
    }

    /** @return Etiqueta del campo contraseña. */
    public JLabel getLbContresenia() {
        return lbContresenia;
    }

    public void setLbContresenia(JLabel lbContresenia) {
        this.lbContresenia = lbContresenia;
    }

    /** @return Etiqueta del campo de verificación de contraseña. */
    public JLabel getLbVerificaContrasenia() {
        return lbVerificaContrasenia;
    }

    public void setLbVerificaContrasenia(JLabel lbVerificaContrasenia) {
        this.lbVerificaContrasenia = lbVerificaContrasenia;
    }

    /** @return Etiqueta del título de la ventana. */
    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    /**
     * Muestra un mensaje en pantalla según el tipo indicado.
     *
     * @param mensaje Contenido del mensaje.
     * @param titulo Título de la ventana de diálogo.
     * @param tipo Tipo del mensaje ("error", "info" o "warning").
     */
    public void mostrarMensaje(String mensaje, String titulo, String tipo) {
        if (tipo.equalsIgnoreCase("error")) {
            JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
        } else if (tipo.equalsIgnoreCase("info")) {
            JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
        } else if (tipo.equalsIgnoreCase("warning")) {
            JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Limpia todos los campos del formulario.
     */
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
