package ec.edu.ups.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Vista interna que permite buscar y eliminar un carrito de compras del sistema.
 * Presenta una tabla con los carritos existentes, y opciones para eliminar uno mediante su código.
 *
 * @author Brandon
 * @version 1.0
 */
public class CarritoEliminarView extends JInternalFrame {

    private JButton btBuscar;
    private JTextField txtCodigo;
    private JButton btEliminar;
    private JPanel panelPrincipal;
    private JTable tblCarritos;
    private JLabel lbTitulo;
    private JLabel lbCodigo;
    private Object[] columnas;
    private DefaultTableModel modelo;

    /**
     * Constructor que inicializa la interfaz gráfica para eliminar un carrito.
     * Configura el modelo de la tabla con columnas predeterminadas.
     */
    public CarritoEliminarView() {
        super("Eliminar Carrito", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);

        modelo = new DefaultTableModel();
        columnas = new Object[]{"Codigo", "Fecha", "Usuario", "Total"};
        modelo.setColumnIdentifiers(columnas);
        tblCarritos.setModel(modelo);
    }

    // Getters

    /** @return Botón para buscar el carrito a eliminar. */
    public JButton getBtBuscar() {
        return btBuscar;
    }

    /** @return Botón para confirmar la eliminación del carrito. */
    public JButton getBtEliminar() {
        return btEliminar;
    }

    /** @return Campo de texto para ingresar el código del carrito. */
    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    /** @return Tabla que muestra los carritos registrados. */
    public JTable getTblCarritos() {
        return tblCarritos;
    }

    /** @return Panel principal del formulario. */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /** @return Etiqueta del título de la ventana. */
    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    /** @return Etiqueta que indica el campo del código. */
    public JLabel getLbCodigo() {
        return lbCodigo;
    }

    /** @return Modelo de la tabla con los datos de los carritos. */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /**
     * Muestra un cuadro de diálogo con un mensaje, dependiendo del tipo (error, info, warning).
     *
     * @param mensaje Texto del mensaje a mostrar.
     * @param titulo  Título del cuadro de diálogo.
     * @param tipo    Tipo de mensaje ("error", "info", "warning").
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
