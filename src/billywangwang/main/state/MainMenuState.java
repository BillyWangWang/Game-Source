package billywangwang.main.state;

import java.awt.Color;
import java.awt.Graphics;

import billywangwang.main.Game;
import billywangwang.main.state.level.TestLevel;
import billywangwang.main.userInterface.mainMenu.MainMenuButton;

public class MainMenuState extends State {

	public MainMenuState(){
		int defaultX = (int)(Game.WIDTH - 400);
		int defaultY = (int)(Game.HEIGHT - 350);
		
		
		userInterface.add(new MainMenuButton("Play", Color.BLACK, defaultX, defaultY, 400, 35){
			public void mouseClicked(){
				Game.state = new TestLevel();
			}
		});
		userInterface.add(new MainMenuButton("Options", Color.BLACK, defaultX - 50, defaultY + 50, 450, 35){
			public void mouseClicked(){
				
			}
		});
		userInterface.add(new MainMenuButton("Exit", Color.BLACK, defaultX - 100, defaultY + 100, 500, 35){
			public void mouseClicked(){
				System.exit(0);
			}
		});
	}
	
	public void tick() {
		tickUserInterface();
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		renderUserInterface(g);
	}
}