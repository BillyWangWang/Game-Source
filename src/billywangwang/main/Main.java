package billywangwang.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import billywangwang.main.entity.Player;
import billywangwang.main.event.Event;
import billywangwang.main.input.KeyInput;
import billywangwang.main.tiles.GrassTile;
import billywangwang.main.tiles.Tile;

@SuppressWarnings("serial")
public class Main extends Canvas implements Runnable{
	
	public static final String TITLE = "Player";
	public static final int WIDTH = 1280, HEIGHT = 720;
	
	public static BufferedImage testImage;
	
	public LinkedList<Event> events = new LinkedList<Event>();
	public LinkedList<Tile> tiles = new LinkedList<Tile>();
	
	private Thread thread = new Thread(this, "Play Thread");
	private boolean running = false;
	
	private Player player;
	
	public Main(){
		Dimension size = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(size);
		
		addKeyListener(new KeyInput());
		
		player = new Player("player", 0.0, 0.0);
		
		events.add(new Event(32, 32){
			public void tick(){
				if(KeyInput.isKeyDown(KeyEvent.VK_SPACE)){
					doEvent();
				}
			}
			
			public void doEvent(){
				System.out.println("Hello");
			}
		});
		
		try {
			testImage = ImageIO.read(Main.class.getResourceAsStream("/test.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		load();
	}
	
	private void load(){
		Scanner file = new Scanner(Main.class.getResourceAsStream("/test.tile"));
		
		String line;
		while(file.hasNext()){
			line = file.nextLine();
			String[] args = line.split("/");
			int id = Integer.parseInt(args[1]);
			int x = Integer.parseInt(args[2]);
			int y = Integer.parseInt(args[3]);
			switch(id){
			case Tile.ID_GRASS:
				tiles.add(new GrassTile(x, y));
			}
		}
		
		file.close();
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
			
			if(System.currentTimeMillis() - timer > 1000){
				System.out.println(frames + " fps, " + ticks + " ticks");
				ticks = 0;
				frames = 0;
				timer += 1000;
			}
		}
	}
	
	private void tick(){
		for(int i = 0; i < events.size(); i++){
			events.get(i).tick();
		}
		for(int i = 0; i < tiles.size(); i++){
			tiles.get(i).tick();
		}
		player.tick();
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
		for(int i = 0; i < tiles.size(); i++){
			tiles.get(i).render(g);
		}
		player.render(g);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame(TITLE);
		
		Main game = new Main();
		
		frame.setResizable(false);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}

}
