package ec.edu.ups.vista;

import javax.swing.*;

/**
 * Vista interna que permite al usuario actualizar la información de un producto existente.
 * Proporciona campos para mostrar los datos actuales y para ingresar los nuevos valores.
 *
 * @author Brandon
 * @version 1.0
 */
public class ProductoActualizarView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtNuevoNombre;
    private JTextField txtNuevoPrecio;
    private JButton ptActualizar;
    private JPanel header;
    private JButton btBuscar;
    private JPanel ingresoCodigo;
    private JLabel lbTitulo;
    private JLabel lbCodigo;
    private JLabel lbOriginal;
    private JLabel lbNombre;
    private JLabel lbPrecio;
    private JLabel lbNuevo;

    /**
     * Constructor que configura la vista para la actualización de productos.
     */
    public ProductoActualizarView(){
        setContentPane(panelPrincipal);
        setTitle("Actualizar");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
    }

    /** @return Campo de texto para el código del producto. */
    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    /** @return Campo de texto con el nombre original del producto. */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /** @return Campo de texto con el precio original del producto. */
    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    /** @return Campo de texto para ingresar el nuevo nombre del producto. */
    public JTextField getTxtNuevoNombre() {
        return txtNuevoNombre;
    }

    /** @return Campo de texto para ingresar el nuevo precio del producto. */
    public JTextField getTxtNuevoPrecio() {
        return txtNuevoPrecio;
    }

    /** @return Botón para confirmar la actualización del producto. */
    public JButton getPtActualizar() {
        return ptActualizar;
    }

    /** @return Botón para buscar un producto por su código. */
    public JButton getBtBuscar() {
        return btBuscar;
    }

    /** @return Panel que contiene el campo para ingresar el código del producto. */
    public JPanel getIngresoCodigo() {
        return ingresoCodigo;
    }

    /** @return Etiqueta del título de la vista. */
    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    /** @return Etiqueta del campo de código. */
    public JLabel getLbCodigo() {
        return lbCodigo;
    }

    /** @return Etiqueta del grupo de datos originales. */
    public JLabel getLbOriginal() {
        return lbOriginal;
    }

    /** @return Etiqueta del campo de nombre. */
    public JLabel getLbNombre() {
        return lbNombre;
    }

    /** @return Etiqueta del campo de precio. */
    public JLabel getLbPrecio() {
        return lbPrecio;
    }

    /** @return Etiqueta del grupo de nuevos datos. */
    public JLabel getLbNuevo() {
        return lbNuevo;
    }

    /** @return Panel principal que contiene todos los componentes. */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /** @return Panel de encabezado. */
    public JPanel getHeader() {
        return header;
    }

    /**
     * Muestra un cuadro de diálogo con un mensaje al usuario.
     *
     * @param mensaje Texto del mensaje a mostrar.
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
