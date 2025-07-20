package ec.edu.ups.vista;

import javax.swing.*;

/**
 * Vista interna que permite al usuario buscar y eliminar un producto existente del sistema.
 * Incluye campos para mostrar los datos del producto y botones para buscar, limpiar y eliminar.
 *
 * @author Brandon
 * @version 1.0
 */
public class ProductoEliminarView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JButton btEliminar;
    private JButton btLimpiar;
    private JButton btBuscar;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JLabel lbCodigo;
    private JLabel lbTitulo;

    /**
     * Constructor que configura la vista para eliminar productos.
     */
    public ProductoEliminarView() {
        setContentPane(panelPrincipal);
        setTitle("Eliminar Producto");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setClosable(true);
        setIconifiable(true);
    }

    /** @return Panel principal del formulario. */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /** @return Botón para eliminar el producto. */
    public JButton getBtEliminar() {
        return btEliminar;
    }

    /** @return Botón para limpiar los campos. */
    public JButton getBtLimpiar() {
        return btLimpiar;
    }

    /** @return Botón para buscar un producto por su código. */
    public JButton getBtBuscar() {
        return btBuscar;
    }

    /** @return Campo de texto para el código del producto. */
    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    /** @return Campo de texto para el nombre del producto. */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /** @return Campo de texto para el precio del producto. */
    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    /** @return Etiqueta del campo código. */
    public JLabel getLbCodigo() {
        return lbCodigo;
    }

    /** @return Etiqueta del título de la vista. */
    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    /**
     * Limpia los campos de texto del formulario.
     */
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }

    /**
     * Muestra un cuadro de diálogo con un mensaje al usuario.
     *
     * @param mensaje Contenido del mensaje.
     * @param titulo  Título del cuadro de diálogo.
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
