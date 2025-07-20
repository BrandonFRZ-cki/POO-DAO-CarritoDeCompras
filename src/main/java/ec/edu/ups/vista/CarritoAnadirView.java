package ec.edu.ups.vista;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Vista interna que permite añadir productos a un carrito de compras.
 * Incluye campos para seleccionar cantidad, calcular subtotal, IVA y total,
 * así como mostrar los productos añadidos en una tabla.
 *
 * @author Brandon
 * @version 1.0
 */
public class CarritoAnadirView extends JInternalFrame {

    // Componentes de la vista
    private MensajeInternacionalizacionHandler mensajeInternacionalizacion;

    private JButton btnBuscar;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JButton btnAnadir;
    private JTable tblProductos;
    private JTextField txtSubtotal;
    private JTextField txtIva;
    private JTextField txtTotal;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JComboBox cbxCantidad;
    private JPanel panelPrincipal;
    private JLabel lbTitulo;
    private JLabel lbProducto;
    private JLabel lbCodigo;
    private JLabel lbNombre;
    private JLabel lbPrecio;
    private JLabel lbCantidad;
    private JLabel lbSubtotal;
    private JLabel lbIva;
    private JLabel lbTotal;
    private JTextField txtPrecio;

    private Usuario usuario;
    private Carrito carrito;
    private DefaultTableModel modelo;
    private Object[] columnas;

    /**
     * Constructor que inicializa la vista para añadir productos al carrito.
     * Configura la tabla de productos y la lista de cantidades.
     */
    public CarritoAnadirView() {
        super("Carrito de Compras", true, true, false, true);

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        cargarDatos();

        modelo = new DefaultTableModel();
        columnas = new Object[] {"Nombre", "Precio", "Cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);
    }

    /**
     * Carga las cantidades de 1 a 20 en el JComboBox.
     */
    private void cargarDatos() {
        cbxCantidad.removeAllItems();
        for (int i = 1; i <= 20; i++) {
            cbxCantidad.addItem(String.valueOf(i));
        }
    }

    // Getters y setters

    /** @return Botón para buscar producto por código. */
    public JButton getBtnBuscar() { return btnBuscar; }

    /** @return Campo de texto para código del producto. */
    public JTextField getTxtCodigo() { return txtCodigo; }

    /** @return Campo de texto para precio del producto. */
    public JTextField getTxtPrecio() { return txtPrecio; }

    /** @return Botón para añadir producto a la tabla. */
    public JButton getBtnAnadir() { return btnAnadir; }

    /** @return Tabla que muestra los productos añadidos. */
    public JTable getTblProductos() { return tblProductos; }

    /** @return Campo de texto para el subtotal. */
    public JTextField getTxtSubtotal() { return txtSubtotal; }

    /** @return Campo de texto para el IVA. */
    public JTextField getTxtIva() { return txtIva; }

    /** @return Campo de texto para el total. */
    public JTextField getTxtTotal() { return txtTotal; }

    /** @return Botón para guardar el carrito. */
    public JButton getBtnGuardar() { return btnGuardar; }

    /** @return Botón para limpiar todos los campos. */
    public JButton getBtnLimpiar() { return btnLimpiar; }

    /** @return ComboBox para seleccionar cantidad. */
    public JComboBox getCbxCantidad() { return cbxCantidad; }

    /** @return Panel principal de la vista. */
    public JPanel getPanelPrincipal() { return panelPrincipal; }

    /** @return Usuario asociado al carrito. */
    public Usuario getUsuario() { return usuario; }

    /** @return Carrito actual. */
    public Carrito getCarrito() { return carrito; }

    /** @param usuario Usuario actual que está añadiendo productos. */
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    /** @param carrito Carrito actual en edición. */
    public void setCarrito(Carrito carrito) { this.carrito = carrito; }

    /** @return Campo de texto del nombre del producto. */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /** @return Manejador de internacionalización de mensajes. */
    public MensajeInternacionalizacionHandler getMensajeInternacionalizacion() {
        return mensajeInternacionalizacion;
    }

    public JLabel getLbTitulo() { return lbTitulo; }
    public JLabel getLbProducto() { return lbProducto; }
    public JLabel getLbCodigo() { return lbCodigo; }
    public JLabel getLbNombre() { return lbNombre; }
    public JLabel getLbPrecio() { return lbPrecio; }
    public JLabel getLbCantidad() { return lbCantidad; }
    public JLabel getLbSubtotal() { return lbSubtotal; }
    public JLabel getLbIva() { return lbIva; }
    public JLabel getLbTotal() { return lbTotal; }

    /** @return Modelo de la tabla. */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /** @param modelo Nuevo modelo de tabla. */
    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    /** @return Arreglo de columnas de la tabla. */
    public Object[] getColumnas() {
        return columnas;
    }

    /** @param columnas Columnas para la tabla de productos. */
    public void setColumnas(Object[] columnas) {
        this.columnas = columnas;
    }

    /**
     * Muestra un cuadro de diálogo con el mensaje y tipo especificado.
     *
     * @param mensaje Contenido del mensaje.
     * @param titulo Título del cuadro de mensaje.
     * @param tipo Tipo del mensaje ("error", "info", "warning").
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

    /**
     * Limpia todos los campos de entrada y reinicia la tabla de productos.
     */
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtPrecio.setText("");
        txtNombre.setText("");
        txtSubtotal.setText("");
        txtIva.setText("");
        txtTotal.setText("");
        modelo.setRowCount(0);
    }

    /**
     * Carga una lista de items del carrito en la tabla de productos.
     *
     * @param items Lista de items a mostrar en la tabla.
     */
    public void cargarItems(List<ItemCarrito> items) {
        modelo.setRowCount(0);
        for (ItemCarrito item : items) {
            Object[] fila = {
                    item.getProducto().getNombre(),
                    item.getProducto().getPrecio(),
                    item.getCantidad(),
                    item.getSubtotal()
            };
            modelo.addRow(fila);
        }
    }
}
