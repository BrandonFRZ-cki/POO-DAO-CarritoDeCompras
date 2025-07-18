package ec.edu.ups.vista;

import ec.edu.ups.modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Vista interna que permite visualizar una lista de productos disponibles.
 * Incluye funcionalidades para buscar por nombre y listar todos los productos.
 *
 * @author Brandon
 * @version 1.0
 */
public class ProductoListaView extends JInternalFrame {

    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JTable tblProductos;
    private JPanel panelPrincipal;
    private JButton btnListar;
    private JLabel lbTitulo;
    private JLabel lbNombre;
    private DefaultTableModel modelo;
    private Object[] columnas;

    /**
     * Constructor que configura la interfaz de listado de productos.
     * Establece el modelo de tabla con columnas predefinidas.
     */
    public ProductoListaView() {
        setContentPane(panelPrincipal);
        setTitle("Listado de Productos");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        columnas = new Object[]{"Codigo", "Nombre", "Precio"};
        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);
    }

    /** @return Campo de texto para ingresar el término de búsqueda. */
    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    /** @return Botón para buscar productos por nombre. */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    /** @return Tabla que contiene la lista de productos. */
    public JTable getTblProductos() {
        return tblProductos;
    }

    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }

    /** @return Panel principal de la interfaz. */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /** @return Botón para listar todos los productos disponibles. */
    public JButton getBtnListar() {
        return btnListar;
    }

    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    /** @return Modelo de tabla utilizado para mostrar los productos. */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    /** @return Etiqueta del título de la ventana. */
    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    /** @return Etiqueta que indica el campo de nombre. */
    public JLabel getLbNombre() {
        return lbNombre;
    }

    public Object[] getColumnas() {
        return columnas;
    }

    public void setLbTitulo(JLabel lbTitulo) {
        this.lbTitulo = lbTitulo;
    }

    public void setLbNombre(JLabel lbNombre) {
        this.lbNombre = lbNombre;
    }

    public void setColumnas(Object[] columnas) {
        this.columnas = columnas;
    }

    /**
     * Carga una lista de productos en la tabla.
     *
     * @param listaProductos Lista de productos a mostrar.
     */
    public void cargarDatos(List<Producto> listaProductos) {
        DefaultTableModel modelo = getModelo();
        modelo.setRowCount(0);

        for (Producto producto : listaProductos) {
            modelo.addRow(new Object[]{
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio()
            });
        }
    }
}
