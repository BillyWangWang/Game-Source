package billywangwang.main.level;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class TestLevel extends Level {

	//Constructor
	public TestLevel(String level) {
		super(level);
	}
	
	public void tick(){
		//Updates both tiles and entities
		tickTiles();
		tickEntities();
	}
	
	public void render(Graphics g){
		//Renders and translates tiles and entities
		((Graphics2D)g).translate(camX, camY);
		renderTiles(g);
		renderEntities(g);
		((Graphics2D)g).translate(-camX, -camY);
	}
}