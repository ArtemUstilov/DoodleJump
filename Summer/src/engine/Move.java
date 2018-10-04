package engine;

import java.util.ArrayList;
import java.util.Random;

import characters.*;
import characters.Character;
import graphicAndSounds.*;

public class Move {
	static Random random = new Random();

	public static void moveRight(Doodle a, String img, Game game) {
		new Thread() {
			public void run() {
				if (a.getImage() != img) {
					a.setImage(img);
				}
				for (int i = 0; i < 20 && game.isGameIsContinue(); i++) {
					game.getDoodleHero().setLocation(a.getX() + 2, a.getY());
					UsableM.pause(7);
				}
			}
		}.start();
	}

	public static void moveLeft(Doodle a, String img, Game game) {
		new Thread() {
			public void run() {
				if (a.getImage() != img) {
					a.setImage(img);
				}
				for (int i = 0; i < 20 && game.isGameIsContinue(); i++) {
					game.getDoodleHero().setLocation(a.getX() - 2, a.getY());
					UsableM.pause(7);
				}
			}
		}.start();
	}

	public static void fire(Doodle a, String img) {
		new Thread() {
			public void run() {
				String temp = a.getImage();
				a.setImage(img);
				UsableM.pause(200);
				a.setImage(temp);

			}
		}.start();
	}

	public static void shot(Doodle doodleHero, Game game) {
		new Thread() {
			public void run() {
				Bullet bullet = new Bullet(Images.bullet,
						doodleHero.getX() + 30, doodleHero.getY(), 20, 20);
				game.getPanel().add(bullet);
				while (bullet.getY() > -20) {
					bullet.setLocation(bullet.getX(), bullet.getY() - 3);
					UsableM.pause(5);

					if (AnimationAndCheckers.checkHitMonsterByBullet(game,
							bullet)) {
						game.safe.setKillMonster(1);
						MusicLoad.playSound(game.getMonsterHi()).join();
						if (game.getMonsters().get(game.getCurrentIndexMonster()) != null)
							game.getPanel().remove(game.getMonsters().get(game.getCurrentIndexMonster()));
						game.getMonsters().set(game.getCurrentIndexMonster(), null);
						game.getPanel().remove(bullet);
					}
				}
				game.getPanel().remove(bullet);
			}

		}.start();
	}
	public static void platformMoveDown(ArrayList<Platform> a,
			Game game, int step) {
		ArrayList<Platform> temp = new ArrayList<Platform>();
		for (int i = 0; i <= a.size() - 1; i++) {
			
			if (a.get(i).getY() > game.getWindowHeight() - game.getPlatfH()) {
				temp.add(a.get(i));
			} else {
				a.get(i).setLocation(a.get(i).getX(), a.get(i).getY() + step);
			}
		}
		for(int i = 0; i < temp.size(); i++){
			a.remove(temp.get(i));
			game.getPanel().remove(temp.get(i));
		}
	}

	public static void monsterMoveDown(int step, Game game) {
		for(int i = 0; i < game.getMonsters().size(); i++){
			Monster y = game.getMonsters().get(i);
			if(y==null)continue;
		if (y.getY() < game.getWindowHeight() + 50) {
			y.setLocation(y.getX(),
					y.getY() + step);
		} else {
			game.getPanel().remove(y);
			game.getMonsters().set(i, null);
		}
		}
	}

	public static void holeMoveDown(int step, Game game) {
		for(int i= 0; i < game.getHoles().size(); i++){
			Hole r = game.getHoles().get(i);
		if (r== null)continue;
		if (r.getY() < game.getWindowHeight() + 50) {
			r.setLocation(r.getX(),
					r.getY() + step);
		} else {
			game.getPanel().remove(r);
			game.getHoles().set(game.getCurrentIndexHole(), null);
		}
		}
	}

	public static void rocketMoveDown(int step, Game game) {
		for(int i= 0; i < game.getRockets().size(); i++){
			Character r = game.getRockets().get(i);
		if (r== null)continue;
		if (r.getY() < game.getWindowHeight() + 50) {
			r.setLocation(r.getX(),
					r.getY() + step);
		} else {
			game.getPanel().remove(r);
			game.getRockets().set(game.getCurrentIndexRocket(), null);
		}
		}
	}

	public static void displayMoveUp(ArrayList<Platform> a, Game game) {
		for (int i = 0; i <= a.size() - 1; i++) {
			if (a.get(i).getY() < 0) {
				UsableM.removePlatf(a.get(i), game);
			}
		}
		int j = game.getWindowHeight();
		for(int i = 0; i < 20; i++){
			Platform p = new Platform(Images.greenF, new Random().nextInt(game.getWindowWidth()-game.getPlatfW()), j+60,
					game.getPlatfW(), game.getPlatfH(), 0, 1);
			game.getPanel().add(p);
			game.getPlatforms().add(p);
			j = p.getY();
		}
		int dist = 1;
		for(int d = 0; d < 80; d++){
		Doodle t = game.getDoodleHero();
		if(t.getY()>game.getWindowHeight()/2)game.getDoodleHero().setLocation(t.getX(), t.getY()-10);
		game.getPanel().updateUI();
		monsterMoveUp(dist, game);
		holeMoveUp(dist, game);
		rocketMoveUp(dist, game);
		for (int i = 0; i <= a.size() - 1; i++) {
			if(a.get(i).getY()<0)UsableM.removePlatf(a.get(i), game); 
			else
			a.get(i).setLocation(a.get(i).getX(),
					 a.get(i).getY() - dist);
		}
		
		dist++;
		UsableM.pause(30);
		}
		UsableM.pause(200);
		int acc = 3;
		Doodle t = game.getDoodleHero();
		while(t.getY()<game.getWindowHeight()+100){
			t.setLocation(t.getX(), t.getY()+acc);
			acc++;
			UsableM.pause(30);
		}
		UsableM.pause(500);

	}

	private static void rocketMoveUp(int dist, Game game) {
		for(int i= 0; i < game.getRockets().size(); i++){
			Character r = game.getRockets().get(i);
		if (r== null)continue;
			if (r.getY() < -100) {
				game.getPanel().remove(r);
				game.getRockets().set(game.getCurrentIndexRocket(), null);
			} else{
				r.setLocation(r.getX(),
				r.getY() - dist);
			}
		
		}
	}

	public static void monsterMoveUp(int dist, Game game) {
		for(int i = 0; i < game.getMonsters().size(); i++){
			Monster y = game.getMonsters().get(i);
			if (y != null) {
			if (y.getY() < -100) {
				game.getPanel().remove(y);
				game.getMonsters().set(i, null);

			} else
				y.setLocation(y.getX(),
						y.getY() - dist);
		}
		}
	}

	public static void holeMoveUp(int dist, Game game) {
		for(int i= 0; i < game.getHoles().size(); i++){
			Hole r = game.getHoles().get(i);
		if (r== null)continue;
			if (r.getY() < -100) {
				game.getPanel().remove(r);
				game.getHoles().set(game.getCurrentIndexHole(), null);
			} else{
				r.setLocation(r.getX(),
				r.getY() - dist);
			}
		
		}
	}

	public static void jumpDoodle(int acc, boolean monsterJump, Game game) {
		while (acc > 0) {
			int i = 0;
			while (i < acc) {
				if (!monsterJump) {
					if (AnimationAndCheckers.checkHitMonster(true, game)) {
						game.setDead(true);
						return;
					}
				}
				if (!monsterJump) {
					if (AnimationAndCheckers.checkHitHole(
							game)) {
						game.setKilledByHole(true);
						game.setDead(true);
						return;
					}
				}
				game.getDoodleHero().setLocation(game.getDoodleHero().getX(),
						game.getDoodleHero().getY() - 1);
				UsableM.pause(35 / acc);
				i++;
			}
			acc--;
		}

	}

	public static void flyRocket(Game game) {
		int sum = 0;
		int dist = 4000;
		int acc = 1;
		Character r = game.getRockets().get(game.getCurrentIndexRocket());
		while (sum < 500) {
			if(!game.nowIsPause){
				if(r.getY()-game.getWindowHeight()/3>50){
					r.setLocation(r.getX(), r.getY()-acc);
					UsableM.pause(25);
				}else{
			Move.platformMoveDown(game.getPlatforms(), game, acc);
//			if (game.getEasyMonster() != null) {
				Move.monsterMoveDown(acc, game);
//			}
			if (game.getBlackHole() != null) {
				Move.holeMoveDown(acc, game);
			}
			game.setScores(game.getScores() + 20);
			game.getScoreLabel().setText("Score: " + game.getScores());
			UsableM.pause(25);
				}
			sum += acc;
			acc += 2;
			}else UsableM.pause(100);
		}
		while (sum < dist - 500) {
			if(!game.nowIsPause){
			Move.platformMoveDown(game.getPlatforms(),game, acc);
//			if (game.getEasyMonster() != null) {
				Move.monsterMoveDown(acc, game);
//			}
			if (game.getBlackHole() != null) {
				Move.holeMoveDown(acc, game);
			}
			game.setScores(game.getScores() + acc);
			game.getScoreLabel().setText("Score: " + game.getScores());
			UsableM.pause(25);
			sum += acc;
			} else UsableM.pause(100);
		}
		while (sum < dist) {
			if(!game.nowIsPause){
		
			Move.platformMoveDown(game.getPlatforms(), game, acc);
//			if (game.getEasyMonster() != null) {
				Move.monsterMoveDown(acc, game);
//			}
			if (game.getBlackHole() != null) {
				Move.holeMoveDown(acc, game);
			}
			game.setScores(game.getScores() + acc);
			game.getScoreLabel().setText("Score: " + game.getScores());
			UsableM.pause(25);
			sum += acc;
			acc -= 2;
			} else UsableM.pause(100);
		}
	}

	public static int displayMoveDown(int dist, int ch, boolean monsterJump,
			Game game) {
		int sum = 11;
		while (sum < dist) {
			if (!monsterJump) {
				if (AnimationAndCheckers.checkHitMonster(true, game)) {
					game.setDead(true);
					break;
				}
			}
				if (AnimationAndCheckers
						.checkHitHole( game)) {
					game.setKilledByHole(true);
					game.setDead(true);
					break;
				}
			Move.platformMoveDown(game.getPlatforms(), game, ch);
//			if (game.getEasyMonster() != null) {
				Move.monsterMoveDown(ch, game);
//			}
			//if (game.getBlackHole() != null) {
				Move.holeMoveDown(ch, game);
			//}
//			if (game.getRocket() != null) {
				Move.rocketMoveDown(ch, game);
//			}
			game.setScores(game.getScores() + ch);
			game.getScoreLabel().setText("Score: " + game.getScores());
			UsableM.pause(25);
			ch -= 1;
			sum += ch;
		}
		return ch;
	}

	public static void updating(Game game) {
		new Thread() {
			public void run() {
				while (!game.isDead()) {
					game.getPanel().updateUI();
					UsableM.pause(15);
				}
			}
		}.start();
	}

	public static void fallDoodle(double FallSpeed, Doodle doodleHero, Game game) {
		game.getDoodleHero().setLocation(game.getDoodleHero().getX(),
				(int) (game.getDoodleHero().getY() + FallSpeed * 1.5));

	}

	public static void moveDownBrokenP(Platform platform, Game game) {
		new Thread() {
			public  void run() {
				platform.id = 5;
				platform.setLocation(platform.getX(), platform.getY() + 5);
				UsableM.pause(30);
				platform.setImage(Images.p_brown2);
				platform.setLocation(platform.getX(), platform.getY() + 5);
				UsableM.pause(30);
				platform.setImage(Images.p_brown3);
				platform.setLocation(platform.getX(), platform.getY() + 5);
				UsableM.pause(30);
				platform.setImage(Images.p_brown4);
				platform.setLocation(platform.getX(), platform.getY() + 5);
				UsableM.pause(30);
				platform.setImage(Images.p_brown5);
				platform.setLocation(platform.getX(), platform.getY() + 5);
				UsableM.pause(30);
				platform.setImage(Images.p_brown6);
				int i = 0;
				while (i < 20 && platform.getY() < game.getWindowHeight() + 5
						&& !game.isDead()) {
					if(!game.nowIsPause){
					platform.setLocation(platform.getX(), platform.getY() + 5);
					UsableM.pause(30);
					i++;
				}else UsableM.pause(50);
				}
				platform.setVisible(false);
			}
		}.start();
	}

	public static void moveGorizontalP(Platform a, Game game) {
		new Thread() {
			public void run() {
				boolean moveRight = true;
				while (!game.isDead() && a != null && game.isGameIsContinue()) {
					if (!game.nowIsPause) {
						if (a.getImage() != Images.p_blue)
							break;
						if (a.getX() + game.getPlatfW() > game.getWindowWidth())
							moveRight = false;
						if (a.getX() < 0)
							moveRight = true;
						if (moveRight)
							a.setLocation(a.getX() + 3, a.getY());
						else
							a.setLocation(a.getX() - 3, a.getY());
					}
					UsableM.pause(30);
				}

			}
		}.start();
	}
}
