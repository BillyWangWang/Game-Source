package billywangwang.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.UIManager;

import billywangwang.main.input.KeyInput;
import billywangwang.main.input.MouseInput;
import billywangwang.main.state.MainMenuState;
import billywangwang.main.state.State;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable{
	
	//Final Variables
	public static final String 	TITLE = "Player";
	public static final double 	WIDTH = 1280.0, HEIGHT = 720.0;
	
	//Static Variables
	public static Resources 		resources;
	public static Options			options;
	public static State           state;
	
	public static JFrame			frame;
	
	//Private Variables
	private Thread 				thread = new Thread(this, "Play Thread");
	private boolean 				running = false;
	
	private Image					screenImage;

	//Constructor
	public Game(JFrame frame){
		//Sets the UI Manager to look like Windows instead of default Java
		Game.frame = frame;
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		//Loads resources
		resources = new Resources();
		
		//Loads options
		options = new Options();
		
		//Adds input capability from the user
		addKeyListener(new KeyInput());
		MouseInput mouseInput;
		addMouseListener(mouseInput = new MouseInput());
		addMouseMotionListener(mouseInput);
		addMouseWheelListener(mouseInput);
		
		//Loads the level
		state = new MainMenuState();
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
		//Create the game image so it can be stretched and manipulated
		screenImage = createVolatileImage((int)WIDTH, (int)HEIGHT);		
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
		//Stop the game if it isn't already stopped
		stop();
	}
	
	//Updates the game
	private void tick(){
		//Update the level
		state.tick();
	}
	
	//Renders the game
	private void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;			
		}
		
		Graphics g = screenImage.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, (int)WIDTH, (int)HEIGHT);
		//Renders the level
		state.render(g);
		g = bs.getDrawGraphics();
		g.drawImage(screenImage, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	//Launches the game
	public static void main(String[] args){
		//Creates a new frame to display the canvas
		JFrame frame = new JFrame(TITLE);
		
		//Creates a new game
		Game game = new Game(frame);
		
		//Variables of the frame
		frame.setResizable(false);
		frame.add(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		//Starts the game
		game.start();
	}
}