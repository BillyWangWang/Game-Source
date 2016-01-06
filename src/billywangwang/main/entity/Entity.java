package billywangwang.main.entity;

import java.awt.Graphics;

public abstract class Entity {
	
	protected String id;
	protected double x, y;
	protected int width, height;
	
	public Entity(String id, double xx, double yy){
		this.id = id;
		x = xx;
		y = yy;
		
		width = 32;
		height = 32;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);

	public String getId() {
		return id;
	}

	public void setId(String id) {
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