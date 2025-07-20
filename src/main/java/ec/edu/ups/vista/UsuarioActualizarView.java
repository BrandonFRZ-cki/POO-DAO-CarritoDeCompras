package ec.edu.ups.vista;

import javax.swing.*;

/**
 * Ventana interna utilizada para actualizar los datos de un usuario existente.
 * Permite modificar nombre, apellido, teléfono, correo y contraseña,
 * buscando primero al usuario por su nombre de usuario.
 *
 * @author Brandon
 * @version 1.0
 */
public class UsuarioActualizarView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField txtUserName;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextField txtContrasena;
    private JButton btnActualizar;
    private JButton btnBuscar;
    private JTextField txtUsername;
    private JLabel lbTitulo;
    private JLabel lbUserName;
    private JLabel lbUser;
    private JLabel lbNombre;
    private JLabel lbApellido;
    private JLabel tbCorreo;
    private JLabel lbTelefono;
    private JLabel lbContrasena;

    /**
     * Constructor que configura la ventana para actualizar usuarios.
     */
    public UsuarioActualizarView() {
        setContentPane(panelPrincipal);
        setTitle("Actualizar");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
    }

    /**
     * @return Panel principal de la vista.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * @return Campo de texto para ingresar el nuevo username.
     */
    public JTextField getTxtUserName() {
        return txtUserName;
    }

    /**
     * @return Campo de texto para ingresar el nuevo nombre.
     */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * @return Campo de texto para ingresar el nuevo apellido.
     */
    public JTextField getTxtApellido() {
        return txtApellido;
    }

    /**
     * @return Campo de texto para ingresar el nuevo teléfono.
     */
    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    /**
     * @return Campo de texto para ingresar el nuevo correo.
     */
    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    /**
     * @return Campo de texto para ingresar la nueva contraseña.
     */
    public JTextField getTxtContrasena() {
        return txtContrasena;
    }

    /**
     * @return Botón para ejecutar la acción de actualización.
     */
    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    /**
     * @return Botón para buscar un usuario antes de actualizarlo.
     */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /**
     * @return Campo de texto para buscar al usuario por nombre de usuario.
     */
    public JTextField getTxtUsername() {
        return txtUsername;
    }

    /**
     * @return Etiqueta del título.
     */
    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    /**
     * @return Etiqueta para el campo de nombre de usuario.
     */
    public JLabel getLbUserName() {
        return lbUserName;
    }

    /**
     * @return Etiqueta adicional para el usuario (posiblemente redundante).
     */
    public JLabel getLbUser() {
        return lbUser;
    }

    /**
     * @return Etiqueta para el nombre.
     */
    public JLabel getLbNombre() {
        return lbNombre;
    }

    /**
     * @return Etiqueta para el apellido.
     */
    public JLabel getLbApellido() {
        return lbApellido;
    }

    /**
     * @return Etiqueta para el correo.
     */
    public JLabel getTbCorreo() {
        return tbCorreo;
    }

    /**
     * @return Etiqueta para el teléfono.
     */
    public JLabel getLbTelefono() {
        return lbTelefono;
    }

    /**
     * @return Etiqueta para la contraseña.
     */
    public JLabel getLbContrasena() {
        return lbContrasena;
    }

    /**
     * Muestra un mensaje emergente en pantalla.
     *
     * @param mensaje Contenido del mensaje.
     * @param titulo  Título de la ventana emergente.
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
     * Limpia todos los campos del formulario y habilita la edición del campo de búsqueda.
     */
    public void limpiarCampos() {
        txtUserName.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtContrasena.setText("");
        txtUsername.setText("");
        txtUsername.setEditable(true);
    }
}
