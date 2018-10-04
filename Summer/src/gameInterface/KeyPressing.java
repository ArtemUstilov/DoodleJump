package gameInterface;

import graphicAndSounds.Images;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import engine.Game;
import engine.Move;

public class KeyPressing implements KeyListener{
	private Game game;
	public KeyPressing(Game game) {
		super();
		this.game = game;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		if(!game.isDead()){
		if (code == 68) {
			if (game.getDoodleHero().getX() + 50 > game.getWindowWidth()) {
				game.getDoodleHero().setLocation(0, game.getDoodleHero().getY());
			}

			Move.moveRight(game.getDoodleHero(), Images.doodleR, game);
		} else if (code == 65) {
			Move.moveLeft(game.getDoodleHero(), Images.doodleL, game);
			if (game.getDoodleHero().getX() + 50 < 0) {
				game.getDoodleHero().setLocation(game.getWindowWidth() - 50,
						game.getDoodleHero().getY());
			}
			}else if(code == 32){
				if(System.currentTimeMillis()-countTimePrevBullet>500){
			      Move.fire(game.getDoodleHero(), Images.dodleS);
			      Move.shot(game.getDoodleHero(), game);
			      countTimePrevBullet = System.currentTimeMillis();
				}
			     
			    
			}
		}
		}
	public static double countTimePrevBullet;
	
	@Override
	public void keyReleased(KeyEvent arg0) {
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
