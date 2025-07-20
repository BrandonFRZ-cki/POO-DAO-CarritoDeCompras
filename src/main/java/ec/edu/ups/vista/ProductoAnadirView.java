package ec.edu.ups.vista;

import ec.edu.ups.modelo.Producto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Vista interna que permite al usuario ingresar los datos de un nuevo producto.
 * Contiene campos para código, nombre y precio, así como botones para aceptar o limpiar los datos.
 *
 * @author Brandon
 * @version 1.0
 */
public class ProductoAnadirView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField txtPrecio;
    private JTextField txtNombre;
    private JTextField txtCodigo;
    private JButton btnAceptar;
    private JButton btnLimpiar;
    private JLabel lbTitulo;
    private JLabel lbCodigo;
    private JLabel lbNombre;
    private JLabel lbPrecio;

    /**
     * Constructor que inicializa y configura la vista para añadir un producto.
     */
    public ProductoAnadirView() {
        setContentPane(panelPrincipal);
        setTitle("Datos del Producto");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        // Evento para limpiar campos
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    /** @return Panel principal del formulario. */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /** @param panelPrincipal Panel a establecer. */
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /** @return Campo de texto para el precio del producto. */
    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    /** @param txtPrecio Campo de texto para el precio. */
    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    /** @return Campo de texto para el nombre del producto. */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /** @param txtNombre Campo de texto para el nombre. */
    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    /** @return Campo de texto para el código del producto. */
    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    /** @param txtCodigo Campo de texto para el código. */
    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    /** @return Botón para aceptar y registrar el producto. */
    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    /** @param btnAceptar Botón aceptar a establecer. */
    public void setBtnAceptar(JButton btnAceptar) {
        this.btnAceptar = btnAceptar;
    }

    /** @return Botón para limpiar los campos del formulario. */
    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    /** @param btnLimpiar Botón limpiar a establecer. */
    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    /** @return Etiqueta del título de la vista. */
    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    /** @return Etiqueta del campo de código. */
    public JLabel getLbCodigo() {
        return lbCodigo;
    }

    /** @return Etiqueta del campo de nombre. */
    public JLabel getLbNombre() {
        return lbNombre;
    }

    /** @return Etiqueta del campo de precio. */
    public JLabel getLbPrecio() {
        return lbPrecio;
    }

    /**
     * Muestra un cuadro de diálogo con un mensaje al usuario.
     *
     * @param mensaje Contenido del mensaje.
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

    /**
     * Limpia los campos del formulario.
     */
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }

    /**
     * Muestra por consola una lista de productos. Utilizado para depuración.
     *
     * @param productos Lista de productos a mostrar.
     */
    public void mostrarProductos(List<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }
}
