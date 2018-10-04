package engine;

import graphicAndSounds.Images;
import graphicAndSounds.MusicLoad;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import main.StartGaming;
import characters.Doodle;
import characters.Hole;
import characters.Character;
import characters.Monster;
import characters.Platform;
import gameInterface.GameOverPanel;
import gameInterface.KeyMoveRocket;
import gameInterface.KeyPressing;
import gameInterface.Label;
import gameInterface.StartPanel;
import gameInterface.statsSafe;

public class Game {

	private final int WINDOW_WIDTH = 500;
	private final int WINDOW_HEIGHT = 800;
	private final int PLATF_W = 100;
	private final int PLATF_H = 40;
	private final int BeginYForPlatf = WINDOW_HEIGHT - 250;
	private int howMuchMoveDownPlatf;
	private int currentIndexPlatf;
	private int currentIndexMonster;
	private int currentIndexRocket;
	private int currentIndexHole;
	private long score = 0;
	
	static Image bgImage;
	private ArrayList<Platform> platforms = new ArrayList<Platform>();
	private JPanel panel;
	private GameOverPanel over;
//	private Monster easyMonster;
	private Hole blackHole;
	private Label scoreLabel;
//	private Character rocket;
	private ArrayList<Monster> monsters = new ArrayList<Monster>();
	private ArrayList<Character> rockets = new ArrayList<Character>();
	private ArrayList<Hole> holes = new ArrayList<Hole>();
	public Character pause;
	private Doodle doodleHero;
	
	public static String bump = new String("/bumb.wav");
	private static String fall = new String("/fall.wav");
	private static String spring = new String("/spring.wav");
	private static String invis = new String("/invis.wav");
	private static String crush = new String("/crush-p-b.wav");
	private String monsterHit = new String("/monsHit.wav");
	private static String concussion = new String("/concussion.wav");
	private static String alienVoice = new String("/alien.wav");
	
	private boolean dead = false;
	private boolean killedByHole = false;
	private boolean gameIsContinue = true;
	private static KeyListener t;
	public boolean nowIsPause = false;
	public statsSafe safe;
	private long timeInGame;

	public Game() {
		StartPanel r = new StartPanel(WINDOW_WIDTH, WINDOW_HEIGHT);
		setPanel(r, this);
		r.start(this);
		getPanel().setPreferredSize(
				new Dimension(getWindowWidth(), getWindowHeight()));

	}

	public void initAfterStart(Game game) {
		Create.createScoreLabel(game);
		Create.createPlatforms(game);
		Create.createHero(game);
		pause = new characters.Character(Images.pause, getWindowWidth() - 62,
				1, 50, 50);
		getPanel().add(pause);
		Levels.initPlatforms(game);
	}

	public void startGame(Game game, Doodle dud){
		timeInGame = System.currentTimeMillis();
		safe = new statsSafe();
		gameIsContinue = true;
		dead = false;
		Levels.addLevels(game);
		Move.updating(game);
		setKeyForMoving(game);
		MusicLoad.playSound(bump).join();
		Move.jumpDoodle(20, false, game);
		double FallSpeed = 2;

		while (true) {

			if (isDead()) {// we killed by monster or hole
				
				if (isKilledByHole()) {
					safe.setDeadWithHole(1);
					MusicLoad.playSound(Game.concussion);
					AnimationAndCheckers.fallInHole(game);
					getDoodleHero().setVisible(false);
				} else {
					safe.setKilledByMonster(1);
					MusicLoad.playSound(concussion).join();
					StartGaming.getWindow().removeKeyListener(t);
					for (int i = 0; i < 10; i++) {
						if (i % 2 == 0) {
							doodleHero.setLocation(doodleHero.getX() - 5,
									doodleHero.getY());
						} else
							doodleHero.setLocation(doodleHero.getX() + 5,
									doodleHero.getY());
						UsableM.pause(120);
					}
					MusicLoad.playSound(fall).join();
				}
				endWindow(game);
				break;
			}
					if (AnimationAndCheckers.checkRocket(game)) {
						Character rocket = getRockets().get(currentIndexRocket);
						//Hole hole=getHoles().get(currentIndexHole);
						safe.setRocketFlied(1);
						StartGaming.getWindow().removeKeyListener(t);
						KeyListener r = new KeyMoveRocket(game);
						StartGaming.getWindow().addKeyListener(r);
						game.getDoodleHero().setVisible(false);
						rocket.setImage(Images.rocketDoodle);
						Move.flyRocket(game);
						UsableM.pause(200);
						game.getDoodleHero().setLocation(
								rocket.getX() + 10,
								rocket.getY() + 30);
						game.getDoodleHero().setVisible(true);
						game.getPanel().remove(rocket);
						game.getRockets().set(currentIndexRocket, null);
						StartGaming.getWindow().addKeyListener(t);
						StartGaming.getWindow().removeKeyListener(r);
						Move.displayMoveDown(200, 20, false, game);
				
			}
			int res = AnimationAndCheckers.checkHitPlatformAndMonst(
					getPlatforms(), getDoodleHero(), getPlatfW(), game);

			if ((res > 0 && res != 4 && res != 11) && FallSpeed > 0)
				AnimationAndCheckers.animationDoodle(getDoodleHero(), game);
			if (getHowMuchMoveDownPlatf() > 200&&res!=3) {
				setHowMuchMoveDownPlatf(200);
			}

			if (res != 4 && res > 0)
				FallSpeed = 0;

			switch (res) {
			case 1:// green or blue platform
				MusicLoad.playSound(bump).join();
				Move.jumpDoodle(Move.displayMoveDown(getHowMuchMoveDownPlatf(),
						20, false, game), false, game);
				break;
			case 2:// white platform
				MusicLoad.playSound(invis).join();
				getPanel().remove(getPlatforms().get(currentIndexPlatf));
				getPlatforms().remove(currentIndexPlatf);
				Move.jumpDoodle(Move.displayMoveDown(getHowMuchMoveDownPlatf(),
						20, false, game), false, game);
				break;
			case 3:// platform with spring
				safe.setJumpWithSpring(1);
				AnimationAndCheckers.animationForSpring(getCurrentIndexPlatf(),
						game);
				MusicLoad.playSound(spring).join();
				Move.jumpDoodle(Move.displayMoveDown(getHowMuchMoveDownPlatf(),
						30, false, game), false, game);
				break;
			case 4:// broken platform
				MusicLoad.playSound(crush).join();
				Move.moveDownBrokenP(
						getPlatforms().get(getCurrentIndexPlatf()), game);
				UsableM.pause((int) ((FallSpeed < 15) ? 30 / FallSpeed : 2));
				break;
			case 10:// monster
				safe.setKillMonster(1);
				MusicLoad.playSound(monsterHit).join();
				int acc1 = 20;
				game.getPanel().remove(getMonsters().get(currentIndexMonster));
				Move.jumpDoodle(Move.displayMoveDown(getHowMuchMoveDownPlatf(),
						acc1, true, game), true, game);
				getMonsters().set(currentIndexMonster, null);
				break;
			case 11:// hole
				setKilledByHole(true);
				game.setDead(true);
				break;

			default:// nothing, and doodle is falling down
				FallSpeed += 0.5;
				Move.fallDoodle(1, doodleHero, game);
				UsableM.pause((int) ((FallSpeed < 15) ? 30 / FallSpeed : 2));
				break;
			}
			if (doodleHero.getY() > getWindowHeight()) {// doodle fell
				safe.setFalls(1);
				MusicLoad.playSound(fall).join();
				endWindow(game);
				break;
			}
		}

	}

	public void setKeyForMoving(Game game) {// add keyListener
		if (t != null)
			StartGaming.getWindow().removeKeyListener(t);
		t = new KeyPressing(game);
		StartGaming.getWindow().addKeyListener(t);
		StartGaming.getWindow().setFocusable(true);
	}

	public KeyListener getKeyForMoving() {
		return t;
	}

	void endWindow(Game game) {// Game ends
		safe.setTimeInGame(System.currentTimeMillis()-timeInGame);
		gameIsContinue = false;
		if(!isKilledByHole())Move.displayMoveUp(getPlatforms(), game);
		setKilledByHole(false);
		setRockets(new ArrayList<Character>());
		setBlackHole(null);
		setOver(new GameOverPanel(game.getWindowWidth(),
				game.getWindowHeight(), getScores(), game));
		StartGaming.getWindow().add(getOver());
		panel.setVisible(false);
		GameOverPanel.setup(getOver());
		game.getOver().updateUI();
		getOver().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				StartGaming.getWindow().remove(getOver());
				setOver(null);
				panel.setVisible(true);
				setScores(0);
				getPanel().removeAll();
				setPlatforms(new ArrayList<Platform>());
				setGameIsContinue(false);
				setMonsters(new ArrayList<Monster>());
				game.initAfterStart(game);
				Thread r = new Thread() {
					public void run() {
						game.setDead(false);
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

	}

	public Doodle getDoodleHero() {
		return doodleHero;
	}

	public void setDoodleHero(Doodle doodleHero) {
		this.doodleHero = doodleHero;
	}

	public int getPlatfW() {
		return PLATF_W;
	}

	public int getPlatfH() {
		return PLATF_H;
	}

	public int getWindowWidth() {
		return WINDOW_WIDTH;
	}
	public int getWindowHeight() {
		return WINDOW_HEIGHT;
	}

	public String getMonsterHi() {
		return monsterHit;
	}

	public void setMonsterHi(String monsterHit, Game game) {
		game.monsterHit = monsterHit;
	}

	public ArrayList<Monster> getMonsters() {
		return monsters;
	}
	public void setMonsters(ArrayList<Monster> a) {
		this.monsters =  a;
	}
	public void addMonster(Monster easyMonster) {
		monsters.add(easyMonster);
	}

	public JPanel getPanel() {
		return panel;
	}

	public static void setPanel(JPanel panel, Game game) {
		game.panel = panel;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public ArrayList<Platform> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(ArrayList<Platform> platforms) {
		this.platforms = platforms;
	}

	public Label getScoreLabel() {
		return scoreLabel;
	}

	public void setScoreLabel(Label scoreLabel) {
		this.scoreLabel = scoreLabel;
	}

	public long getScores() {
		return score;
	}

	public void setScores(long scores) {
		this.score = scores;
	}

	public int getBeginyforplatf() {
		return BeginYForPlatf;
	}

	public int getCurrentIndexPlatf() {
		return currentIndexPlatf;
	}

	public void setCurrentIndexPlatf(int currentIndexPlatf) {
		this.currentIndexPlatf = currentIndexPlatf;
	}

	public int getHowMuchMoveDownPlatf() {
		return howMuchMoveDownPlatf;
	}

	public void setHowMuchMoveDownPlatf(int howMuchMoveDownPlatf) {
		this.howMuchMoveDownPlatf = howMuchMoveDownPlatf;
	}

	public String getAlienVoic() {
		return alienVoice;
	}

	@SuppressWarnings("static-access")
	public static void setAlienVoic(String alienVoice, Game game) {
		game.alienVoice = alienVoice;
	}

	public boolean isGameIsContinue() {
		return gameIsContinue;
	}

	public void setGameIsContinue(boolean gameIsContinue) {
		this.gameIsContinue = gameIsContinue;
	}

	public GameOverPanel getOver() {
		return over;
	}

	public void setOver(GameOverPanel over) {
		this.over = over;
	}

	public Hole getBlackHole() {
		return blackHole;
	}

	public void setBlackHole(Hole blackHole) {
		this.blackHole = blackHole;
	}

	public boolean isKilledByHole() {
		return killedByHole;
	}

	public void setKilledByHole(boolean killeedByHole) {
		this.killedByHole = killeedByHole;
	}

	public ArrayList<Character> getRockets() {
		return rockets;
	}

	public void setRockets(ArrayList<Character> r) {
		this.rockets = r;
	}
	public void addRocket(Character r) {
		this.rockets.add(r);
	}
	public void addHole(Hole r) {
		this.holes.add(r);
	}
	public ArrayList<Hole> getHoles() {
		return holes;
	}

	public void setHoles(ArrayList<Hole> r) {
		this.holes = r;
	}
	public int getCurrentIndexMonster() {
		return currentIndexMonster;
	}

	public void setCurrentIndexMonster(int currentIndexMonster) {
		this.currentIndexMonster = currentIndexMonster;
	}

	public int getCurrentIndexRocket() {
		return currentIndexRocket;
	}

	public void setCurrentIndexRocket(int currentIndexRocket) {
		this.currentIndexRocket = currentIndexRocket;
	}
	public int getCurrentIndexHole() {
		return currentIndexHole;
	}

	public void setCurrentIndexHole(int currentIndexHole) {
		this.currentIndexHole = currentIndexHole;
	}

	

}