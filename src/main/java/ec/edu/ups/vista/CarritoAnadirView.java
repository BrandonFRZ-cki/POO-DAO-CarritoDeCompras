package ec.edu.ups.vista;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CarritoAnadirView extends JInternalFrame {
    private MensajeInternacionalizacionHandler mensajeInternacionalizacion;

    private JButton btnBuscar;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnAnadir;
    private JTable tblProductos;
    private JTextField txtSubtotal;
    private JTextField txtIva;
    private JTextField txtTotal;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JComboBox cbxCantidad;
    private JPanel panelPrincipal;
    private JLabel lbTitulo;
    private JLabel lbProducto;
    private JLabel lbCodigo;
    private JLabel lbNombre;
    private JLabel lbPrecio;
    private JLabel lbCantidad;
    private JLabel lbSubtotal;
    private JLabel lbIva;
    private JLabel lbTotal;

    private Usuario usuario;
    private Carrito carrito;
    private DefaultTableModel modelo;

    private Object[] columnas;
    public CarritoAnadirView() {
        super("Carrito de Compras", true, true, false, true);

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        cargarDatos();

        modelo = new DefaultTableModel();
        columnas = new Object[] {"Nombre", "Precio", "Cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);
    }

    private void cargarDatos() {
        cbxCantidad.removeAllItems();
        for (int i = 1; i <= 20; i++) {
            cbxCantidad.addItem(String.valueOf(i));
        }
    }

    // Getters
    public JButton getBtnBuscar() { return btnBuscar; }
    public JTextField getTxtCodigo() { return txtCodigo; }
    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtPrecio() { return txtPrecio; }
    public JButton getBtnAnadir() { return btnAnadir; }
    public JTable getTblProductos() { return tblProductos; }
    public JTextField getTxtSubtotal() { return txtSubtotal; }
    public JTextField getTxtIva() { return txtIva; }
    public JTextField getTxtTotal() { return txtTotal; }
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public JComboBox getCbxCantidad() { return cbxCantidad; }
    public JPanel getPanelPrincipal() { return panelPrincipal; }
    public Usuario getUsuario() { return usuario; }
    public Carrito getCarrito() { return carrito; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setCarrito(Carrito carrito) { this.carrito = carrito; }

    public MensajeInternacionalizacionHandler getMensajeInternacionalizacion() {
        return mensajeInternacionalizacion;
    }

    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    public JLabel getLbProducto() {
        return lbProducto;
    }

    public JLabel getLbCodigo() {
        return lbCodigo;
    }

    public JLabel getLbNombre() {
        return lbNombre;
    }

    public JLabel getLbPrecio() {
        return lbPrecio;
    }

    public JLabel getLbCantidad() {
        return lbCantidad;
    }

    public JLabel getLbSubtotal() {
        return lbSubtotal;
    }

    public JLabel getLbIva() {
        return lbIva;
    }

    public JLabel getLbTotal() {
        return lbTotal;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public Object[] getColumnas() {
        return columnas;
    }

    public void setColumnas(Object[] columnas) {
        this.columnas = columnas;
    }

    public void mostrarMensaje(String mensaje, String titulo, String tipo) {
        if (tipo.equals("error")) {
            JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
        }else if (tipo.equals("info")) {
            JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
        }else if (tipo.equals("warning")) {
            JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.WARNING_MESSAGE);
        }


    }
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtSubtotal.setText("");
        txtIva.setText("");
        txtTotal.setText("");
        modelo.setRowCount(0);
    }

    public void cargarItems(List<ItemCarrito> items) {
        modelo.setRowCount(0);
        for (ItemCarrito item : items) {
            Object[] fila = {
                    item.getProducto().getNombre(),
                    item.getProducto().getPrecio(),
                    item.getCantidad(),
                    item.getSubtotal()
            };
            modelo.addRow(fila);
        }
    }
}