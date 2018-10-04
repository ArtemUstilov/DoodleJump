package characters;

import engine.Game;

public class BreakingPlatform extends Platform{
	private static final long serialVersionUID = 1L;
	
	public BreakingPlatform(String img, int x, int y, int speed, int id, Game game) {
		super(img, x, y, game.getPlatfW(), game.getPlatfH(), speed, id);
	}
}
