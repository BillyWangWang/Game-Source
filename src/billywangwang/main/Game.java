package billywangwang.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import billywangwang.main.input.KeyInput;
import billywangwang.main.level.Level;
import billywangwang.main.level.TestLevel;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable{
	
	//Final Variables
	public static final String 	TITLE = "Player";
	public static final int 		WIDTH = 1280, HEIGHT = 720;
	
	//Static Variables
	public static Resources 		resources;
	
	public static BufferedImage 	testImage;
	
	public static Level           level;
	
	//Private Variables
	private Thread 				thread = new Thread(this, "Play Thread");
	private boolean 				running = false;

	//Constructor
	public Game(){
		//Sets the UI Manager to look like Windows instead of default Java
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		//Gets input from the user to determine which level to load
		String levelName = JOptionPane.showInputDialog("Enter the level name - ");
		
		//Loads resources
		resources = new Resources();
		
		//Sets the size of the canvas
		Dimension size = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(size);
		
		//Adds input capability from the user
		addKeyListener(new KeyInput());
		
		//Loads the level
		level = new TestLevel(levelName);
	}
	
	//Starts the game
	public void start(){
		running = true;
		//Calls the run method from within this class
		thread.start();
	}
	
	//Stops the game
	public void stop(){
		running = false;
		try{
			thread.join();
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	//Renders and updates the game and logs the frames and ticks every second
	public void run(){
		//Timing to make sure we get 60 ticks
		long last = System.nanoTime();
		double ns = 1000000000.0 / 60.0;
		double delta = 0.0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int ticks = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - last) / ns;
			last = now;
			while(delta >= 1){
				//Update
				tick();
				ticks++;
				delta--;
			}
			
			//Render
			render();
			frames++;

			/*
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			*/
			
			if(System.currentTimeMillis() - timer > 1000){
				//Log the frames and ticks
				System.out.println(frames + " fps, " + ticks + " ticks");
				//Reset the frames and ticks
				ticks = 0;
				frames = 0;
				//Increment the timer
				timer += 1000;
			}
		}
		//Stop the game if it isnt already stopped
		stop();
	}
	
	//Updates the game
	private void tick(){
		//Update the level
		level.tick();
	}
	
	//Renders the game
	private void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;			
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//Renders the level
		level.render(g);
		g.dispose();
		bs.show();
	}
	
	//Launches the game
	public static void main(String[] args){
		//Creates a new frame to display the canvas
		JFrame frame = new JFrame(TITLE);
		
		//Creates a new game
		Game game = new Game();
		
		//Variables of the frame
		frame.setResizable(false);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		//Starts the game
		game.start();
	}
}