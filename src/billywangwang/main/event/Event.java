package billywangwang.main.event;

import java.awt.Rectangle;

public abstract class Event {
	
	//TODO: Implement this class
	
	protected double 	x, y;
	protected int 		width, height;
	
	//Set the variables to the arguments and width and height to 32
	public Event(double xx, double yy){
		x = xx;
		y = yy;
		
		width = 32;
		height = 32;
	}
	
	//Default methods
	public abstract void tick();
	public abstract void doEvent();
	
	//Getters and setters
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, width, height);
	}
}