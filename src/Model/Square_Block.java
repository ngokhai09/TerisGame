package Model;

import java.awt.Color;
import java.awt.Graphics;

public class Square_Block implements Shape{
	private Square h1,h2,h3,h4;
	/*
	 *  Khối vuông có 4 ô vuông, xếp theo thứ tự
	 *  [1][2]
	 *  [3][4]
	 *  h1,h2 cùng 1 hàng h2 bên phải h1
	 *  h3,h4 tương tự và h4 bên phải h3
	 *  mỗi ô cách nhau Distance
	 */

	public Square_Block(int x, int y) {
		h3 = new Square(x,y);
		h1 = new Square(x,y-h3.getSize());
		h2 = new Square(x+h3.getSize(),y-h3.getSize());
		h4 = new Square(x+h3.getSize(),y);
		h1.setColor(1);
		h2.setColor(1);
		h3.setColor(1);
		h4.setColor(1);
		h1.setRong(false);
		h2.setRong(false);
		h3.setRong(false);
		h4.setRong(false);
	}

	@Override
	public String toString() {
		return "KhoiVuong [h1=" + h1 + ", h2=" + h2 + ", h3=" + h3 + ", h4=" + h4 + "]";
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

	public Square_Block() {
		super();
	}

	@Override
	public void move(int x, int y) {
		h3.move(x, y);
		h1.move(x, y-h3.getSize());
		h2.move(x+h3.getSize(), y-h3.getSize());
		h4.move(x+h3.getSize(), y);
//		h1.move(x,y);
//		h2.move(x+h1.getSize(), y);
//		h3.move(x, y+h1.getSize());
//		h4.move(x+h1.getSize(), y+h1.getSize());
		
	}

	@Override
	public void rotate() {
		return;
		
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.red);
		h1.paint(g);
		h2.paint(g);
		h3.paint(g);
		h4.paint(g);
		
	}

	@Override
	public int lastvalue() {
		return h3.getDiem().getY();
	}

	@Override
	public int maxright() {
		return h2.getDiem().getX();
	}

	@Override
	public int maxleft() {
		return h1.getDiem().getX();
	}

	@Override
	public boolean touch(Square game[][]) {
		if (h3.touch(game)||h4.touch(game))
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
		h1.addvalue(game);
		h2.addvalue(game);
		h3.addvalue(game);
		h4.addvalue(game);
		
	}

	@Override
	public int rotate(int value, Square[][] game) {
		this.rotate();
		return value;
		
	}

	@Override
	public boolean touchl(Square[][] game) {
		if (h3.touchl(game)||h1.touchl(game))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean touchr(Square[][] game) {
		if (h2.touch(game)||h4.touch(game))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean isrotate(Square[][] game) {
		return this.touch(game);
	}
	

}
