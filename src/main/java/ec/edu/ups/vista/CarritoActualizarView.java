package ec.edu.ups.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoActualizarView extends JInternalFrame{
    private JPanel header;
    private JButton ptActualizar;
    private JPanel ingresoCodigo;
    private JButton btBuscar;
    private JTextField txtCodigo;
    private JPanel panelPrincipal;
    private JTable tblProductos;
    private JTextField txtTotal;
    private JTextField txtSubtotal;
    private JTextField txtIva;

    private DefaultTableModel modelo;

    public CarritoActualizarView() {
        super("Actualizar Carrito", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Nombre", "Precio","Cantidad","Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);
    }

}
