package billywangwang.main.userInterface;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class UIElement {
	
	public static final int UI_BUTTON = 0;
	
	protected int x, y;
	protected int width, height;
	protected int id;
	
	public UIElement(int id, int xx, int yy){
		this.id = id;
		x = xx;
		y = yy;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}