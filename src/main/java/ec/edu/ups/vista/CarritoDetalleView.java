package ec.edu.ups.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoDetalleView extends JInternalFrame {
    private JTextField txtCodigo;
    private JTable tblProductos;
    private JTextField txtTotal;
    private JTextField txtSubtotal;
    private JTextField txtIva;
    private JPanel panelPrincipal;
    private DefaultTableModel modelo;

    public CarritoDetalleView() {
        super("Detalle Carrito", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        modelo = new DefaultTableModel();
        Object[] columnas = {"Codigo","Nombre", "Precio", "Cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);

    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public JTextField getTxtSubtotal() {
        return txtSubtotal;
    }

    public JTextField getTxtIva() {
        return txtIva;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }
}
