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
    private JTextField txtPregunta8;
    private JLabel lbPregunta8;
    private JLabel lbPregunta9;
    private JTextField txtPregunta9;
    private JLabel lbDia;
    private JLabel lbMes;
    private JLabel lbAnio;

    public ResponderPreguntas() {
        setContentPane(panelPrincipal);
        setTitle("Registro");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public void setBtnAceptar(JButton btnAceptar) {
        this.btnAceptar = btnAceptar;
    }

    public JTextField getTxtDia() {
        return txtDia;
    }

    public void setTxtDia(JTextField txtDia) {
        this.txtDia = txtDia;
    }

    public JComboBox getCbxMes() {
        return cbxMes;
    }

    public void setCbxMes(JComboBox cbxMes) {
        this.cbxMes = cbxMes;
    }

    public JTextField getTxtAnio() {
        return txtAnio;
    }

    public void setTxtAnio(JTextField txtAnio) {
        this.txtAnio = txtAnio;
    }

    public JLabel getLbPregunta1() {
        return lbPregunta1;
    }

    public void setLbPregunta1(JLabel lbPregunta1) {
        this.lbPregunta1 = lbPregunta1;
    }

    public JTextField getTxtPregunta1() {
        return txtPregunta1;
    }

    public void setTxtPregunta1(JTextField txtPregunta1) {
        this.txtPregunta1 = txtPregunta1;
    }

    public JLabel getLbPregunta2() {
        return lbPregunta2;
    }

    public void setLbPregunta2(JLabel lbPregunta2) {
        this.lbPregunta2 = lbPregunta2;
    }

    public JLabel getLbPregunta3() {
        return lbPregunta3;
    }

    public void setLbPregunta3(JLabel lbPregunta3) {
        this.lbPregunta3 = lbPregunta3;
    }

    public JTextField getTxtPregunta3() {
        return txtPregunta3;
    }

    public void setTxtPregunta3(JTextField txtPregunta3) {
        this.txtPregunta3 = txtPregunta3;
    }

    public JLabel getLbPregunta4() {
        return lbPregunta4;
    }

    public void setLbPregunta4(JLabel lbPregunta4) {
        this.lbPregunta4 = lbPregunta4;
    }

    public JTextField getTxtPregunta4() {
        return txtPregunta4;
    }

    public void setTxtPregunta4(JTextField txtPregunta4) {
        this.txtPregunta4 = txtPregunta4;
    }

    public JLabel getLbPregunta5() {
        return lbPregunta5;
    }

    public void setLbPregunta5(JLabel lbPregunta5) {
        this.lbPregunta5 = lbPregunta5;
    }

    public JTextField getTxtPregunta5() {
        return txtPregunta5;
    }

    public void setTxtPregunta5(JTextField txtPregunta5) {
        this.txtPregunta5 = txtPregunta5;
    }

    public JTextField getTxtPregunta6() {
        return txtPregunta6;
    }

    public void setTxtPregunta6(JTextField txtPregunta6) {
        this.txtPregunta6 = txtPregunta6;
    }

    public JLabel getLbPregunta6() {
        return lbPregunta6;
    }

    public void setLbPregunta6(JLabel lbPregunta6) {
        this.lbPregunta6 = lbPregunta6;
    }

    public JTextField getTxtPregunta2() {
        return txtPregunta2;
    }

    public void setTxtPregunta2(JTextField txtPregunta2) {
        this.txtPregunta2 = txtPregunta2;
    }

    public JLabel getLbPregunta7() {
        return lbPregunta7;
    }

    public void setLbPregunta7(JLabel lbPregunta7) {
        this.lbPregunta7 = lbPregunta7;
    }

    public JTextField getTxtPregunta7() {
        return txtPregunta7;
    }

    public void setTxtPregunta7(JTextField txtPregunta7) {
        this.txtPregunta7 = txtPregunta7;
    }

    public JTextField getTxtPregunta8() {
        return txtPregunta8;
    }

    public void setTxtPregunta8(JTextField txtPregunta8) {
        this.txtPregunta8 = txtPregunta8;
    }

    public JLabel getLbPregunta8() {
        return lbPregunta8;
    }

    public void setLbPregunta8(JLabel lbPregunta8) {
        this.lbPregunta8 = lbPregunta8;
    }

    public JLabel getLbPregunta9() {
        return lbPregunta9;
    }

    public void setLbPregunta9(JLabel lbPregunta9) {
        this.lbPregunta9 = lbPregunta9;
    }

    public JTextField getTxtPregunta9() {
        return txtPregunta9;
    }

    public void setTxtPregunta9(JTextField txtPregunta9) {
        this.txtPregunta9 = txtPregunta9;
    }

    public void setTxtPregunta10(JTextField txtPregunta10) {
        this.txtPregunta9 = txtPregunta10;
    }

    public JLabel getLbDia() {
        return lbDia;
    }

    public JLabel getLbMes() {
        return lbMes;
    }

    public JLabel getLbAnio() {
        return lbAnio;
    }

    public void limpiarCampos() {
        txtDia.setText("");
        txtAnio.setText("");
        cbxMes.setSelectedIndex(0);

        JTextField[] respuestas = getCamposRespuestas();
        for (JTextField campo : respuestas) {
            campo.setText("");
        }
    }
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
