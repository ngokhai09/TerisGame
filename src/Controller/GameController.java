package Controller;

import Config.Constants;
import Model.*;
import Model.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Random;

public class GameController extends JPanel implements GameFeature, Runnable {
    // Các biến mặc định
    private static int score;
    public ScoreRecord scoreRecord = new ScoreRecord();
    private Random random;
    private Graphics g;

    // New Game
    private int xrun = Constants.sizex / 2 + 1, yrun = 21;
    private Square[][] game = new Square[(Constants.sizey + Constants.size) / Constants.size + 10][(Constants.sizex + Constants.size) / Constants.size + 10];


    // Các biến luồng
    private Thread thread1;
    private Thread thread2;
    //Chạy Game
    Shape shape;
    public int rdh = 0, rdn = 0;

    public GameController() {
        super();
        setFocusable(true);
        addKeyListener(this);
        setPreferredSize(new Dimension(Constants.sizex + 240, Constants.sizey + 1));
        this.setLayout(null);
        thread1 = new Thread(this);
        this.newgame();
        thread1.start();

    }

    @Override
    public void run() {
        rdh = 1 + random.nextInt(7);
        while (endgame() == false) {
            int row = 0;
            int check = 0;
            xrun = Constants.sizex / 2 + 1;
            yrun = 0;
            switch (rdh) {
                case 1:
                    shape = new Square_Block(xrun, yrun);
                    break;
                case 2:
                    shape = new N_Block(xrun, yrun);
                    break;
                case 3:
                    shape = new Lr_Block(xrun, yrun);
                    break;
                case 4:
                    shape = new T_Block(xrun, yrun);
                    break;
                case 5:
                    shape = new I_Block(xrun, yrun);
                    break;
                case 6:
                    shape = new L_Block(xrun, yrun);
                    break;
                case 7:
                    shape = new Nl_Block(xrun, yrun);
                    break;
            }
            rdh = 1 + random.nextInt(7);
            rdn = rdh;
            while (true) {
                try {
                    thread1.sleep(Constants.TIME);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                repaint();
                if (shape == null) {
                    break;
                }
                if (shape.touch(game) == true) {
                    shape.addvalue(game);
                    break;
                }
                if (shape.lastvalue() + Constants.size > Constants.sizey - 10) {
                    shape.addvalue(game);
                    break;
                }
                yrun += Constants.size;
                shape.move(xrun, yrun);

            }

        }
        try {
            scoreRecord.writeHightScore();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "You Loss", "End Game", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            if (shape.maxleft() == 1 || shape.touch(game)) {
            } else {
                xrun -= Constants.size;
            }
            shape.move(xrun, yrun);
        }
        if (key == KeyEvent.VK_RIGHT) {
            if (shape.maxright() + Constants.size == Constants.size + 1 || shape.touchr(game)) {
            } else {
                xrun += Constants.size;
            }
            shape.move(xrun, yrun);
        }
        if (key == KeyEvent.VK_DOWN) {
            if (shape.lastvalue() + Constants.size > Constants.sizey - 19 || shape.touch(game)) {
            } else {
                yrun += Constants.size;
            }
            shape.move(xrun, yrun);
        }
        if (key == KeyEvent.VK_UP) {
            if (shape.lastvalue() > 440) {
                return;
            }
            xrun = shape.rotate(xrun, game);
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void newgame() {
        random = new Random();
        if (thread2 != null) {
            thread2 = null;
        }
        shape = null;
        for (int i = 0; i <= Constants.maxY; i++) {
            for (int j = 0; j < Constants.maxX; j++) {
                game[i][j] = new Square(j * 20 + 1, i * 20 + 1);
            }
        }
        repaint();
        score = 0;
        xrun = Constants.sizex / 2 + 1;
        yrun = 0;
    }

    @Override
    public boolean endgame() {
        for (int i = 0; i < Constants.maxX; i++) {
            if (!game[0][i].isRong()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void checkpoint(Graphics g) {
        int s = 19;
        int slt = 0;
        for (int i = 0; i < Constants.maxY; i++) {
            int cnt = 0;
            for (int j = 0; j < Constants.maxX; j++) {
                if (game[i][j].isRong() == false) {
                    cnt++;
                }
            }
            if (cnt == Constants.maxX) {
            	g.setColor(Color.GRAY);
				g.fillRect(1, i*20+1, Constants.sizex, 20);
                for (int k = i; k > 0; k--) {
                    for (int j = 0; j < Constants.maxX; j++) {
                        game[k][j].setColor(game[k - 1][j].getColor());
                        game[k][j].setRong(game[k - 1][j].isRong());
                    }
                }
                for (int j = 0; j < Constants.maxX; j++) {
                    game[0][j] = new Square(j * 20 + 1, i * 20 + 1);
                }
                printColor(g);
                score += 500;
                slt++;
            }
        }
        scoreRecord.setScore(score);
    }



    @Override
    public void exitgame() {
        System.exit(0);
    }
    public void paint(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(1, 1, Constants.sizex + 240, Constants.sizey + 1);
        Grid(g);
        try {
            Control(g);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (shape == null) {
            return;
        } else {
            shape.paint(g);
        }
        checkpoint(g);
        int s = 18;
        for(int i = 0; i < Constants.maxY; i++){
            for(int j = 0; j < Constants.maxX; j++){
                int tx = game[i][j].getDiem().getX();
                int ty = game[i][j].getDiem().getY();
                if(game[i][j].getColor() > 0){
                    if (game[i][j].getColor()==1)
                    {
                        g.setColor(Color.RED);
                        g.fillRect(tx, ty, s, s);
                    }
                    if (game[i][j].getColor()==2)
                    {
                        g.setColor(Color.BLUE);
                        g.fillRect(tx, ty, s, s);
                    }
                    if (game[i][j].getColor()==3)
                    {
                        g.setColor(Color.CYAN);
                        g.fillRect(tx, ty, s, s);
                    }
                    if (game[i][j].getColor()==4)
                    {
                        g.setColor(Color.YELLOW);
                        g.fillRect(tx, ty, s, s);
                    }
                    if (game[i][j].getColor()==5)
                    {
                        g.setColor(Color.GREEN);
                        g.fillRect(tx, ty, s, s);
                    }
                    if (game[i][j].getColor()==6)
                    {
                        g.setColor(Color.PINK);
                        g.fillRect(tx, ty, s, s);
                    }
                    if (game[i][j].getColor()==7)
                    {
                        g.setColor(Color.ORANGE);
                        g.fillRect(tx, ty, s, s);
                    }
                }
            }
        }
    }
    public void printColor(Graphics g){
        int s = 18;
        for(int i = 0; i < Constants.maxY; i++){
            for(int j = 0; j < Constants.maxX; j++){
                int tx = game[i][j].getDiem().getX();
                int ty = game[i][j].getDiem().getY();
                if(game[i][j].getColor() > 0){
                    if (game[i][j].getColor()==1)
                    {
                        g.setColor(Color.RED);
                        g.fillRect(tx, ty, s, s);
                    }
                    if (game[i][j].getColor()==2)
                    {
                        g.setColor(Color.BLUE);
                        g.fillRect(tx, ty, s, s);
                    }
                    if (game[i][j].getColor()==3)
                    {
                        g.setColor(Color.CYAN);
                        g.fillRect(tx, ty, s, s);
                    }
                    if (game[i][j].getColor()==4)
                    {
                        g.setColor(Color.YELLOW);
                        g.fillRect(tx, ty, s, s);
                    }
                    if (game[i][j].getColor()==5)
                    {
                        g.setColor(Color.GREEN);
                        g.fillRect(tx, ty, s, s);
                    }
                    if (game[i][j].getColor()==6)
                    {
                        g.setColor(Color.PINK);
                        g.fillRect(tx, ty, s, s);
                    }
                    if (game[i][j].getColor()==7)
                    {
                        g.setColor(Color.ORANGE);
                        g.fillRect(tx, ty, s, s);
                    }
                }
            }
        }
    }

    public void Grid(Graphics g){
        g.setColor(Color.WHITE);
        for(int i = 0; i <= Constants.maxX; i++){
            g.drawLine(i * Constants.size + 1, 1, i * Constants.size + 1, 481);
        }
        for(int i = 0; i <= Constants.maxY; i++){
            g.drawLine(1, i * Constants.size + 1, 241, i * Constants.size + 1);
        }
    }
    public void Control(Graphics g) throws SQLException {
        Font font = new Font("ToolTipText", 1,14);
        g.setFont(font);
        // Váº½ 1 hÃ¬nh vuÃ´ng hiá»ƒn thá»‹ hÃ¬nh tiáº¿p theo
        paintnext(g);
        g.setColor(Color.RED);
        g.drawRect(261,50,100,100);
        g.setColor(Color.WHITE);
        g.drawLine(242,1,242,481);
        g.setColor(Color.WHITE);
        String s = "Score: " + Integer.toString(scoreRecord.getScore());
        g.drawString(s, 261, 200);
        g.drawString("Next", 281, 40);
        g.drawString("→: Go Right", 261, 240);
        g.drawString("↑: Go Up", 261, 280);
        g.drawString("↓: Go Down", 261, 320);
        g.drawString("←: Go Left", 261, 360);
        scoreRecord.setHighsocre();
        g.drawString("HighScore: " + Integer.toString(scoreRecord.getHighsocre()), 261, 420);
    }
    public void paintnext(Graphics g){
        Shape square;
        switch (rdn) {
            case 1:
                square = new Square_Block(301,121);
                square.paint(g);
                break;
            case 2:
                square = new N_Block(301,121);
                square.paint(g);
                break;
            case 3:
                square = new Lr_Block(301,121);
                square.paint(g);
                break;
            case 4:
                square = new T_Block(301,121);
                square.paint(g);
                break;
            case 5:
                square = new I_Block(301,121);
                square.paint(g);
                break;
            case 6:
                square = new L_Block(301,121);
                square.paint(g);
                break;
            case 7:
                square = new Nl_Block(301,121);
                square.paint(g);
                break;
            default:
                break;
        }
    }


}
