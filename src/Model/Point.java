package Model;

import java.awt.*;

public class Point implements Shape{
    @Override
    public String toString() {
        return "Diem [x=" + x + ", y=" + y + "]";
    }

    private int x,y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point() {
        super();
    }

    public Point(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public void move(int x, int y) {
        this.x=x;
        this.y=y;

    }

    @Override
    public void rotate() {
        return;

    }

    @Override
    public void paint(Graphics g) {
        return;

    }

    @Override
    public int lastvalue() {
        return y;
    }

    @Override
    public int maxright() {
        return x;
    }

    @Override
    public int maxleft() {

        return x;
    }

    @Override
    public boolean touch(Square game[][]) {
        int xt = (int)x/20;
        int yt = (int)y/20;
        if (yt<0)
        {
            return false;
        }
        if (xt<0||xt>11||yt+1>23)
        {
            return true;
        }
        if (game[yt+1][xt].isRong()==true)
        {
            return false;
        }
        else {
            return true;
        }

    }

    @Override
    public void addvalue(Square[][] game) {
        return;

    }

    @Override
    public int rotate(int value,Square game[][]) {
        return value;

    }

    @Override
    public boolean touchl(Square[][] game) {
        int xt = (int)x/20;
        int yt = (int)y/20;
        if (xt-1<0||yt<0||xt>11||yt>23)
        {
            return true;
        }
        if (game[yt][xt-1].isRong()==true)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    @Override
    public boolean touchr(Square[][] game) {
        int xt = (int)x/20;
        int yt = (int)y/20;
        if (xt<0||yt<0||xt>=11||yt>23)
        {
            return true;
        }
        if (game[yt][xt+1].isRong()==true)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public boolean isrotate(Square[][] game) {
        return false;
    }
}
