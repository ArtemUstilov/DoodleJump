package engine;
import graphicAndSounds.*;

import java.util.Random;

import characters.*;
import characters.Character;
import gameInterface.Label;
public class Create {
	public static void createScoreLabel(Game game){
		game.setScoreLabel(new Label("Score: " + game.getScores(), 5, 5, 150, 20,
				GameFont.getFont(20f), game.getScores()));
		game.getPanel().add(game.getScoreLabel());
	}
	public static void createPlatforms(Game game){
		Platform start = new Platform(Images.greenF, game.getWindowWidth() / 2 - game.getPlatfW()
				/ 2, game.getWindowHeight() - game.getPlatfH() * 5, game.getPlatfW(), game.getPlatfH(), 5, 1);
		game.getPlatforms().add(start);
		Platform a;
		int floor = 0;
		for (int i = 0; i < 16; i++) {
				a = new Platform(Images.greenF, new Random().nextInt(game.getWindowWidth()-game.getPlatfW()),
						new Random().nextInt(80) + floor, game.getPlatfW(), game.getPlatfH(), 5, 1);
				floor += 50;
			game.getPlatforms().add(a);
		}
	}

	public static void createMonster(Game game, int x, int y, boolean Fly){
	    	  Monster Mon = new Monster(Images.bat1, x,y, 200,100,Fly);
	    	  game.addMonster(Mon);
	    	  int j = game.getMonsters().indexOf(Mon);
		  		game.getPanel().add(Mon);
		  		 if(Mon.flight){
		              AnimationAndCheckers.monsterFlight(Mon, game);
		            }	
		  	    new Thread(){
		  	      public void run(){
		  		
		  		long i = 0;
	    	while(!game.isDead()&&game.getMonsters().get(j)!=null&&game.isGameIsContinue()){
	    		if(!game.nowIsPause){
	    		if(Mon!=null){
	    		if(i%2==0&&Mon.getY()>-50&&Mon.getY()<game.getWindowHeight()-100)
						MusicLoad.playSound(game.getAlienVoic()).join();
	    		}
	  		UsableM.pause(200);
	  		if(Mon!=null){
	  		Mon.setImage(Images.bat2);
	  		}
	  		UsableM.pause(200);
	  		if(Mon!=null){
	  		Mon.setImage(Images.bat1);
	  		i++;
	  		}
	    	  }else UsableM.pause(100);
	    		}
	      }
	    }.start();
	  }
	public static void createHero(Game game){
		if(game.getDoodleHero()!=null)game.setDoodleHero(null);
		game.setDoodleHero(new Doodle(Images.doodleL, game.getWindowWidth() / 2
				- game.getPlatfW() / 3, game.getPlatforms().get(0).getY() - Doodle.HEIGHT
				+ game.getPlatfH()));
		game.getPanel().add(game.getDoodleHero());
	}
	public static void createHole(Game game, int x, int y) {
		Hole r = new Hole(Images.hole,  x,  y, 200, 250);
		game.addHole(r);
		game.getPanel().add(r);
	}
	public static void createRocket(Game game, int x, int y){
		Character r = new Character(Images.rocket,  x,  y, 200, 250);
		game.addRocket(r);
		game.getPanel().add(r);
		 
	}
}
