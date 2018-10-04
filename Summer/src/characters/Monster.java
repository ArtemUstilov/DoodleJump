package characters;

public class Monster extends Character{
	private static final long serialVersionUID = 1L;
	public boolean flight;
	
	public Monster(String img, int X, int Y, int Width, int Height,boolean flight) {
		super(img, X, Y, Width, Height);
		this.flight=flight;
	}
}
