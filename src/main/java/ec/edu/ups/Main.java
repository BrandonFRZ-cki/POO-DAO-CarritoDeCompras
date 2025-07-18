package ec.edu.ups;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.DAOArchivoBinario.PreguntaDAOArchivoBinario;
import ec.edu.ups.dao.impl.DAOArchivoBinario.ProductoDAOArchivoBinario;
import ec.edu.ups.dao.impl.DAOArchivoTexto.CarritoDAOArchivoTexto;
import ec.edu.ups.dao.impl.DAOArchivoTexto.UsuarioDAOArchivoTexto;
import ec.edu.ups.dao.impl.PreguntaDAOMemoria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.DAOConfigUtil;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.awt.Image;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Image icono = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/icon_app.png"));

                /**
                 * ╔════════════════════════════════════╗
                 * ║      💀 INTERNACIONALIZACION       ║
                 * ╚════════════════════════════════════╝
                 */
                MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler = new MensajeInternacionalizacionHandler("es", "EC");

                /**
                 * ╔════════════════════════════════════╗
                 * ║      📂 ELECCIÓN DE ALMACENAMIENTO ║
                 * ╚════════════════════════════════════╝
                 */

                // Selector de modo de almacenamiento (ARCHIVO o MEMORIA)
                String[] opciones = {"MEMORIA", "ARCHIVO"};
                int seleccion = JOptionPane.showOptionDialog(
                        null,
                        "Seleccione el tipo de almacenamiento",
                        "Configuración inicial",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                );

                // Declaración de DAOs
                ProductoDAO productoDAO;
                UsuarioDAO usuarioDAO;
                CarritoDAO carritoDAO;
                PreguntaDAO preguntaDAO;

                if (seleccion == 1) { // ARCHIVO
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int resultado = fileChooser.showOpenDialog(null);

                    if (resultado == JFileChooser.APPROVE_OPTION) {
                        String ruta = fileChooser.getSelectedFile().getAbsolutePath();
                        System.out.println(ruta);
                        // DAO con ruta específica: PRODUCTO y PREGUNTA en binario, USUARIO y CARRITO en texto
                        productoDAO = new ec.edu.ups.dao.impl.DAOArchivoBinario.ProductoDAOArchivoBinario(ruta + "/productos.dat");
                        preguntaDAO = new ec.edu.ups.dao.impl.DAOArchivoBinario.PreguntaDAOArchivoBinario(ruta + "/preguntas.dat");
                        usuarioDAO = new ec.edu.ups.dao.impl.DAOArchivoTexto.UsuarioDAOArchivoTexto(ruta + "/usuarios.txt");
                        carritoDAO = new CarritoDAOArchivoTexto(ruta + "/carritos.txt");


                    } else {
                        JOptionPane.showMessageDialog(null, "No se seleccionó carpeta. Cerrando aplicación.");
                        System.exit(0);
                        return;
                    }
                } else { // MEMORIA
                    productoDAO = new ec.edu.ups.dao.impl.ProductoDAOMemoria();
                    preguntaDAO = new ec.edu.ups.dao.impl.PreguntaDAOMemoria();
                    usuarioDAO = new ec.edu.ups.dao.impl.UsuarioDAOMemoria();
                    carritoDAO = new ec.edu.ups.dao.impl.CarritoDAOMemoria();
                }

                // Resto del código continúa igual...

                LoginView loginView = new LoginView();
                loginView.setIconImage(icono);
                loginView.setVisible(true);
                RegistrarView registrarView = new RegistrarView();
                registrarView.setIconImage(icono);

                UsuarioAnadirView usuarioAnadirView = new UsuarioAnadirView();
                UsuarioListaView usuarioListaView = new UsuarioListaView();
                UsuarioEliminarView usuarioEliminarView = new UsuarioEliminarView();
                UsuarioActualizarView usuarioActualizarView = new UsuarioActualizarView();
                ResponderPreguntas responderPreguntas = new ResponderPreguntas();

                UsuarioController usuarioController = new UsuarioController(
                        usuarioDAO,
                        loginView,
                        usuarioAnadirView,
                        usuarioListaView,
                        usuarioEliminarView,
                        usuarioActualizarView,
                        registrarView,
                        responderPreguntas,
                        preguntaDAO,
                        mensajeInternacionalizacionHandler
                );
                loginView.getBtEN().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mensajeInternacionalizacionHandler.setLenguaje("en","US");
                        usuarioController.cambiarIdioma("en","US");
                    }
                });
                loginView.getBtSP().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mensajeInternacionalizacionHandler.setLenguaje("es","EC");
                        usuarioController.cambiarIdioma("es","EC");
                    }
                });
                loginView.getBtFR().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mensajeInternacionalizacionHandler.setLenguaje("fr","FR");
                        usuarioController.cambiarIdioma("fr","FR");

                    }
                });
                loginView.addWindowListener(new WindowAdapter() {

                    @Override
                    public void windowClosed(WindowEvent e) {
                        /**
                         * ╔════════════════════════════════════╗
                         * ║     🖥️ VISTAS - INTERFAZ GRÁFICA   ║
                         * ╚════════════════════════════════════╝
                         */
                        MenuPrincipalView principalView = new MenuPrincipalView(mensajeInternacionalizacionHandler);
                        principalView.setIconImage(icono);
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
                            principalView.mostrarMensaje(mensajeInternacionalizacionHandler.get("bienvenido")+" : " + usuarioAuntenticado.getUsername());
                            principalView.setTitle(mensajeInternacionalizacionHandler.get("titulo")+" ---------------------- "+mensajeInternacionalizacionHandler.get("usuario")+" →  " + usuarioAuntenticado.getUsername());
                            usuarioController.cambiarIdioma(""," ");
                            carritoController.cambiarIdioma(" "," ");
                            productoController.cambiarIdioma(" "," ");

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
                                    usuarioActualizarView.setVisible(false);
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
                                    loginView.getTxtContrasena().setText("");
                                    loginView.getTxtUsername().setText("");
                                    usuarioActualizarView.limpiarCampos();
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
                                    usuarioController.cambiarIdioma("es", "EC");
                                }
                            });
                            principalView.getMenuItemIdiomaIngles().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    principalView.cambiarIdioma("en", "US");
                                    productoController.cambiarIdioma("en", "US");
                                    carritoController.cambiarIdioma("en", "US");
                                    usuarioController.cambiarIdioma("en","US");
                                }
                            });
                            principalView.getMenuItemIdiomaFrances().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    principalView.cambiarIdioma("fr", "FR");
                                    productoController.cambiarIdioma("fr", "FR");
                                    carritoController.cambiarIdioma("fr", "FR");
                                    usuarioController.cambiarIdioma("fr","FR");
                                }
                            });
                        }
                    }
                });
            }
        });

    }



}
