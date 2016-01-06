package billywangwang.main.tiles;

import java.awt.Graphics;

public abstract class Tile {
	
	public static final int WIDTH = 32, HEIGHT = 32;
	
	public static final int ID_GRASS = 0;
	
	protected int id;
	protected int x, y;
	
	public Tile(int id, int xx, int yy){
		this.id = id;
		x = xx;
		y = yy;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
}