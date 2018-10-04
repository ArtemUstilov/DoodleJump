package characters;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JSlider;

public class MySlider extends JSlider{
	private static final long serialVersionUID = 1L;
	private Image bg;
	private URL imge;
	public MySlider(String img, int start, int end, int cur){
		super(start, end, cur);
		try {
			this.imge = getClass().getResource(img);
			bg = ImageIO.read(imge);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0,0, null);
		super.paintComponent(g);
	}
}
