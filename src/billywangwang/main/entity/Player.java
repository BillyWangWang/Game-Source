package billywangwang.main.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import billywangwang.main.input.KeyInput;

public class Player extends Entity{
	
	public Player(double xx, double yy){
		super(EntityConstants.ID_PLAYER, xx, yy);
	}
	
	public void tick(){
		double vx, vy = vx = 0;
		
		if(KeyInput.isKeyDown(KeyEvent.VK_A)){
			vx -= 6.0;
		}
		
		if(KeyInput.isKeyDown(KeyEvent.VK_D)){
			vx += 6.0;
		}
		
		if(KeyInput.isKeyDown(KeyEvent.VK_S)){
			vy += 6.0;
		}
		
		if(KeyInput.isKeyDown(KeyEvent.VK_W)){
			vy -= 6.0;
		}
		
		x += vx;
		y += vy;
	}
	
	public void render(Graphics g){
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, width, height);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, width, height);
	}

}
