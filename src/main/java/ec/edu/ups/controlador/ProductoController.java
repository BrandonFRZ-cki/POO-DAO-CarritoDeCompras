package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController {

    private final ProductoAnadirView productoAnadirView;
    private final ProductoListaView productoListaView;
    private final ProductoActualizarView productoActualizarView;
    private final CarritoAnadirView carritoAnadirView;
    private final ProductoEliminarView productoEliminarView;

    private final ProductoDAO productoDAO;

    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    public ProductoController(ProductoDAO productoDAO,
                              ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView,
                              ProductoActualizarView productoActualizarView,
                              CarritoAnadirView carritoAnadirView,
                              ProductoEliminarView productoEliminarView,
                              MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler) {

        this.productoDAO = productoDAO;
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.productoEliminarView=productoEliminarView;
        this.carritoAnadirView = carritoAnadirView;
        this.productoActualizarView = productoActualizarView;
        this.mensajeInternacionalizacionHandler = mensajeInternacionalizacionHandler;
        this.configurarEventosEnVistas();

    }

    private void configurarEventosEnVistas() {


        productoActualizarView.getBtBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoParaActualizar();
            }
        });
        productoActualizarView.getPtActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar();
            }
        });

        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });

        productoListaView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });

        productoListaView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProductos();
            }
        });


        productoEliminarView.getBtBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoParaEliminar();
            }
        });
        productoEliminarView.getBtLimpiar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productoEliminarView.limpiarCampos();
            }
        });
        productoEliminarView.getBtEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        carritoAnadirView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoPorCodigo();
            }
        });

    }

    private void guardarProducto() {
        String codTexto = productoAnadirView.getTxtCodigo().getText();
        String nombre = productoAnadirView.getTxtNombre().getText();
        String precioTexto = productoAnadirView.getTxtPrecio().getText();

        if (codTexto.isEmpty() || nombre.isEmpty() || precioTexto.isEmpty()) {
            productoAnadirView.mostrarMensaje(mensajes(0), titulosMensajes(0), "warning");
            return;
        }

        try {
            int codigo = Integer.parseInt(codTexto);
            double precio = Double.parseDouble(precioTexto);

            if (productoDAO.buscarPorCodigo(codigo) != null) {
                productoAnadirView.mostrarMensaje(mensajes(1), titulosMensajes(1), "error");
                return;
            }

            productoDAO.crear(new Producto(codigo, nombre, precio));
            productoAnadirView.mostrarMensaje(mensajes(2), titulosMensajes(2), "info");
            productoAnadirView.limpiarCampos();
            productoAnadirView.mostrarProductos(productoDAO.listarTodos());

        } catch (NumberFormatException e) {
            productoAnadirView.mostrarMensaje(mensajes(3), titulosMensajes(3), "error");
        }
    }
    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();

        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productosEncontrados);
    }
    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }
    private void buscarProductoPorCodigo() {
        try {
            int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
            Producto producto = productoDAO.buscarPorCodigo(codigo);

            if (producto == null) {
                carritoAnadirView.mostrarMensaje(mensajes(4), titulosMensajes(4), "warning");
                carritoAnadirView.getTxtNombre().setText("");
                carritoAnadirView.getTxtPrecio().setText("");
            } else {
                carritoAnadirView.getTxtNombre().setText(producto.getNombre());
                carritoAnadirView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
            }
        } catch (NumberFormatException e) {
            carritoAnadirView.mostrarMensaje(mensajes(5), titulosMensajes(5), "error");
        }
    }
    private boolean buscarProductoParaEliminar() {
        try {
            int codigo = Integer.parseInt(productoEliminarView.getTxtCodigo().getText());
            Producto producto = productoDAO.buscarPorCodigo(codigo);

            if (producto == null) {
                productoEliminarView.getTxtNombre().setText(" - ");
                productoEliminarView.getTxtPrecio().setText(" - ");
                return false;
            } else {
                productoEliminarView.getTxtNombre().setText(producto.getNombre());
                productoEliminarView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
                return true;
            }

        } catch (NumberFormatException e) {
            productoEliminarView.mostrarMensaje(mensajes(6), titulosMensajes(6), "error");
            return false;
        }
    }
    private void eliminarProducto() {
        if (!buscarProductoParaEliminar()) {
            productoEliminarView.mostrarMensaje(mensajes(7), titulosMensajes(7), "warning");
            return;
        }

        try {
            int codigo = Integer.parseInt(productoEliminarView.getTxtCodigo().getText());
            int seguroMensaje = JOptionPane.showOptionDialog(
                    null, "¿Seguro que deseas eliminar este producto?", "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, new Object[]{"Sí", "No"}, null);

            if (seguroMensaje == JOptionPane.YES_OPTION) {
                productoDAO.eliminar(codigo);
                productoEliminarView.mostrarMensaje(mensajes(9), titulosMensajes(9), "info");
                productoEliminarView.limpiarCampos();
            }

        } catch (NumberFormatException e) {
            productoEliminarView.mostrarMensaje(mensajes(10), titulosMensajes(10), "error");
        }
    }
    private void buscarProductoParaActualizar() {
        try {
            int codigo = Integer.parseInt(productoActualizarView.getTxtCodigo().getText());
            Producto producto = productoDAO.buscarPorCodigo(codigo);

            if (producto != null) {
                productoActualizarView.getTxtNombre().setText(producto.getNombre());
                productoActualizarView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));

                productoActualizarView.getTxtCodigo().setEditable(false);
                productoActualizarView.getTxtNuevoNombre().setEditable(true);
                productoActualizarView.getTxtNuevoPrecio().setEditable(true);
            } else {
                productoActualizarView.getTxtCodigo().setText(" - ");
                productoActualizarView.getTxtNombre().setText(" - ");
                productoActualizarView.getTxtPrecio().setText(" - ");
                productoActualizarView.mostrarMensaje(mensajes(11), titulosMensajes(11), "warning");
            }

        } catch (NumberFormatException e) {
            productoActualizarView.mostrarMensaje(mensajes(12), titulosMensajes(12), "error");
        }
    }
    private void actualizar() {
        String nuevoNombre = productoActualizarView.getTxtNuevoNombre().getText();
        String nuevoPrecioTexto = productoActualizarView.getTxtNuevoPrecio().getText();

        if (nuevoNombre.isEmpty() || nuevoPrecioTexto.isEmpty()) {
            productoActualizarView.mostrarMensaje(mensajes(13), titulosMensajes(13), "warning");
            return;
        }

        try {
            int codigo = Integer.parseInt(productoActualizarView.getTxtCodigo().getText());
            double nuevoPrecio = Double.parseDouble(nuevoPrecioTexto);

            Producto producto = productoDAO.buscarPorCodigo(codigo);
            producto.setNombre(nuevoNombre);
            producto.setPrecio(nuevoPrecio);

            productoActualizarView.getTxtCodigo().setEditable(true);
            productoActualizarView.getTxtCodigo().setText(" - ");
            productoActualizarView.getTxtNombre().setText(" - ");
            productoActualizarView.getTxtPrecio().setText(" - ");
            productoActualizarView.getTxtNuevoNombre().setText("");
            productoActualizarView.getTxtNuevoPrecio().setText("");
            productoActualizarView.mostrarMensaje(mensajes(14), titulosMensajes(14), "info");

        } catch (NumberFormatException e) {
            productoActualizarView.mostrarMensaje(mensajes(15), titulosMensajes(15), "error");
        }
    }

    public void cambiarIdioma(String lenguaje, String pais) {

        /**
         * ╔════════════════════════════════════╗x
         * ║          ➕ CREAR                  ║
         * ╚════════════════════════════════════╝
         */
        productoAnadirView.setTitle(mensajeInternacionalizacionHandler.get("producto.crear"));
        productoAnadirView.getLbTitulo().setText(mensajeInternacionalizacionHandler.get("producto.crear")+ " ➕");
        productoAnadirView.getLbCodigo().setText(mensajeInternacionalizacionHandler.get("codigo"));
        productoAnadirView.getLbNombre().setText(mensajeInternacionalizacionHandler.get("nombre"));
        productoAnadirView.getLbPrecio().setText(mensajeInternacionalizacionHandler.get("precio"));
        productoAnadirView.getBtnAceptar().setText(mensajeInternacionalizacionHandler.get("aceptar"));
        productoAnadirView.getBtnLimpiar().setText(mensajeInternacionalizacionHandler.get("limpiar"));
        /**
         * ╔════════════════════════════════════╗
         * ║         ❌ ELIMINAR                ║
         * ╚════════════════════════════════════╝
         */
        productoEliminarView.setTitle(mensajeInternacionalizacionHandler.get("producto.eliminar"));
        productoEliminarView.getLbTitulo().setText(mensajeInternacionalizacionHandler.get("producto.eliminar")+" 🗑️");
        productoEliminarView.getLbCodigo().setText(mensajeInternacionalizacionHandler.get("codigo"));
        productoEliminarView.getBtEliminar().setText(mensajeInternacionalizacionHandler.get("eliminar"));
        productoEliminarView.getBtLimpiar().setText(mensajeInternacionalizacionHandler.get("limpiar"));
        /**
         * ╔════════════════════════════════════╗
         * ║         📝 ACTUALIZAR              ║
         * ╚════════════════════════════════════╝
         */
        productoActualizarView.setTitle(mensajeInternacionalizacionHandler.get("producto.actualizar"));
        productoActualizarView.getLbTitulo().setText(mensajeInternacionalizacionHandler.get("producto.actualizar")+" 🔃");
        productoActualizarView.getLbCodigo().setText(mensajeInternacionalizacionHandler.get("codigo"));
        productoActualizarView.getLbNombre().setText(mensajeInternacionalizacionHandler.get("nombre"));
        productoActualizarView.getLbPrecio().setText(mensajeInternacionalizacionHandler.get("precio"));
        productoActualizarView.getLbNuevo().setText(mensajeInternacionalizacionHandler.get("nuevo"));
        productoActualizarView.getLbOriginal().setText(mensajeInternacionalizacionHandler.get("original"));
        productoActualizarView.getPtActualizar().setText(mensajeInternacionalizacionHandler.get("actualizar"));
        /**
         * ╔════════════════════════════════════╗
         * ║         🔍 LISTAR                  ║
         * ╚════════════════════════════════════╝
         */
        productoListaView.getLbNombre().setText(mensajeInternacionalizacionHandler.get("nombre"));
        productoListaView.getLbTitulo().setText(mensajeInternacionalizacionHandler.get("producto.buscar")+" 📁");
        Object[] columnas = {mensajeInternacionalizacionHandler.get("codigo"), mensajeInternacionalizacionHandler.get("nombre"), mensajeInternacionalizacionHandler.get("precio")};
        productoListaView.getModelo().setColumnIdentifiers(columnas);

    }
    private String mensajes(int cod) {
        return switch (cod) {
            case 0 -> mensajeInternacionalizacionHandler.get("mensaje.0");
            case 1 -> mensajeInternacionalizacionHandler.get("mensaje.1");
            case 2 -> mensajeInternacionalizacionHandler.get("mensaje.2");
            case 3 -> mensajeInternacionalizacionHandler.get("mensaje.3");
            case 4 -> mensajeInternacionalizacionHandler.get("mensaje.4");
            case 5 -> mensajeInternacionalizacionHandler.get("mensaje.5");
            case 6 -> mensajeInternacionalizacionHandler.get("mensaje.6");
            case 7 -> mensajeInternacionalizacionHandler.get("mensaje.7");
            case 8 -> mensajeInternacionalizacionHandler.get("mensaje.8");
            case 9 -> mensajeInternacionalizacionHandler.get("mensaje.9");
            case 10 -> mensajeInternacionalizacionHandler.get("mensaje.10");
            case 11 -> mensajeInternacionalizacionHandler.get("mensaje.11");
            case 12 -> mensajeInternacionalizacionHandler.get("mensaje.12");
            case 13 -> mensajeInternacionalizacionHandler.get("mensaje.13");
            case 14 -> mensajeInternacionalizacionHandler.get("mensaje.14");
            case 15 -> mensajeInternacionalizacionHandler.get("mensaje.15");
            default -> "";
        };
    }

    private String titulosMensajes(int cod) {
        return switch (cod) {
            case 0 -> mensajeInternacionalizacionHandler.get("titulo.0");
            case 1 -> mensajeInternacionalizacionHandler.get("titulo.1");
            case 2 -> mensajeInternacionalizacionHandler.get("titulo.2");
            case 3 -> mensajeInternacionalizacionHandler.get("titulo.3");
            case 4 -> mensajeInternacionalizacionHandler.get("titulo.4");
            case 5 -> mensajeInternacionalizacionHandler.get("titulo.5");
            case 6 -> mensajeInternacionalizacionHandler.get("titulo.6");
            case 7 -> mensajeInternacionalizacionHandler.get("titulo.7");
            case 8 -> mensajeInternacionalizacionHandler.get("titulo.8");
            case 9 -> mensajeInternacionalizacionHandler.get("titulo.9");
            case 10 -> mensajeInternacionalizacionHandler.get("titulo.10");
            case 11 -> mensajeInternacionalizacionHandler.get("titulo.11");
            case 12 -> mensajeInternacionalizacionHandler.get("titulo.12");
            case 13 -> mensajeInternacionalizacionHandler.get("titulo.13");
            case 14 -> mensajeInternacionalizacionHandler.get("titulo.14");
            case 15 -> mensajeInternacionalizacionHandler.get("titulo.15");
            default -> "";
        };
    }

}