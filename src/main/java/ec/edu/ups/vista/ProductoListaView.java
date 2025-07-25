package ec.edu.ups.vista;

import ec.edu.ups.modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductoListaView extends JInternalFrame {

    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JTable tblProductos;
    private JPanel panelPrincipal;
    private JButton btnListar;
    private JLabel lbTitulo;
    private JLabel lbNombre;
    private DefaultTableModel modelo;
    private Object[] columnas ;

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

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public JLabel getLbTitulo() {
        return lbTitulo;
    }

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

    public void cargarDatos(List<Producto> listaProductos) {


        DefaultTableModel modelo = getModelo();
        modelo.setRowCount(0);

        for (Producto producto : listaProductos) {
            modelo.addRow(new Object[] {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio()
            });
        }

    }

}
