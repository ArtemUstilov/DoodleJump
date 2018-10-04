package gameInterface;

import graphicAndSounds.GameFont;
import graphicAndSounds.Images;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.StartGaming;
import characters.Character;
import engine.Game;

public class Settings extends JPanel{
	private static final long serialVersionUID = 1L;
	private BufferedImage background;
	private static int width;
	private static int height;
	
	public static StartPanel t;
	private Settings set;
	private static float curVolume = 70f;

   
	@SuppressWarnings("static-access")
	public Settings(Game game, int width, int height, StartPanel t, JSlider volume)  {
		this.width = width;
		this.height = height;
		this.t = t;
		set = this;
		this.setPreferredSize(new Dimension(width, height));
		try {
			background = ImageIO.read(getClass().getResource(Images.bg_grid));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setLayout(null);
		ChangeListener y = new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				StartGaming.getWindow().requestFocus();
				JSlider source = (JSlider)arg0.getSource();
				set.updateUI();
				curVolume = (float)source.getValue();
			}
			
		};
		
		
		volume.addChangeListener(y);
		this.add(volume);
	    Character back= new Character(Images.back,width/2-120/2, (height/3*2)+150,100,50);
		this.add(back);
		JLabel LabelV = new JLabel("Volume: ");
		LabelV.setFont(GameFont.getFont(30));
		LabelV.setLocation(50, 46);
		LabelV.setSize(200, 40);
		this.add(LabelV);
		Settings temp=this;
		back.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				StartGaming.getWindow().remove(temp);
				volume.removeChangeListener(y);
				volume.setFocusable(false);
				temp.setVisible(false);
				t.setVisible(true);
				
			}
			
		});
		JLabel LabelB = new JLabel("Brightness: ");
		LabelB.setFont(GameFont.getFont(30));
		LabelB.setLocation(50, 146);
		LabelB.setSize(200, 40);
		this.add(LabelB);
	
		
		ButtonGroup group = new ButtonGroup();
		
		JRadioButton small = new JRadioButton("s");
		try {
			small.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(Images.low))));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		small.setLocation(210, 146);
		small.setSize(67,40);
		group.add(small);
		this.add(small);
		JRadioButton medium = new JRadioButton("m");
		try {
			medium.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(Images.normal))));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		medium.setLocation(282, 146);
		medium.setSize(100,40);
		group.add(medium);
		this.add(medium);
		JRadioButton large = new JRadioButton("l");
		try {
			large.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(Images.high))));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		large.setLocation(380, 146);
		large.setSize(80,40);
		group.add(large);
		RadioListener listener = new RadioListener(small ,medium, large);
		large.addActionListener(listener);
		medium.addActionListener(listener);
		small.addActionListener(listener);
		this.add(large);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, width, height, 0, 0,
		Math.min(background.getWidth(), width),
		Math.min(background.getHeight(), height), null);
	}
	public static float getVol(){
		return (float) (curVolume/100.0);
		
	}
	
}

