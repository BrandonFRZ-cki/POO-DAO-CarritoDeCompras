package ec.edu.ups.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoEliminarView extends JInternalFrame{
    private JButton btBuscar;
    private JTextField txtCodigo;
    private JButton btEliminar;
    private JPanel panelPrincipal;
    private JTable tblCarritos;
    private JLabel lbTitulo;
    private JLabel lbCodigo;
    private Object[] columnas;
    private DefaultTableModel modelo;
    public CarritoEliminarView() {
        super("Eliminar Carrito", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);

        modelo = new DefaultTableModel();
        columnas = new Object[]{"Codigo", "Fecha","Usuario","Total"};
        modelo.setColumnIdentifiers(columnas);
        tblCarritos.setModel(modelo);

    }

    public JButton getBtBuscar() {
        return btBuscar;
    }

    public JButton getBtEliminar() {
        return btEliminar;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JTable getTblCarritos() {
        return tblCarritos;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    public JLabel getLbCodigo() {
        return lbCodigo;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

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
