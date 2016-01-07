package billywangwang.main.level;

import java.awt.Graphics;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

import billywangwang.main.entity.Entity;
import billywangwang.main.entity.Player;
import billywangwang.main.tile.TileConstants;
import billywangwang.main.tiles.GrassTile;
import billywangwang.main.tiles.Tile;
import billywangwang.main.tiles.WaterTile;

public abstract class Level {
	
	protected LinkedList<Entity> entities = new LinkedList<Entity>();
	protected LinkedList<Tile> tiles = new LinkedList<Tile>();
	
	protected double camX, camY;
	
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
	
	public Tile createTile(int id, int x, int y){
		switch(id){
		case TileConstants.ID_GRASS:
			return new GrassTile(x, y);
		case TileConstants.ID_WATER:
			return new WaterTile(x, y);
		}
		
		return null;
	}
	
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
				
				tiles.add(createTile(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])));
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
	
	public double getCamX() {
		return camX;
	}

	public void setCamX(double camX) {
		this.camX = camX;
	}

	public double getCamY() {
		return camY;
	}

	public void setCamY(double camY) {
		this.camY = camY;
	}

	public LinkedList<Tile> getTiles(){
		return tiles;
	}
	
	public LinkedList<Entity> getEntities(){
		return entities;
	}
}