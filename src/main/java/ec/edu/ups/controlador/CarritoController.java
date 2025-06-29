package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.*;
import ec.edu.ups.vista.CarritoActualizarView;
import ec.edu.ups.vista.CarritoAnadirView;
import ec.edu.ups.vista.CarritoEliminarView;
import ec.edu.ups.vista.CarritoListaView;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

public class CarritoController {

    private final CarritoDAO carritoDAO;
    private final ProductoDAO productoDAO;
    private final CarritoAnadirView carritoAnadirView;
    private final CarritoEliminarView carritoEliminarView;
    private final CarritoActualizarView carritoActualizarView;
    private final CarritoListaView carritoListaView;

    public Usuario usuario;
    private Carrito nuevoCarrito;

    public CarritoController(Usuario usuario,
                             CarritoDAO carritoDAO,
                             ProductoDAO productoDAO,
                             CarritoAnadirView carritoAnadirView,
                             CarritoListaView carritoListaView,
                             CarritoEliminarView carritoEliminarView,
                             CarritoActualizarView carritoActualizarView) {
        this.carritoDAO = carritoDAO;
        this.productoDAO = productoDAO;
        this.carritoAnadirView = carritoAnadirView;
        this.carritoListaView = carritoListaView;
        this.carritoActualizarView = carritoActualizarView;
        this.carritoEliminarView = carritoEliminarView;
        configurarEventosEnVistas();
    }

    private void configurarEventosEnVistas() {
        /**
         * ╔════════════════════════════════════╗
         * ║         🛒 CARRITO - CRUD          ║
         * ╚════════════════════════════════════╝
         *
         * ╔════════════════════════════════════╗
         * ║          ➕ CREAR                  ║
         * ╚════════════════════════════════════╝
         */
        carritoAnadirView.getBtnAnadir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirProducto();
            }
        });

        carritoAnadirView.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCarrito();
            }
        });

        /**
         * ╔════════════════════════════════════╗
         * ║         🔍 LISTAR                  ║
         * ╚════════════════════════════════════╝
         */

        carritoListaView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCarrito();
            }
        });
        carritoListaView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarCarritos();
            }
        });


    }
    private void guardarCarrito() {
        GregorianCalendar actual = new GregorianCalendar();
        Carrito nuevoCarrito = carritoAnadirView.getCarrito();

        if (nuevoCarrito == null) {
            nuevoCarrito = new Carrito(carritoAnadirView.getUsuario());
            carritoAnadirView.setCarrito(nuevoCarrito);
        }

        nuevoCarrito.setFechaCreacion(actual);
        nuevoCarrito.setUsuario(carritoAnadirView.getUsuario());

        carritoDAO.crear(nuevoCarrito);

        carritoAnadirView.mostrarMensaje("Carrito creado correctamente");
        carritoAnadirView.setCarrito(new Carrito(carritoAnadirView.getUsuario()));
        carritoAnadirView.limpiarCampos();
    }

    private void listarCarritos() {

        List<Carrito> carritos = carritoDAO.listarTodos();
        DefaultTableModel modelo = carritoListaView.getModelo();
        modelo.setRowCount(0);

        for (Carrito carro : carritos) {
            String nombreUsuario = "Desconocido";
            if (carro.getUsuario() != null)
                nombreUsuario = carro.getUsuario().getUsername();

            if(carro.getUsuario().getRol()== Rol.ADMINISTRADOR){
                modelo.addRow(new Object[]{
                        carro.getCodigo(),
                        carro.getFechaCreacionConFormato(),
                        nombreUsuario,
                        carro.calcularTotal()
                });
            }else{
                if (carro.getUsuario().getUsername().equals(usuario.getUsername())) {
                    modelo.addRow(new Object[]{
                            carro.getCodigo(),
                            carro.getFechaCreacionConFormato(),
                            nombreUsuario,
                            carro.calcularTotal()
                    });
                }
            }
        }
    }



    private void anadirProducto() {

        int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        int cantidad =  Integer.parseInt(carritoAnadirView.getCbxCantidad().getSelectedItem().toString());
        carritoAnadirView.getCarrito().agregarProducto(producto, cantidad);

        cargarProductos();
        mostrarTotales();

    }

    private void cargarProductos(){

        List<ItemCarrito> items = carritoAnadirView.getCarrito().obtenerItems();
        DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        modelo.setNumRows(0);
        for (ItemCarrito item : items) {
            modelo.addRow(new Object[]{ item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    item.getProducto().getPrecio(),
                    item.getCantidad(),
                    item.getProducto().getPrecio() * item.getCantidad() });
        }
    }

    private void mostrarTotales(){
        String subtotal = String.valueOf(carritoAnadirView.getCarrito().calcularSubtotal());
        String iva = String.valueOf(carritoAnadirView.getCarrito().calcularIVA());
        String total = String.valueOf(carritoAnadirView.getCarrito().calcularTotal());

        carritoAnadirView.getTxtSubtotal().setText(subtotal);
        carritoAnadirView.getTxtIva().setText(iva);
        carritoAnadirView.getTxtTotal().setText(total);
    }

    private void buscarCarrito() {
        String codigo = carritoAnadirView.getTxtCodigo().getText();
        Carrito carrito;
        carrito = carritoDAO.buscarPorCodigo(Integer.parseInt(codigo));
        List<Carrito> carritos = carritoDAO.listarTodos();

        DefaultTableModel modelo = (DefaultTableModel) carritoListaView.getTblProductos().getModel();
        modelo.setNumRows(0);
        for (Carrito carro : carritos) {
            modelo.addRow(new Object[]{
                    carro.getCodigo(),
                    carro.getFechaCreacionConFormato(),
                    carro.getUsuario().getUsername(),
                    carro.calcularTotal()
            });
        }
    }



}
