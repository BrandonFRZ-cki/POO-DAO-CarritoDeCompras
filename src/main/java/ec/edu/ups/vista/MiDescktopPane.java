package ec.edu.ups.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.QuadCurve2D;

public class MiDescktopPane extends JDesktopPane {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo con patrón de cajas apiladas
        int anchoCaja = 60;
        int altoCaja = 40;
        Color colorCaja = new Color(210, 180, 140); // color cartón claro
        Color sombraCaja = new Color(180, 150, 110);

        for (int y = 0; y < getHeight(); y += altoCaja) {
            for (int x = (y / altoCaja) % 2 == 0 ? 0 : -anchoCaja / 2; x < getWidth(); x += anchoCaja) {
                g2d.setColor(colorCaja);
                g2d.fillRect(x, y, anchoCaja, altoCaja);
                g2d.setColor(sombraCaja);
                g2d.drawRect(x, y, anchoCaja, altoCaja);
            }
        }

        // Logo "Amachon"
        String logoTexto = "Amachon";
        Font fontLogo = new Font("SansSerif", Font.BOLD, 48);
        g2d.setFont(fontLogo);
        FontMetrics fm = g2d.getFontMetrics();
        int anchoTexto = fm.stringWidth(logoTexto);
        int xTexto = (getWidth() - anchoTexto) / 2;
        int yTexto = getHeight() / 2;

        g2d.setColor(new Color(35, 47, 62)); // gris oscuro
        g2d.drawString(logoTexto, xTexto, yTexto);

        // Flecha estilo "Amazon"
        int x1 = xTexto + fm.stringWidth("A");
        int x2 = xTexto + anchoTexto - fm.stringWidth("n") / 2;
        int yFlecha = yTexto + 10;
        int ctrlX = (x1 + x2) / 2;
        int ctrlY = yTexto + 30;

        g2d.setStroke(new BasicStroke(3f));
        g2d.setColor(new Color(255, 153, 0)); // naranja tipo Amazon

        // Dibujar curva (sonrisa/flecha)
        QuadCurve2D flecha = new QuadCurve2D.Float();
        flecha.setCurve(x1, yFlecha, ctrlX, ctrlY, x2, yFlecha);
        g2d.draw(flecha);

        // Punta de flecha
        Polygon punta = new Polygon();
        punta.addPoint(x2, yFlecha);
        punta.addPoint(x2 - 10, yFlecha - 6);
        punta.addPoint(x2 - 10, yFlecha + 6);
        g2d.fill(punta);

        g2d.dispose();
    }
}