package M;


public class Config {
	public static enum state {MENU, GAME, HELP};
	
	private static int width = 1280;
	private static int height = 720;
	
	public static int getWidth() { return width; }
	public static int getHeight() { return height; }
}
