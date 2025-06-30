package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
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

    public ProductoController(ProductoDAO productoDAO,
                              ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView,
                              ProductoActualizarView productoActualizarView,
                              CarritoAnadirView carritoAnadirView,
                              ProductoEliminarView productoEliminarView) {

        this.productoDAO = productoDAO;
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.productoEliminarView=productoEliminarView;
        this.carritoAnadirView = carritoAnadirView;
        this.productoActualizarView = productoActualizarView;
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
            productoAnadirView.mostrarMensaje("Todos los campos son obligatorios", "Datos incompletos", "warning");
            return;
        }

        try {
            int codigo = Integer.parseInt(codTexto);
            double precio = Double.parseDouble(precioTexto);

            if (productoDAO.buscarPorCodigo(codigo) != null) {
                productoAnadirView.mostrarMensaje("Ya existe un producto con ese código", "Código duplicado", "error");
                return;
            }

            productoDAO.crear(new Producto(codigo, nombre, precio));
            productoAnadirView.mostrarMensaje("Producto guardado correctamente", "Guardado", "info");
            productoAnadirView.limpiarCampos();
            productoAnadirView.mostrarProductos(productoDAO.listarTodos());

        } catch (NumberFormatException e) {
            productoAnadirView.mostrarMensaje("El código debe ser entero y el precio numérico", "Formato inválido", "error");
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
                carritoAnadirView.mostrarMensaje("No se encontró el producto", "Sin coincidencias", "warning");
                carritoAnadirView.getTxtNombre().setText("");
                carritoAnadirView.getTxtPrecio().setText("");
            } else {
                carritoAnadirView.getTxtNombre().setText(producto.getNombre());
                carritoAnadirView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
            }
        } catch (NumberFormatException e) {
            carritoAnadirView.mostrarMensaje("Ingrese un código numérico válido", "Código inválido", "error");
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
            productoEliminarView.mostrarMensaje("Ingrese un código válido", "Error de formato", "error");
            return false;
        }
    }
    private void eliminarProducto() {
        if (!buscarProductoParaEliminar()) {
            productoEliminarView.mostrarMensaje("Ingrese un producto existente", "No encontrado", "warning");
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
                productoEliminarView.mostrarMensaje("Producto eliminado exitosamente", "Eliminado", "info");
                productoEliminarView.limpiarCampos();
            }

        } catch (NumberFormatException e) {
            productoEliminarView.mostrarMensaje("Código inválido", "Error", "error");
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
                productoActualizarView.mostrarMensaje("Ingrese un código existente", "No encontrado", "warning");
            }

        } catch (NumberFormatException e) {
            productoActualizarView.mostrarMensaje("Código inválido. Ingrese un número", "Error de formato", "error");
        }
    }
    private void actualizar() {
        String nuevoNombre = productoActualizarView.getTxtNuevoNombre().getText();
        String nuevoPrecioTexto = productoActualizarView.getTxtNuevoPrecio().getText();

        if (nuevoNombre.isEmpty() || nuevoPrecioTexto.isEmpty()) {
            productoActualizarView.mostrarMensaje("Debe ingresar nombre y precio nuevos", "Campos vacíos", "warning");
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

            productoActualizarView.mostrarMensaje("Producto actualizado exitosamente", "Actualización", "info");

        } catch (NumberFormatException e) {
            productoActualizarView.mostrarMensaje("Precio inválido. Debe ser numérico", "Formato incorrecto", "error");
        }
    }
}