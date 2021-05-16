package Model;

import java.awt.*;

public class I_Block implements Shape{
    private Square h1,h2,h3,h4;
    /*
     * quản lý hình chữ I theo ô vuông thứ 4
     * [1]
     * [2]
     * [3]
     * [4]
     * gia tri khởi tạo là ô vuông thứ 4
     */
    public I_Block() {
        super();
    }
    public I_Block(int x, int y) {
        h4 = new Square(x,y);
        h3 = new Square(x,y-h4.getSize());
        h2 = new Square(x,y-h4.getSize()*2);
        h1 = new Square(x,y-h4.getSize()*3);
        h1.setColor(5);
        h2.setColor(5);
        h3.setColor(5);
        h4.setColor(5);
        h1.setRong(false);
        h2.setRong(false);
        h3.setRong(false);
        h4.setRong(false);
    }

    public Square getH1() {
        return h1;
    }

    public void setH1(Square h1) {
        this.h1 = h1;
    }

    public Square getH2() {
        return h2;
    }

    public void setH2(Square h2) {
        this.h2 = h2;
    }

    public Square getH3() {
        return h3;
    }

    public void setH3(Square h3) {
        this.h3 = h3;
    }

    public Square getH4() {
        return h4;
    }

    public void setH4(Square h4) {
        this.h4 = h4;
    }
    @Override
    public void move(int x, int y) {
        /*
         * di chuyển theo thằng 3 hết.
         */
        int size = h2.getSize();
        if (h2.getDiem().getX()==h3.getDiem().getX())
        {
            h3.move(x, y);
            h2.move(x, y-size);
            h1.move(x, y-size*2);
            h4.move(x, y+size);
        }
        else
        {
            h3.move(x, y);
            h2.move(x+size, y);
            h1.move(x+size*2, y);
            h4.move(x-size, y);
        }

    }

    @Override
    public void rotate() {
        /*
         * [1]
         * [2]
         * [*] → [4][*][2][1]
         * [4]
         */
        int x = h3.getDiem().getX();
        int y = h3.getDiem().getY();
        int size = h3.getSize();
        if (x==h2.getDiem().getX())
        {
            h1.move(x+size*2, y);
            h2.move(x+size, y);
            h4.move(x-size, y);
        }
        else
        {
            h1.move(x, y-size*2);
            h2.move(x, y-size);
            h4.move(x, y+size);
        }

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        h1.paint(g);
        h2.paint(g);
        h3.paint(g);
        h4.paint(g);

    }
    @Override
    public int lastvalue() {
        return h4.getDiem().getY();
    }
    @Override
    public int maxright() {
        /*
         * trả về 1 nếu ngang
         */
        if (h2.getDiem().getX()==h3.getDiem().getX())
        {
            return h1.getDiem().getX();
        }
        else
        {
            return h1.getDiem().getX();
        }
    }
    @Override
    public int maxleft() {
        /*
         * trả về 4 nếu ngang
         */
        if (h2.getDiem().getX()==h3.getDiem().getX())
        {
            return h1.getDiem().getX();
        }
        else
        {
            return h4.getDiem().getX();
        }
    }
    @Override
    public boolean touch(Square game[][]) {
        if (h2.getDiem().getX()==h3.getDiem().getX())
        {
            if (h4.touch(game))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if (h4.touch(game)||h3.touch(game)||h2.touch(game)||h1.touch(game))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    @Override
    public void addvalue(Square[][] game) {
        h1.addvalue(game);
        h2.addvalue(game);
        h3.addvalue(game);
        h4.addvalue(game);

    }
    @Override
    public int rotate(int value, Square[][] game) {
        int x = h3.getDiem().getX();
        int y = h3.getDiem().getY();
        int i =(int) h3.getDiem().getX()/20;
        int j = (int)h3.getDiem().getY()/20;
        int size = h3.getSize();
        if (this.touchl(game)&&this.touchr(game))
            /*
             * neu left và right deu khong di chuyen duoc
             */
        {
            if (this.isrotate(game))
            {
                /*
                 * neu xoay duoc thi xoay
                 */
                this.rotate();
                return value;
            }
            else
            {
                return value;
            }
        }
        else
        {
            if (this.isrotate(game))
            {
                //xoay duoc thi xoay
                this.rotate();
                return value;
            }
            else
            {
                if (x == h2.getDiem().getX())
                {
                    if (h3.touchl(game))
                    {
                        /*
                         * mac trai
                         */
                        if(game[j][i+1].isRong()&&game[j][i+2].isRong()&&game[j][i+3].isRong())
                        {
                            h3.move(x+size, y);
                            h4.move(x, y);
                            h1.move(x+size*4, y);
                            h2.move(x+size*2, y);
                            return x+size;
                        }
                        else
                        {
                            return value;
                        }
                    }
                    else if(h3.touchr(game))
                    {
                        /*
                         * mac phai khong cach
                         * tuc la can 3 o trong phia trai
                         */
                        if (game[j][i-1].isRong()&&game[j][i-2].isRong()&&game[j][i-3].isRong())
                        {
                            h1.move(x, y);
                            h2.move(x-size, y);
                            h3.move(x-size*2, y);
                            h4.move(x-size*3, y);
                            return x-size*2;
                        }
                        else
                        {
                            return value;
                        }
                    }
                    else
                    {
                        /*
                         * cach 2 o hoac bi mac trái
                         * tuc la can 2 o phia trai de xoay duoc
                         * va o ngoai cung cung phai rong
                         */
                        if (game[j][i-1].isRong()&&game[j][i+1].isRong()&&game[j][i-2].isRong())
                        {
                            h1.move(x+size, y);
                            h2.move(x, y);
                            h3.move(x-size, y);
                            h4.move(x-size*2, y);
                            return x- size;
                        }
                        else
                        {
                            return value;
                        }
                    }
                }
                else
                {
                    /*
                     * khi chu i la hang ngang thi khong co truong hop nao
                     *
                     */
                    return value;
                }
            }
        }

    }
    @Override
    public boolean touchl(Square[][] game) {
        if (h2.getDiem().getX()==h3.getDiem().getX())
        {
            if (h4.touchl(game)||h3.touchl(game)||h2.touchl(game)||h1.touchl(game))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if (h4.touchl(game))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    @Override
    public boolean touchr(Square[][] game) {
        if (h2.getDiem().getX()==h3.getDiem().getX())
        {
            if (h4.touchr(game)||h3.touchr(game)||h2.touchr(game)||h1.touchr(game))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if (h1.touchr(game))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    @Override
    public boolean isrotate(Square[][] game) {
        /*
         * true la xoay duoc
         * false la khong xoay duoc
         */
        int x =(int)h3.getDiem().getX()/20;
        int y =(int)h3.getDiem().getY()/20;
        int size = h3.getSize();
        if (h2.getDiem().getX()==h3.getDiem().getX())
        {
            if (h3.touchl(game)||h3.touchr(game))
            {
                return false;
            }
            else
            {
                if (x>9)
                {
                    return false;
                }
                if (game[y][x-1].isRong()==true&&game[y][x+1].isRong()==true&&game[y][x+2].isRong()==true)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            if (h3.touch(game))
            {
                return false;
            }
            if (game[y-1][x].isRong()&&game[y-2][x].isRong()&&game[y+1][x].isRong())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
