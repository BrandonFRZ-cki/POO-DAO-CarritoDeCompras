package ec.edu.ups.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Vista interna para la actualización de carritos de compra.
 * Permite visualizar y modificar los productos dentro de un carrito específico,
 * así como actualizar cantidades o eliminar elementos.
 *
 * @author Brandon
 * @version 1.0
 */
public class CarritoActualizarView extends JInternalFrame {

    // Componentes de la interfaz
    private JPanel header;
    private JButton btActualizar;
    private JPanel ingresoCodigo;
    private JButton btBuscar;
    private JTextField txtCodigo;
    private JPanel panelPrincipal;
    private JTable tblProductos;
    private JTextField txtSubtotal;
    private JTextField txtIva;
    private JTextField txtTotal;
    private JTextField txtFecha;
    private JTextField txtUsuario;
    private JLabel lbTitulo;
    private JLabel lbCodigo;
    private JLabel lbFecha;
    private JLabel lbUsuario;
    private JPanel lbSubtotal;
    private JLabel lbSubTotal;
    private JLabel lbTotal;
    private JLabel lbIVA;

    private DefaultTableModel modelo;

    /**
     * Constructor que inicializa la vista de actualización del carrito.
     * Configura el modelo de la tabla y el diseño general de la ventana.
     */
    public CarritoActualizarView() {
        super("Actualizar Carrito", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Codigo", "Nombre", "Precio", "Cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);
    }

    // Métodos getter

    /** @return Botón para buscar carrito por código. */
    public JButton getBtBuscar() {
        return btBuscar;
    }

    /** @return Botón para actualizar el carrito. */
    public JButton getPtActualizar() {
        return btActualizar;
    }

    /** @return Campo de texto para ingresar el código del carrito. */
    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    /** @return Tabla de productos del carrito. */
    public JTable getTblProductos() {
        return tblProductos;
    }

    /** @return Campo de texto del subtotal. */
    public JTextField getTxtSubtotal() {
        return txtSubtotal;
    }

    /** @return Modelo de la tabla de productos. */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /** @return Campo de texto del IVA. */
    public JTextField getTxtIva() {
        return txtIva;
    }

    /** @return Campo de texto del total. */
    public JTextField getTxtTotal() {
        return txtTotal;
    }

    /** @return Campo de texto de la fecha del carrito. */
    public JTextField getTxtFecha() {
        return txtFecha;
    }

    /** @return Campo de texto con el nombre del usuario. */
    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    /** @return Botón para ejecutar la acción de actualizar. */
    public JButton getBtActualizar() {
        return btActualizar;
    }

    /** @return Panel de ingreso del código. */
    public JPanel getIngresoCodigo() {
        return ingresoCodigo;
    }

    /** @return Panel principal de la vista. */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /** @return Etiqueta del título principal. */
    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    /** @return Etiqueta del campo código. */
    public JLabel getLbCodigo() {
        return lbCodigo;
    }

    /** @return Etiqueta del campo fecha. */
    public JLabel getLbFecha() {
        return lbFecha;
    }

    /** @return Etiqueta del campo usuario. */
    public JLabel getLbUsuario() {
        return lbUsuario;
    }

    /** @return Panel para la etiqueta subtotal. */
    public JPanel getLbSubtotal() {
        return lbSubtotal;
    }

    /** @return Etiqueta del subtotal. */
    public JLabel getLbSubTotal() {
        return lbSubTotal;
    }

    /** @return Etiqueta del total. */
    public JLabel getLbTotal() {
        return lbTotal;
    }

    /** @return Etiqueta del IVA. */
    public JLabel getLbIVA() {
        return lbIVA;
    }

    /**
     * Muestra un mensaje emergente según el tipo especificado.
     *
     * @param mensaje Contenido del mensaje.
     * @param titulo Título del cuadro de diálogo.
     * @param tipo Tipo del mensaje ("error", "info" o "warning").
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
     * Limpia todos los campos de entrada y la tabla de productos.
     * Deja la vista en estado inicial.
     */
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtSubtotal.setText("");
        txtIva.setText("");
        txtTotal.setText("");
        tblProductos.setModel(new DefaultTableModel());
    }
}
