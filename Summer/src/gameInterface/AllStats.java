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

public class AllStats extends JPanel {
	private static final long serialVersionUID = 1L;
	private BufferedImage background;
	private static int width;
	private static int height;

	@SuppressWarnings("static-access")
	public AllStats(Game game, int width, int height) {
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
		String allStats = "";
		FileInputStream t;
		try {
			t = new FileInputStream(UsableM.getPathToStats());
		
		byte[] buffer = new byte[t.available()];
		t.read(buffer, 0, t.available());
		for (int i = 0; i < buffer.length; i++) {
			allStats += (char) buffer[i];
		}
		t.close();
		} catch (FileNotFoundException e9) {
			e9.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String stats[] = allStats.split("&&");
		String line = "";
		List<String> lines = new ArrayList<String>();
		try {
			FileInputStream r = new FileInputStream(UsableM.getPathToScores());
			byte[] buffer1 = new byte[r.available()];
			r.read(buffer1, 0, r.available());
			for (int i = 0; i < buffer1.length; i++) {
				line += (char) buffer1[i];
			}
			r.close();
		} catch (FileNotFoundException e1) {
		} catch (IOException e) {
		}
		String[] arr = line.split("&&");
		for (String u : arr) {
			lines.add(u);
		}
		String max = "";
		max = lines.get(0);
		for (int j = 0; j < lines.size(); j++) {
			if (Long.valueOf(max.split("-")[1]) < Long.valueOf(lines.get(j)
					.split("-")[1])) {
				max = lines.get(j);
			}
		}
		for (int i = 0; i < 8; i++) {
			switch (i) {
			case 0:
				 JLabel l1 = new JLabel("Best score");
					 l1.setFont(GameFont.getFont(20));
					 l1.setLocation(40, 50+i*25);
					 l1.setSize(300,25);
					 this.add(l1);
					 JLabel e1 = new JLabel(max.split("-")[1]);
					 e1.setFont(GameFont.getFont(20));
					 e1.setLocation(360, 50+i*25);
					 e1.setSize(300,25);
					 this.add(e1);
				break;
			case 1:
				JLabel l2 = new JLabel("Killed monster");
				 l2.setFont(GameFont.getFont(20));
				 l2.setLocation(40, 50+i*25);
				 l2.setSize(300,25);
				 this.add(l2);
				 JLabel e2 = new JLabel(stats[i-1]);
				 e2.setFont(GameFont.getFont(20));
				 e2.setLocation(360, 50+i*25);
				 e2.setSize(300,25);
				 this.add(e2);
				break;
			case 2:
				JLabel l3 = new JLabel("Jump on spring");
				 l3.setFont(GameFont.getFont(20));
				 l3.setLocation(40, 50+i*25);
				 l3.setSize(300,25);
				 this.add(l3);
				 JLabel e3 = new JLabel(stats[i-1]);
				 e3.setFont(GameFont.getFont(20));
				 e3.setLocation(360, 50+i*25);
				 e3.setSize(300,25);
				 this.add(e3);
				break;
			case 3:
				JLabel l4 = new JLabel("Fly on rocket");
				 l4.setFont(GameFont.getFont(20));
				 l4.setLocation(40, 50+i*25);
				 l4.setSize(300,25);
				 this.add(l4);
				 JLabel e4 = new JLabel(stats[i-1]);
				 e4.setFont(GameFont.getFont(20));
				 e4.setLocation(360, 50+i*25);
				 e4.setSize(300,25);
				 this.add(e4);
				break;
			case 4:
				JLabel l5 = new JLabel("Time in game");
				 l5.setFont(GameFont.getFont(20));
				 l5.setLocation(40, 50+i*25);
				 l5.setSize(300,25);
				 this.add(l5);
				 long temp = Long.valueOf(stats[i-1]);
				 int sec = (int) (temp/1000);
				 int min = sec/60;
				 sec = sec - min*60;
				 JLabel e5 = new JLabel(min + ":"+sec);
				 e5.setFont(GameFont.getFont(20));
				 e5.setLocation(360, 50+i*25);
				 e5.setSize(300,25);
				 this.add(e5);
				break;
			case 5:
				JLabel l6= new JLabel("Dead in black holes");
				 l6.setFont(GameFont.getFont(20));
				 l6.setLocation(40, 50+i*25);
				 l6.setSize(300,25);
				 this.add(l6);
				 JLabel e6 = new JLabel(stats[i-1]);
				 e6.setFont(GameFont.getFont(20));
				 e6.setLocation(360, 50+i*25);
				 e6.setSize(300,25);
				 this.add(e6);
				break;
			case 6:
				JLabel l7 = new JLabel("Killed by monster");
				 l7.setFont(GameFont.getFont(20));
				 l7.setLocation(40, 50+i*25);
				 l7.setSize(300,25);
				 this.add(l7);
				 JLabel e7 = new JLabel(stats[i-1]);
				 e7.setFont(GameFont.getFont(20));
				 e7.setLocation(360, 50+i*25);
				 e7.setSize(300,25);
				 this.add(e7);
				break;
			case 7:
				JLabel l8 = new JLabel("Fall down");
				 l8.setFont(GameFont.getFont(20));
				 l8.setLocation(40, 50+i*25);
				 l8.setSize(300,25);
				 this.add(l8);
				 JLabel e8 = new JLabel(stats[i-1]);
				 e8.setFont(GameFont.getFont(20));
				 e8.setLocation(360, 50+i*25);
				 e8.setSize(300,25);
				 this.add(e8);
				break;
			}
		}
		Character back = new Character(Images.back, width / 2 - 120 / 2,
				(height / 3 * 2) + 150, 100, 50);
		this.add(back);
		AllStats temp = this;
		back.addMouseListener(new MouseAdapter() {
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
