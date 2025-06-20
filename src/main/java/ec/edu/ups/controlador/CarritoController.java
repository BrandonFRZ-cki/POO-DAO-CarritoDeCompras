package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.CarritoAnadirView;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CarritoController {
    private CarritoAnadirView carritoAnadirView;
    private Carrito carrito;
    private ProductoDAO productoDAo;


    private CarritoDAO carritoDAO;
    public CarritoController( CarritoAnadirView carritoAnadirView,ProductoDAO productoDAO) {
        this.carritoAnadirView = carritoAnadirView;
        this.productoDAo = productoDAO;
        carrito = new Carrito();
        configurarEventos();
    }
    private void configurarEventos() {
        carritoAnadirView.getBtnAnadir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarItem();
            }
        });
    }
    public void agregarItem() {
        int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        int cantidad = Integer.parseInt(carritoAnadirView.getCbxCantidad().getSelectedItem().toString());

        carrito.agregarProducto(productoDAo.buscarPorCodigo(codigo),cantidad);
        List<ItemCarrito> items = carrito.obtenerItems();
        carritoAnadirView.cargarItems(items);
    }
}
