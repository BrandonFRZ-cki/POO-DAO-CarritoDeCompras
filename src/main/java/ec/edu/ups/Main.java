package ec.edu.ups;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                // Iniciar Secion
                UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();
                LoginView loginView = new LoginView();
                loginView.setVisible(true);

                UsuarioController usuarioController = new UsuarioController(usuarioDAO, loginView);

                loginView.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        if (usuarioController.getUsuarioAutenticado() != null) {
                            iniciarAplicacion();
                        }else {
                            System.exit(0);
                        }
                    }
                });
            }
        });
    }
    public static void iniciarAplicacion() {
        //instanciamos DAO (Singleton)
        ProductoDAO productoDAO = new ProductoDAOMemoria();



        //instancio Vistas
        MenuPrincipalView principalView = new MenuPrincipalView();
        ProductoAnadirView productoAnadirView = new ProductoAnadirView();
        ProductoListaView productoListaView = new ProductoListaView();
        ProductoActualizarView productoActualizarView = new ProductoActualizarView();

        CarritoAnadirView carritoAnadirView = new CarritoAnadirView();



        //instanciamos Controladores
        ProductoController productoController = new ProductoController(productoDAO,
                productoAnadirView, productoListaView, carritoAnadirView, productoActualizarView);
        CarritoController carritoController = new CarritoController(carritoAnadirView,productoDAO);

        principalView.getMenuItemCrearProducto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!productoAnadirView.isVisible()){
                    productoAnadirView.setVisible(true);
                    principalView.getjDesktopPane().add(productoAnadirView);
                }
            }
        });

        principalView.getMenuItemBuscarProducto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!productoListaView.isVisible()){
                    productoListaView.setVisible(true);
                    principalView.getjDesktopPane().add(productoListaView);
                }
            }
        });

        principalView.getMenuItemActualizarProducto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!productoActualizarView.isVisible()){
                    productoActualizarView.setVisible(true);
                    principalView.getjDesktopPane().add(productoActualizarView);
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
    }
}
