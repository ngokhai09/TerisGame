package Model;

import java.awt.*;

public class Square implements Shape{
    private Point diem;
    private boolean rong = true;
    private int color=-1;
    /*
     * Red: 1 - V
     * Blue: 2 - N
     * Cyan: 3 - Lr
     * White:0 - cham
     * Yellow:4 - T
     * Green: 5 - I
     * pink: 6 - L
     * orange: 7 - Lr
     */
    private int size=20;
    public boolean isRong() {
        return rong;
    }
    public void setRong(boolean rong) {
        this.rong = rong;
        /*
         * true la co rong
         * false la khong rong
         */
    }
    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }
    @Override
    public void move(int x, int y) {
        diem.setX(x);
        diem.setY(y);

    }
    public Square() {
        diem =new Point();
        this.rong=true;
        this.color=-1;
        this.rong=false;
    }
    public Square(Point diem, int size) {
        diem =new Point();
        this.diem = diem;
        this.size = size;
    }
    public Square(int x,int y,int size) {
        super();
        diem =new Point(x,y);
        this.size=size;
    }
    public Square(int x,int y) {
        super();
        diem =new Point(x,y);
        this.rong=true;
    }
    @Override
    public void rotate() {
        return;

    }
    public Point getDiem() {
        return diem;
    }
    public void setDiem(Point diem) {
        this.diem = diem;
    }
    public void setDiem(int x,int y) {
        this.diem.setX(x);
        this.diem.setY(y);
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    @Override
    public String toString() {
        return "("+diem.getX()+","+diem.getY()+")";
    }
    @Override
    public void paint(Graphics g) {

        g.fillRect(diem.getX(), diem.getY(), this.size-2, this.size-2);

    }
    public void paint(Graphics g,int D)
    {
        g.fillRect(diem.getX(), diem.getY(), this.size-D, this.size-D);
    }
    @Override
    public int lastvalue() {
        return diem.getY();
    }
    @Override
    public int maxright() {
        return diem.getX();
    }
    @Override
    public int maxleft() {
        return diem.getX();
    }
    @Override
    public boolean touch(Square[][] game) {
        return diem.touch(game);
    }
    @Override
    public void addvalue(Square[][] game) {
        int i =(int)diem.getX()/this.size;
        int j = (int)diem.getY()/this.size;
        if (i<0||j<0||j>23||i>11)
        {

        }
        else
        {
            game[j][i]=this;

        }
    }
    @Override
    public int rotate(int value,Square game[][]) {
        return value;

    }
    @Override
    public boolean touchl(Square[][] game) {

        return diem.touchl(game);
    }
    @Override
    public boolean touchr(Square[][] game) {
        return diem.touchr(game);
    }
    @Override
    public boolean isrotate(Square[][] game) {
        return false;
    }

}
