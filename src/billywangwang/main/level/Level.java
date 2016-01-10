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
import billywangwang.main.tiles.DesertTile;
import billywangwang.main.tiles.GrassTile;
import billywangwang.main.tiles.StoneTile;
import billywangwang.main.tiles.Tile;
import billywangwang.main.tiles.WaterTile;

public abstract class Level {
	
	protected LinkedList<Entity> 	entities = new LinkedList<Entity>();
	protected LinkedList<Tile> 	tiles = new LinkedList<Tile>();
	
	protected double 				camX, camY;
	
	//Loads the level specified
	public Level(String path){
		try{
			load(path);
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	//Default methods
	public abstract void tick();
	public abstract void render(Graphics g);
	
	//Returns a tile with the specified id at the x and y coordinate
	public Tile createTile(int id, int x, int y){
		switch(id){
		case TileConstants.ID_GRASS:
			return new GrassTile(x, y);
		case TileConstants.ID_WATER:
			return new WaterTile(x, y);
		case TileConstants.ID_DESERT:
			return new DesertTile(x, y);
		case TileConstants.ID_STONE:
			return new StoneTile(x, y);
		}
		
		//If the id specified is not a tile crash the game
		//TODO: Find a different way to exit the game than just to crash it
		System.out.println(id);
		System.out.println("Tile does not exist!");
		System.exit(0);
		
		return null;
	}
	
	//Updates all the entities in the level
	public void tickEntities(){
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).tick();
		}
	}
	
	//Renders all the entities in the level
	public void renderEntities(Graphics g){
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).render(g);
		}
	}
	
	//Updates all the tiles in the level
	public void tickTiles(){
		for(int i = 0; i < tiles.size(); i++){
			tiles.get(i).tick();
		}
	}
	
	//Renders all the tiles in the level
	public void renderTiles(Graphics g){
		for(int i = 0; i < tiles.size(); i++){
			if(tiles.get(i).shouldRender()){
				tiles.get(i).render(g);
			}
		}
	}
	
	//Loads the player x and y coordinate and all the tiles in the .tile and .spawn file from the .level file
	private void load(String level) throws Exception{
		//Gets the level file from the relative location
		Path currentRelativePath = Paths.get("");
		File levelFile = new File(currentRelativePath.toAbsolutePath().toString() + "\\levels\\" + level + ".level");
		
		//If the level exists load the map
		if(levelFile.exists()){
			//Loads in the .tile file
			Scanner tileInput = new Scanner(new File(currentRelativePath.toAbsolutePath().toString() + "\\levels\\" + level + "\\" + level + ".tile"));
			
			while(tileInput.hasNext()){
				//Gets the id and the x and y coordinates of the tile on this line
				String line = tileInput.nextLine();
				
				//Splits up all the arguments
				String[] args = line.split("/");
				
				//Creates the tile
				tiles.add(createTile(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])));
			}
			
			//Close the .tile file
			tileInput.close();
			
			//Loads the .spawn file
			Scanner spawnInput = new Scanner(new File(currentRelativePath.toAbsolutePath().toString() + "\\levels\\" + level + "\\" + level + ".spawn"));
			
			while(spawnInput.hasNext()){
				//Gets the x and y coordinate of the player
				String line = spawnInput.nextLine();
				
				//Splits up the x and y coordinates
				String[] args = line.split("/");
				
				//Creates the player at the correct position
				entities.add(new Player(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
			}
			
			//Closes the .spawn file
			spawnInput.close();
		}
		else{
			//Prints out the .level file path and tells the player that it doesnt exist
			System.out.println(levelFile.getAbsolutePath().toString());
			System.out.println("File does not exist!");
		}
	}
	
	//Getters and setters
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