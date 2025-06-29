package ec.edu.ups.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoListaView extends JInternalFrame{
    private JTable tblProductos;
    private JButton btnBuscar;
    private JTextField txtBuscar;
    private JButton btnListar;
    private JPanel panelPrincipal;

    private DefaultTableModel modelo;

     public CarritoListaView() {
         super("Carrito de Compras", true, true, false, true);
         setContentPane(panelPrincipal);
         setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
         setSize(500, 500);
         modelo = new DefaultTableModel();
         Object[] columnas = {"Codigo","Fecha","Usuario","Total"};
         modelo.setColumnIdentifiers(columnas);
         tblProductos.setModel(modelo);
     }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }
}
