package View;

import Config.Constants;

import javax.swing.*;
import java.awt.*;

public class DrawGame extends JLabel {

    public DrawGame(){
        super();
        setFocusable(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);

        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 18; j++) {
                g.drawRect(i * 32, j * 32, 32, 32);
            }
        }

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, Constants.WIDTH, Constants.HEIGHT);

        repaint();
    }
}
