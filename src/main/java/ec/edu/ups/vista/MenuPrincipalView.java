package ec.edu.ups.vista;

import javax.swing.*;

public class MenuPrincipalView extends JFrame {
    private JMenuBar menuBar;

    private JMenu menuProducto;
    private JMenu menuCarrito;
    private JMenu menuUsuario;

    private JMenuItem menuItemCrearUsuario;
    private JMenuItem menuItemEliminarUsuario;
    private JMenuItem menuItemActualizarUsuario;
    private JMenuItem menuItemBuscarUsuario;
    private JMenuItem menuItemCerrarSesion;



    private JMenuItem menuItemCrearProducto;
    private JMenuItem menuItemEliminarProducto;
    private JMenuItem menuItemActualizarProducto;
    private JMenuItem menuItemBuscarProducto;

    private JMenuItem menuItemCrearCarrito;

    private JDesktopPane jDesktopPane;

    public MenuPrincipalView() {
        jDesktopPane = new JDesktopPane();
        menuBar = new JMenuBar();

        menuUsuario = new JMenu("Usuario");
        menuProducto = new JMenu("Producto");
        menuCarrito = new JMenu("Carrito");


        menuItemCrearUsuario = new JMenuItem("Crear Usuario");
        menuItemEliminarUsuario = new JMenuItem("Eliminar Usuario");
        menuItemActualizarUsuario = new JMenuItem("Actualizar Usuario");
        menuItemBuscarUsuario = new JMenuItem("Buscar Usuario");
        menuItemCerrarSesion = new JMenuItem("Cerrar Sesion");

        menuUsuario.add(menuItemCrearUsuario);
        menuUsuario.add(menuItemEliminarUsuario);
        menuUsuario.add(menuItemActualizarUsuario);
        menuUsuario.add(menuItemBuscarUsuario);
        menuUsuario.add(menuItemCerrarSesion);

        menuItemCrearProducto = new JMenuItem("Crear Producto");
        menuItemEliminarProducto = new JMenuItem("Eliminar Producto");
        menuItemActualizarProducto = new JMenuItem("Actualizar Producto");
        menuItemBuscarProducto = new JMenuItem("Buscar Producto");

        menuItemCrearCarrito = new JMenuItem("Crear Carrito");

        menuBar.add(menuProducto);
        menuBar.add(menuCarrito);
        menuBar.add(menuUsuario);

        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemEliminarProducto);
        menuProducto.add(menuItemActualizarProducto);
        menuProducto.add(menuItemBuscarProducto);

        menuCarrito.add(menuItemCrearCarrito);

        setJMenuBar(menuBar);
        setContentPane(jDesktopPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sistema de Carrito de Compras En Línea");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setVisible(true);

    }

    public JMenu getMenuProducto() {
        return menuProducto;
    }

    public JMenu getMenuCarrito() {
        return menuCarrito;
    }

    public JMenu getMenuUsuario() {
        return menuUsuario;
    }

    public JMenuItem getMenuItemCrearUsuario() {
        return menuItemCrearUsuario;
    }

    public JMenuItem getMenuItemEliminarUsuario() {
        return menuItemEliminarUsuario;
    }

    public JMenuItem getMenuItemActualizarUsuario() {
        return menuItemActualizarUsuario;
    }

    public JMenuItem getMenuItemBuscarUsuario() {
        return menuItemBuscarUsuario;
    }

    public JMenuItem getMenuItemCerrarSesion() {
        return menuItemCerrarSesion;
    }

    public JMenuItem getMenuItemCrearProducto() {
        return menuItemCrearProducto;
    }

    public JMenuItem getMenuItemEliminarProducto() {
        return menuItemEliminarProducto;
    }

    public JMenuItem getMenuItemActualizarProducto() {
        return menuItemActualizarProducto;
    }

    public JMenuItem getMenuItemBuscarProducto() {
        return menuItemBuscarProducto;
    }

    public JMenuItem getMenuItemCrearCarrito() {
        return menuItemCrearCarrito;
    }

    public JDesktopPane getjDesktopPane() {
        return jDesktopPane;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void deshabilitarMenusAdministrador() {

        getMenuItemCrearProducto().setEnabled(false);

        getMenuItemActualizarProducto().setEnabled(false);
        getMenuItemEliminarProducto().setEnabled(false);

        getMenuItemCrearUsuario().setEnabled(false);
        getMenuItemEliminarUsuario().setEnabled(false);
        getMenuItemActualizarUsuario().setEnabled(false);
        getMenuItemBuscarUsuario().setEnabled(false);
    }
}
