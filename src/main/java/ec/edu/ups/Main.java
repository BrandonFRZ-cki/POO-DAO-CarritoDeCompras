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
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase principal que inicia la aplicación del carrito de compras.
 * Maneja la selección de idioma y tipo de almacenamiento (MEMORIA o ARCHIVO),
 * inicializa los DAOs correspondientes e inicia el flujo de login.
 *
 * @author Brandon
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Image icono = Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/icon/icon_app.png"));

                MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler = new MensajeInternacionalizacionHandler("es", "EC");

                selectorDAOView selectorView = new selectorDAOView();
                selectorView.setIconImage(icono);

                final ProductoDAO[] productoDAO = new ProductoDAO[1];
                final UsuarioDAO[] usuarioDAO = new UsuarioDAO[1];
                final CarritoDAO[] carritoDAO = new CarritoDAO[1];
                final PreguntaDAO[] preguntaDAO = new PreguntaDAO[1];

                selectorView.getBtEN().addActionListener(e -> {
                    mensajeInternacionalizacionHandler.setLenguaje("en", "US");
                    selectorView.getLbTitulo().setText(mensajeInternacionalizacionHandler.get("almacenamiento"));
                });
                selectorView.getBtSP().addActionListener(e -> {
                    mensajeInternacionalizacionHandler.setLenguaje("es", "EC");
                    selectorView.getLbTitulo().setText(mensajeInternacionalizacionHandler.get("almacenamiento"));
                });
                selectorView.getBtFR().addActionListener(e -> {
                    mensajeInternacionalizacionHandler.setLenguaje("fr", "FR");
                    selectorView.getLbTitulo().setText(mensajeInternacionalizacionHandler.get("almacenamiento"));
                });

                selectorView.getBtMemoria().addActionListener(e -> {
                    productoDAO[0] = new ProductoDAOMemoria();
                    preguntaDAO[0] = new PreguntaDAOMemoria();
                    usuarioDAO[0] = new UsuarioDAOMemoria();
                    carritoDAO[0] = new CarritoDAOMemoria();
                    selectorView.dispose();
                    lanzarLogin(icono, mensajeInternacionalizacionHandler, productoDAO[0], usuarioDAO[0], carritoDAO[0], preguntaDAO[0]);
                });

                selectorView.getBtArchivo().addActionListener(e -> {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int resultado = fileChooser.showOpenDialog(null);

                    if (resultado == JFileChooser.APPROVE_OPTION) {
                        String ruta = fileChooser.getSelectedFile().getAbsolutePath();
                        productoDAO[0] = new ProductoDAOArchivoBinario(ruta + "/productos.dat");
                        preguntaDAO[0] = new PreguntaDAOArchivoBinario(ruta + "/preguntas.dat");
                        usuarioDAO[0] = new UsuarioDAOArchivoTexto(ruta + "/usuarios.txt");
                        carritoDAO[0] = new CarritoDAOArchivoTexto(ruta + "/carritos.txt");
                        selectorView.dispose();
                        lanzarLogin(icono, mensajeInternacionalizacionHandler, productoDAO[0], usuarioDAO[0], carritoDAO[0], preguntaDAO[0]);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se seleccionó carpeta. Cerrando aplicación.");
                        System.exit(0);
                    }
                });

                selectorView.setVisible(true);
            }
        });
    }

    /**
     * Lanza la vista de login e inicializa controladores y vistas requeridas.
     *
     * @param icono Icono principal de la aplicación
     * @param mensajeInternacionalizacionHandler Manejador de internacionalización
     * @param productoDAO DAO de productos (memoria o archivo)
     * @param usuarioDAO DAO de usuarios (memoria o archivo)
     * @param carritoDAO DAO de carritos (memoria o archivo)
     * @param preguntaDAO DAO de preguntas de seguridad (memoria o archivo)
     */
    private static void lanzarLogin(Image icono,
                                    MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler,
                                    ProductoDAO productoDAO,
                                    UsuarioDAO usuarioDAO,
                                    CarritoDAO carritoDAO,
                                    PreguntaDAO preguntaDAO) {

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

        loginView.getBtEN().addActionListener(e -> {
            mensajeInternacionalizacionHandler.setLenguaje("en","US");
            usuarioController.cambiarIdioma("en","US");
        });
        loginView.getBtSP().addActionListener(e -> {
            mensajeInternacionalizacionHandler.setLenguaje("es","EC");
            usuarioController.cambiarIdioma("es","EC");
        });
        loginView.getBtFR().addActionListener(e -> {
            mensajeInternacionalizacionHandler.setLenguaje("fr","FR");
            usuarioController.cambiarIdioma("fr","FR");
        });

        loginView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
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

                if (usuarioAuntenticado != null) {
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

                    principalView.getMenuItemCrearProducto().addActionListener(e1 -> {
                        if(!productoAnadirView.isVisible()){
                            productoAnadirView.setVisible(true);
                            principalView.getjDesktopPane().add(productoAnadirView);
                        }
                    });
                    principalView.getMenuItemEliminarProducto().addActionListener(e1 -> {
                        if(!productoEliminarView.isVisible()){
                            productoEliminarView.setVisible(true);
                            principalView.getjDesktopPane().add(productoEliminarView);
                        }
                    });
                    principalView.getMenuItemBuscarProducto().addActionListener(e1 -> {
                        if(!productoListaView.isVisible()){
                            productoListaView.setVisible(true);
                            principalView.getjDesktopPane().add(productoListaView);
                        }
                    });
                    principalView.getMenuItemActualizarProducto().addActionListener(e1 -> {
                        if(!productoActualizarView.isVisible()){
                            productoActualizarView.setVisible(true);
                            principalView.getjDesktopPane().add(productoActualizarView);
                            productoActualizarView.getTxtNuevoNombre().setEditable(false);
                            productoActualizarView.getTxtNuevoPrecio().setEditable(false);
                        }
                    });
                    principalView.getjDesktopPane().add(carritoDetalleView);
                    principalView.getMenuItemCrearCarrito().addActionListener(e1 -> {
                        if(!carritoAnadirView.isVisible()){
                            carritoAnadirView.setVisible(true);
                            principalView.getjDesktopPane().add(carritoAnadirView);
                        }
                    });
                    principalView.getMenuItemEliminarCarrito().addActionListener(e1 -> {
                        if (!carritoEliminarView.isVisible()) {
                            carritoEliminarView.setVisible(true);
                            principalView.getjDesktopPane().add(carritoEliminarView);
                        }
                    });
                    principalView.getMenuItemActualizarCarrito().addActionListener(e1 -> {
                        if (!carritoActualizarView.isVisible()) {
                            carritoActualizarView.setVisible(true);
                            principalView.getjDesktopPane().add(carritoActualizarView);
                        }
                    });
                    principalView.getMenuItemBuscarCarrito().addActionListener(e1 -> {
                        if(!carritoListaView.isVisible()){
                            carritoListaView.setVisible(true);
                            principalView.getjDesktopPane().add(carritoListaView);
                        }
                    });
                    principalView.getMenuItemCrearUsuario().addActionListener(e1 -> {
                        if(!usuarioAnadirView.isVisible()){
                            usuarioAnadirView.setVisible(true);
                            principalView.getjDesktopPane().add(usuarioAnadirView);
                        }
                    });
                    principalView.getMenuItemBuscarUsuario().addActionListener(e1 -> {
                        if(!usuarioListaView.isVisible()){
                            usuarioListaView.setVisible(true);
                            principalView.getjDesktopPane().add(usuarioListaView);
                        }
                    });
                    principalView.getMenuItemActualizarUsuario().addActionListener(e1 -> {
                        usuarioActualizarView.setVisible(false);
                        if(!usuarioActualizarView.isVisible()){
                            usuarioActualizarView.setVisible(true);
                            principalView.getjDesktopPane().add(usuarioActualizarView);
                            if (!usuarioAuntenticado.getRol().equals(Rol.ADMINISTRADOR)) {
                                usuarioActualizarView.getBtnBuscar().setVisible(false);
                                usuarioActualizarView.getTxtUsername().setText(usuarioAuntenticado.getUsername());
                                usuarioController.buscarParaActualizar();
                                usuarioActualizarView.getTxtUsername().setEditable(false);
                            } else {
                                usuarioActualizarView.getBtnBuscar().setVisible(true);
                            }
                        }
                    });
                    principalView.getMenuItemEliminarUsuario().addActionListener(e1 -> {
                        if(!usuarioEliminarView.isVisible()){
                            usuarioEliminarView.setVisible(true);
                            principalView.getjDesktopPane().add(usuarioEliminarView);
                        }
                    });
                    principalView.getMenuItemCerrarSesion().addActionListener(e1 -> {
                        principalView.setVisible(false);
                        productoListaView.setVisible(false);
                        carritoAnadirView.setVisible(false);
                        productoEliminarView.setVisible(false);
                        loginView.getTxtContrasena().setText("");
                        loginView.getTxtUsername().setText("");
                        usuarioActualizarView.limpiarCampos();
                        loginView.setVisible(true);
                    });
                    principalView.getMenuItemIdiomaEspanol().addActionListener(e1 -> {
                        principalView.cambiarIdioma("es", "EC");
                        productoController.cambiarIdioma("es", "EC");
                        carritoController.cambiarIdioma("es", "EC");
                        usuarioController.cambiarIdioma("es", "EC");
                    });
                    principalView.getMenuItemIdiomaIngles().addActionListener(e1 -> {
                        principalView.cambiarIdioma("en", "US");
                        productoController.cambiarIdioma("en", "US");
                        carritoController.cambiarIdioma("en", "US");
                        usuarioController.cambiarIdioma("en", "US");
                    });
                    principalView.getMenuItemIdiomaFrances().addActionListener(e1 -> {
                        principalView.cambiarIdioma("fr", "FR");
                        productoController.cambiarIdioma("fr", "FR");
                        carritoController.cambiarIdioma("fr", "FR");
                        usuarioController.cambiarIdioma("fr", "FR");
                    });
                }
            }
        });
    }
}
