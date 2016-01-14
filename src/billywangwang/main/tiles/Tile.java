package billywangwang.main.tiles;

import java.awt.Graphics;
import java.awt.Rectangle;

import billywangwang.main.tile.TileConstants;

public abstract class Tile {
	
	protected int 		id;
	protected int 		x, y;
	protected boolean collidable = false;
	protected boolean render = false;
	
	//Constructor
	public Tile(int id, int xx, int yy){
		this.id = id;
		x = xx;
		y = yy;
	}
	
	//Alternate constructor to set whether or not the block is collidable (if you can move through it or not)
	public Tile(int id, int xx, int yy, boolean collidable){
		this.id = id;
		x = xx;
		y = yy;
		this.collidable = collidable;
	}
	
	//Default methods
	public abstract void render(Graphics g);
	
	//Returns a rectangle which encases the tile
	public Rectangle getBounds(){
		return new Rectangle(x, y, TileConstants.WIDTH, TileConstants.HEIGHT);
	}

	//Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isCollidable() {
		return collidable;
	}

	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}
	
	public boolean shouldRender(){
		return render;
	}
	
	public void setRender(boolean r){
		render = r;
	}
}