package gameInterface;

import graphicAndSounds.BrightnessManager;
import graphicAndSounds.Images;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

import main.StartGaming;

public class RadioListener implements ActionListener{
	public RadioListener(JRadioButton s,JRadioButton m,JRadioButton l){
		super();
		this.s = s;
		this.m = m;
		this.l = l;
	}
	JRadioButton s;
	JRadioButton m;
	JRadioButton l;
	@Override
		public void actionPerformed(ActionEvent event){
		StartGaming.getWindow().requestFocus();
		JRadioButton t = (JRadioButton) event.getSource();
		int brightness = 100;
		if(t.getText()=="s"){
			brightness = 30;
			try {
				m.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(Images.normal))));
				l.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(Images.high))));
				t.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(Images.lowP))));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(t.getText()=="m"){
			brightness = 70;
			try {
				s.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(Images.low))));
				l.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(Images.high))));
				t.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(Images.normalP))));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(t.getText()=="l"){
			brightness = 250;
			try {
				m.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(Images.normal))));
				s.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(Images.low))));
				t.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(Images.highP))));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			BrightnessManager.setBrightness(brightness);
		} catch (IOException e) {
			e.printStackTrace();
		}}
	
}
