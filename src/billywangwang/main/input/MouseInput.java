package billywangwang.main.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import billywangwang.main.Options;

public class MouseInput implements MouseMotionListener, MouseListener, MouseWheelListener {

	private static double 	x, y;
	
	private static boolean[] 	buttons = new boolean[20];
	
	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
	}

	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton()] = false;
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {
		x = 0.0 + e.getX();
		y = 0.0 + e.getY();
		x /= Options.WINDOW_SCALE_X;
		y /= Options.WINDOW_SCALE_Y;
	}

	public void mouseMoved(MouseEvent e) {
		x = 0.0 + e.getX();
		y = 0.0 + e.getY();
		x /= Options.WINDOW_SCALE_X;
		y /= Options.WINDOW_SCALE_Y;
	}
	
	public static double getX(){
		return x;
	}
	
	public static double getY(){
		return y;
	}
	
	public static boolean isButtonDown(int button){
		return buttons[button];
	}
}