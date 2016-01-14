package billywangwang.main.userInterface.mainMenu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import billywangwang.main.userInterface.Button;

public abstract class MainMenuButton extends Button {

	private Polygon 	button;
	
	private boolean 	extend = false;
	private int 		startX, endX;
	
	private Color 		color = new Color(255, 0, 0);
	
	private int 		shapeX;
	
	private String		caption = "";
	private Color		captionColor = Color.WHITE;
	
	public MainMenuButton(String name, Color captionColor, int xx, int yy, int w, int h) {
		super(xx, yy, w, h);
		
		caption = name;
		
		startX = xx;
		endX = xx - (width / 4);
		
		shapeX = x + 35;
		
		int[] xpoints = {shapeX, x + width, x + width, x};
		int[] ypoints = {y, y, y + height, y + height};
		
		button = new Polygon(xpoints, ypoints, 4);
		
		width = width + (width / 4);
	}
	
	public void tick(){
		update();
		
		int[] xpoints = {shapeX, x + width, x + width, x};
		int[] ypoints = {y, y, y + height, y + height};
		
		button = new Polygon(xpoints, ypoints, 4);
		
		extendButton();
	}
	
	public void render(Graphics g){
		g.setColor(color);
		((Graphics2D)g).fill(button);
		g.setColor(captionColor);
		g.drawString(caption, x + 50, y + 20);
	}

	public void mouseOver() {
		
	}

	public void mouseEntered() {
		extend = true;
	}

	public void mouseExited() {
		extend = false;
	}
	
	private void extendButton(){
		if(extend){
			double val = (x - endX) * 0.1;
			x -= (int)val;
			double greenTween = (color.getGreen() - 255) * 0.1;
			double redTween = (color.getRed() - 0) * 0.1;
			int redValue = (int)(color.getRed() - redTween);
			int greenValue = (int)(color.getGreen() - greenTween);
			color = new Color(redValue, greenValue, 0);
		}
		else{
			double val = (x - startX) * 0.1;
			x -= (int)val;
			double greenTween = (color.getGreen() - 0) * 0.1;
			double redTween = (color.getRed() - 255) * 0.1;
			int redValue = (int)(color.getRed() - redTween);
			int greenValue = (int)(color.getGreen() - greenTween);
			color = new Color(redValue, greenValue, 0);
		}
		
		shapeX = x + 35;
	}
}