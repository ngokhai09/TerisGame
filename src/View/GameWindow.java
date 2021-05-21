package View;

import Config.Constants;
import Controller.GameController;
import Model.ScoreRecord;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameWindow {

    public GameWindow() throws IOException {
        super();
        init();
    }
    GameController gamet;
    JFrame frame;

    public void init() throws IOException {
        ScoreRecord.loadDriver(); // Tải driver để kết nối với MySQL
        frame = new JFrame("Tetris Game");
        gamet  = new GameController();
        frame.add(gamet);
        frame.setSize(Constants.WIDTH, Constants.HEIGHT);
        frame.setResizable(false);

        SelectMenu menu = new SelectMenu();
        frame.setJMenuBar(menu);
        // Gán hành động cho nút new game
        menu.getNewGame().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gamet.newgame();

            }
        });


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}
