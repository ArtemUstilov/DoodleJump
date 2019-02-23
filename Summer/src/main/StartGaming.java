package main;



import graphicAndSounds.MusicLoad;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import characters.Character;
import characters.Doodle;
import characters.Platform;
import engine.Game;
import engine.UsableM;
import gameInterface.StartPanel;

public class StartGaming {
	public static Game game;
	private static JFrame window;
	public static boolean gameIsStarted = false;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
		game = new Game();
		for(int i=0;i<10;i++) {
			System.out.println("Hello!!!");
		}
		game.getPanel().setVisible(true);
		initial(game);
        } });
	}
	public static void initial(Game game){
		gameIsStarted = false;
		setWindow(new JFrame());
		getWindow().setResizable(false);
		getWindow().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getWindow().setSize(game.getWindowWidth(), game.getWindowHeight());
		getWindow().repaint();
		game.getPanel().setLayout(null);
		getWindow().add(game.getPanel());
		getWindow().setVisible(true);
	}
	public static JFrame getWindow() {
		return window;
	}

	public static void setWindow(JFrame window) {
		StartGaming.window = window;
	}
		
	
	public static void startAnimation(Doodle a, Platform platform, Game game) {
		new Thread(){
			public void run(){
				while(!gameIsStarted){
					moveStartDoodUp(20, a, game);
					moveStartDoodDown(210, a, game);
					if(!gameIsStarted&&StartPanel.t.isVisible())
					MusicLoad.playSound(Game.bump).join();
					game.getPanel().updateUI();
				}
			
			}
	}.start();
	}
	public static void moveStartDoodUp(int acc, Doodle dood, Game game) {
		while (acc > 0&&!gameIsStarted&&!game.isDead()) {
			int i = 0;
			while (i < acc&&!gameIsStarted) {
					dood.setLocation(dood.getX(),
						dood.getY() - 1);
				UsableM.pause(35 / acc);
				i++;
				game.getPanel().updateUI();
			}
			acc--;
		}

	}
	public static void moveStartDoodDown(int acc, Character dood, Game game) {
		int FallSpeed = 1;
		int sum = 0;
		while (sum<acc&&!gameIsStarted&&!game.isDead()) {
			
					dood.setLocation(dood.getX(),
							dood.getY() + FallSpeed);
				UsableM.pause(19);
				game.getPanel().updateUI(); 
				sum+=FallSpeed++;
		}

	}
}
