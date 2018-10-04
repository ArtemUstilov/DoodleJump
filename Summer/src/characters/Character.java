package characters;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JLabel;


public class Character extends JLabel {
	private static final long serialVersionUID = 1L;
	private Image I;
	private URL imge;
	private String imag;
	private int imageW = -1;
	private int imageH = -1;
	public Character(String img, int X, int Y, int Width, int Height){
		super();
		this.imag = img;
		this.imge = getClass().getResource(img);
		try {
			I = ImageIO.read(imge);
		} catch (IOException e) {
}
		this.setSize(Width, Height);
		this.setLocation(X,Y);
	}
	public Character(String img, int X, int Y, int Width, int Height, int imageW, int imageH){
		super();
		this.imag = img;
		this.imge = getClass().getResource(img);
		try {
			I = ImageIO.read(imge);
		} catch (IOException e) {
}
		this.setSize(Width, Height);
		this.setLocation(X,Y);
		this.setImageH(imageH);
		this.setImageW(imageW);
	}
	@Override
	protected void paintComponent(Graphics g) {
	    if(getImageH()==-1)g.drawImage(I, 0, 0, null);
	    else g.drawImage(I, 0, 0,getImageW(), getImageH(), null);
	}
	public void setImage(String im){
		this.imge = getClass().getResource(im);
		this.imag = im;
		try {
			I = ImageIO.read(imge);
		} catch (IOException e) {
}
	}
	public String getImage(){
	return this.imag;
	}
	public int getImageW() {
		return imageW;
	}
	public void setImageW(int imageW) {
		this.imageW = imageW;
	}
	public int getImageH() {
		return imageH;
	}
	public void setImageH(int imageH) {
		this.imageH = imageH;
	}
}
