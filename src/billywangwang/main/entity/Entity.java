package billywangwang.main.entity;

import java.awt.Graphics;

public abstract class Entity {
	
	//Variables
	protected int 		id;
	protected double 	x, y;
	protected int 		width, height;
	
	//Constructor
	public Entity(int id, double xx, double yy){
		this.id = id;
		x = xx;
		y = yy;
		
		//Default width and height
		width = 32;
		height = 32;
	}
	
	//Abstract methods
	public abstract void tick();
	public abstract void render(Graphics g);

	//Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}