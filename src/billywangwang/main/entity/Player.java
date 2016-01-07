package billywangwang.main.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import billywangwang.main.Game;
import billywangwang.main.input.KeyInput;
import billywangwang.main.tiles.Tile;

public class Player extends Entity{
	
	private double moveSpeed = 6.0;
	
	public Player(double xx, double yy){
		super(EntityConstants.ID_PLAYER, xx, yy);
	}
	
	public void tick(){
		double vx, vy = vx = 0;
		
		if(KeyInput.isKeyDown(KeyEvent.VK_A)){
			vx -= moveSpeed;
		}
		
		if(KeyInput.isKeyDown(KeyEvent.VK_D)){
			vx += moveSpeed;
		}
		
		if(KeyInput.isKeyDown(KeyEvent.VK_S)){
			vy += moveSpeed;
		}
		
		if(KeyInput.isKeyDown(KeyEvent.VK_W)){
			vy -= moveSpeed;
		}
		
		if(vx > 0){
			if(!isColliding(x + width + vx, y, x + width + vx, y + height)){
				x += vx;
			}
		}
		else if(vx < 0){
			if(!isColliding(x - sign(vx), y, x - sign(vx), y + height)){
				x += vx;
			}
		}
		
		if(vy > 0){
			if(!isColliding(x, y + height + vy, x + width, y + height + vy)){
				y += vy;
			}
		}
		
		else if(vy < 0){
			if(!isColliding(x, y - sign(vy), x + width, y - sign(vy))){
				y += vy;
			}
		}
		
		double camX = -(x - (Game.WIDTH / 2));
		double camY = -(y - (Game.HEIGHT / 2));
		
		Game.level.setCamX(camX);
		Game.level.setCamY(camY);
	}
	
	public void render(Graphics g){
		g.drawImage(Game.resources.player, (int)x, (int)y, null);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	public boolean isColliding(double x1, double y1, double x2, double y2){
		for(int i = 0; i < Game.level.getTiles().size(); i++){
			Tile t = Game.level.getTiles().get(i);
			
			if(t.isCollidable() && (t.getBounds().contains(x1, y1) || t.getBounds().contains(x2, y2))){
				return true;
			}
		}
		return false;
	}
	
	public double sign(double val){
		if(val < 0){
			return val * -1;
		}
		
		return val;
	}

}
