package ec.edu.ups.vista;

import javax.swing.*;

/**
 * Ventana interna utilizada para añadir un nuevo usuario al sistema.
 * Permite ingresar datos como nombre, apellido, correo, teléfono, nombre de usuario y contraseña.
 *
 * @author Brandon
 * @version 1.0
 */
public class UsuarioAnadirView extends JInternalFrame {

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

    /**
     * Constructor que inicializa y configura la ventana para añadir usuarios.
     */
    public UsuarioAnadirView() {
        setContentPane(panelPrincipal);
        setTitle("Datos del Usuario");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
    }

    /**
     * @return Campo de texto para el nombre de usuario.
     */
    public JTextField getTxtUserName() {
        return txtUserName;
    }

    /**
     * @return Campo de texto para la contraseña.
     */
    public JTextField getTxtContrasena() {
        return txtContrasena;
    }

    /**
     * @return Botón para limpiar los campos del formulario.
     */
    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    /**
     * @return Botón para aceptar y guardar los datos ingresados.
     */
    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    /**
     * @return Panel principal de la vista.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * @return Campo de texto para el nombre del usuario.
     */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * @return Campo de texto para el apellido del usuario.
     */
    public JTextField getTxtApellido() {
        return txtApellido;
    }

    /**
     * @return Campo de texto para el correo del usuario.
     */
    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    /**
     * @return Campo de texto para el teléfono del usuario.
     */
    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    /**
     * @return Campo de texto para la verificación de la contraseña.
     */
    public JPasswordField getTxtVerificaContrasena() {
        return txtVerificaContrasena;
    }

    /**
     * @return Etiqueta del título de la ventana.
     */
    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    /**
     * @return Etiqueta del campo nombre de usuario.
     */
    public JLabel getLbNombreDeUsuario() {
        return lbNombreDeUsuario;
    }

    /**
     * @return Etiqueta del campo nombre.
     */
    public JLabel getLbNombre() {
        return lbNombre;
    }

    /**
     * @return Etiqueta del campo apellido.
     */
    public JLabel getLbApellido() {
        return lbApellido;
    }

    /**
     * @return Etiqueta del campo correo.
     */
    public JLabel getLbCorreo() {
        return lbCorreo;
    }

    /**
     * @return Etiqueta del campo teléfono.
     */
    public JLabel getLbTelefono() {
        return lbTelefono;
    }

    /**
     * @return Etiqueta del campo contraseña.
     */
    public JLabel getLbContrasena() {
        return lbContrasena;
    }

    /**
     * @return Etiqueta del campo de verificación de contraseña.
     */
    public JLabel getLbVerificaContrasena() {
        return lbVerificaContrasena;
    }

    /**
     * Muestra un cuadro de diálogo con el mensaje correspondiente.
     *
     * @param mensaje Texto del mensaje a mostrar.
     * @param titulo  Título del cuadro de diálogo.
     * @param tipo    Tipo de mensaje ("error", "info", "warning").
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

    /**
     * Limpia todos los campos del formulario.
     */
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
