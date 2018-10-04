package gameInterface;

public class statsSafe {
private int rocketFlied = 0;
private int deadWithHole = 0;
private int killMonster= 0;
private int jumpWithSpring= 0;
private int killedByMonster= 0;
private long timeInGame= 0;
private int falls= 0;
public statsSafe(){
	
}
public int getRocketFlied() {
	return rocketFlied;
}
public void setRocketFlied(int rocketFlied) {
	this.rocketFlied = this.rocketFlied+rocketFlied;
}
public int getDeadWithHole() {
	return deadWithHole;
}
public void setDeadWithHole(int deadWithHole) {
	this.deadWithHole = this.deadWithHole+deadWithHole;
}
public int getJumpWithSpring() {
	return jumpWithSpring;
}
public void setJumpWithSpring(int jumpWithSpring) {
	this.jumpWithSpring = this.jumpWithSpring+jumpWithSpring;
}
public long getTimeInGame() {
	return timeInGame;
}
public void setTimeInGame(long timeInGame) {
	this.timeInGame = timeInGame;
}
public int getKilledByMonster() {
	return killedByMonster;
}
public void setKilledByMonster(int killedByMonster) {
	this.killedByMonster = this.killedByMonster+killedByMonster;
}
public int getKillMonster() {
	return killMonster;
}
public void setKillMonster(int killMonster) {
	this.killMonster = this.killMonster+killMonster;
}
public void setFalls(int i) {
	this.falls = i;
	
}
public int getFalls() {
	return falls;
	
}
}
