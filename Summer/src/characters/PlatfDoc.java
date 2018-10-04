package characters;


public class PlatfDoc {
 private int id;//id of platform
 private String image;//image that must be
 private int x;
 private int y;//location(above the display)
 public PlatfDoc(int id, String image, int x, int y){
	 //x - real cordinates
	 //y - relatively prev board
	 this.id = id;
	 this.image = image;
	 this.x = x;
	 this.y = y;
 }
public int getId() {
	return id;
}
public int getX() {
	return x;
}
public int getY() {
	return y;
}
public String getImage() {
	return image;
}
public Platform createPlatf(int prevY){
	Platform  p = new Platform(this.getImage(), this.getX(), prevY-this.getY(), 100,40,0,this.getId());
	return p;
}
}
