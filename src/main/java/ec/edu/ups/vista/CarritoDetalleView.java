package ec.edu.ups.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Vista interna que muestra los detalles de un carrito seleccionado.
 * Presenta los productos agregados al carrito, su subtotal, IVA y total.
 * Se utiliza para visualizar carritos sin permitir edición.
 *
 * @author Brandon
 * @version 1.0
 */
public class CarritoDetalleView extends JInternalFrame {

    private JTextField txtCodigo;
    private JTable tblProductos;
    private JTextField txtTotal;
    private JTextField txtSubtotal;
    private JTextField txtIva;
    private JPanel panelPrincipal;
    private JLabel lbTitulo;
    private JLabel lbCodigo;
    private JLabel lbSubtotal;
    private JLabel lbIVA;
    private JLabel lbTotal;
    private DefaultTableModel modelo;

    /**
     * Constructor que inicializa la interfaz para mostrar el detalle de un carrito.
     * Configura el modelo de la tabla con las columnas correspondientes.
     */
    public CarritoDetalleView() {
        super("Detalle Carrito", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Codigo", "Nombre", "Precio", "Cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);
    }

    // Getters

    /** @return Campo de texto con el código del carrito. */
    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    /** @return Tabla que contiene los productos del carrito. */
    public JTable getTblProductos() {
        return tblProductos;
    }

    /** @return Campo de texto con el total del carrito. */
    public JTextField getTxtTotal() {
        return txtTotal;
    }

    /** @return Campo de texto con el subtotal del carrito. */
    public JTextField getTxtSubtotal() {
        return txtSubtotal;
    }

    /** @return Campo de texto con el IVA calculado. */
    public JTextField getTxtIva() {
        return txtIva;
    }

    /** @return Panel principal que contiene todos los componentes. */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /** @return Modelo de la tabla de productos. */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /** @return Etiqueta del título de la ventana. */
    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    /** @return Etiqueta para el código del carrito. */
    public JLabel getLbCodigo() {
        return lbCodigo;
    }

    /** @return Etiqueta para el subtotal del carrito. */
    public JLabel getLbSubtotal() {
        return lbSubtotal;
    }

    /** @return Etiqueta para el IVA del carrito. */
    public JLabel getLbIVA() {
        return lbIVA;
    }

    /** @return Etiqueta para el total del carrito. */
    public JLabel getLbTotal() {
        return lbTotal;
    }
}
