package characters;

public class Platform extends Character{
	private static final long serialVersionUID = 1L;
	public int id;
	public Platform(String img, int X, int Y, int Width, int Height, int speed, int id) {
		super(img, X, Y, Width, Height);
		this.id=id;
	}
	
}
