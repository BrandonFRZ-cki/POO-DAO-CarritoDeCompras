package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class MenuPrincipalView extends JFrame {
    private JMenuBar menuBar;

    /**
     * ╔════════════════════════════════════╗
     * ║         🧍 USUARIO - CRUD          ║
     * ╚════════════════════════════════════╝
     */
    private JMenu menuUsuario;
    private JMenuItem menuItemCrearUsuario;
    private JMenuItem menuItemEliminarUsuario;
    private JMenuItem menuItemActualizarUsuario;
    private JMenuItem menuItemBuscarUsuario;
    private JMenuItem menuItemCerrarSesion;
    /**
     * ╔════════════════════════════════════╗
     * ║          🛍️ PRODUCTO - CRUD        ║
     * ╚════════════════════════════════════╝
     */
    private JMenu menuProducto;
    private JMenuItem menuItemCrearProducto;
    private JMenuItem menuItemEliminarProducto;
    private JMenuItem menuItemActualizarProducto;
    private JMenuItem menuItemBuscarProducto;
    /**
     * ╔════════════════════════════════════╗
     * ║         🛒 CARRITO - CRUD          ║
     * ╚════════════════════════════════════╝
     */
    private JMenu menuCarrito;
    private JMenuItem menuItemCrearCarrito;
    private JMenuItem menuItemEliminarCarrito;
    private JMenuItem menuItemActualizarCarrito;
    private JMenuItem menuItemBuscarCarrito;
    /**
     * ╔════════════════════════════════════╗
     * ║              IDIOMAS 💀            ║
     * ╚════════════════════════════════════╝
     */
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;
    private JMenu menuIdioma;
    private JMenuItem menuItemIdiomaEspanol;
    private JMenuItem menuItemIdiomaIngles;
    private JMenuItem menuItemIdiomaFrances;;


    private MiDescktopPane jDesktopPane;

    public MenuPrincipalView(MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler) {
        this.mensajeInternacionalizacionHandler = mensajeInternacionalizacionHandler;
        initComponent();

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

    public JMenuItem getMenuItemEliminarCarrito() {
        return menuItemEliminarCarrito;
    }

    public JMenuItem getMenuItemActualizarCarrito() {
        return menuItemActualizarCarrito;
    }

    public JMenuItem getMenuItemBuscarCarrito() {
        return menuItemBuscarCarrito;
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
        getMenuItemBuscarUsuario().setEnabled(false);
    }

    public MensajeInternacionalizacionHandler getMensajeInternacionalizacionHandler() {
        return mensajeInternacionalizacionHandler;
    }

    public JMenu getMenuIdioma() {
        return menuIdioma;
    }

    public JMenuItem getMenuItemIdiomaEspanol() {
        return menuItemIdiomaEspanol;
    }

    public JMenuItem getMenuItemIdiomaIngles() {
        return menuItemIdiomaIngles;
    }

    public JMenuItem getMenuItemIdiomaFrances() {
        return menuItemIdiomaFrances;
    }

    public void initComponent() {
        jDesktopPane = new MiDescktopPane();
        menuBar = new JMenuBar();

        menuUsuario = new JMenu(mensajeInternacionalizacionHandler.get("usuario"));
        menuProducto = new JMenu(mensajeInternacionalizacionHandler.get("producto"));
        menuCarrito = new JMenu(mensajeInternacionalizacionHandler.get("carrito"));
        menuIdioma = new JMenu(mensajeInternacionalizacionHandler.get("idioma"));

        menuItemCrearUsuario = new JMenuItem(mensajeInternacionalizacionHandler.get("usuario.crear"));
        menuItemEliminarUsuario = new JMenuItem(mensajeInternacionalizacionHandler.get("usuario.eliminar"));
        menuItemActualizarUsuario = new JMenuItem(mensajeInternacionalizacionHandler.get("usuario.actualizar"));
        menuItemBuscarUsuario = new JMenuItem(mensajeInternacionalizacionHandler.get("usuario.buscar"));
        menuItemCerrarSesion = new JMenuItem(mensajeInternacionalizacionHandler.get("usuario.cerrarSesion"));

        menuUsuario.add(menuItemCrearUsuario);
        menuUsuario.add(menuItemEliminarUsuario);
        menuUsuario.add(menuItemActualizarUsuario);
        menuUsuario.add(menuItemBuscarUsuario);
        menuUsuario.add(menuItemCerrarSesion);

        menuItemCrearProducto = new JMenuItem(mensajeInternacionalizacionHandler.get("producto.crear"));
        menuItemEliminarProducto = new JMenuItem(mensajeInternacionalizacionHandler.get("producto.eliminar"));
        menuItemActualizarProducto = new JMenuItem(mensajeInternacionalizacionHandler.get("producto.actualizar"));
        menuItemBuscarProducto = new JMenuItem(mensajeInternacionalizacionHandler.get("producto.buscar"));


        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemEliminarProducto);
        menuProducto.add(menuItemActualizarProducto);
        menuProducto.add(menuItemBuscarProducto);

        menuItemCrearCarrito = new JMenuItem(mensajeInternacionalizacionHandler.get("carrito.crear"));
        menuItemEliminarCarrito = new JMenuItem(mensajeInternacionalizacionHandler.get("carrito.eliminar"));
        menuItemActualizarCarrito = new JMenuItem(mensajeInternacionalizacionHandler.get("carrito.actualizar"));
        menuItemBuscarCarrito = new JMenuItem(mensajeInternacionalizacionHandler.get("carrito.buscar"));

        menuCarrito.add(menuItemCrearCarrito);
        menuCarrito.add(menuItemEliminarCarrito);
        menuCarrito.add(menuItemActualizarCarrito);
        menuCarrito.add(menuItemBuscarCarrito);
        menuCarrito.add(menuItemEliminarCarrito);
        menuCarrito.add(menuItemCrearCarrito);

        menuItemIdiomaFrances = new JMenuItem(mensajeInternacionalizacionHandler.get("idioma.fr"));
        menuItemIdiomaEspanol = new JMenuItem(mensajeInternacionalizacionHandler.get("idioma.es"));
        menuItemIdiomaIngles = new JMenuItem(mensajeInternacionalizacionHandler.get("idioma.en"));


        menuIdioma.add(menuItemIdiomaFrances);
        menuIdioma.add(menuItemIdiomaEspanol);
        menuIdioma.add(menuItemIdiomaIngles);


        menuBar.add(menuProducto);
        menuBar.add(menuCarrito);
        menuBar.add(menuUsuario);
        menuBar.add(menuIdioma);

        setJMenuBar(menuBar);
        setContentPane(jDesktopPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(mensajeInternacionalizacionHandler.get("titulo"));
        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
    public void cambiarIdioma(String lenguaje, String pais) {
        mensajeInternacionalizacionHandler.setLenguaje(lenguaje, pais);

        setTitle(mensajeInternacionalizacionHandler.get("titulo"));

        // Menús principales
        menuProducto.setText(mensajeInternacionalizacionHandler.get("producto"));
        menuCarrito.setText(mensajeInternacionalizacionHandler.get("carrito"));
        menuUsuario.setText(mensajeInternacionalizacionHandler.get("usuario"));
        menuIdioma.setText(mensajeInternacionalizacionHandler.get("idioma"));

        // Ítems de Producto
        menuItemCrearProducto.setText(mensajeInternacionalizacionHandler.get("producto.crear"));
        menuItemEliminarProducto.setText(mensajeInternacionalizacionHandler.get("producto.eliminar"));
        menuItemActualizarProducto.setText(mensajeInternacionalizacionHandler.get("producto.actualizar"));
        menuItemBuscarProducto.setText(mensajeInternacionalizacionHandler.get("producto.buscar"));

        // Ítems de Carrito
        menuItemCrearCarrito.setText(mensajeInternacionalizacionHandler.get("carrito.crear"));
        menuItemEliminarCarrito.setText(mensajeInternacionalizacionHandler.get("carrito.eliminar"));
        menuItemActualizarCarrito.setText(mensajeInternacionalizacionHandler.get("carrito.actualizar"));
        menuItemBuscarCarrito.setText(mensajeInternacionalizacionHandler.get("carrito.buscar"));

        // Ítems de Usuario
        menuItemCrearUsuario.setText(mensajeInternacionalizacionHandler.get("usuario.crear"));
        menuItemEliminarUsuario.setText(mensajeInternacionalizacionHandler.get("usuario.eliminar"));
        menuItemActualizarUsuario.setText(mensajeInternacionalizacionHandler.get("usuario.actualizar"));
        menuItemBuscarUsuario.setText(mensajeInternacionalizacionHandler.get("usuario.buscar"));
        menuItemCerrarSesion.setText(mensajeInternacionalizacionHandler.get("usuario.cerrarSesion"));

        // Ítems de idioma
        menuItemIdiomaEspanol.setText(mensajeInternacionalizacionHandler.get("idioma.es"));
        menuItemIdiomaIngles.setText(mensajeInternacionalizacionHandler.get("idioma.en"));
        menuItemIdiomaFrances.setText(mensajeInternacionalizacionHandler.get("idioma.fr"));
    }

}
