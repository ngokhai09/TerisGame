package Model;

import java.awt.Color;
import java.awt.Graphics;

public class T_Block implements Shape {
	private Square h1,h2,h3,h4;
	/*
	 * quản lý khối chữ T với 4 ô vuông
	 * [1][2][3]
	 * 	  [4]
	 * Hàm tạo sẽ nhận giá trị cho h4 và nội suy ra các giá trị khác
	 * khi rotate sẽ nhận theo h2
	 */
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

	public T_Block() {
		super();
	}
	public T_Block(int x, int y) {
		h4 = new Square(x,y);
		h2 = new Square(x,y-h4.getSize());
		h3 = new Square(x+h4.getSize(),y-h4.getSize());
		h1 = new Square(x-h4.getSize(),y-h4.getSize());
		h1.setColor(4);
		h2.setColor(4);
		h3.setColor(4);
		h4.setColor(4);
		h1.setRong(false);
		h2.setRong(false);
		h3.setRong(false);
		h4.setRong(false);
	}
	@Override
	public void rotate() {
		/*   		  []     []     []
		 * [][][] → [][] → [][][] → [][]
		 *   []       []			[]
		 */
		int x = h2.getDiem().getX();
		int y = h2.getDiem().getY();
		int size = h2.getSize();
		if (y==h1.getDiem().getY())
		{
			if (x>h1.getDiem().getX())
			{
				h1.move(x, y-size);
				h3.move(x, y+size);
				h4.move(x-size, y);
			}
			else
			{
				h1.move(x, y+size);
				h3.move(x, y-size);
				h4.move(x+size, y);
			}
		}
		else
		{
			if (y>h1.getDiem().getY())
			{
				h1.move(x+size, y);
				h3.move(x-size, y);
				h4.move(x, y-size);
			}
			else
			{
				h1.move(x-size, y);
				h3.move(x+size, y);
				h4.move(x, y+size);
			}
		}
	}

	@Override
	public String toString() {
		return "KhoiChuT [h1=" + h1 + ", h2=" + h2 + ", h3=" + h3 + ", h4=" + h4 + "]";
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.yellow);
		h1.paint(g);
		h2.paint(g);
		h3.paint(g);
		h4.paint(g);
		
	}
	@Override
	public void move(int x, int y) {
		int size = h2.getSize();
		if(h2.getDiem().getY() == h1.getDiem().getY())
		{
			if (h2.getDiem().getX()>h1.getDiem().getX())
			{
				h2.move(x, y);
				h4.move(x, y+size);
				h3.move(x+size, y);
				h1.move(x-size, y);
			}
			else
			{
				h2.move(x,y);
				h3.move(x-size, y);
				h1.move(x+size, y);
				h4.move(x,y-size);
			}
		}
		else
		{
			if (h2.getDiem().getY() > h1.getDiem().getY())
			{
				h2.move(x, y);
				h3.move(x, y+size);
				h1.move(x, y-size);
				h4.move(x-size, y);

			}
			else
			{
				h2.move(x, y);
				h4.move(x+size, y);
				h3.move(x, y-size);
				h1.move(x, y+size);
			}
		}
	}

	@Override
	public int lastvalue() {
		int x = h2.getDiem().getX();
		int y = h2.getDiem().getY();
		int size = h2.getSize();
		if(y == h3.getDiem().getY())
		{
			if (x<h3.getDiem().getX())
			{
				return h4.getDiem().getY();
			}
			else
			{
				return y;
			}
		}
		else
		{
			if (y < h3.getDiem().getY())
			{
				return h3.getDiem().getY();
			}
			else
			{
				return h1.getDiem().getY();
			}
		}
	}

	@Override
	public int maxright() {
		if(h2.getDiem().getY() == h1.getDiem().getY())
		{
			if (h2.getDiem().getX()>h1.getDiem().getX())
			{
				return h3.getDiem().getX();
			}
			else
			{
				return h1.getDiem().getX();
			}
		}
		else
		{
			if (h2.getDiem().getY() > h1.getDiem().getY())
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
	public int maxleft() {
		
		if(h2.getDiem().getY() == h1.getDiem().getY())
		{
			if (h2.getDiem().getX()>h1.getDiem().getX())
			{
				return h1.getDiem().getX();
			}
			else
			{
				return h3.getDiem().getX();
			}
		}
		else
		{
			if (h2.getDiem().getY() > h1.getDiem().getY())
			{
				return h4.getDiem().getX();
			}
			else
			{
				return h2.getDiem().getX();
			}
		}
	}

	@Override
	public boolean touch(Square game[][]) {
		if (h2.getDiem().getX()==h3.getDiem().getX())
		{
			if (h2.getDiem().getX()<h3.getDiem().getX()) // chữ T
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
			else // chữ ngược T
			{
				if (h1.touch(game)||h2.touch(game)||h3.touch(game))
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
			if (h3.getDiem().getY()>h2.getDiem().getY()) // hình chữ T bên trái
			{
				if (h3.touch(game)||h4.touch(game))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else // hình chữ T bên phải
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
		int x = h2.getDiem().getX();
		int y = h2.getDiem().getY();
		int size = h2.getSize();
		int i =(int)x/20;
		int j = (int)y/20;
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
				if (h3.getDiem().getY()==y)
				{
					return value;
				}
				else
				{
					if (y<h3.getDiem().getY())
					{
						if (h2.touchr(game))
						{
							if (game[j][i-2].isRong()&&game[j-1][i-1].isRong())
							{
								h1.move(x, y);
								h2.move(x-size, y);
								h3.move(x-size*2, y);
								h4.move(x-size, y-size);
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
						if (h2.touchl(game))
						{
							if (game[j][i+2].isRong()&&game[j+1][i+1].isRong())
							{
								h1.move(x, y);
								h2.move(x+size, y);
								h3.move(x+size, y);
								h4.move(x+size, y+size);
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
			}
		}
		
	}

	@Override
	public boolean touchl(Square[][] game) {
		if (h2.getDiem().getX()==h3.getDiem().getX())
		{
			if (h2.getDiem().getX()<h3.getDiem().getX()) // chữ T
			{
				if (h1.touchl(game)||h4.touchl(game))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else // chữ ngược T
			{
				if (h4.touchl(game)||h3.touchl(game))
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
			if (h3.getDiem().getY()>h2.getDiem().getY()) // hình chữ T bên trái
			{
				if (h3.touchl(game)||h4.touchl(game)||h1.touchl(game))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else // hình chữ T bên phải
			{
				if (h1.touchl(game)||h2.touchl(game)||h3.touchl(game))
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
			if (h2.getDiem().getX()<h3.getDiem().getX()) // chữ T
			{
				if (h3.touchr(game)||h4.touchr(game))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else // chữ ngược T
			{
				if (h1.touchr(game)||h4.touchr(game))
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
			if (h3.getDiem().getY()>h2.getDiem().getY()) // hình chữ T bên trái
			{
				if (h3.touchr(game)||h2.touchr(game)||h1.touchr(game))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else // hình chữ T bên phải
			{
				if (h1.touchr(game)||h4.touchr(game)||h3.touchr(game))
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
		int x = h2.getDiem().getX();
		int y = h2.getDiem().getY();
		int size = h2.getSize();
		int i =(int)x/20;
		int j = (int)y/20;
		if (y == h3.getDiem().getY())
		{
			/*
			 * xet th dau khac o tren va duoi
			 */
			if (x<h3.getDiem().getX())
			{
				/*
				 * duoi
				 */
				if (game[j-1][i].isRong())
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
				/*
				 * tren
				 */
				if (game[j+1][i].isRong())
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
			/*
			 * cai khac o ben phai hoc ben trai
			 */
			if (y<h3.getDiem().getY())
			{
				/*
				 * ben trai
				 */
				if (h2.touchr(game))
				{
					return false;
				}
				if (game[j][i+1].isRong())
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
				/*
				 * ben phai
				 */
				if (h2.touchl(game))
				{
					return false;
				}
				if(game[j][i-1].isRong())
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
