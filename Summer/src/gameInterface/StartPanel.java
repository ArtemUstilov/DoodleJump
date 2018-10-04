package gameInterface;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import characters.Character;
import characters.Doodle;
import characters.Hole;
import characters.MySlider;
import characters.Platform;
import engine.Game;
import graphicAndSounds.Images;
import main.StartGaming;

public class StartPanel extends JPanel {
	private int width;
	private int height;
	static Image bgImage;
	public static StartPanel t;

	private static final long serialVersionUID = 1L;

	public StartPanel(int width, int height) {
		this.width = width;
		this.height = height;
		t = this;

	}

	public void start(Game game) {
		try {
			topBar = ImageIO.read(getClass().getResource(Images.topBar));
			bgImage = ImageIO.read(getClass().getResource(Images.bg_grid));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Doodle dood = new Doodle(Images.doodleR,
				(game.getWindowWidth() / 2) - (160), 400);
		game.getPanel().add(dood);
		Character tit = new Character(Images.Tittle,85,100, 300, 60);
		game.getPanel().add(tit);
		Character LolKek = new Character(Images.LolKek,20,700, 220, 40);
		game.getPanel().add(LolKek);
		LolKek.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "<html><h3><i>Created by</i></h3><h1>Artem Ustilov</h1><h1>Roman Shchybryc</h1>"
			   	           ,"Lol-Kek Inc.", JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		  
		Platform platform = new Platform(Images.greenF,
				(game.getWindowWidth() / 2) - (160), 459, game.getPlatfW(),
				game.getPlatfH(), 5, 5);
		game.getPanel().add(platform);
		StartGaming.startAnimation(dood, platform, game);
		MySlider volume = new MySlider(Images.sliderBG, 0,100,70);
		volume.setSize(width-220, 30);
		volume.setLocation(177, 56);
		volume.setOpaque(false);
		
		
		
		Hole startHole = new Hole(Images.hole,
				(game.getWindowWidth() / 2) + 20, 230, 150, 138);
		game.getPanel().add(startHole);
		Character start = new Character(Images.start,
				(game.getWindowWidth() / 2) - 60,
				game.getWindowHeight() / 2 +20, 150, 75);

		game.getPanel().add(start);
		StartPanel temp = this;
		start.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				StartGaming.gameIsStarted = true;
				temp.removeAll();
				
				game.initAfterStart(game);
				game.getPanel().setFocusable(true);
				Thread r = new Thread() {
					public void run() {
						game.startGame(game, game.getDoodleHero());
					}
				};
				r.start();
				game.pause.addMouseListener(new MouseAdapter() {
					@SuppressWarnings("deprecation")
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if (!game.nowIsPause) {
							r.suspend();
							StartGaming.getWindow().removeKeyListener(
									game.getKeyForMoving());
							game.pause.setImage(Images.contin);
							game.nowIsPause = true;
						} else {
							r.resume();
							game.nowIsPause = false;
							game.pause.setImage(Images.pause);
							StartGaming.getWindow().addKeyListener(
									game.getKeyForMoving());
						}
					}
				});
			}

		});
		Character exit = new Character(Images.exit,
				start.getX(),
				start.getY()+start.getHeight()*2+10*2, 150, 75);

		game.getPanel().add(exit);
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		Character settings = new Character(Images.setting,
				start.getX()-107,
				start.getY()+85, 260, 75);

		game.getPanel().add(settings);
		settings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Settings set = new Settings(game, game
						.getWindowWidth(), game.getWindowHeight(), t, volume);
				StartGaming.getWindow().add(set);
				t.setVisible(false);
			}
		});
		game.getPanel().updateUI();

	}

	Image topBar;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bgImage, 0, 0, width, height, null);
		g.drawImage(topBar, 0, -10, 500, 50, null);
		
	}
}
