package ec.edu.ups;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //instanciamos DAO (Singleton)
                ProductoDAO productoDAO = new ProductoDAOMemoria();
                CarritoDAO carritoDAO = new CarritoDAOMemoria();

                //Iniciar sesión
                UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();
                LoginView loginView = new LoginView();
                loginView.setVisible(true);

                UsuarioController usuarioController = new UsuarioController(usuarioDAO, loginView);
                //instancio Vistas


                loginView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        MenuPrincipalView principalView = new MenuPrincipalView();
                        ProductoAnadirView productoAnadirView = new ProductoAnadirView();
                        ProductoListaView productoListaView = new ProductoListaView();
                        ProductoEliminarView productoEliminarView = new ProductoEliminarView();
                        ProductoActualizarView productoActualizarView = new ProductoActualizarView();

                        CarritoAnadirView carritoAnadirView = new CarritoAnadirView();

                        //instanciamos Controladores
                        ProductoController productoController = new ProductoController(productoDAO, productoAnadirView, productoListaView,productoActualizarView, carritoAnadirView,productoEliminarView);
                        CarritoController carritoController = new CarritoController(carritoDAO, productoDAO, carritoAnadirView);
                        Usuario usuarioAuntenticado = usuarioController.getUsuarioAutenticado();
                        if (usuarioAuntenticado != null) {

                            principalView.setVisible(true);
                            principalView.mostrarMensaje("Bienvenido: " + usuarioAuntenticado.getUsername());
                            principalView.setTitle("Sistema de Carrito de Compras en Linea ---------------------- USUARIO →  " + usuarioAuntenticado.getUsername());
                            if (usuarioAuntenticado.getRol().equals(Rol.USUARIO)) {
                                principalView.deshabilitarMenusAdministrador();
                            }

                            //--------------------------------------------------------------------------------------------- PRODUCTO

                            principalView.getMenuItemCrearProducto().addActionListener(new ActionListener() {//------------ Crear
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!productoAnadirView.isVisible()){
                                        productoAnadirView.setVisible(true);
                                        principalView.getjDesktopPane().add(productoAnadirView);
                                    }
                                }
                            });
                            principalView.getMenuItemEliminarProducto().addActionListener(new ActionListener() {//---------- Eliminar
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!productoEliminarView.isVisible()){
                                        productoEliminarView.setVisible(true);
                                        principalView.getjDesktopPane().add(productoEliminarView);
                                    }
                                }
                            });
                            principalView.getMenuItemBuscarProducto().addActionListener(new ActionListener() {//------------ Buscar / Listar
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!productoListaView.isVisible()){
                                        productoListaView.setVisible(true);
                                        principalView.getjDesktopPane().add(productoListaView);
                                    }
                                }
                            });
                            principalView.getMenuItemActualizarProducto().addActionListener(new ActionListener() {// --------- Actualizar
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!productoActualizarView.isVisible()){
                                        productoActualizarView.setVisible(true);
                                        principalView.getjDesktopPane().add(productoActualizarView);
                                        productoActualizarView.getTxtNuevoNombre().setEditable(false);
                                        productoActualizarView.getTxtNuevoPrecio().setEditable(false);
                                    }
                                }
                            });
                            principalView.getMenuItemCrearCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!carritoAnadirView.isVisible()){
                                        carritoAnadirView.setVisible(true);
                                        principalView.getjDesktopPane().add(carritoAnadirView);
                                    }
                                }
                            });
                            principalView.getMenuItemCerrarSesion().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    principalView.setVisible(false);
                                    productoListaView.setVisible(false);
                                    carritoAnadirView.setVisible(false);
                                    productoEliminarView.setVisible(false);

                                    loginView.setVisible(true);
                                }
                            });
                        }
                    }
                });
            }
        });

    }


}
