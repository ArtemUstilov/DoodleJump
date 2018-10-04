package engine;

import gameInterface.GameOverPanel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import characters.Platform;

public class UsableM {
	public static void pause(int x){
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static long getLong(String score){
		String u[] = score.split("-");
		return Long.valueOf(u[u.length-1]);
	}
	public static int getBegin(String path){
		char t[] = path.toCharArray();
		for(int i =  t.length-1; i>0; i--){
			if(t[i]==':')return i-1;
		}
		return 0;
	}
	public static int getEnd(String path){
		if(path.contains("exe")){
		char t[] = path.toCharArray();
		int counter = 0;
		for(int i = t.length-1; i > 0; i--){
			if(t[i]=='/')counter++;
			if(counter==2)return i+1;
		}
		return 0;
		}else{
			char t[] = path.toCharArray();
			int counter = 0;
			for(int i = t.length-1; i > 0; i--){
				if(t[i]=='/')counter++;
				if(counter==3)return i+1;
			}
			return 0;
		}
	}
	public static String getPathToScores(){
		 URL resource = GameOverPanel.class.getResource("/scores.txt");
		 String y = resource.toString().substring(0, UsableM.getEnd(resource.toString()));
		 y+="/scores.txt";
		 y = y.substring(UsableM.getBegin(y));
		 return y;
	}
	public static String getPathToStats(){
		 URL resource = GameOverPanel.class.getResource("/scores.txt");
		 String y = resource.toString().substring(0, UsableM.getEnd(resource.toString()));
		 y+="/stats.txt";
		 y = y.substring(UsableM.getBegin(y));
		 return y;
	}
	public static String getPathToFont(){
		 URL resource = GameOverPanel.class.getResource("/scores.txt");
		 String y = resource.toString().substring(0, UsableM.getEnd(resource.toString()));
		 y+="/ARDESTINE.ttf";
		 y = y.substring(UsableM.getBegin(y));
		 return y;
	}
	public static void removePlatf(Platform p, Game game){
		game.getPanel().remove(p);
		game.getPlatforms().remove(p);
	}
	public static void writeStats(Game game){
		String allStats = "";
		FileInputStream t;
		try {
			t = new FileInputStream(UsableM.getPathToStats());
		
		byte[] buffer = new byte[t.available()];
		t.read(buffer, 0, t.available());
		for (int i = 0; i < buffer.length; i++) {
			allStats += (char) buffer[i];
		}
		t.close();
		} catch (FileNotFoundException e9) {
			e9.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String stat[] = allStats.split("&&");
		int u[] = new int[7];
		for(int i = 0; i  < u.length; i++){
			if(stat.length==7)u[i]=Integer.valueOf(stat[i]);
			switch(i){
			case 0:
				u[i]+=game.safe.getKillMonster();
				break;
			case 1:
				u[i]+=game.safe.getJumpWithSpring();
				break;
			case 2:
				u[i]+=game.safe.getRocketFlied();
				break;
			case 3:
				u[i]+=game.safe.getTimeInGame();
				break;
			case 4:
				u[i]+=game.safe.getDeadWithHole();
				break;
			case 5:
				u[i]+=game.safe.getKilledByMonster();
				break;
			case 6:
				u[i]+=game.safe.getFalls();
				break;
			}
		}
		
		try {
		FileOutputStream w = new FileOutputStream(UsableM
				.getPathToStats(), false);
		for(int i = 0; i < 7; i++){
			
				w.write((String.valueOf(u[i])+"&&").getBytes());
			
		}
		w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
