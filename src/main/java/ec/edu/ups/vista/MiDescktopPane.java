package ec.edu.ups.vista;

import javax.swing.*;
import java.awt.*;

public class MiDescktopPane extends JDesktopPane {

    public MiDescktopPane() {
        super();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        setBackground(Color.BLUE);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        // dibuja la cara
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(210, 210, 200, 200);
        // dibuja los ojos
        g2d.setColor(Color.BLACK); g2d.fillOval(255, 265, 30, 30);
        g2d.fillOval(335, 265, 30, 30);
        // dibuja la boca
        g2d.fillOval(250, 310, 120, 60);
    }
}
