package ec.edu.ups.vista;

import javax.swing.*;

/**
 * Ventana interna utilizada para eliminar un usuario del sistema.
 * Permite buscar un usuario por nombre de usuario (username) y eliminarlo si existe.
 * También muestra los datos básicos como nombre y apellido antes de confirmar la eliminación.
 *
 * @author Brandon
 * @version 1.0
 */
public class UsuarioEliminarView extends JInternalFrame {

    private JButton btBuscar;
    private JTextField txtUsername;
    private JButton btEliminar;
    private JButton btLimpiar;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JPanel panelPrincipal;
    private JLabel lbTitulo;
    private JLabel lbUsername;

    /**
     * Constructor que configura la ventana para eliminar usuarios.
     */
    public UsuarioEliminarView() {
        setContentPane(panelPrincipal);
        setTitle("Eliminar Usuario");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setClosable(true);
        setIconifiable(true);
    }

    /**
     * @return Botón para buscar al usuario por nombre de usuario.
     */
    public JButton getBtBuscar() {
        return btBuscar;
    }

    /**
     * @return Campo de texto para ingresar el nombre de usuario.
     */
    public JTextField getTxtUsername() {
        return txtUsername;
    }

    /**
     * @return Botón para confirmar la eliminación del usuario.
     */
    public JButton getBtEliminar() {
        return btEliminar;
    }

    /**
     * @return Botón para limpiar los campos del formulario.
     */
    public JButton getBtLimpiar() {
        return btLimpiar;
    }

    /**
     * @return Campo de texto con el nombre del usuario.
     */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * @return Campo de texto con el apellido del usuario.
     */
    public JTextField getTxtApellido() {
        return txtApellido;
    }

    /**
     * @return Panel principal de la vista.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Limpia los campos del formulario.
     */
    public void limpiarCampos() {
        txtUsername.setText("");
        txtApellido.setText("");
        txtNombre.setText("");
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
    public JLabel getLbUsername() {
        return lbUsername;
    }

    /**
     * Muestra un mensaje en un cuadro de diálogo.
     *
     * @param mensaje El mensaje a mostrar.
     * @param titulo  El título del cuadro de diálogo.
     * @param tipo    El tipo de mensaje: "error", "info" o "warning".
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
