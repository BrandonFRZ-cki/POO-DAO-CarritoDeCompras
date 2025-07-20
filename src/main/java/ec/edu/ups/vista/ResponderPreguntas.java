package ec.edu.ups.vista;

import javax.swing.*;

/**
 * Vista para que el usuario pueda responder preguntas de seguridad
 * y proporcionar su fecha de nacimiento. Esta información se utiliza
 * como método de verificación de identidad para recuperación de cuenta.
 * Contiene campos para 9 preguntas, día, mes y año.
 *
 * @author Brandon
 * @version 1.0
 */
public class ResponderPreguntas extends JFrame {

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
    private JTextField txtPregunta8;
    private JLabel lbPregunta8;
    private JLabel lbPregunta9;
    private JTextField txtPregunta9;
    private JLabel lbDia;
    private JLabel lbMes;
    private JLabel lbAnio;
    private JLabel lbTitulo;

    /**
     * Constructor que configura la vista para responder preguntas.
     */
    public ResponderPreguntas() {
        setContentPane(panelPrincipal);
        setTitle("Registro");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /** @return Panel principal del formulario. */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /** @return Botón para limpiar campos. */
    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    /** @return Botón para aceptar las respuestas. */
    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public void setBtnAceptar(JButton btnAceptar) {
        this.btnAceptar = btnAceptar;
    }

    /** @return Campo para ingresar el día de nacimiento. */
    public JTextField getTxtDia() {
        return txtDia;
    }

    public void setTxtDia(JTextField txtDia) {
        this.txtDia = txtDia;
    }

    /** @return ComboBox para seleccionar el mes de nacimiento. */
    public JComboBox getCbxMes() {
        return cbxMes;
    }

    public void setCbxMes(JComboBox cbxMes) {
        this.cbxMes = cbxMes;
    }

    /** @return Campo para ingresar el año de nacimiento. */
    public JTextField getTxtAnio() {
        return txtAnio;
    }

    public void setTxtAnio(JTextField txtAnio) {
        this.txtAnio = txtAnio;
    }

    // Getters y setters para preguntas

    public JLabel getLbPregunta1() { return lbPregunta1; }
    public JTextField getTxtPregunta1() { return txtPregunta1; }
    public JLabel getLbPregunta2() { return lbPregunta2; }
    public JTextField getTxtPregunta2() { return txtPregunta2; }
    public JLabel getLbPregunta3() { return lbPregunta3; }
    public JTextField getTxtPregunta3() { return txtPregunta3; }
    public JLabel getLbPregunta4() { return lbPregunta4; }
    public JTextField getTxtPregunta4() { return txtPregunta4; }
    public JLabel getLbPregunta5() { return lbPregunta5; }
    public JTextField getTxtPregunta5() { return txtPregunta5; }
    public JLabel getLbPregunta6() { return lbPregunta6; }
    public JTextField getTxtPregunta6() { return txtPregunta6; }
    public JLabel getLbPregunta7() { return lbPregunta7; }
    public JTextField getTxtPregunta7() { return txtPregunta7; }
    public JLabel getLbPregunta8() { return lbPregunta8; }
    public JTextField getTxtPregunta8() { return txtPregunta8; }
    public JLabel getLbPregunta9() { return lbPregunta9; }
    public JTextField getTxtPregunta9() { return txtPregunta9; }

    /**
     * Setter alternativo para el campo pregunta 9
     * utilizado como pregunta 10 en algunos contextos.
     * @param txtPregunta10 campo de texto con respuesta
     */
    public void setTxtPregunta10(JTextField txtPregunta10) {
        this.txtPregunta9 = txtPregunta10;
    }

    /** @return Etiqueta del día de nacimiento. */
    public JLabel getLbDia() {
        return lbDia;
    }

    /** @return Etiqueta del mes de nacimiento. */
    public JLabel getLbMes() {
        return lbMes;
    }

    /** @return Etiqueta del año de nacimiento. */
    public JLabel getLbAnio() {
        return lbAnio;
    }

    /** @return Etiqueta del título principal. */
    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    /**
     * Limpia todos los campos de preguntas y fecha.
     */
    public void limpiarCampos() {
        txtDia.setText("");
        txtAnio.setText("");
        cbxMes.setSelectedIndex(0);
        for (JTextField campo : getCamposRespuestas()) {
            campo.setText("");
        }
    }

    /**
     * Retorna un arreglo con todos los campos de texto usados
     * para responder preguntas de seguridad.
     *
     * @return Arreglo de campos JTextField de respuestas.
     */
    public JTextField[] getCamposRespuestas() {
        return new JTextField[]{
                txtPregunta1,
                txtPregunta2,
                txtPregunta3,
                txtPregunta4,
                txtPregunta5,
                txtPregunta6,
                txtPregunta7,
                txtPregunta8,
                txtPregunta9
        };
    }
}
