package billywangwang.main.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import billywangwang.main.Game;
import billywangwang.main.input.KeyInput;
import billywangwang.main.tiles.Tile;

public class Player extends Entity {

	//Final variables
	private final double MOVE_SPEED = 2;
	
	//Variables
	private int 			moveDir = -1;
	private double 		target = 0;

	private int 			walkInterval = 5;
	private long 			walkTimer = System.currentTimeMillis();

	private boolean[] 		keys = new boolean[4];

	//Constructor
	public Player(double xx, double yy) {
		super(EntityConstants.ID_PLAYER, xx, yy);
	}

	public void tick() {
		//Updates the move dir and keys[] variable to move in a direction. Also checks for collisions
		if (KeyInput.isKeyDown(KeyEvent.VK_W) && !keys[0] && moveDir == -1 && !isColliding(x, y - (height / 2))) {
			target = y - 32;
			moveDir = 0;
			keys[0] = true;
		} else if (KeyInput.isKeyDown(KeyEvent.VK_A) && !keys[1] && moveDir == -1 && !isColliding(x - (width / 2), y)) {
			target = x - 32;
			moveDir = 3;
			keys[1] = true;
		} else if (KeyInput.isKeyDown(KeyEvent.VK_S) && !keys[2] && moveDir == -1 && !isColliding(x, y + height + (height / 2))) {
			target = y + 32;
			moveDir = 2;
			keys[2] = true;
		} else if (KeyInput.isKeyDown(KeyEvent.VK_D) && !keys[3] && moveDir == -1 && !isColliding(x + width + (width / 2), y)) {
			target = x + 32;
			moveDir = 1;
			keys[3] = true;
		}

		//Resets the keys[] variable if the key has been released
		if (keys[0])
			if (!KeyInput.isKeyDown(KeyEvent.VK_W))
				keys[0] = false;
		if (keys[1])
			if (!KeyInput.isKeyDown(KeyEvent.VK_A))
				keys[1] = false;
		if (keys[2])
			if (!KeyInput.isKeyDown(KeyEvent.VK_S))
				keys[2] = false;
		if (keys[3])
			if (!KeyInput.isKeyDown(KeyEvent.VK_D))
				keys[3] = false;

		//Every time walktimer goes over the walkinterval walk towards our current target point
		if (System.currentTimeMillis() - walkTimer > walkInterval) {
			//If we are moving
			if (moveDir != -1) {
				switch (moveDir) {
				case 0:
					//If we arent already at our target move towards our target
					if (y != target) {
						y -= MOVE_SPEED;
					} else {
						//If we want to keep going in that direction keep moving otherwise stop
						if (KeyInput.isKeyDown(KeyEvent.VK_W) && !isColliding(x, y - (height / 2))) {
							target = y - 32;
							moveDir = 0;
						} else
							moveDir = -1;
					}
					break;
				case 1:
					if (x != target) {
						x += MOVE_SPEED;
					} else {
						if (KeyInput.isKeyDown(KeyEvent.VK_D) && !isColliding(x + width + (width / 2), y)) {
							target = x + 32;
							moveDir = 1;
						} else
							moveDir = -1;
					}
					break;
				case 2:
					if (y != target) {
						y += MOVE_SPEED;
					} else {
						if (KeyInput.isKeyDown(KeyEvent.VK_S) && !isColliding(x, y + height + (height / 2))) {
							target = y + 32;
							moveDir = 2;
						} else
							moveDir = -1;
					}
					break;
				case 3:
					if (x != target) {
						x -= MOVE_SPEED;
					} else {
						if (KeyInput.isKeyDown(KeyEvent.VK_A) && !isColliding(x - (width / 2), y)) {
							target = x - 32;
							moveDir = 3;
						} else
							moveDir = -1;
					}
					break;
				}
			}

			//Increment the walktimer
			walkTimer += walkInterval;
		}

		//Sets the camera to center on the player
		//TODO: Add half of the player width onto camX and camY
		double camX = -(x - (Game.WIDTH / 2));
		double camY = -(y - (Game.HEIGHT / 2));

		Game.level.setCamX(camX);
		Game.level.setCamY(camY);
	}

	//Draws the player at the current x and y
	public void render(Graphics g) {
		g.drawImage(Game.resources.player, (int) x, (int) y, null);
	}

	//Returns a rectangle which en-cases the player
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}

	//Checks if the tile at the x and y variable specified is collidable
	public boolean isColliding(double x, double y) {
		for (int i = 0; i < Game.level.getTiles().size(); i++) {
			Tile t = Game.level.getTiles().get(i);

			//If it is collidable return true
			if (t.isCollidable() && t.getBounds().contains(x, y)) {
				return true;
			}
		}
		//Else return false
		return false;
	}

	//This method only returns a positive number
	public double sign(double val) {
		if (val < 0) {
			return val * -1;
		}

		return val;
	}

}
