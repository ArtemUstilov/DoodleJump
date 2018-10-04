package engine;

import java.util.ArrayList;

import characters.*;
import characters.Character;
import graphicAndSounds.Images;
import graphicAndSounds.MusicLoad;

public class AnimationAndCheckers {
	public static void fallInHole(Game game) {
		int x = game.getHoles().get(game.getCurrentIndexHole()).getX() + 75;
		int y = game.getHoles().get(game.getCurrentIndexHole()).getY() + 70;
		int xD = game.getDoodleHero().getX();
		int yD = game.getDoodleHero().getY();
		double changeX = (xD - x) / 25;
		double changeY = (yD - y) / 25;
		int h = 58;
		int w = 58;

		for (int i = 0; i < 27; i++) {
			game.getDoodleHero().setImageW(w);
			game.getDoodleHero().setImageH(h);
			game.getDoodleHero().setLocation(
					(int) (game.getDoodleHero().getX() - changeX),
					(int) (game.getDoodleHero().getY() - changeY));
			game.getDoodleHero().setAncle(Math.PI / 5, h / 2, h / 2);
			game.getDoodleHero().repaint();
			w -= 2;
			h -= 2;
			UsableM.pause(40);
		}
	}

	public static int checkHitPlatformAndMonst(ArrayList<Platform> a,
			Doodle dood, int PLATF_W, Game game) {

		if (checkHitHole(game)) {
			return 11;
		}

		if (checkHitMonster(false, game))
			return 10;
		for (Platform other : game.getPlatforms()) {
			if (other.id == 5)
				continue;
			if (dood.getX() + 50 >= other.getX()
					&& dood.getX() <= other.getX() + 40

					&& dood.getY() + 50 == other.getY()) {
				game.setCurrentIndexPlatf(a.indexOf(other));
				game.setHowMuchMoveDownPlatf((game.getBeginyforplatf()
						- other.getY() > 0) ? game.getBeginyforplatf()
						- other.getY() : 0);
				return other.id;
			}
		}
		return -1;
	}

	public static boolean checkHitMonster(boolean checkBottom, Game game) {
		if (game.getMonsters().size() > 0) {
			if (checkBottom) {
				for (int i = 0; i < game.getMonsters().size(); i++) {
					Monster monst = game.getMonsters().get(i);
					if(monst==null)continue;
					if (game.getDoodleHero().getX() >= monst.getX() - 35
							&& game.getDoodleHero().getX() <= monst.getX() + 140
							&& game.getDoodleHero().getY() >= monst.getY() + 60
							&& game.getDoodleHero().getY() <= monst.getY() + 80) {
						game.setCurrentIndexMonster(i);
						return true;
					}
				}
			} else {
				for (int i = 0; i < game.getMonsters().size(); i++) {
					Monster monst = game.getMonsters().get(i);
					if(monst==null)continue;
					if (game.getDoodleHero().getX() >= monst.getX() - 35
							&& game.getDoodleHero().getX() <= monst.getX() + 130
							&& game.getDoodleHero().getY() + 110 >= monst
									.getY()
							&& game.getDoodleHero().getY() + 90 <= monst.getY()) {
						game.setCurrentIndexMonster(i);
						return true;
					}
				}
			}
		}

		return false;
	}

	public static boolean checkHitHole( Game game) {
		for(int i= 0; i < game.getHoles().size(); i++){
			Hole hole = game.getHoles().get(i);
			if(hole!=null){
			if (game.getDoodleHero().getX() >= hole.getX() - 25
					&& game.getDoodleHero().getX() <= hole.getX() + 120
					&& game.getDoodleHero().getY() >= hole.getY() + 90
					&& game.getDoodleHero().getY() <= hole.getY() + 110) {
				game.setCurrentIndexHole(i);
				return true;
			}
			if (game.getDoodleHero().getX() >= hole.getX() - 25
					&& game.getDoodleHero().getX() <= hole.getX() + 120
					&& game.getDoodleHero().getY() + 50 >= hole.getY()
					&& game.getDoodleHero().getY() + 40 <= hole.getY()) {
				game.setCurrentIndexHole(i);
				return true;
			}
		}
		}
	

		return false;
	}

	public static boolean checkHitMonsterByBullet(Game game, Bullet bullet) {
		for(int i = 0; i < game.getMonsters().size(); i++){
			Monster y = game.getMonsters().get(i);
		 if(bullet!=null&& y!=null){
		 if(bullet.getX() >= y.getX() - 30
		 && bullet.getX() <= y.getX() + 110
		 && bullet.getY() >= y.getY() + 60
		 && bullet.getY() <= y.getY() + 80){
			 game.setCurrentIndexMonster(i);
			 return true;
		 }
		 }}
		return false;
	}

	public static void monsterFlight(Monster monster, Game game) {
		new Thread() {
			public void run() {
				boolean moveRight = true;
				while (!game.isDead() && monster != null
						&& game.isGameIsContinue()) {
					if (!game.nowIsPause) {
						if (monster.getX() + game.getPlatfW() > game
								.getWindowWidth())
							moveRight = false;
						if (monster.getX() < 0)
							moveRight = true;
						if (moveRight)
							monster.setLocation(monster.getX() + 3,
									monster.getY());
						else
							monster.setLocation(monster.getX() - 3,
									monster.getY());
					}
					UsableM.pause(30);
				}
			}
		}.start();
	}

	public static void animationForSpring(int currentIndexPlatf, Game game) {
		new Thread() {
			public void run() {
				Platform t = game.getPlatforms().get(currentIndexPlatf);
				t.setImage(Images.p_greenS1);
				t.setLocation(t.getX(), t.getY() - 12);
				UsableM.pause(500);
				t.setImage(Images.p_greenS0);
				t.setLocation(t.getX(), t.getY() + 12);
			}
		}.start();
	}

	public static boolean checkRocket(Game game) {
		for(int i= 0; i < game.getRockets().size(); i++){
		if (game.getRockets().get(i) == null)continue;
			Character r = game.getRockets().get(i);
			if (game.getDoodleHero().getY() >= r.getY()
					&& game.getDoodleHero().getY() <= r.getY() + 250
					&& game.getDoodleHero().getX() > r.getX()
					&& game.getDoodleHero().getX() < r.getX() + 200){
				game.setCurrentIndexRocket(i);
				return true;
		}}
		return false;
	}

	public static void animationDoodle(Doodle dud, Game game) {
		new Thread() {
			public void run() {
				if (dud.getImage() == Images.doodleL) {
					dud.setImage(Images.doodleLJump);
					UsableM.pause(100);
					if (dud.getImage() != Images.doodleRJump)
						dud.setImage(Images.doodleL);
					else
						dud.setImage(Images.doodleR);
				} else {
					dud.setImage(Images.doodleRJump);
					UsableM.pause(100);
					if (dud.getImage() == Images.doodleRJump)
						dud.setImage(Images.doodleR);
					else
						dud.setImage(Images.doodleL);
				}
			}
		}.start();

	}

	public static int count = 0;

	public static void MonsterFly(Game game, int monster) {
		if (game.getMonsters().get(monster).flight) {
			AnimationAndCheckers.monsterFlight(game.getMonsters().get(monster),
					game);
		}
		new Thread() {
			public void run() {

				long i = 0;
				int curCount = count;
				while (!game.isDead() && curCount == count
						&& game.isGameIsContinue()) {
					if (!game.nowIsPause) {
						if (game.getMonsters().get(monster) != null) {
							if (i % 2 == 0
									&& game.getMonsters().get(monster).getY() > -50
									&& game.getMonsters().get(monster).getY() < game
											.getWindowHeight() - 100)
								MusicLoad.playSound(game.getAlienVoic()).join();
						}
						UsableM.pause(200);
						if (game.getMonsters().get(monster) != null) {
							game.getMonsters().get(monster)
									.setImage(Images.bat2);
						}
						UsableM.pause(200);
						if (game.getMonsters().get(monster) != null) {
							game.getMonsters().get(monster)
									.setImage(Images.bat1);
							i++;
						}
					} else
						UsableM.pause(100);
				}
			}
		}.start();
	}
}
