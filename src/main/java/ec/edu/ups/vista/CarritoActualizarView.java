package ec.edu.ups.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoActualizarView extends JInternalFrame{
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

    public CarritoActualizarView() {
        super("Actualizar Carrito", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Codigo","Nombre", "Precio","Cantidad","Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);
    }
    public JButton getBtBuscar() {
        return btBuscar;
    }

    public JButton getPtActualizar() {
        return btActualizar;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public JTextField getTxtSubtotal() {
        return txtSubtotal;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public JTextField getTxtIva() {
        return txtIva;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public JTextField getTxtFecha() {
        return txtFecha;
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public JButton getBtActualizar() {
        return btActualizar;
    }

    public JPanel getIngresoCodigo() {
        return ingresoCodigo;
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

    public JLabel getLbFecha() {
        return lbFecha;
    }

    public JLabel getLbUsuario() {
        return lbUsuario;
    }

    public JPanel getLbSubtotal() {
        return lbSubtotal;
    }

    public JLabel getLbSubTotal() {
        return lbSubTotal;
    }

    public JLabel getLbTotal() {
        return lbTotal;
    }

    public JLabel getLbIVA() {
        return lbIVA;
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
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtSubtotal.setText("");
        txtIva.setText("");
        txtTotal.setText("");
        tblProductos.setModel(new DefaultTableModel());
    }

}
