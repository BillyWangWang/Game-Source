package billywangwang.main.level;

import java.awt.Graphics;

public class TestLevel extends Level {

	public TestLevel(String level) {
		super(level);
	}
	
	public void tick(){
		tickTiles();
		tickEntities();
	}
	
	public void render(Graphics g){
		renderTiles(g);
		renderEntities(g);
	}
}