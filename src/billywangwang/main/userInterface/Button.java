package billywangwang.main.userInterface;

import java.awt.event.MouseEvent;

import billywangwang.main.input.MouseInput;

public abstract class Button extends UIElement {

	private boolean mouseEntered = false;
	private boolean mouseClicked = false;
	
	public Button(int xx, int yy, int w, int h){
		super(UIElement.UI_BUTTON, xx, yy);
		
		width = w;
		height = h;
	}
	
	public void update(){
		if(getBounds().contains(MouseInput.getX(), MouseInput.getY() + 17)){
			if(!mouseEntered){
				mouseEntered();
				mouseEntered = true;
			}
			mouseOver();
			
			if(MouseInput.isButtonDown(MouseEvent.BUTTON1) && !mouseClicked){
				mouseClicked();
				mouseClicked = true;
			}
		}
		else{
			if(mouseEntered){
				mouseExited();
				mouseEntered = false;
			}
		}
		
		if(mouseClicked){
			if(!MouseInput.isButtonDown(MouseEvent.BUTTON1))
				mouseClicked = false;
		}
	}
	
	public abstract void mouseOver();
	public abstract void mouseEntered();
	public abstract void mouseExited();
	public abstract void mouseClicked();

}
