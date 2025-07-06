package ec.edu.ups.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class MiDescktopPane extends JDesktopPane {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint fondoGradiente = new GradientPaint(
                0, 0, new Color(250, 240, 230),
                0, getHeight(), new Color(210, 180, 140)
        );
        g2d.setPaint(fondoGradiente);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        int anchoCaja = 60;
        int altoCaja = 40;
        Color colorCaja = new Color(210, 180, 140);
        Color sombraCaja = new Color(180, 150, 110);
        for (int y = 0; y < getHeight(); y += altoCaja) {
            for (int x = (y / altoCaja) % 2 == 0 ? 0 : -anchoCaja / 2; x < getWidth(); x += anchoCaja) {
                g2d.setColor(colorCaja);
                g2d.fillRect(x, y, anchoCaja, altoCaja);

                GradientPaint sombra = new GradientPaint(x, y, sombraCaja, x + anchoCaja, y + altoCaja, colorCaja);
                g2d.setPaint(sombra);
                g2d.drawRect(x, y, anchoCaja, altoCaja);
            }
        }

        String logoTexto = "Amachon";
        Font fontLogo = new Font("SansSerif", Font.BOLD, 80);
        g2d.setFont(fontLogo);
        FontMetrics fm = g2d.getFontMetrics();
        int anchoTexto = fm.stringWidth(logoTexto);
        int xTexto = (getWidth() - anchoTexto) / 2;
        int yTexto = getHeight() / 2;

        RadialGradientPaint halo = new RadialGradientPaint(
                new Point(xTexto + anchoTexto / 2, yTexto - 20),
                180,
                new float[]{0f, 1f},
                new Color[]{new Color(255, 255, 255, 80), new Color(255, 255, 255, 0)}
        );
        g2d.setPaint(halo);
        g2d.fillOval(xTexto - 120, yTexto - 130, anchoTexto + 240, 240);

        g2d.setColor(new Color(0, 0, 0, 80));
        g2d.drawString(logoTexto, xTexto + 4, yTexto + 4);

        g2d.setColor(new Color(35, 47, 62));
        g2d.drawString(logoTexto, xTexto, yTexto);

        int xInicio = xTexto + fm.stringWidth("A") / 2;
        int xFin = xTexto + anchoTexto - fm.stringWidth("n") / 2;
        int yBase = yTexto + 12;
        int ctrlX = (xInicio + xFin) / 2;
        int ctrlY = yTexto + 75;

        g2d.setStroke(new BasicStroke(7f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(new Color(255, 145, 0));

        QuadCurve2D curva = new QuadCurve2D.Float();
        curva.setCurve(xInicio, yBase, ctrlX, ctrlY, xFin, yBase);
        g2d.draw(curva);

        Path2D flechaPerfecta = new Path2D.Float();
        int puntaLargo = 14;
        int puntaAlto = 8;

        flechaPerfecta.moveTo(xFin, yBase);
        flechaPerfecta.lineTo(xFin - puntaLargo, yBase - puntaAlto / 2);
        flechaPerfecta.curveTo(
                xFin - puntaLargo + 1, yBase,
                xFin - puntaLargo + 1, yBase,
                xFin - puntaLargo, yBase + puntaAlto / 2
        );
        flechaPerfecta.closePath();

        g2d.setColor(new Color(255, 145, 0));
        g2d.fill(flechaPerfecta);

        g2d.dispose();
    }
}