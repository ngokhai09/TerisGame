package Model;

import java.awt.Color;
import java.awt.Graphics;

public class L_Block implements Shape{
	
	private Square h1,h2,h3,h4;
	/*
	 * quản lý theo cơ chế thằng h3 theo hình:
	 * [1][2]
	 *    [3]
	 *    [4]
	 *    hàm tạo nhận giá trị cho h4 trước
	 */

	@Override
	public String toString() {
		return "KhoiHinhL [h1=" + h1 + ", h2=" + h2 + ", h3=" + h3 + ", h4=" + h4 + "]";
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

	public L_Block() {
		super();
	}
	public L_Block(int x, int y) {
		h4 = new Square(x,y);
		h3 = new Square(x,y-h4.getSize());
		h2 = new Square(x,y-h4.getSize()*2);
		h1 = new Square(x-h4.getSize(), y-h4.getSize()*2);
		h1.setColor(6);
		h2.setColor(6);
		h3.setColor(6);
		h4.setColor(6);
		h1.setRong(false);
		h2.setRong(false);
		h3.setRong(false);
		h4.setRong(false);
	}
	@Override
	public void move(int x, int y) {
		/*
		 * dong nhat move theo h3
		 */
		int size  = h4.getSize();
		if (h3.getDiem().getX()==h2.getDiem().getX())
		{
			if (h3.getDiem().getY()>h2.getDiem().getY())
			{
				h3.move(x, y);
				h1.move(x-size, y-size);
				h2.move(x, y-size);
				h4.move(x, y+size);
			}
			else
			{
				h3.move(x, y);
				h1.move(x+size, y+size);
				h2.move(x, y+size);
				h4.move(x, y-size);
			}
		}
		else
		{
			if (h3.getDiem().getX()>h2.getDiem().getX())
			{
				h3.move(x, y);
				h2.move(x-size, y);
				h4.move(x+size, y);
				h1.move(x-size, y+size);
			}
			else
			{
				h3.move(x, y);
				h2.move(x+size, y);
				h4.move(x-size, y);
				h1.move(x+size, y-size);
			}
		}
		
	}

	@Override
	public void rotate() {
		/*
		 *[1][2]                  [4]	   	    [1]
		 *   [3]   → [2][3][4] →  [3]   → [4][3][2]
		 *   [4]     [1]       [1][2]
		 */
		int size = h3.getSize();
		int x = h3.getDiem().getX();
		int y = h3.getDiem().getY();
		if (x==h2.getDiem().getX())
		{
			if (y>h2.getDiem().getY())
			{
				h4.move(x+size, y);
				h2.move(x-size, y);
				h1.move(x-size, y+size);
			}
			else
			{
				h4.move(x-size, y);
				h2.move(x+size, y);
				h1.move(x+size, y-size);
			}
		}
		else
		{
			if (x>h2.getDiem().getX())
			{
				h2.move(x, y+size);
				h4.move(x, y-size);
				h1.move(x+size, y+size);
			}
			else
			{
				h4.move(x, y+size);
				h2.move(x, y-size);
				h1.move(x-size, y-size);
			}
		}
		
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.pink);
		h1.paint(g);
		h2.paint(g);
		h3.paint(g);
		h4.paint(g);
		
	}

	@Override
	public int lastvalue() {
		int size = h3.getSize();
		int x = h3.getDiem().getX();
		int y = h3.getDiem().getY();
		if (x==h2.getDiem().getX())
		{
			if (y>h2.getDiem().getY())
			{
				return h4.getDiem().getY();
			}
			else
			{
				return h2.getDiem().getY();
			}
		}
		else
		{
			if (x>h2.getDiem().getX())
			{
				return h1.getDiem().getY();
			}
			else
			{
				return y;
			}
		}
	}

	@Override
	public int maxright() {
		if (h3.getDiem().getX()==h2.getDiem().getX())
		{
			if (h3.getDiem().getY()>h2.getDiem().getY())
			{
				return h2.getDiem().getX();
			}
			else
			{
				return h1.getDiem().getX();
			}
		}
		else
		{
			if (h3.getDiem().getX()>h2.getDiem().getX())
			{
				return h4.getDiem().getX();
			}
			else
			{
				return h1.getDiem().getX();
			}
		}
	}

	@Override
	public int maxleft() {
		if (h3.getDiem().getX()==h2.getDiem().getX())
		{
			if (h3.getDiem().getY()>h2.getDiem().getY())
			{
				return h1.getDiem().getX();
			}
			else
			{
				return h2.getDiem().getX();
			}
		}
		else
		{
			if (h3.getDiem().getX()>h2.getDiem().getX())
			{
				return h2.getDiem().getX();
			}
			else
			{
				return h4.getDiem().getX();
			}
		}
	}

	@Override
	public boolean touch(Square game[][]) {
		if (h2.getDiem().getX()==h3.getDiem().getX())
		{
			if (h2.getDiem().getY()<h3.getDiem().getY())
			{
				if (h1.touch(game)||h4.touch(game))
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
				if (h1.touch(game)||h2.touch(game))
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
			if (h3.getDiem().getX()>h2.getDiem().getX())
			{
				if (h1.touch(game)||h3.touch(game)||h4.touch(game))
				{
					return true;
				}else
				{
					return false;
				}
			}
			else
			{
				if(h2.touch(game)||h3.touch(game)||h4.touch(game))
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

	@Override
	public void addvalue(Square[][] game) {
		h1.addvalue(game);
		h2.addvalue(game);
		h3.addvalue(game);
		h4.addvalue(game);
	}

	@Override
	public int rotate(int value, Square[][] game) {
		int size = h3.getSize();
		int x =h3.getDiem().getX();
		int y =h3.getDiem().getY();
		int i = (int)x/size;
		int j = (int)y/size;
		if (this.touchl(game)&&this.touchr(game))
		{
			if (this.isrotate(game))
			{
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
				this.rotate();
				return value;
			}
			else
			{
				if (x==h2.getDiem().getX())
				{
					if (y>h2.getDiem().getY())
					{
						if (h3.touchr(game))
						{
							if (game[j][i-1].isRong()&&game[j][i-2].isRong()&&game[j+1][i-2].isRong())
							{
								h4.move(x, y);
								h3.move(x-size, y);
								h2.move(x-size*2, y);
								h1.move(x-size*2, y+size);
								return x-size;
							}
							else
							{
								return value;
							}
						}
						else
						{
							return value;
						}
					}
					else
					{
						if (h3.touchl(game))
						{
							if (game[j][i+1].isRong()&&game[j][i+2].isRong()&&game[j-1][i+2].isRong())
							{
								h4.move(x, y);
								h3.move(x+size, y);
								h2.move(x+size*2, y);
								h1.move(x+size*2, y-size);
								return x+size;
							}
							else
							{
								return value;
							}
						}
						else
						{
							return value;
						}
					}
				}
				else
				{
					return value;
				}
			}
		}
		
		
	}

	@Override
	public boolean touchl(Square[][] game) {
		if (h2.getDiem().getX()==h3.getDiem().getX())
		{
			if (h2.getDiem().getY()<h3.getDiem().getY())
			{
				if (h1.touchl(game)||h4.touchl(game)||h3.touchl(game))
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
				if (h3.touchl(game)||h2.touchl(game)||h4.touchl(game))
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
			if (h3.getDiem().getX()>h2.getDiem().getX())
			{
				if (h1.touchl(game)||h2.touchl(game))
				{
					return true;
				}else
				{
					return false;
				}
			}
			else
			{
				if(h1.touchl(game)||h4.touchl(game))
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

	@Override
	public boolean touchr(Square[][] game) {
		if (h2.getDiem().getX()==h3.getDiem().getX())
		{
			if (h2.getDiem().getY()<h3.getDiem().getY())
			{
				if (h3.touch(game)||h4.touch(game)||h2.touch(game))
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
				if (h1.touch(game)||h3.touch(game)||h4.touch(game))
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
			if (h3.getDiem().getX()>h2.getDiem().getX())
			{
				if (h1.touch(game)||h4.touch(game))
				{
					return true;
				}else
				{
					return false;
				}
			}
			else
			{
				if(h2.touch(game)||h1.touch(game))
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
	@Override
	public boolean isrotate(Square[][] game) {
		int x = h3.getDiem().getX();
		int y = h3.getDiem().getY();
		int size = h3.getSize();
		int i = (int)x/20;
		int j= (int)y/20;
		if (x==h2.getDiem().getX())
		{
			/*
			 * co 2 hinh can fix loi
			 * vi khi xoay se rat de bi loi
			 * chu L nguoc thi ben phai
			 * L thi ben trai
			 */
			if (y>h2.getDiem().getY())
			{
				if (h3.touchr(game))
				{
					return false;
				}
				if (game[j][i+1].isRong()&&game[j][i-1].isRong()&&game[j+1][i-1].isRong())
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
				if (h3.touchl(game))
				{
					return false;
				}
				if (game[j][i+1].isRong()&&game[j][i-1].isRong()&&game[j-1][i+1].isRong())
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
			if (j+1>23)
			{
				return false;
			}
			if (x>h2.getDiem().getX())
			{
				if (game[j+1][i].isRong()&&game[j-1][i].isRong()&&game[j+1][i+1].isRong())
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
				if (game[j-1][i].isRong()&&game[j-1][i-1].isRong()&&game[j+1][i].isRong())
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

}
