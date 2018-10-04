package gameInterface;

import graphicAndSounds.GameFont;
import graphicAndSounds.Images;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.StartGaming;
import characters.Character;
import engine.Game;
import engine.UsableM;

public class AllScores extends JPanel{
	private static final long serialVersionUID = 1L;
	private BufferedImage background;
	private static int width;
	private static int height;
   
	@SuppressWarnings("static-access")
	public AllScores(Game game, int width, int height) {
		this.width = width;
		this.height = height;
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new BorderLayout());
		try {
			background = ImageIO.read(getClass().getResource(Images.bg_grid));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setLayout(null);
		 String line = "";
	        List<String> lines = new ArrayList<String>();
		try {
			FileInputStream t = new FileInputStream(UsableM.getPathToScores());
			byte[] buffer = new byte[t.available()];
			t.read(buffer, 0, t.available());
			for(int i=0; i<buffer.length;i++){
			    line+=(char)buffer[i];
			}
			t.close();
		} catch (FileNotFoundException e1) {
		} catch (IOException e) {
		}
		String []arr = line.split("&&");
		for(String u:arr){
			lines.add(u);
		}
		for(int i = 0; i < lines.size()-1; i++){
			for(int j = 0; j<lines.size()-1; j++){
				if(Long.valueOf(lines.get(j).split("-")[1])<Long.valueOf(lines.get(j+1).split("-")[1])){
					String t = lines.get(j);
					String r = lines.get(j+1);
					lines.remove(j);
					lines.remove(j);
					lines.add(j, r);
					lines.add(j+1, t);
				}
			}
		}
	    for(int i = 0; i < lines.size(); i++){
	    	if(i<20){
	    	String name = lines.get(i).split("-")[0];
	    	String score = lines.get(i).split("-")[1];
	    	JLabel l = new JLabel(name);
	    	l.setFont(GameFont.getFont(20));
	    	l.setLocation(40, 50+i*25);
	    	l.setSize(300,25);
	    	this.add(l);
	    	JLabel e = new JLabel(score);
	    	e.setFont(GameFont.getFont(20));
	    	e.setLocation(360, 50+i*25);
	    	e.setSize(300,25);
	    	this.add(e);
	    	}else break;
	    }
	    Character back= new Character(Images.back,width/2-120/2, (height/3*2)+150,100,50);
		this.add(back);
		AllScores temp=this;
		back.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				StartGaming.getWindow().remove(temp);
				game.getOver().setVisible(true);
				
			}
			
		});
	}
	@Override
	public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, width, height, 0, 0,
		Math.min(background.getWidth(), width),
		Math.min(background.getHeight(), height), null);
	}
}
