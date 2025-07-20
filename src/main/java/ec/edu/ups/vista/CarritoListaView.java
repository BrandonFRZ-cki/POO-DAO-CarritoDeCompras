package ec.edu.ups.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Vista interna para listar y buscar carritos de compras.
 * Presenta una tabla con la información general de los carritos registrados.
 * Permite filtrar por nombre de usuario y visualizar el total.
 *
 * @author Brandon
 * @version 1.0
 */
public class CarritoListaView extends JInternalFrame {

    private JTable tblProductos;
    private JButton btnBuscar;
    private JTextField txtBuscar;
    private JButton btnListar;
    private JPanel panelPrincipal;
    private JLabel lbTitulo;
    private JLabel lbNombre;
    private DefaultTableModel modelo;

    /**
     * Constructor que inicializa la vista de listado de carritos.
     * Configura la tabla con columnas predeterminadas.
     */
    public CarritoListaView() {
        super("Carrito de Compras", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Fecha", "Usuario", "Total"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);
    }

    // Getters

    /** @return Tabla que muestra los carritos encontrados o listados. */
    public JTable getTblProductos() {
        return tblProductos;
    }

    /** @return Botón para realizar una búsqueda por nombre de usuario. */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /** @return Campo de texto para ingresar el término de búsqueda. */
    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    /** @return Botón para listar todos los carritos disponibles. */
    public JButton getBtnListar() {
        return btnListar;
    }

    /** @return Panel principal que contiene todos los componentes. */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /** @return Etiqueta del título de la ventana. */
    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    /** @return Etiqueta que indica el campo de nombre/usuario. */
    public JLabel getLbNombre() {
        return lbNombre;
    }

    /** @return Modelo de tabla que contiene los datos de los carritos. */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /**
     * Muestra un mensaje contextual en un cuadro de diálogo según su tipo.
     *
     * @param mensaje Contenido del mensaje a mostrar.
     * @param titulo  Título de la ventana del diálogo.
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
