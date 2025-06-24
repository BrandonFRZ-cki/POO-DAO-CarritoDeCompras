package ec.edu.ups.vista;

import javax.swing.*;

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

    public ProductoActualizarView(){
        setContentPane(panelPrincipal);
        setTitle("Actualizar");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
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

    public JTextField getTxtNuevoNombre() {
        return txtNuevoNombre;
    }

    public JTextField getTxtNuevoPrecio() {
        return txtNuevoPrecio;
    }

    public JButton getPtActualizar() {
        return ptActualizar;
    }

    public JButton getBtBuscar() {
        return btBuscar;
    }

    public JPanel getIngresoCodigo() {
        return ingresoCodigo;
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
