package Model;

import java.awt.Color;
import java.awt.Graphics;

public class Nl_Block implements Shape{
	private Square h1,h2,h3,h4;
	/*
	 * [1]
	 * [2][3]
	 * 	  [4]
	 * hệ thống quản lý theo cơ chế quản lý thằng cuối cùng
	 * h4 sẽ được nhận giá trị đầu tiên
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
	public Nl_Block() {
		super();
	}
	@Override
	public void move(int x, int y) {
		int size = h4.getSize();
		if (h2.getDiem().getX() == h3.getDiem().getX())
		{
			h2.move(x, y);
			h1.move(x+size, y);
			h4.move(x-size, y+size);
			h3.move(x, y+size);
		}
		else
		{
			h2.move(x, y);
			h4.move(x+size, y+size);
			h3.move(x+size, y);
			h1.move(x, y-size);
		}
	}

	public Nl_Block(int x, int y) {
		h4 = new Square(x,y);
		h3 = new Square(x,y-h4.getSize());
		h2 = new Square(x-h4.getSize(),y-h4.getSize());
		h1 = new Square(x-h4.getSize(),y-h4.getSize()*2);
		h1.setColor(7);
		h2.setColor(7);
		h3.setColor(7);
		h4.setColor(7);
		h1.setRong(false);
		h2.setRong(false);
		h3.setRong(false);
		h4.setRong(false);
	}

	@Override
	public void rotate() {
		/*
		 * [1]
		 * [2][3] →    [2][1]
		 *    [4]   [4][3]
		 */
		int x = h2.getDiem().getX();
		int y = h2.getDiem().getY();
		int size = h2.getSize();
		if (y==h3.getDiem().getY())
		{
			h1.move(x+size, y);
			h3.move(x, y+size);
			h4.move(x-size,y+size);
		}
		else
		{
			h3.move(x+size, y);
			h4.move(x+size, y+size);
			h1.move(x, y-size);
		}
		
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.ORANGE);
		h1.paint(g);
		h2.paint(g);
		h3.paint(g);
		h4.paint(g);
		
	}
	@Override
	public int lastvalue() {
		if (h2.getDiem().getY()==h3.getDiem().getY())
		{
			return h4.getDiem().getY();
		}
		else
		{
			return h4.getDiem().getY();
		}
	}
	@Override
	public int maxright() {
		if (h2.getDiem().getY()==h3.getDiem().getY())
		{
			return h3.getDiem().getX();
		}
		else
		{
			return h1.getDiem().getX();
		}
	}
	@Override
	public int maxleft() {
		if (h2.getDiem().getY()==h3.getDiem().getY())
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
		if (h2.getDiem().getY()==h3.getDiem().getY())
		{
			if (h2.touch(game)||h4.touch(game))
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
	@Override
	public void addvalue(Square[][] game) {
		h1.addvalue(game);
		h2.addvalue(game);
		h3.addvalue(game);
		h4.addvalue(game);
//		game[y1][x1].setColor(7);
//		game[y2][x2].setColor(7);
//		game[y3][x3].setColor(7);
//		game[y4][x4].setColor(7);
	}
	@Override
	public int rotate(int value, Square[][] game) {
		int x = h2.getDiem().getX();
		int y = h2.getDiem().getY();
		int size = h2.getSize();
		int i = (int)x/20;
		int j=(int)y/20;
		/*
		 * bi kep thi khong can xu ly vi
		 * xoay duoc thi xoay thoi
		 */
		if (this.touchl(game)&&this.touchr(game))
		{
			if (isrotate(game))
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
			/*
			 * neu khong bi kep ma van xoay duoc thi xoay
			 */
			if (this.isrotate(game))
			{
				this.rotate();
				return value;
			}
			else
			{
				if (x==h3.getDiem().getX())
				{
					return value;
				}
				else
				{
					if (h2.touchl(game))
					{
						if (game[j+1][i].isRong()&&game[j][i+2].isRong())
						{
							h4.move(x, y+size);
							h3.move(x+size, y+size);
							h2.move(x+size, y);
							h1.move(x+size, y);
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
	@Override
	public boolean touchl(Square[][] game) {
		if (h2.getDiem().getY()==h3.getDiem().getY())
		{
			if (h1.touchl(game)||h2.touchl(game)||h4.touchl(game))
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
			if (h2.touchl(game)||h4.touchl(game))
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
		if (h2.getDiem().getY()==h3.getDiem().getY())
		{
			if (h1.touchr(game)||h3.touchr(game)||h4.touchr(game))
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
			if (h1.touchr(game)||h3.touchr(game))
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
		int x = h2.getDiem().getX();
		int y = h2.getDiem().getY();
		int size = h2.getSize();
		int i = (int)x/20;
		int j=(int)y/20;
		if(y == h3.getDiem().getY())
		{
			/*
			 * neu bi mac trai thi se khong xoay duoc
			 * 
			 */
			if (h2.touchl(game))
			{
				return false;
			}
			if (game[j+1][i].isRong()&&game[j+1][i-1].isRong())
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
			if (game[j+1][i+1].isRong()&&game[j-1][i].isRong())
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
