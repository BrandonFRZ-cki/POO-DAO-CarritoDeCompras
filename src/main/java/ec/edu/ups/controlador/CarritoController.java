package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.*;
import ec.edu.ups.vista.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
    private CarritoDetalleView carritoDetalleView;

    public Usuario usuario;

    private final MenuPrincipalView principalView;

    public CarritoController(Usuario usuario,
                             CarritoDAO carritoDAO,
                             ProductoDAO productoDAO,
                             CarritoAnadirView carritoAnadirView,
                             CarritoListaView carritoListaView,
                             CarritoEliminarView carritoEliminarView,
                             CarritoActualizarView carritoActualizarView,
                             CarritoDetalleView carritoDetalleView,
                             MenuPrincipalView principalView) {
        this.usuario = usuario;
        this.carritoDAO = carritoDAO;
        this.productoDAO = productoDAO;
        this.carritoAnadirView = carritoAnadirView;
        this.carritoListaView = carritoListaView;
        this.carritoEliminarView = carritoEliminarView;
        this.carritoActualizarView = carritoActualizarView;
        this.carritoDetalleView = carritoDetalleView;
        this.principalView = principalView;
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
        carritoListaView.getTblProductos().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE) {
                    int fila = carritoListaView.getTblProductos().getSelectedRow();

                    if (fila != -1) {
                        int codigo = (int) carritoListaView.getTblProductos().getValueAt(fila, 0);

                        // Si ya se cerró antes, creamos una nueva instancia
                        if (carritoDetalleView.isClosed()) {
                            carritoDetalleView = new CarritoDetalleView(); // <-- Necesitarás que sea atributo no-final
                            principalView.getjDesktopPane().add(carritoDetalleView);
                        }

                        mostrarDetalleCarrito(codigo);
                        e.consume();
                    }
                }
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
            if (carro.getUsuario() != null) {
                String nombreUsuario = carro.getUsuario().getUsername();

                if (usuario.getRol() == Rol.ADMINISTRADOR ||
                        carro.getUsuario().getUsername().equals(usuario.getUsername())) {

                    modelo.addRow(new Object[]{
                            carro.getCodigo(),
                            carro.getFechaCreacionConFormato(),
                            nombreUsuario,
                            carro.calcularTotal(),
                            "Detalle"
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
        String codigoTexto = carritoListaView.getTxtBuscar().getText(); // ← usa tu campo de texto de búsqueda
        if (codigoTexto.isEmpty()) {
            carritoListaView.mostrarMensaje("Ingrese un código para buscar","Error al buscar","error");
            return;
        }

        try {
            int codigo = Integer.parseInt(codigoTexto);
            Carrito carrito = carritoDAO.buscarPorCodigo(codigo);
            DefaultTableModel modelo = carritoListaView.getModelo();
            modelo.setRowCount(0);

            if (carrito != null) {
                if (carrito.getUsuario() != null &&
                        (usuario.getRol().equals(Rol.ADMINISTRADOR) ||
                                carrito.getUsuario().getUsername().equals(usuario.getUsername()))) {

                    modelo.addRow(new Object[]{
                            carrito.getCodigo(),
                            carrito.getFechaCreacionConFormato(),
                            carrito.getUsuario().getUsername(),
                            carrito.calcularTotal()
                    });

                } else {
                    carritoListaView.mostrarMensaje("No tiene permiso para ver este carrito.","Error al buscar","error");
                }
            } else {
                carritoListaView.mostrarMensaje("No se encontró un carrito con ese código.","Error al buscar","error");
            }

        } catch (NumberFormatException e) {
            carritoListaView.mostrarMensaje("Código inválido","Error al buscar","error");
        }
    }
    public void mostrarDetalleCarrito(int codigo) {
        Carrito carrito = carritoDAO.buscarPorCodigo(codigo);
        if (carrito == null) return;

        DefaultTableModel modelo = carritoDetalleView.getModelo();
        modelo.setRowCount(0);

        carritoDetalleView.getTxtCodigo().setText(String.valueOf(carrito.getCodigo()));
        carritoDetalleView.getTxtSubtotal().setText(String.format("%.2f", carrito.calcularSubtotal()));
        carritoDetalleView.getTxtIva().setText(String.format("%.2f", carrito.calcularIVA()));
        carritoDetalleView.getTxtTotal().setText(String.format("%.2f", carrito.calcularTotal()));

        for (ItemCarrito item : carrito.obtenerItems()) {
            modelo.addRow(new Object[]{
                    item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    item.getProducto().getPrecio(),
                    item.getCantidad(),
                    item.getSubtotal()
            });
        }

        if (!carritoDetalleView.isVisible()) {
            carritoDetalleView.setVisible(true);
        } else {
            carritoDetalleView.toFront();
        }
    }

}
