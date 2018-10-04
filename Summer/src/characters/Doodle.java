package characters;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Doodle extends Character{
	private static final long serialVersionUID = 1L;
	public final static int WIDTH=60;
	public final static int HEIGHT=90;
	public Doodle(String img,int X, int Y) {
		super(img,X,Y,WIDTH, HEIGHT);
	}
	double ancle = 0;
	int x = 30; 
	int y = 30;
	@Override
	public void paintComponent(Graphics g){
		Graphics2D r = (Graphics2D) g;
		r.rotate(ancle, x,y);
		super.paintComponent(r);
	}
	public void setAncle(double ancle, int x, int y){
		this.ancle = ancle+this.ancle;
		this.x = x;
		this.y = y;
	}
}
