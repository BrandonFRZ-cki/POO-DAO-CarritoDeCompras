package ec.edu.ups.vista;

import javax.swing.*;

public class ResponderPreguntas extends JFrame{
    private JPanel panelPrincipal;
    private JButton btnLimpiar;
    private JButton btnAceptar;
    private JTextField txtDia;
    private JComboBox cbxMes;
    private JTextField txtAnio;
    private JLabel lbPregunta1;
    private JTextField txtPregunta1;
    private JLabel lbPregunta2;
    private JLabel lbPregunta3;
    private JTextField txtPregunta3;
    private JLabel lbPregunta4;
    private JTextField txtPregunta4;
    private JLabel lbPregunta5;
    private JTextField txtPregunta5;
    private JTextField txtPregunta6;
    private JLabel lbPregunta6;
    private JTextField txtPregunta2;
    private JLabel lbPregunta7;
    private JTextField txtPregunta7;
    private JTextField txtPregunta9;
    private JLabel lbPregunta9;
    private JLabel lbPregunta10;
    private JTextField txtPregunta10;

    public ResponderPreguntas() {
        setContentPane(panelPrincipal);
        setTitle("Registro");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
    }
}
