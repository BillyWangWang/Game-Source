package billywangwang.main.level;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class TestLevel extends Level {

	public TestLevel(String level) {
		super(level);
	}
	
	public void tick(){
		tickTiles();
		tickEntities();
	}
	
	public void render(Graphics g){
		((Graphics2D)g).translate(camX, camY);
		renderTiles(g);
		renderEntities(g);
		((Graphics2D)g).translate(-camX, -camY);
	}
}