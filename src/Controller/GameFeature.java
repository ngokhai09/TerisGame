package Controller;

import java.awt.*;
import java.awt.event.KeyListener;

public interface GameFeature extends Runnable, KeyListener {
    public void newgame();
    public boolean endgame();
    public void checkpoint(Graphics g);
    public void exitgame();
}
