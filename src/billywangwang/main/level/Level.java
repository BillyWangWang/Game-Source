package billywangwang.main.level;

import java.awt.Graphics;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

import billywangwang.main.entity.Entity;
import billywangwang.main.entity.Player;
import billywangwang.main.tiles.GrassTile;
import billywangwang.main.tiles.Tile;

public abstract class Level {
	
	protected LinkedList<Entity> entities = new LinkedList<Entity>();
	protected LinkedList<Tile> tiles = new LinkedList<Tile>();
	
	public Level(String path){
		try{
			load(path);
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public void tickEntities(){
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).tick();
		}
	}
	
	public void renderEntities(Graphics g){
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).render(g);
		}
	}
	
	public void tickTiles(){
		for(int i = 0; i < tiles.size(); i++){
			tiles.get(i).tick();
		}
	}
	
	public void renderTiles(Graphics g){
		for(int i = 0; i < tiles.size(); i++){
			tiles.get(i).render(g);
		}
	}
	
	private void load(String level) throws Exception{
		Path currentRelativePath = Paths.get("");
		File levelFile = new File(currentRelativePath.toAbsolutePath().toString() + "\\levels\\" + level + ".level");
		
		if(levelFile.exists()){
			Scanner tileInput = new Scanner(new File(currentRelativePath.toAbsolutePath().toString() + "\\levels\\" + level + "\\" + level + ".tile"));
			
			while(tileInput.hasNext()){
				String line = tileInput.nextLine();
				
				String[] args = line.split("/");
				
				switch(Integer.parseInt(args[1])){
				case Tile.ID_GRASS:
					tiles.add(new GrassTile(Integer.parseInt(args[2]), Integer.parseInt(args[3])));
					break;
				}
			}
			
			tileInput.close();
			
			Scanner spawnInput = new Scanner(new File(currentRelativePath.toAbsolutePath().toString() + "\\levels\\" + level + "\\" + level + ".spawn"));
			
			while(spawnInput.hasNext()){
				String line = spawnInput.nextLine();
				
				String[] args = line.split("/");
				
				entities.add(new Player(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
			}
			
			spawnInput.close();
		}
		else{
			System.out.println(levelFile.getAbsolutePath().toString());
			System.out.println("File does not exist!");
		}
	}
}