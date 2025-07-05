package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.*;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;


public class CarritoController {

    private final CarritoDAO carritoDAO;
    private final ProductoDAO productoDAO;
    private final CarritoAnadirView carritoAnadirView;
    private final CarritoEliminarView carritoEliminarView;
    private final CarritoActualizarView carritoActualizarView;
    private final CarritoListaView carritoListaView;
    private CarritoDetalleView carritoDetalleView;
    private Locale locale;

    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

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
                             MenuPrincipalView principalView,
                             MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler) {
        this.usuario = usuario;
        this.carritoDAO = carritoDAO;
        this.productoDAO = productoDAO;
        this.carritoAnadirView = carritoAnadirView;
        this.carritoListaView = carritoListaView;
        this.carritoEliminarView = carritoEliminarView;
        this.carritoActualizarView = carritoActualizarView;
        this.carritoDetalleView = carritoDetalleView;
        this.principalView = principalView;
        this.mensajeInternacionalizacionHandler = mensajeInternacionalizacionHandler;

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
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE) {
                    abrirDetalle();
                }
            }
        });

        /**
         * ╔════════════════════════════════════╗
         * ║         ❌ ELIMINAR                ║
         * ╚════════════════════════════════════╝
         */
        carritoEliminarView.getBtBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEliminarCarrito();
            }
        });

        carritoEliminarView.getBtEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCarrito();
            }
        });

        /**
         * ╔════════════════════════════════════╗
         * ║         📝 ACTUALIZAR              ║
         * ╚════════════════════════════════════╝
         */
        carritoActualizarView.getPtActualizar().setVisible(false);
        carritoActualizarView.getPtActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCarrito();
            }
        });
        carritoActualizarView.getBtBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarActualizarCarrito();
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

        carritoAnadirView.mostrarMensaje(mensajes(0), titulosMensajes(0), "info");
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
                            FormateadorUtils.formatearMoneda(carro.calcularTotal(), locale)
                    });
                }
            }
        }
    }

    private void anadirProducto() {

        int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        int cantidad = Integer.parseInt(carritoAnadirView.getCbxCantidad().getSelectedItem().toString());
        carritoAnadirView.getCarrito().agregarProducto(producto, cantidad);

        cargarProductos();
        mostrarTotales();

    }

    private void cargarProductos() {

        List<ItemCarrito> items = carritoAnadirView.getCarrito().obtenerItems();
        DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        modelo.setNumRows(0);
        for (ItemCarrito item : items) {
            modelo.addRow(new Object[]{item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    item.getProducto().getPrecio(),
                    item.getCantidad(),
                    item.getProducto().getPrecio() * item.getCantidad()});
        }
    }

    private void mostrarTotales() {
        String subtotal = FormateadorUtils.formatearMoneda(carritoAnadirView.getCarrito().calcularSubtotal(), locale);
        String iva = FormateadorUtils.formatearMoneda(carritoAnadirView.getCarrito().calcularIVA(), locale);
        String total = FormateadorUtils.formatearMoneda(carritoAnadirView.getCarrito().calcularTotal(), locale);


        carritoAnadirView.getTxtSubtotal().setText(subtotal);
        carritoAnadirView.getTxtIva().setText(iva);
        carritoAnadirView.getTxtTotal().setText(total);
    }

    private void buscarCarrito() {
        String codigoTexto = carritoListaView.getTxtBuscar().getText();

        if (codigoTexto.isEmpty()) {
            carritoListaView.mostrarMensaje(mensajes(1), titulosMensajes(1), "error");
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
                    carritoListaView.mostrarMensaje(mensajes(2), titulosMensajes(2), "error");
                }
            } else {
                carritoListaView.mostrarMensaje(mensajes(3), titulosMensajes(3), "error");
            }

        } catch (NumberFormatException e) {
            carritoListaView.mostrarMensaje(mensajes(4), titulosMensajes(4), "error");
        }
    }

    private void abrirDetalle() {

        int fila = carritoListaView.getTblProductos().getSelectedRow();

        if (fila != -1) {
            int codigo = (int) carritoListaView.getTblProductos().getValueAt(fila, 0);
            if (carritoDetalleView.isClosed()) {
                carritoDetalleView = new CarritoDetalleView();
                principalView.getjDesktopPane().add(carritoDetalleView);
            }
            mostrarDetalleCarrito(codigo);
        }
    }

    private void mostrarDetalleCarrito(int codigo) {
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

    private void buscarEliminarCarrito() {
        String codigoTexto = carritoEliminarView.getTxtCodigo().getText();
        DefaultTableModel modelo = (DefaultTableModel) carritoEliminarView.getTblCarritos().getModel();
        modelo.setRowCount(0);

        try {
            int codigo = Integer.parseInt(codigoTexto);
            Carrito carrito = carritoDAO.buscarPorCodigo(codigo);

            if (carrito != null &&
                    carrito.getUsuario() != null &&
                    (usuario.getRol().equals(Rol.ADMINISTRADOR) || carrito.getUsuario().getUsername().equals(usuario.getUsername()))) {

                modelo.addRow(new Object[]{
                        carrito.getCodigo(),
                        carrito.getFechaCreacionConFormato(),
                        carrito.getUsuario().getUsername(),
                        carrito.calcularTotal()
                });

            } else {
                carritoEliminarView.mostrarMensaje(mensajes(5), titulosMensajes(5), "warning");
            }

        } catch (NumberFormatException ex) {
            carritoEliminarView.mostrarMensaje(mensajes(6), titulosMensajes(6), "error");
        }
    }

    private void eliminarCarrito() {
        int fila = carritoEliminarView.getTblCarritos().getSelectedRow();
        if (fila == -1) {
            carritoEliminarView.mostrarMensaje(mensajes(7), titulosMensajes(7), "warning");
            return;
        }

        int codigo = (int) carritoEliminarView.getTblCarritos().getValueAt(fila, 0);
        Carrito carrito = carritoDAO.buscarPorCodigo(codigo);

        if (carrito != null &&
                (usuario.getRol().equals(Rol.ADMINISTRADOR) || carrito.getUsuario().getUsername().equals(usuario.getUsername()))) {

            int confirmacion = JOptionPane.showOptionDialog(
                    null,
                    mensajes(8),
                    titulosMensajes(8),
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    new Object[]{mensajes(11), mensajes(12)},
                    null
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                carritoDAO.eliminar(codigo);
                carritoEliminarView.mostrarMensaje(mensajes(9), titulosMensajes(9), "info");
                ((DefaultTableModel) carritoEliminarView.getTblCarritos().getModel()).removeRow(fila);
            }

        } else {
            carritoEliminarView.mostrarMensaje(mensajes(10), titulosMensajes(10), "error");
        }
    }

    private void actualizarCarrito() {
        String codigoTexto = carritoActualizarView.getTxtCodigo().getText();

        try {
            int codigo = Integer.parseInt(codigoTexto);
            Carrito carrito = carritoDAO.buscarPorCodigo(codigo);

            if (carrito == null) {
                carritoActualizarView.mostrarMensaje(mensajes(29), titulosMensajes(11), "error");
                return;
            }

            if (!usuario.getRol().equals(Rol.ADMINISTRADOR) &&
                    !carrito.getUsuario().getUsername().equals(usuario.getUsername())) {
                carritoActualizarView.mostrarMensaje(mensajes(30), titulosMensajes(12), "warning");
                return;
            }

            String[] opciones = {
                    mensajes(33), mensajes(34), mensajes(35)
            };

            String seleccion = (String) JOptionPane.showInputDialog(
                    carritoActualizarView,
                    mensajes(31),
                    mensajes(32),
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            if (seleccion == null) return;

            String eliminar = mensajes(33);
            String agregar = mensajes(34);
            String cambiar = mensajes(35);

            if (seleccion.equals(eliminar)) {
                String codProdEliminar = JOptionPane.showInputDialog(mensajes(36));
                carrito.eliminarProducto(Integer.parseInt(codProdEliminar));

            } else if (seleccion.equals(agregar)) {
                String codNuevo = JOptionPane.showInputDialog(mensajes(37));
                String cantNuevo = JOptionPane.showInputDialog(mensajes(38));
                Producto prod = productoDAO.buscarPorCodigo(Integer.parseInt(codNuevo));
                if (prod != null) {
                    carrito.agregarProducto(prod, Integer.parseInt(cantNuevo));
                } else {
                    carritoActualizarView.mostrarMensaje(mensajes(39), titulosMensajes(11), "error");
                }

            } else if (seleccion.equals(cambiar)) {
                String codEditar = JOptionPane.showInputDialog(mensajes(40));
                String nuevaCantidad = JOptionPane.showInputDialog(mensajes(41));
                for (ItemCarrito item : carrito.obtenerItems()) {
                    if (item.getProducto().getCodigo() == Integer.parseInt(codEditar)) {
                        item.setCantidad(Integer.parseInt(nuevaCantidad));
                        break;
                    }
                }
            }

            carritoActualizarView.mostrarMensaje(mensajes(42), titulosMensajes(13), "info");
            buscarActualizarCarrito();

        } catch (NumberFormatException ex) {
            carritoActualizarView.mostrarMensaje(mensajes(43), titulosMensajes(11), "error");
        }
    }


    private void buscarActualizarCarrito() {
        String codigoTexto = carritoActualizarView.getTxtCodigo().getText();

        try {
            int codigo = Integer.parseInt(codigoTexto);
            Carrito carrito = carritoDAO.buscarPorCodigo(codigo);


            if (carrito == null) {
                carritoActualizarView.mostrarMensaje("Carrito no encontrado", "Error", "error");
                carritoActualizarView.limpiarCampos();
                return;
            }

            if (!usuario.getRol().equals(Rol.ADMINISTRADOR) &&
                    !carrito.getUsuario().getUsername().equals(usuario.getUsername())) {
                carritoActualizarView.mostrarMensaje("No tiene permiso para acceder a este carrito", "Acceso denegado", "warning");
                carritoActualizarView.limpiarCampos();
                return;
            }

            carritoActualizarView.getPtActualizar().setVisible(true);
            carritoActualizarView.getTxtFecha().setText(carrito.getFechaCreacionConFormato());
            carritoActualizarView.getTxtUsuario().setText(carrito.getUsuario().getUsername());
            carritoActualizarView.getTxtIva().setText(String.valueOf(carrito.calcularIVA()));
            carritoActualizarView.getTxtSubtotal().setText(String.valueOf(carrito.calcularSubtotal()));
            carritoActualizarView.getTxtTotal().setText(String.valueOf(carrito.calcularTotal()));

            DefaultTableModel modelo = carritoActualizarView.getModelo();
            modelo.setRowCount(0);

            for (ItemCarrito item : carrito.obtenerItems()) {
                modelo.addRow(new Object[]{
                        item.getProducto().getCodigo(),
                        item.getProducto().getNombre(),
                        item.getProducto().getPrecio(),
                        item.getCantidad(),
                        item.getSubtotal()
                });
            }

            carritoActualizarView.getTxtSubtotal().setText(String.format("%.2f", carrito.calcularSubtotal()));


        } catch (NumberFormatException ex) {
            carritoActualizarView.mostrarMensaje("Código inválido. Ingrese un número", "Error", "error");
            carritoActualizarView.limpiarCampos();
        }
    }

    public void cambiarIdioma(String lenguaje, String pais) {

        /**
         * ╔════════════════════════════════════╗
         * ║          ➕ CREAR                  ║
         * ╚════════════════════════════════════╝
         */
        carritoAnadirView.setTitle(mensajeInternacionalizacionHandler.get("carrito.crear"));
        carritoAnadirView.getLbTitulo().setText(mensajeInternacionalizacionHandler.get("carrito.crear") + " 🛒");

        carritoAnadirView.getLbProducto().setText(mensajeInternacionalizacionHandler.get("producto"));
        carritoAnadirView.getLbCodigo().setText(mensajeInternacionalizacionHandler.get("codigo"));
        carritoAnadirView.getLbNombre().setText(mensajeInternacionalizacionHandler.get("nombre"));
        carritoAnadirView.getLbPrecio().setText(mensajeInternacionalizacionHandler.get("precio"));
        carritoAnadirView.getLbCantidad().setText(mensajeInternacionalizacionHandler.get("cantidad"));
        carritoAnadirView.getLbSubtotal().setText(mensajeInternacionalizacionHandler.get("subtotal"));
        carritoAnadirView.getLbIva().setText(mensajeInternacionalizacionHandler.get("iva"));
        carritoAnadirView.getLbTotal().setText(mensajeInternacionalizacionHandler.get("total"));

        carritoAnadirView.getBtnGuardar().setText(mensajeInternacionalizacionHandler.get("aceptar"));
        carritoAnadirView.getBtnAnadir().setText(mensajeInternacionalizacionHandler.get("anadir"));

        carritoAnadirView.getCbxCantidad().setToolTipText(mensajeInternacionalizacionHandler.get("cantidad"));
        Object[] columnasAnadir = {mensajeInternacionalizacionHandler.get("nombre"), mensajeInternacionalizacionHandler.get("precio"), mensajeInternacionalizacionHandler.get("cantidad"), mensajeInternacionalizacionHandler.get("subtotal")};
        carritoAnadirView.getModelo().setColumnIdentifiers(columnasAnadir);
        /**
         * ╔════════════════════════════════════╗
         * ║         ❌ ELIMINAR                ║
         * ╚════════════════════════════════════╝
         */
        carritoEliminarView.setTitle(mensajeInternacionalizacionHandler.get("carrito.eliminar"));
        carritoEliminarView.getLbTitulo().setText(mensajeInternacionalizacionHandler.get("carrito.eliminar") + " 🗑️");
        carritoEliminarView.getLbCodigo().setText(mensajeInternacionalizacionHandler.get("codigo"));
        carritoEliminarView.getBtBuscar().setText(mensajeInternacionalizacionHandler.get("buscar"));
        carritoEliminarView.getBtEliminar().setText(mensajeInternacionalizacionHandler.get("eliminar"));
        Object[] columnasEliminar = {mensajeInternacionalizacionHandler.get("codigo"), mensajeInternacionalizacionHandler.get("fecha"), mensajeInternacionalizacionHandler.get("usuario"), mensajeInternacionalizacionHandler.get("total")};
        carritoEliminarView.getModelo().setColumnIdentifiers(columnasEliminar);
        /**
         * ╔════════════════════════════════════╗
         * ║         📝 ACTUALIZAR              ║
         * ╚════════════════════════════════════╝
         */
        carritoActualizarView.setTitle(mensajeInternacionalizacionHandler.get("carrito.actualizar"));
        carritoActualizarView.getLbTitulo().setText(mensajeInternacionalizacionHandler.get("carrito.actualizar")+" 🔃 ");
        carritoActualizarView.getLbCodigo().setText(mensajeInternacionalizacionHandler.get("codigo"));
        carritoActualizarView.getLbSubTotal().setText(mensajeInternacionalizacionHandler.get("subtotal"));
        carritoActualizarView.getLbTotal().setText(mensajeInternacionalizacionHandler.get("total"));
        carritoActualizarView.getLbIVA().setText(mensajeInternacionalizacionHandler.get("iva"));
        carritoActualizarView.getLbFecha().setText(mensajeInternacionalizacionHandler.get("fecha"));
        carritoActualizarView.getLbUsuario().setText(mensajeInternacionalizacionHandler.get("usuario"));
        carritoActualizarView.getBtActualizar().setText(mensajeInternacionalizacionHandler.get("actualizar"));
        Object[] columnasActualizar = {mensajeInternacionalizacionHandler.get("codigo"),mensajeInternacionalizacionHandler.get("nombre"), mensajeInternacionalizacionHandler.get("precio"),mensajeInternacionalizacionHandler.get("cantidad"),mensajeInternacionalizacionHandler.get("subtotal")};
        carritoActualizarView.getModelo().setColumnIdentifiers(columnasActualizar);


        /**
         * ╔════════════════════════════════════╗
         * ║         🔍 LISTAR                  ║
         * ╚════════════════════════════════════╝
         */


        /**
         * ╔════════════════════════════════════╗
         * ║         📃 DETALLE                 ║
         * ╚════════════════════════════════════╝
         */

    }

    private String mensajes(int cod) {
        return switch (cod) {
            case 0 -> mensajeInternacionalizacionHandler.get("mensaje.16");
            case 1 -> mensajeInternacionalizacionHandler.get("mensaje.17");
            case 2 -> mensajeInternacionalizacionHandler.get("mensaje.18");
            case 3 -> mensajeInternacionalizacionHandler.get("mensaje.19");
            case 4 -> mensajeInternacionalizacionHandler.get("mensaje.20");
            case 5 -> mensajeInternacionalizacionHandler.get("mensaje.21");
            case 6 -> mensajeInternacionalizacionHandler.get("mensaje.22");
            case 7 -> mensajeInternacionalizacionHandler.get("mensaje.23");
            case 8 -> mensajeInternacionalizacionHandler.get("mensaje.24");
            case 9 -> mensajeInternacionalizacionHandler.get("mensaje.25");
            case 10 -> mensajeInternacionalizacionHandler.get("mensaje.26");
            case 11 -> mensajeInternacionalizacionHandler.get("mensaje.27");
            case 12 -> mensajeInternacionalizacionHandler.get("mensaje.28");
            case 29 -> mensajeInternacionalizacionHandler.get("mensaje.29");
            case 30 -> mensajeInternacionalizacionHandler.get("mensaje.30");
            case 31 -> mensajeInternacionalizacionHandler.get("mensaje.31");
            case 32 -> mensajeInternacionalizacionHandler.get("mensaje.32");
            case 33 -> mensajeInternacionalizacionHandler.get("mensaje.33");
            case 34 -> mensajeInternacionalizacionHandler.get("mensaje.34");
            case 35 -> mensajeInternacionalizacionHandler.get("mensaje.35");
            case 36 -> mensajeInternacionalizacionHandler.get("mensaje.36");
            case 37 -> mensajeInternacionalizacionHandler.get("mensaje.37");
            case 38 -> mensajeInternacionalizacionHandler.get("mensaje.38");
            case 39 -> mensajeInternacionalizacionHandler.get("mensaje.39");
            case 40 -> mensajeInternacionalizacionHandler.get("mensaje.40");
            case 41 -> mensajeInternacionalizacionHandler.get("mensaje.41");
            case 42 -> mensajeInternacionalizacionHandler.get("mensaje.42");
            case 43 -> mensajeInternacionalizacionHandler.get("mensaje.43");

            default -> "";
        };
    }
    private String titulosMensajes(int cod) {
        return switch (cod) {
            case 0 -> mensajeInternacionalizacionHandler.get("titulo.16");
            case 1, 2, 3, 4 -> mensajeInternacionalizacionHandler.get("titulo.17");
            case 5 -> mensajeInternacionalizacionHandler.get("titulo.18");
            case 6 -> mensajeInternacionalizacionHandler.get("titulo.19");
            case 7 -> mensajeInternacionalizacionHandler.get("titulo.20");
            case 8 -> mensajeInternacionalizacionHandler.get("titulo.21");
            case 9 -> mensajeInternacionalizacionHandler.get("titulo.22");
            case 10 -> mensajeInternacionalizacionHandler.get("titulo.23");
            case 11 -> mensajeInternacionalizacionHandler.get("titulo.24");
            case 12 -> mensajeInternacionalizacionHandler.get("titulo.25");
            case 13 -> mensajeInternacionalizacionHandler.get("titulo.26");

            default -> "";
        };
    }

}
