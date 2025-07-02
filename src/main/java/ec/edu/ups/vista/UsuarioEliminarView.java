package ec.edu.ups.vista;

import javax.swing.*;

public class UsuarioEliminarView extends JInternalFrame {
    private JButton btBuscar;
    private JTextField txtUsername;
    private JButton btEliminar;
    private JButton btLimpiar;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JPanel panelPrincipal;

    public UsuarioEliminarView() {
        setContentPane(panelPrincipal);
        setTitle("Eliminar Usuario");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setClosable(true);
        setIconifiable(true);
    }

    public JButton getBtBuscar() {
        return btBuscar;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JButton getBtEliminar() {
        return btEliminar;
    }

    public JButton getBtLimpiar() {
        return btLimpiar;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtApellido() {
        return txtApellido;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
    public void limpiarCampos(){
        txtUsername.setText("");
        txtApellido.setText("");
        txtNombre.setText("");
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
