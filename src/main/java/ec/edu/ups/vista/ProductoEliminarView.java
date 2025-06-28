package ec.edu.ups.vista;

import javax.swing.*;

public class ProductoEliminarView extends JInternalFrame{
    private JPanel panelPrincipal;
    private JButton btEliminar;
    private JButton btLimpiar;
    private JButton btBuscar;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;

    public ProductoEliminarView() {
        setContentPane(panelPrincipal);
        setTitle("Eliminar Producto");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setClosable(true);
        setIconifiable(true);
    }
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JButton getBtEliminar() {
        return btEliminar;
    }

    public JButton getBtLimpiar() {
        return btLimpiar;
    }

    public JButton getBtBuscar() {
        return btBuscar;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
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

}
