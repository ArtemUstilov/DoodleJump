package gameInterface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import engine.Game;
import engine.UsableM;
import graphicAndSounds.Images;

public class KeyMoveRocket implements KeyListener {
	private Game game;

	public KeyMoveRocket(Game game) {
		super();
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		new Thread() {
			public void run() {
				if (!game.isDead()) {
					characters.Character r = game.getRockets().get(game.getCurrentIndexRocket());
					if (code == 68) {
						if (r.getX() + 50 > game
								.getWindowWidth()) {
							r.setLocation(0,
									r.getY());
						}
						if (r.getImage() != Images.rocketDoodle) {
							r.setImage(Images.rocketDoodle);
						}
						for (int i = 0; i < 20 && game.isGameIsContinue(); i++) {
							if (r != null)
								r.setLocation(
										r.getX() + 2,
										r.getY());
							else
								break;
							UsableM.pause(7);
						}
					} else if (code == 65) {
						if (r.getImage() != Images.rocketDoodleL) {
							r.setImage(Images.rocketDoodleL);
						}
						for (int i = 0; i < 20 && game.isGameIsContinue(); i++) {
							if (r != null)
								r.setLocation(
										r.getX() - 2,
										r.getY());
							else
								break;
							UsableM.pause(7);
						}
						if(r!=null)
						if (r.getX() + 50 < 0) {
							r.setLocation(
									game.getWindowWidth() - 50,
									r.getY());
						}
					}
				}
			}
		}.start();

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
