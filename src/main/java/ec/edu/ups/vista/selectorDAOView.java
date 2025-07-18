package ec.edu.ups.vista;

import javax.swing.*;

public class selectorDAOView extends JFrame {
    private JLabel lbTitulo;
    private JButton btFR;
    private JButton btSP;
    private JButton btEN;
    private JPanel panelPrincipal;
    private JButton btMemoria;
    private JButton btArchivo;

    public selectorDAOView() {
        add(panelPrincipal);
        setTitle("Amachon");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    public JButton getBtFR() {
        return btFR;
    }

    public JButton getBtSP() {
        return btSP;
    }

    public JButton getBtEN() {
        return btEN;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JButton getBtMemoria() {
        return btMemoria;
    }

    public JButton getBtArchivo() {
        return btArchivo;
    }
}
