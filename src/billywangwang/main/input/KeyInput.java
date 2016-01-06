package billywangwang.main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{

	private static boolean[] keys = new boolean[65536];
	
	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	public static boolean isKeyDown(int key){
		return keys[key];
	}
	

}
