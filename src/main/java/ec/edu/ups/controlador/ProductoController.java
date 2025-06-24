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
        int codigo = Integer.parseInt(productoAnadirView.getTxtCodigo().getText());
        String nombre = productoAnadirView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoAnadirView.getTxtPrecio().getText());

        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoAnadirView.mostrarMensaje("Producto guardado correctamente");
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProductos(productoDAO.listarTodos());
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
        int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto == null) {
            carritoAnadirView.mostrarMensaje("No se encontro el producto");
            carritoAnadirView.getTxtNombre().setText("");
            carritoAnadirView.getTxtPrecio().setText("");
        } else {
            carritoAnadirView.getTxtNombre().setText(producto.getNombre());
            carritoAnadirView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        }

    }
    private boolean buscarProductoParaEliminar() {
        int codigo = Integer.parseInt(productoEliminarView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto == null) {
            productoEliminarView.getTxtNombre().setText(" - ");
            productoEliminarView.getTxtPrecio().setText(" - ");
            return false;
        } else {
            productoEliminarView.getTxtNombre().setText(producto.getNombre());
            productoEliminarView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        }
        return true;
    }
    private void eliminarProducto() {
        int codigo = Integer.parseInt(productoEliminarView.getTxtCodigo().getText());
        int seguroMensaje = JOptionPane.showOptionDialog(null,"Seguro papi?","Eliminar",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Si","No"},null );
        if (buscarProductoParaEliminar()) {
            if (seguroMensaje == JOptionPane.YES_OPTION) {
                productoDAO.eliminar(codigo);
                productoEliminarView.mostrarMensaje("Producto Eliminado Exitosamente","Eliminado","warning");
            }
            productoEliminarView.limpiarCampos();
        }else{
            productoEliminarView.mostrarMensaje("Ingrese un producto existente","Data no encontrada","error");
        }

    }
    private void buscarProductoParaActualizar() {
        int codigo = Integer.parseInt(productoActualizarView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto != null) {
            productoActualizarView.getTxtNombre().setText(producto.getNombre());
            productoActualizarView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));

            productoActualizarView.getTxtCodigo().setEditable(false);
            productoActualizarView.getTxtNuevoNombre().setEditable(true);
            productoActualizarView.getTxtNuevoPrecio().setEditable(true);
        }else{
            productoActualizarView.getTxtCodigo().setText(" - ");
            productoActualizarView.getTxtNombre().setText(" - ");
            productoActualizarView.getTxtPrecio().setText(" - ");
            productoActualizarView.mostrarMensaje("Ingrese un codigo existente","Data no encontrada","error");
        }
    }
    private void actualizar() {
        Producto producto = productoDAO.buscarPorCodigo(Integer.parseInt(productoActualizarView.getTxtCodigo().getText()));
        producto.setPrecio(Double.parseDouble(productoActualizarView.getTxtNuevoPrecio().getText()));
        producto.setNombre(productoActualizarView.getTxtNuevoNombre().getText());
        productoActualizarView.getTxtCodigo().setEditable(true);

        productoActualizarView.getTxtCodigo().setText(" - ");
        productoActualizarView.getTxtNombre().setText(" - ");
        productoActualizarView.getTxtPrecio().setText(" - ");
        productoActualizarView.getTxtNuevoNombre().setText("");
        productoActualizarView.getTxtNuevoPrecio().setText("");
        productoActualizarView.mostrarMensaje("Actualizado exitosamente","Producto actualizado","info");
    }
}