package ec.edu.ups.vista;

import javax.swing.*;

/**
 * Vista principal que se muestra al iniciar el sistema.
 * Permite al usuario seleccionar el tipo de almacenamiento
 * (en memoria o en archivo) y cambiar el idioma de la aplicación.
 *
 * Esta clase representa la primera pantalla del sistema "Amachon".
 *
 * @author Brandon
 * @version 1.0
 */
public class selectorDAOView extends JFrame {

    private JLabel lbTitulo;
    private JButton btFR;
    private JButton btSP;
    private JButton btEN;
    private JPanel panelPrincipal;
    private JButton btMemoria;
    private JButton btArchivo;

    /**
     * Constructor que inicializa y configura la ventana de selección de DAO e idioma.
     */
    public selectorDAOView() {
        add(panelPrincipal);
        setTitle("Amachon");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * @return Etiqueta del título de la ventana.
     */
    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    /**
     * @return Botón para seleccionar idioma francés.
     */
    public JButton getBtFR() {
        return btFR;
    }

    /**
     * @return Botón para seleccionar idioma español.
     */
    public JButton getBtSP() {
        return btSP;
    }

    /**
     * @return Botón para seleccionar idioma inglés.
     */
    public JButton getBtEN() {
        return btEN;
    }

    /**
     * @return Panel principal de la vista.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * @return Botón para seleccionar almacenamiento en memoria.
     */
    public JButton getBtMemoria() {
        return btMemoria;
    }

    /**
     * @return Botón para seleccionar almacenamiento en archivos.
     */
    public JButton getBtArchivo() {
        return btArchivo;
    }
}
