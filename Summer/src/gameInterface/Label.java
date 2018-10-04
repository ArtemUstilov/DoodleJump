package gameInterface;

import java.awt.Font;

import javax.swing.JLabel;

public class Label extends JLabel{
	private static final long serialVersionUID = 1L;

	public Label(String string, int x, int y, int width, int height,
			Font font, long scores) {
		super("Score: " + scores);
		this.setLocation(x,y);
		this.setSize(width, height);
		this.setFont(font);
	}

}
