package View;

import javax.swing.*;
import java.awt.*;

public class DrawPreview extends JLabel {
    protected void paintComponent(Graphics g){
        super.paintComponents(g);

        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                g.drawRect(i * 32 + 32, j * 32, 32, 32);
            }
        }

        g.setColor(Color.BLACK);
        g.drawRect(32, 0, 128, 128);

        repaint();
    }
}
