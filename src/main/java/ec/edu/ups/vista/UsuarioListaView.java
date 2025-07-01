package ec.edu.ups.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UsuarioListaView extends JInternalFrame {
    private JTable tblUsuarios;
    private JButton btnBuscar;
    private JTextField txtBuscar;
    private JButton btnListar;
    private JPanel panelPrincipal;

    private DefaultTableModel modelo;

    public UsuarioListaView() {
        setContentPane(panelPrincipal);
        setTitle("Listado de Usuarios");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Username", "Nombre", "Apellido", "Contraseña ","Correo", "Telefono"};
        modelo.setColumnIdentifiers(columnas);
        tblUsuarios.setModel(modelo);
    }

    public JTable getTblUsuarios() {
        return tblUsuarios;
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
    public void limpiarTabla() {
        modelo.setRowCount(0);
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
