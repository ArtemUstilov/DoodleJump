package engine;

import java.util.Random;
import java.util.Stack;
import characters.PlatfDoc;
import characters.Platform;
import graphicAndSounds.Images;

public class Levels {
	private static Platform prevP;

	public static void initPlatforms(Game game) {
		prevP = game.getPlatforms().get(0);
		for (int i = 0; i < game.getPlatforms().size(); i++) {

			game.getPlatforms().get(i).id = 1;
			game.getPlatforms().get(i).setImage(Images.greenF);
			game.getPanel().add(game.getPlatforms().get(i));
			if (game.getPlatforms().get(i).getY() < prevP.getY())
				prevP = game.getPlatforms().get(i);
		}
		secondLvl(game);
		sixthLvl(game);
	
	}

	public static void addLevels(Game game) {
		new Thread(){
			public void run(){
while(!game.isDead()){
	if (prevP.getY()>-500) {
			int y = new Random().nextInt(16);
			int rocket = new Random().nextInt(50);
			if(rocket==3)Create.createRocket(game, game.getWindowWidth()/((y<2)?2:y),-300);
			
			switch (y) {
			case 0:
				secondLvl(game);
				break;
			case 1:
				Bl_W_M_Lvl(game);
				break;
			case 2:
				thirdLvl(game);
				break;
			case 3:
				fourthLvl(game);
				break;
			case 4:
				fifthLvl(game);
				break;
			case 5:
				onlySpringsLvl(game);
				break;
			case 6:
				sixthLvl(game);
				break;
			case 7:
				seventhLvl(game);
				break;
			case 8:
				eighthLvl(game);
				break;
			case 9:
				ninthLvl(game);
				break;
			case 10:
				tenthLvl(game);
				break;
			case 11:
				eleventhLvl(game);
				break;
			case 12:
				twelfthLvl(game);
				break;
			case 13:
				thirteenthLvl(game);
				break;
			case 14:
				fourteenthLvl(game);
				break;
			case 15:
				fifteenthLvl(game);
			case 16:
				sixteenthLvl(game);
				break;
			}
			
		}
		UsableM.pause(500);
			}
				}
		}.start();
	}

	public static void secondLvl(Game game) {
		for (int i = 0; i < 20; i++) {
			if (game.isDead())
				return;
			Platform p;
			if(i==10)Create.createMonster(game, 200, prevP.getY(), true);
			if (i % 3 == 0)
				p = new Platform(Images.p_brown1, new Random().nextInt(game
						.getWindowWidth() - game.getPlatfW()),
						prevP.getY() - 60, game.getPlatfW(), game.getPlatfH(),
						0, 4);
			else
				p = new Platform(Images.greenF, new Random().nextInt(game
						.getWindowWidth() - game.getPlatfW()),
						prevP.getY() - 60, game.getPlatfW(), game.getPlatfH(),
						0, 1);

			game.getPlatforms().add(p);
			game.getPanel().add(p);
			prevP = p;

		}
	}

	public static void fourthLvl(Game game) {
		Create.createHole(game, 300, prevP.getY());
		for (int i = 0; i < 10; i++) {
			if (game.isDead())
				return;
			Platform p = new Platform(Images.p_White, new Random().nextInt(game
					.getWindowWidth() - game.getPlatfW()), prevP.getY() - 60,
					game.getPlatfW(), game.getPlatfH(), 0, 2);
			game.getPlatforms().add(p);
			game.getPanel().add(p);
			prevP = p;
		}
	}

	public static void thirdLvl(Game game) {
		for (int i = 0; i < 10; i++) {
			if (game.isDead())
				return;
			Platform p = new Platform(Images.p_blue, new Random().nextInt(game
					.getWindowWidth() - game.getPlatfW()), prevP.getY() - 60,
					game.getPlatfW(), game.getPlatfH(), 0, 1);
			Move.moveGorizontalP(p, game);
			game.getPlatforms().add(p);
			game.getPanel().add(p);
			prevP = p;
		}
	}

	public static void fifthLvl(Game game) {
		Stack<PlatfDoc> r = new Stack<PlatfDoc>();
		for (int i = 0; i < 5; i++) {
			r.push(new PlatfDoc(1, Images.greenF, 10, 150));
			r.push(new PlatfDoc(1, Images.greenF, game.getWindowWidth()
					- game.getPlatfW() - 10, 150));
		}
		for (int i = 0; i < 10; i++) {
			if (game.isDead())
				return;
			Platform p = new Platform(Images.greenF, game.getWindowWidth()
					- game.getPlatfW() - 10, prevP.getY() - 60,
					game.getPlatfW(), game.getPlatfH(), 0, 1);
			Platform q = new Platform(Images.greenF, 10, prevP.getY() - 60,
					game.getPlatfW(), game.getPlatfH(), 0, 1);
			game.getPlatforms().add(p);
			game.getPanel().add(p);
			game.getPlatforms().add(q);
			game.getPanel().add(q);
			prevP = p;
		}
	}

	public static void onlySpringsLvl(Game game) {
		Stack<PlatfDoc> r = new Stack<PlatfDoc>();
		for (int i = 0; i < 2; i++) {
			r.push(new PlatfDoc(3, Images.p_greenS0, new Random().nextInt(game
					.getWindowWidth() - game.getPlatfW() - 10), 350));
		}
		r.push(new PlatfDoc(3, Images.p_greenS0, new Random().nextInt(game
				.getWindowWidth() - game.getPlatfW() - 10), 100));
		for (int i = 0; i < 3; i++) {
			if (game.isDead())
				return;
			Platform p = r.pop().createPlatf(prevP.getY());
			game.getPlatforms().add(p);
			game.getPanel().add(p);
			prevP = p;
		}
	}

	public static void Bl_W_M_Lvl(Game game) {
		Stack<PlatfDoc> r = new Stack<PlatfDoc>();
		for (int i = 0; i < 2; i++) {
			r.push(new PlatfDoc(1, Images.p_blue, new Random().nextInt(game
					.getWindowWidth() - game.getPlatfW() - 10), 130));
		}
		for (int i = 1; i < 4; i++) {
			r.push(new PlatfDoc(2, Images.p_White, (game.getWindowWidth()
					- game.getPlatfW() - 10)
					/ 4 * i, (i == 3) ? 130 : 0));
		}
		for (int i = 1; i < 4; i++) {
			r.push(new PlatfDoc(2, Images.p_White, (game.getWindowWidth()
					- game.getPlatfW() - 10)
					/ 4 * i, (i == 3) ? 130 : 0));
		}
		for (int i = 0; i < 2; i++) {
			r.push(new PlatfDoc(1, Images.p_blue, new Random().nextInt(game
					.getWindowWidth() - game.getPlatfW() - 10), 130));
		}
		for (int i = 0; i < 11; i++) {
			if (game.isDead())
				return;
			if (i == 8) {
				Create.createMonster(game, game.getWindowWidth() / 2 - 100,
						prevP.getY() - 120, false);
			} else {
				Platform p = r.pop().createPlatf(prevP.getY());
				game.getPlatforms().add(p);
				game.getPanel().add(p);
				if (p.getImage() == Images.p_blue)
					Move.moveGorizontalP(p, game);
				prevP = p;
			}
		}
	}

	public static void sixthLvl(Game game) {
		Stack<PlatfDoc> r = new Stack<PlatfDoc>();
		Create.createRocket(game, game.getWindowWidth()/2,prevP.getY());
		for (int i = 0; i < 4; i++) {
			if (game.isDead())
				return;
			if (i % 2 == 0) {
				r.push(new PlatfDoc(2, Images.p_White, 200, 150));
			} else {
				r.push(new PlatfDoc(1, Images.p_blue, new Random().nextInt(game
						.getWindowWidth() - game.getPlatfW() - 10), 150));
			}
			Platform p = r.pop().createPlatf(prevP.getY());
			game.getPlatforms().add(p);
			game.getPanel().add(p);
			if (p.getImage() == Images.p_blue)
				Move.moveGorizontalP(p, game);
			prevP = p;
		}
	}

	public static void seventhLvl(Game game) {
		Stack<PlatfDoc> r = new Stack<PlatfDoc>();

		r.push(new PlatfDoc(3, Images.p_greenS0, new Random().nextInt(game
				.getWindowWidth() - game.getPlatfW() - 10), 250));

		for (int i = 0; i < 8; i++) {
			if (i == 5 || i == 11) {
				r.push(new PlatfDoc(3, Images.p_greenS0,
						new Random().nextInt(game.getWindowWidth()
								- game.getPlatfW() - 10), 110));
			} else {
				r.push(new PlatfDoc(2, Images.p_White,
						new Random().nextInt(game.getWindowWidth()
								- game.getPlatfW() - 10),110));
			}
		}

		for (int i = 0; i < 8; i++) {
			if (game.isDead())
				return;

			Platform p = r.pop().createPlatf(prevP.getY());
			game.getPlatforms().add(p);
			game.getPanel().add(p);
			prevP = p;

		}
	}

	public static void eighthLvl(Game game) {
		Stack<PlatfDoc> r = new Stack<PlatfDoc>();

		for (int i = 0; i < 5; i++) {

			if (i % 5 != 0) {
				r.push(new PlatfDoc(1, Images.p_blue, new Random().nextInt(game
						.getWindowWidth() - game.getPlatfW() - 10), 160));
			} else {
				r.push(new PlatfDoc(1, Images.greenF, new Random().nextInt(game
						.getWindowWidth() - game.getPlatfW() - 10), 160));
			}
		}

		for (int i = 0; i < 5; i++) {
			if (game.isDead())
				return;
			Platform p = r.pop().createPlatf(prevP.getY());
			game.getPlatforms().add(p);
			game.getPanel().add(p);
			if (i == 3)
				Create.createHole(game, 230, prevP.getY());
			if (i == 7)
				Create.createHole(game, 50, prevP.getY());
			if (p.getImage() == Images.p_blue)
				Move.moveGorizontalP(p, game);
			prevP = p;
		}

	}

	public static void ninthLvl(Game game) {
		Stack<PlatfDoc> r = new Stack<PlatfDoc>();

		for (int i = 0; i < 5; i++) {

			r.push(new PlatfDoc(1, Images.greenF, (i % 2 == 0) ? 10 : game
					.getWindowWidth() - 70, 150));

		}

		for (int i = 0; i < 5; i++) {
			if (game.isDead())
				return;
			Platform p = r.pop().createPlatf(prevP.getY());
			game.getPlatforms().add(p);
			game.getPanel().add(p);
			prevP = p;
		}
	}

	public static void tenthLvl(Game game) {
		Stack<PlatfDoc> r = new Stack<PlatfDoc>();
		r.push(new PlatfDoc(3, Images.p_greenS0, new Random().nextInt(game
				.getWindowWidth() - game.getPlatfW() - 10), 160));
		for (int i = 0; i < 2; i++) {
			r.push(new PlatfDoc(1, Images.p_blue, new Random().nextInt(game
					.getWindowWidth() - game.getPlatfW() - 10), 320));
			r.push(new PlatfDoc(3, Images.p_greenS0, new Random().nextInt(game
					.getWindowWidth() - game.getPlatfW() - 10), 160));
		}

		for (int i = 0; i < 5; i++) {
			if (game.isDead())
				return;
			Platform p = r.pop().createPlatf(prevP.getY());
			game.getPlatforms().add(p);
			if (i == 1)
				Create.createHole(game, 130, prevP.getY());
			game.getPanel().add(p);
			if (p.getImage() == Images.p_blue)
				Move.moveGorizontalP(p, game);
			prevP = p;
		}
	}

	public static void eleventhLvl(Game game) {
		Stack<PlatfDoc> r = new Stack<PlatfDoc>();

		r.push(new PlatfDoc(1, Images.p_blue, new Random().nextInt(game
				.getWindowWidth() - game.getPlatfW() - 10), 120));
		r.push(new PlatfDoc(3, Images.p_greenS0, new Random().nextInt(game
				.getWindowWidth() - game.getPlatfW() - 10), 90));
		r.push(new PlatfDoc(1, Images.p_brown1, new Random().nextInt(game
				.getWindowWidth() - game.getPlatfW() - 10), 10));
		r.push(new PlatfDoc(1, Images.p_blue, new Random().nextInt(game
				.getWindowWidth() - game.getPlatfW() - 10), 150));
		r.push(new PlatfDoc(1, Images.greenF, new Random().nextInt(game
				.getWindowWidth() - game.getPlatfW() - 10), 150));
		r.push(new PlatfDoc(1, Images.greenF, new Random().nextInt(game
				.getWindowWidth() - game.getPlatfW() - 210), 130));
		r.push(new PlatfDoc(1, Images.greenF, new Random().nextInt(game
				.getWindowWidth() - game.getPlatfW() - 110), 120));
		r.push(new PlatfDoc(1, Images.greenF, new Random().nextInt(game
				.getWindowWidth() - game.getPlatfW() - 310), 135));
		r.push(new PlatfDoc(2, Images.p_White, new Random().nextInt(game
				.getWindowWidth() - game.getPlatfW() - 10), 140));
		r.push(new PlatfDoc(2, Images.p_White, 30, 145));
		r.push(new PlatfDoc(2, Images.p_White, new Random().nextInt(game
				.getWindowWidth() - game.getPlatfW() - 110), 140));
		for (int i = 0; i < 11; i++) {
			if (game.isDead())
				return;
			Platform p = r.pop().createPlatf(prevP.getY());
			game.getPlatforms().add(p);
			if (i == 5)
				Create.createMonster(game, 30, prevP.getY(), true);
			if (i == 5)
				Create.createMonster(game, 400, prevP.getY(), true);
			game.getPanel().add(p);
			if (p.getImage() == Images.p_blue)
				Move.moveGorizontalP(p, game);
			prevP = p;
		}
	}
	public static void twelfthLvl(Game game){
	        Stack<PlatfDoc> r = new Stack<PlatfDoc>();
	        r.push(new PlatfDoc(2, Images.p_White, new Random().nextInt(game.getWindowWidth()-game.getPlatfW()-10), 100));
	        r.push(new PlatfDoc(1, Images.greenF, new Random().nextInt(game.getWindowWidth()-game.getPlatfW()-310), 50));
	            for(int i = 0; i < 3; i++){
	            r.push(new PlatfDoc(4,  Images.p_brown1, new Random().nextInt(game.getWindowWidth()-game.getPlatfW()-10), 17));
	            }
	            
	            r.push(new PlatfDoc(1, Images.greenF, new Random().nextInt(game.getWindowWidth()-game.getPlatfW()-310), 50));
	           
	            for(int i = 0; i < 3; i++){
	              r.push(new PlatfDoc(4,  Images.p_brown1, new Random().nextInt(game.getWindowWidth()-game.getPlatfW()-10), 17));
	            }
	            
	            r.push(new PlatfDoc(1, Images.greenF, new Random().nextInt(game.getWindowWidth()-game.getPlatfW()-310), 50));
	            for(int i = 0; i < 3; i++){
	              r.push(new PlatfDoc(4,  Images.p_brown1, new Random().nextInt(game.getWindowWidth()-game.getPlatfW()-10), 17));
	              }
	            r.push(new PlatfDoc(1, Images.greenF, new Random().nextInt(game.getWindowWidth()-game.getPlatfW()-310), 100));
	           
	    for(int i = 0; i <14 ; i++){
	        if(game.isDead())return;
	        Platform p = r.pop().createPlatf(prevP.getY());
	        game.getPlatforms().add(p);
	        if(i==5)Create.createMonster(game, 30,prevP.getY(),true);
	        game.getPanel().add(p);
	        prevP = p;
	        }
	    }
	    public static void thirteenthLvl(Game game){
	        Stack<PlatfDoc> r = new Stack<PlatfDoc>();
	        for(int i = 0; i < 3; i++){
	            r.push(new PlatfDoc(1, Images.p_blue, new Random().nextInt(game.getWindowWidth()-game.getPlatfW()-10), 160));
	            }
	            for(int i = 0; i < 3; i++){
	            r.push(new PlatfDoc(2, Images.p_White, new Random().nextInt(game.getWindowWidth()-game.getPlatfW()-110),(i==2)?340:140));
	            }
	            r.push(new PlatfDoc(3, Images.p_greenS0, new Random().nextInt(game.getWindowWidth()-game.getPlatfW()-10), 90));
	           
	            for(int i = 0; i < 3; i++){
	              r.push(new PlatfDoc(1, Images.p_blue, new Random().nextInt(game.getWindowWidth()-game.getPlatfW()-10), 160));
	            }
	            for(int i = 0; i < 3; i++){
	            r.push(new PlatfDoc(2, Images.p_White, new Random().nextInt(game.getWindowWidth()-game.getPlatfW()-110),(i==2)?340:160));
	            }
	            r.push(new PlatfDoc(3, Images.p_greenS0, new Random().nextInt(game.getWindowWidth()-game.getPlatfW()-10), 90));
	    for(int i = 0; i < 11; i++){
	        if(game.isDead())return;
	        Platform p = r.pop().createPlatf(prevP.getY());
	        game.getPlatforms().add(p);
	        if(i==5)Create.createMonster(game, 30,prevP.getY()
	           ,true);
	        game.getPanel().add(p);
	        if(p.getImage()==Images.p_blue)Move.moveGorizontalP(p, game);
	        prevP = p;
	        }
	    }
	    
	    public static void fourteenthLvl(Game game) {
	        Stack<PlatfDoc> r = new Stack<PlatfDoc>();
	        for (int i = 0; i < 6; i++) {

	          r.push(new PlatfDoc(2, Images.p_White, (i % 2 == 0) ? 10 : game
	              .getWindowWidth() - 70, 190));

	        }
	        for (int i = 0; i < 5; i++) {

	          r.push(new PlatfDoc(2, Images.p_White, (i % 2 == 0) ? 10 : game
	              .getWindowWidth() - 70, 150));

	        }

	        for (int i = 0; i < 11; i++) {
	          if (game.isDead())
	            return;
	          Platform p = r.pop().createPlatf(prevP.getY());
	          game.getPlatforms().add(p);
	          game.getPanel().add(p);
	          prevP = p;
	        }
	      }
	    public static void fifteenthLvl(Game game){
            Stack<PlatfDoc> r = new Stack<PlatfDoc>();
            for(int i = 0; i < 11; i++){
                r.push(new PlatfDoc(3, Images.p_greenS0, new Random().nextInt(game.getWindowWidth()-game.getPlatfW()-10), (i==10)?110:330));
            }
        for(int i = 0; i < 11; i++){
            if(game.isDead())return;
            Platform p = r.pop().createPlatf(prevP.getY());
            game.getPlatforms().add(p);
            if(i==2)Create.createMonster(game, 30,prevP.getY()
               ,true);
            if(i==6)Create.createMonster(game, 30,prevP.getY()
                   ,true);
            if(i==10)Create.createMonster(game, 30,prevP.getY()
                   ,true);
            game.getPanel().add(p);
           
            prevP = p;
            }
        }
	    public static void sixteenthLvl(Game game){
	         new Random().nextInt(50);
	          Stack<PlatfDoc> r = new Stack<PlatfDoc>();
	         
	          r.push(new PlatfDoc(1, Images.p_blue, new Random().nextInt(game
	          .getWindowWidth() - game.getPlatfW() - 10),new Random().nextInt(50)+280));
	      r.push(new PlatfDoc(3, Images.p_greenS0, new Random().nextInt(game
	          .getWindowWidth() - game.getPlatfW() - 10),  new Random().nextInt(50)+40));
	      r.push(new PlatfDoc(1, Images.p_blue, new Random().nextInt(game
	          .getWindowWidth() - game.getPlatfW() - 10),  new Random().nextInt(50)+40));
	      r.push(new PlatfDoc(1, Images.greenF, new Random().nextInt(game
	          .getWindowWidth() - game.getPlatfW() - 10),  new Random().nextInt(50)+40));
	      r.push(new PlatfDoc(1, Images.greenF, new Random().nextInt(game
	          .getWindowWidth() - game.getPlatfW() - 210), new Random().nextInt(50)+40));
	      r.push(new PlatfDoc(1, Images.greenF, new Random().nextInt(game
	          .getWindowWidth() - game.getPlatfW() - 110), new Random().nextInt(50)+40));
	      r.push(new PlatfDoc(1, Images.greenF, new Random().nextInt(game
	          .getWindowWidth() - game.getPlatfW() - 310), new Random().nextInt(50)+40));
	      r.push(new PlatfDoc(2, Images.p_White, new Random().nextInt(game
	          .getWindowWidth() - game.getPlatfW() - 10), new Random().nextInt(50)+40));
	      r.push(new PlatfDoc(2, Images.p_White, 30, 145));
	      r.push(new PlatfDoc(2, Images.p_White, new Random().nextInt(game
	          .getWindowWidth() - game.getPlatfW() - 110), new Random().nextInt(50)+40));
	      
	      r.push(new PlatfDoc(1, Images.p_blue, new Random().nextInt(game
	          .getWindowWidth() - game.getPlatfW() - 10),  new Random().nextInt(50)+40));
	      r.push(new PlatfDoc(1, Images.greenF, new Random().nextInt(game
	          .getWindowWidth() - game.getPlatfW() - 10),  new Random().nextInt(50)+40));
	      r.push(new PlatfDoc(1, Images.greenF, new Random().nextInt(game
	          .getWindowWidth() - game.getPlatfW() - 210), new Random().nextInt(50)+40));
	      r.push(new PlatfDoc(1, Images.greenF, new Random().nextInt(game
	          .getWindowWidth() - game.getPlatfW() - 110), new Random().nextInt(50)+40));
	      r.push(new PlatfDoc(1, Images.greenF, new Random().nextInt(game
	          .getWindowWidth() - game.getPlatfW() - 310), new Random().nextInt(50)+40));
	      r.push(new PlatfDoc(2, Images.p_White, new Random().nextInt(game
	          .getWindowWidth() - game.getPlatfW() - 10), new Random().nextInt(50)+40));
	      r.push(new PlatfDoc(2, Images.p_White, 30, 145));
	      r.push(new PlatfDoc(2, Images.p_White, new Random().nextInt(game
	          .getWindowWidth() - game.getPlatfW() - 110), new Random().nextInt(50)+40));
	          
	      for(int i = 0; i < 18; i++){
	          if(game.isDead())return;
	          Platform p = r.pop().createPlatf(prevP.getY());
	          game.getPlatforms().add(p);
	          if(i==17)Create.createMonster(game, 30,prevP.getY()+80
	                ,true);
	          game.getPanel().add(p);
	          if(p.getImage()==Images.p_blue)Move.moveGorizontalP(p, game);
	          prevP = p;
	          }
	      }
}
