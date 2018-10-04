package gameInterface;

import graphicAndSounds.GameFont;
import graphicAndSounds.Images;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.StartGaming;
import characters.Character;
import characters.Monster;
import characters.Platform;
import engine.Game;
import engine.UsableM;

public class GameOverPanel extends JPanel {
	private static final long serialVersionUID = 5496400981778894020L;

	private BufferedImage background;

	protected static Character showAll;
	private static int width;
	private static int height;
	private static long score;
	private static Game game;

	@SuppressWarnings("static-access")
	public GameOverPanel(int width, int height, Long score, Game game) {
		this.width = width;
		this.score = score;
		this.height = height;
		this.game = game;
		this.setPreferredSize(new Dimension(width, height));
		try {
			background = ImageIO.read(getClass().getResource(Images.bg_grid));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void setup(GameOverPanel panel) {
		JTextField entry = new JTextField("Enter Your Name");
		entry.setFocusable(true);
		JLabel yourScore = new JLabel("YOUR SCORE: ");

		// yourScore.setFont(new Font("AR DESTINE", Font.BOLD, 35));
		yourScore.setFont(GameFont.getFont(35));
		yourScore.setLocation((width / 2) - 160, 100);
		yourScore.setSize(300, 50);
		game.getOver().setLayout(null);
		game.getOver().add(yourScore);
		JLabel scoreLabel = new JLabel(String.valueOf(score));
		scoreLabel.setFont(GameFont.getFont(35));
		scoreLabel.setLocation((width / 2) + 105, 100);
		scoreLabel.setSize(300, 50);
		game.getOver().add(scoreLabel);
		entry.setFont(GameFont.getFont(42));
		entry.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (entry.getText().equals("")) {
					entry.setText("Enter Your Name");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (entry.getText().equals("Enter Your Name")) {
					entry.setText("");
				}

			}
		});
		entry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				UsableM.writeStats(game);
				JTextField textBox = (JTextField) arg0.getSource();
				try {
					FileOutputStream r = new FileOutputStream(UsableM
							.getPathToScores(), true);
					String ww = textBox.getText() + "-" + String.valueOf(score)
							+ "&&";
					r.write(ww.getBytes());
					r.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				JLabel restart = new JLabel("Click anywhere to restart");
				restart.setFont(GameFont.getFont(19));
				restart.setLocation(width / 2 - 140, height / 8 * 7);
				restart.setSize(300, 30);
				game.getOver().add(restart);
				Container parent = textBox.getParent();
				parent.remove(textBox);
				parent.repaint();
				Character exit = new Character(Images.exit, game
						.getWindowWidth() / 2 - 75,
						game.getWindowHeight() / 3 * 2 + 90, 150, 75);
				panel.add(exit);
				Character menu = new Character(Images.menu, game
						.getWindowWidth() / 2 - exit.getHeight(), exit.getY()
						- exit.getHeight() * 3, 160, 75);
				game.getOver().add(menu);
				menu.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						StartGaming.getWindow().remove(game.getOver());
						StartGaming.gameIsStarted = false;
						game.setOver(null);
						game.getPanel().setVisible(true);
						game.setScores(0);
						game.getPanel().removeAll();
						game.setPlatforms(new ArrayList<Platform>());
						game.setGameIsContinue(false);
						StartGaming.getWindow().remove(game.getPanel());
						game = new Game();
						game.setDead(false);
						StartGaming.getWindow().setDefaultCloseOperation(
								JFrame.EXIT_ON_CLOSE);
						StartGaming.getWindow().setSize(game.getWindowWidth(),
								game.getWindowHeight());
						StartGaming.getWindow().repaint();
						game.getPanel().setLayout(null);
						StartGaming.getWindow().add(game.getPanel());
						game.getPanel().updateUI();
						StartGaming.getWindow().setVisible(true);
					}
				});
				Character stats = new Character(Images.stats, game
						.getWindowWidth() / 2 - exit.getHeight(), exit.getY()
						- exit.getHeight(), 160, 75);
				game.getOver().add(stats);
				stats.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						AllStats all = new AllStats(game,
								game.getWindowWidth(), game.getWindowHeight());
						StartGaming.getWindow().add(all);
						game.getOver().setVisible(false);
					}
				});
				panel.setLayout(null);
				Monster endMon = new Monster(Images.bat1, 100, 200, 200, 100,
						true);
				panel.add(endMon);
				endFly(endMon, game.getOver());
				Monster endMon2 = new Monster(Images.bat1, 300, 200, 200, 100,
						true);
				panel.add(endMon2);
				endFly(endMon2, game.getOver());
				Monster endMon3 = new Monster(Images.bat1, 300, 300, 200, 100,
						true);
				panel.add(endMon3);
				endFly(endMon3, game.getOver());
				Monster endMon4 = new Monster(Images.bat1, 100, 300, 200, 100,
						true);
				panel.add(endMon4);
				endFly(endMon4, game.getOver());

				showAll = new Character(Images.ShowAll, menu.getX(), exit
						.getY() - exit.getHeight() * 2, menu.getWidth(), menu
						.getHeight());
				showAll.setVisible(true);

				panel.add(showAll);
				panel.updateUI();
				showAll.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent arg0) {
						AllScores all = new AllScores(game, game
								.getWindowWidth(), game.getWindowHeight());
						StartGaming.getWindow().add(all);
						game.getOver().setVisible(false);
					}
				});

				exit.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						System.exit(0);
					}
				});

			}
		});
		entry.setSize(new Dimension(width, height / 7));
		entry.setLocation(0, 660);
		panel.add(entry);

	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, width, height, 0, 0,
				background.getWidth(), background.getHeight(), null);
	}

	public static void endFly(Monster monster, GameOverPanel panel) {
		new Thread() {
			public void run() {
				while (true) {
					UsableM.pause(200);
					if (monster != null && panel != null) {
						monster.setImage(Images.bat2);
						panel.updateUI();
					}
					UsableM.pause(200);
					if (monster != null && panel != null) {
						monster.setImage(Images.bat1);
						panel.updateUI();

					}
				}
			}
		}.start();
	}
}
