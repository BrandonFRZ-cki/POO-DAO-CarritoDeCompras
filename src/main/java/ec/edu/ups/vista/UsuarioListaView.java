package ec.edu.ups.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Vista interna que muestra el listado de usuarios registrados en el sistema.
 * Permite buscar un usuario por username o listar todos los usuarios en una tabla.
 *
 * @author Brandon
 * @version 1.0
 */
public class UsuarioListaView extends JInternalFrame {

    private JTable tblUsuarios;
    private JButton btnBuscar;
    private JTextField txtBuscar;
    private JButton btnListar;
    private JPanel panelPrincipal;
    private JLabel lbTitulo;
    private JLabel lbUsername;
    private DefaultTableModel modelo;

    /**
     * Constructor que inicializa los componentes y configura la tabla de usuarios.
     */
    public UsuarioListaView() {
        setContentPane(panelPrincipal);
        setTitle("Listado de Usuarios");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Username", "Nombre", "Apellido", "Correo", "Telefono", "Fecha de nacimiento"};
        modelo.setColumnIdentifiers(columnas);
        tblUsuarios.setModel(modelo);
    }

    /**
     * @return La tabla que muestra los usuarios.
     */
    public JTable getTblUsuarios() {
        return tblUsuarios;
    }

    /**
     * @return Botón para buscar un usuario por username.
     */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /**
     * @return Campo de texto para ingresar el criterio de búsqueda.
     */
    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    /**
     * @return Botón para listar todos los usuarios.
     */
    public JButton getBtnListar() {
        return btnListar;
    }

    /**
     * @return Panel principal que contiene todos los componentes.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * @return Modelo de la tabla de usuarios.
     */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /**
     * @return Etiqueta del título de la ventana.
     */
    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    /**
     * @return Etiqueta que representa el campo de username.
     */
    public JLabel getLbUsername() {
        return lbUsername;
    }

    /**
     * Limpia todas las filas de la tabla.
     */
    public void limpiarTabla() {
        modelo.setRowCount(0);
    }

    /**
     * Muestra un mensaje emergente al usuario.
     *
     * @param mensaje Mensaje a mostrar.
     * @param titulo  Título de la ventana del mensaje.
     * @param tipo    Tipo de mensaje: "error", "info" o "warning".
     */
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
