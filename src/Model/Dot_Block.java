package Model;

import java.awt.*;

public class Dot_Block implements Shape{
    private Square h;

    public Dot_Block(int x, int y) {
        super();
        h = new Square(x, y);
        h.setColor(0);
    }
    public Dot_Block() {
        super();
    }
    public Square getH() {
        return h;
    }

    public void setH(Square h) {
        this.h = h;
    }

    @Override
    public void move(int x, int y) {
        h.move(x, y);
    }

    @Override
    public void rotate() {
        return;

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        h.paint(g);

    }
    @Override
    public int lastvalue() {
        return h.getDiem().getY();
    }
    @Override
    public int maxright() {
        return this.h.getDiem().getX();
    }
    @Override
    public int maxleft() {
        return this.h.getDiem().getX();
    }
    @Override
    public boolean touch(Square game[][]) {
        if (h.touch(game)==true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    @Override
    public void addvalue(Square[][] game) {
        h.addvalue(game);

    }
    @Override
    public int rotate(int value, Square[][] game) {
        return value;

    }
    @Override
    public boolean touchl(Square[][] game) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean touchr(Square[][] game) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isrotate(Square[][] game) {
        // TODO Auto-generated method stub
        return false;
    }
}
