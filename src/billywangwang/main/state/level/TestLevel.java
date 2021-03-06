package billywangwang.main.state.level;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

public class TestLevel extends Level {
	
	//Constructor
	public TestLevel() {
		super();
		
		String levelName = JOptionPane.showInputDialog("Enter a level name - ");
		
		try{
			load(levelName);
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
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