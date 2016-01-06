package billywangwang.main.event;

import java.awt.Rectangle;

public abstract class Event {
	
	protected double x, y;
	protected int width, height;
	
	public Event(double xx, double yy){
		x = xx;
		y = yy;
		
		width = 32;
		height = 32;
	}
	
	public abstract void tick();
	public abstract void doEvent();
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, width, height);
	}
}