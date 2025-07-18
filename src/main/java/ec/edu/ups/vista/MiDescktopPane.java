package ec.edu.ups.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * Clase personalizada que extiende {@link JDesktopPane} para aplicar un fondo visual
 * atractivo con gradientes, patrones decorativos y un logo gráfico en el escritorio MDI.
 *
 * <p>Este escritorio personalizado se utiliza como contenedor de ventanas internas
 * en la aplicación principal. Incluye un fondo tipo "checker", un texto con el nombre
 * de la aplicación ("Amachon") y una flecha decorativa curvada debajo del texto.</p>
 *
 * @author Brandon
 * @version 1.0
 */
public class MiDescktopPane extends JDesktopPane {

    /**
     * Método sobrescrito que permite personalizar completamente el fondo del escritorio.
     *
     * @param g Objeto Graphics proporcionado por Swing.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // === Fondo degradado vertical ===
        GradientPaint fondoGradiente = new GradientPaint(
                0, 0, new Color(250, 240, 230),    // Color superior
                0, getHeight(), new Color(210, 180, 140) // Color inferior
        );
        g2d.setPaint(fondoGradiente);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // === Patrón decorativo de cajas ===
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

        // === Texto del logo "Amachon" ===
        String logoTexto = "Amachon";
        Font fontLogo = new Font("SansSerif", Font.BOLD, 80);
        g2d.setFont(fontLogo);
        FontMetrics fm = g2d.getFontMetrics();
        int anchoTexto = fm.stringWidth(logoTexto);
        int xTexto = (getWidth() - anchoTexto) / 2;
        int yTexto = getHeight() / 2;

        // Halo blanco radial detrás del texto
        RadialGradientPaint halo = new RadialGradientPaint(
                new Point(xTexto + anchoTexto / 2, yTexto - 20),
                180,
                new float[]{0f, 1f},
                new Color[]{new Color(255, 255, 255, 80), new Color(255, 255, 255, 0)}
        );
        g2d.setPaint(halo);
        g2d.fillOval(xTexto - 120, yTexto - 130, anchoTexto + 240, 240);

        // Sombra del texto
        g2d.setColor(new Color(0, 0, 0, 80));
        g2d.drawString(logoTexto, xTexto + 4, yTexto + 4);

        // Texto principal del logo
        g2d.setColor(new Color(35, 47, 62)); // Azul oscuro
        g2d.drawString(logoTexto, xTexto, yTexto);

        // === Línea curva con flecha debajo del texto ===
        int xInicio = xTexto + fm.stringWidth("A") / 2;
        int xFin = xTexto + anchoTexto - fm.stringWidth("n") / 2;
        int yBase = yTexto + 12;
        int ctrlX = (xInicio + xFin) / 2;
        int ctrlY = yTexto + 75;

        g2d.setStroke(new BasicStroke(7f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(new Color(255, 145, 0)); // Naranja vibrante

        QuadCurve2D curva = new QuadCurve2D.Float();
        curva.setCurve(xInicio, yBase, ctrlX, ctrlY, xFin, yBase);
        g2d.draw(curva);

        // Flecha al final de la curva
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
