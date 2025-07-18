package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
/**
 * Controlador encargado de gestionar las acciones relacionadas con los productos,
 * como creación, listado, búsqueda, actualización y eliminación. También maneja
 * la configuración de idioma dinámico para todas las vistas involucradas.
 *
 * @author Brandon
 * @version 1.0
 */
public class ProductoController {


    private final ProductoAnadirView productoAnadirView;
    private final ProductoListaView productoListaView;
    private final ProductoActualizarView productoActualizarView;
    private final CarritoAnadirView carritoAnadirView;
    private final ProductoEliminarView productoEliminarView;

    private final ProductoDAO productoDAO;

    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;
    private Locale locale;

    /**
     * Crea una nueva instancia del controlador de productos, enlazando las vistas y el DAO correspondiente.
     *
     * @param productoDAO DAO para operaciones CRUD de productos.
     * @param productoAnadirView Vista para añadir productos.
     * @param productoListaView Vista para listar productos.
     * @param productoActualizarView Vista para actualizar productos.
     * @param carritoAnadirView Vista para añadir productos al carrito.
     * @param productoEliminarView Vista para eliminar productos.
     * @param mensajeInternacionalizacionHandler Manejador de mensajes internacionalizados.
     */

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
        locale = mensajeInternacionalizacionHandler.getLocale();
        this.configurarEventosEnVistas();

    }
    /**
     * Registra los listeners de eventos para todos los botones de las vistas asociadas a productos,
     * incluyendo añadir, listar, buscar, actualizar, eliminar y búsqueda en carrito.
     */
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
    /**
     * Guarda un nuevo producto validando los campos del formulario.
     * Si el código ya existe o los datos son inválidos, se muestran los mensajes correspondientes.
     */
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
    /**
     * Busca productos por nombre desde la vista de listado y carga los resultados en la tabla.
     */
    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();

        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        cargarDatos(productosEncontrados);
    }
    /**
     * Lista todos los productos disponibles y los muestra en la tabla de la vista correspondiente.
     */
    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        cargarDatos(productos);
    }
    /**
     * Carga una lista de productos en el modelo de tabla de la vista de productos.
     *
     * @param listaProductos Lista de productos a mostrar en la tabla.
     */
    public void cargarDatos(List<Producto> listaProductos) {
        DefaultTableModel modelo = productoListaView.getModelo();
        modelo.setRowCount(0);

        for (Producto producto : listaProductos) {
            modelo.addRow(new Object[] {
                    producto.getCodigo(),
                    producto.getNombre(),
                    FormateadorUtils.formatearMoneda(producto.getPrecio(),locale)
            });
        }

    }
    /**
     * Busca un producto por su código desde la vista de carrito.
     * Si lo encuentra, llena los campos de nombre y precio; si no, los deja vacíos.
     */
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
                carritoAnadirView.getTxtPrecio().setText(FormateadorUtils.formatearMoneda(producto.getPrecio(),locale));
            }
        } catch (NumberFormatException e) {
            carritoAnadirView.mostrarMensaje(mensajes(5), titulosMensajes(5), "error");
        }
    }
    /**
     * Busca un producto por su código en la vista de eliminación.
     *
     * @return true si el producto existe y se cargaron sus datos; false si no se encontró.
     */
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
    /**
     * Elimina un producto después de confirmar con el usuario. Si no existe o hay error,
     * se muestra un mensaje adecuado.
     */
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
    /**
     * Busca un producto por su código desde la vista de actualización.
     * Si lo encuentra, habilita los campos para modificación.
     */
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
    /**
     * Actualiza los datos de un producto con los valores ingresados en los campos correspondientes.
     * Si hay errores en los datos numéricos o campos vacíos, muestra los mensajes respectivos.
     */
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
    /**
     * Cambia el idioma de la interfaz gráfica de todas las vistas relacionadas con productos,
     * actualizando etiquetas, títulos y botones.
     *
     * @param lenguaje Código ISO del lenguaje (por ejemplo, "es").
     * @param pais Código ISO del país (por ejemplo, "EC").
     */
    public void cambiarIdioma(String lenguaje, String pais) {
        locale = mensajeInternacionalizacionHandler.getLocale();
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
        productoListaView.getBtnListar().setText(mensajeInternacionalizacionHandler.get("listar"));
        Object[] columnas = {mensajeInternacionalizacionHandler.get("codigo"), mensajeInternacionalizacionHandler.get("nombre"), mensajeInternacionalizacionHandler.get("precio")};
        productoListaView.getModelo().setColumnIdentifiers(columnas);

    }
    /**
     * Retorna el mensaje internacionalizado correspondiente al código numérico proporcionado.
     *
     * @param cod Código del mensaje.
     * @return Mensaje correspondiente al código dado.
     */
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
    /**
     * Retorna el título internacionalizado correspondiente al código numérico proporcionado.
     *
     * @param cod Código del título.
     * @return Título correspondiente al código dado.
     */
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