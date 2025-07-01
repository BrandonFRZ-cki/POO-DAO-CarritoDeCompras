package ec.edu.ups.vista;

import javax.swing.*;

public class UsuarioActualizarView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField txtUserName;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextField txtContrasena;
    private JButton btnActualizar;
    private JButton btnBuscar;
    private JTextField txtUsername;

    public UsuarioActualizarView() {
        setContentPane(panelPrincipal);
        setTitle("Actualizar");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JTextField getTxtUserName() {
        return txtUserName;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtApellido() {
        return txtApellido;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public JTextField getTxtContrasena() {
        return txtContrasena;
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
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
