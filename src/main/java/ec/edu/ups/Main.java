package ec.edu.ups;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.PreguntaDAOMemoria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                /**
                 * ╔════════════════════════════════════╗
                 * ║      💀 INTERNACIONALIZACION       ║
                 * ╚════════════════════════════════════╝
                 */
                //instanciamos MensajeHandler (Singleton)
                MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler = new MensajeInternacionalizacionHandler("es", "EC");



                /**
                 * ╔════════════════════════════════════╗
                 * ║          📦 DAO - ACCESO A DATOS   ║
                 * ╚════════════════════════════════════╝
                 */
                ProductoDAO productoDAO = new ProductoDAOMemoria();
                productoDAO.crear(new Producto(1001, "Cafetera eléctrica", 45.90));
                productoDAO.crear(new Producto(1002, "Lámpara LED de escritorio", 29.50));
                productoDAO.crear(new Producto(1003, "Set de cuchillos", 49.99));
                productoDAO.crear(new Producto(1004, "Carro de control remoto", 27.80));
                productoDAO.crear(new Producto(1005, "Muñeca interactiva", 39.99));
                productoDAO.crear(new Producto(1006, "Soporte para laptop", 21.10));

                PreguntaDAO preguntaDAO = new PreguntaDAOMemoria();
                UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();
                Usuario admin = usuarioDAO.buscarPorUsername("admin");
                Usuario user = usuarioDAO.buscarPorUsername("user");

                CarritoDAO carritoDAO = new CarritoDAOMemoria();

                Carrito carrito1 = new Carrito(admin);
                carrito1.agregarProducto(productoDAO.buscarPorCodigo(1001), 1); // Cafetera
                carrito1.agregarProducto(productoDAO.buscarPorCodigo(1003), 1); // Cuchillos
                carritoDAO.crear(carrito1);
                admin.agregarCarrito(carrito1);

                Carrito carrito2 = new Carrito(user);
                carrito2.agregarProducto(productoDAO.buscarPorCodigo(1004), 2); // Carro RC
                carrito2.agregarProducto(productoDAO.buscarPorCodigo(1005), 1); // Muñeca
                carritoDAO.crear(carrito2);
                user.agregarCarrito(carrito2);

                LoginView loginView = new LoginView();
                loginView.setVisible(true);
                RegistrarView registrarView = new RegistrarView();

                UsuarioAnadirView usuarioAnadirView = new UsuarioAnadirView();
                UsuarioListaView usuarioListaView = new UsuarioListaView();
                UsuarioEliminarView usuarioEliminarView = new UsuarioEliminarView();
                UsuarioActualizarView usuarioActualizarView = new UsuarioActualizarView();
                ResponderPreguntas responderPreguntas = new ResponderPreguntas();
                UsuarioController usuarioController = new UsuarioController(usuarioDAO,loginView,usuarioAnadirView,usuarioListaView,usuarioEliminarView,usuarioActualizarView,registrarView, responderPreguntas,preguntaDAO);

                loginView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        /**
                         * ╔════════════════════════════════════╗
                         * ║     🖥️ VISTAS - INTERFAZ GRÁFICA   ║
                         * ╚════════════════════════════════════╝
                         */
                        MenuPrincipalView principalView = new MenuPrincipalView(mensajeInternacionalizacionHandler);
                        ProductoAnadirView productoAnadirView = new ProductoAnadirView();
                        ProductoListaView productoListaView = new ProductoListaView();
                        ProductoEliminarView productoEliminarView = new ProductoEliminarView();
                        ProductoActualizarView productoActualizarView = new ProductoActualizarView();

                        Usuario usuarioAuntenticado = usuarioController.getUsuarioAutenticado();

                        CarritoAnadirView carritoAnadirView = new CarritoAnadirView();
                        carritoAnadirView.setUsuario(usuarioAuntenticado);
                        carritoAnadirView.setCarrito(new Carrito(usuarioAuntenticado));
                        CarritoListaView carritoListaView = new CarritoListaView();
                        CarritoEliminarView carritoEliminarView = new CarritoEliminarView();
                        CarritoActualizarView carritoActualizarView = new CarritoActualizarView();
                        CarritoDetalleView carritoDetalleView = new CarritoDetalleView();

                        /**
                         * ╔════════════════════════════════════╗
                         * ║         🧍 USUARIO / LOGIN         ║
                         * ╚════════════════════════════════════╝
                         */
                        if (usuarioAuntenticado != null) {


                            /**
                             * ╔════════════════════════════════════╗
                             * ║          🧠 CONTROLADORES          ║
                             * ╚════════════════════════════════════╝
                             */

                            ProductoController productoController = new ProductoController(productoDAO, productoAnadirView, productoListaView,productoActualizarView, carritoAnadirView,productoEliminarView, mensajeInternacionalizacionHandler);
                            CarritoController carritoController = new CarritoController(
                                    usuarioAuntenticado,
                                    carritoDAO,
                                    productoDAO,
                                    carritoAnadirView,
                                    carritoListaView,
                                    carritoEliminarView,
                                    carritoActualizarView,
                                    carritoDetalleView,
                                    principalView,
                                    mensajeInternacionalizacionHandler
                            );
                            principalView.setVisible(true);
                            principalView.mostrarMensaje("Bienvenido: " + usuarioAuntenticado.getUsername());
                            principalView.setTitle("Sistema de Carrito de Compras en Linea ---------------------- USUARIO →  " + usuarioAuntenticado.getUsername());
                            if (usuarioAuntenticado.getRol().equals(Rol.USUARIO)) {
                                principalView.deshabilitarMenusAdministrador();
                            }

                            /**
                             * ╔════════════════════════════════════╗
                             * ║          🛍️ PRODUCTO - CRUD        ║
                             * ╚════════════════════════════════════╝
                             */

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
                            /**
                             * ╔════════════════════════════════════╗
                             * ║         🛒 CARRITO - CRUD          ║
                             * ╚════════════════════════════════════╝
                             */
                            principalView.getjDesktopPane().add(carritoDetalleView);

                            principalView.getMenuItemCrearCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!carritoAnadirView.isVisible()){
                                        carritoAnadirView.setVisible(true);
                                        principalView.getjDesktopPane().add(carritoAnadirView);
                                    }
                                }
                            });

                            principalView.getMenuItemEliminarCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!carritoEliminarView.isVisible()) {
                                        carritoEliminarView.setVisible(true);
                                        principalView.getjDesktopPane().add(carritoEliminarView);
                                    }
                                }
                            });

                            principalView.getMenuItemActualizarCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!carritoActualizarView.isVisible()) {
                                        carritoActualizarView.setVisible(true);
                                        principalView.getjDesktopPane().add(carritoActualizarView);
                                    }
                                }
                            });

                            principalView.getMenuItemBuscarCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!carritoListaView.isVisible()){
                                        carritoListaView.setVisible(true);
                                        principalView.getjDesktopPane().add(carritoListaView);
                                    }
                                }
                            });
                            /**
                             * ╔════════════════════════════════════╗
                             * ║        👤 USUARIO - CRUD TOTAL     ║
                             * ╚════════════════════════════════════╝
                             */

                            principalView.getMenuItemCrearUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!usuarioAnadirView.isVisible()){
                                        usuarioAnadirView.setVisible(true);
                                        principalView.getjDesktopPane().add(usuarioAnadirView);
                                    }
                                }
                            });
                            principalView.getMenuItemBuscarUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!usuarioListaView.isVisible()){
                                        usuarioListaView.setVisible(true);
                                        principalView.getjDesktopPane().add(usuarioListaView);
                                    }
                                }
                            });
                            principalView.getMenuItemActualizarUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!usuarioActualizarView.isVisible()){
                                        usuarioActualizarView.setVisible(true);
                                        principalView.getjDesktopPane().add(usuarioActualizarView);
                                        if (!usuarioAuntenticado.getRol().equals(Rol.ADMINISTRADOR)) {
                                            usuarioActualizarView.getBtnBuscar().setVisible(false);
                                            usuarioActualizarView.getTxtUsername().setText(usuarioAuntenticado.getUsername());
                                            usuarioController.buscarParaActualizar();
                                            usuarioActualizarView.getTxtUsername().setEditable(false);
                                        }
                                        else{
                                            usuarioActualizarView.getBtnBuscar().setVisible(true);
                                        }
                                    }

                                }
                            });
                            principalView.getMenuItemEliminarUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!usuarioEliminarView.isVisible()){
                                        usuarioEliminarView.setVisible(true);
                                        principalView.getjDesktopPane().add(usuarioEliminarView);
                                    }
                                }
                            });

                            /**
                             * ╔════════════════════════════════════╗
                             * ║          🚪 CIERRE DE SESIÓN       ║
                             * ╚════════════════════════════════════╝
                             */
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
                            /**
                             * ╔════════════════════════════════════╗
                             * ║      💀 INTERNACIONALIZACION       ║
                             * ╚════════════════════════════════════╝
                             */
                            principalView.getMenuItemIdiomaEspanol().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    principalView.cambiarIdioma("es", "EC");
                                    productoController.cambiarIdioma("es", "EC");
                                    carritoController.cambiarIdioma("es", "EC");
                                }
                            });

                            principalView.getMenuItemIdiomaIngles().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    principalView.cambiarIdioma("en", "US");
                                    productoController.cambiarIdioma("en", "US");
                                    carritoController.cambiarIdioma("en", "US");
                                }
                            });

                            principalView.getMenuItemIdiomaFrances().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    principalView.cambiarIdioma("fr", "FR");
                                    productoController.cambiarIdioma("fr", "FR");
                                    carritoController.cambiarIdioma("fr", "FR");
                                }
                            });


                        }
                    }
                });
            }
        });

    }


}
