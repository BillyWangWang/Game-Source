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
	
	public static final String TITLE = "Player";
	public static final int WIDTH = 1280, HEIGHT = 720;
	
	public static Resources resources;
	
	public static BufferedImage testImage;
	
	public static Level level;
	
	private Thread thread = new Thread(this, "Play Thread");
	private boolean running = false;
	
	public Game(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		String levelName = JOptionPane.showInputDialog("Enter the level name - ");
		
		resources = new Resources();
		
		Dimension size = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(size);
		
		addKeyListener(new KeyInput());
		
		level = new TestLevel(levelName);
	}
	
	public void start(){
		running = true;
		thread.start();
	}
	
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
	
	public void run(){
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
				tick();
				ticks++;
				delta--;
			}
			
			render();
			frames++;
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(System.currentTimeMillis() - timer > 1000){
				System.out.println(frames + " fps, " + ticks + " ticks");
				ticks = 0;
				frames = 0;
				timer += 1000;
			}
		}
	}
	
	private void tick(){
		level.tick();
	}
	
	private void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;			
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		level.render(g);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame(TITLE);
		
		Game game = new Game();
		
		frame.setResizable(false);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
}