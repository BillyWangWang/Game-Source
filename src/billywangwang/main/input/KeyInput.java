package billywangwang.main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{

	//Contains all the values to all the needed keys
	private static boolean[] keys = new boolean[65536];
	
	
	//USELESS
	public void keyTyped(KeyEvent e) {
		
	}

	//Sets the corresponding key to true if it is being pressed
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	//Sets the corresponding key to false if it has been released
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	//Returns the keys value from the specified key code
	public static boolean isKeyDown(int key){
		return keys[key];
	}
	

}
