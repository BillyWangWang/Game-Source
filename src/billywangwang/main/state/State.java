package billywangwang.main.state;

import java.awt.Graphics;
import java.util.LinkedList;

import billywangwang.main.entity.Entity;
import billywangwang.main.userInterface.UIElement;

public abstract class State {
	
	//List of all the entities in the current state
	protected LinkedList<Entity> 		entities = new LinkedList<Entity>();
	//List of all the UI elements in the current state
	protected LinkedList<UIElement>	userInterface = new LinkedList<UIElement>();
	
	//Default methods
	public abstract void tick();
	public abstract void render(Graphics g);
	
	//Updates all the entities in the state
	public void tickEntities(){
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).tick();
		}
	}
	
	//Renders all the entities in the state
	public void renderEntities(Graphics g){
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).render(g);
		}
	}
	
	//Updates all the UI elements in the state
	public void tickUserInterface(){
		for(int i = 0; i < userInterface.size(); i++){
			userInterface.get(i).tick();
		}
	}
	
	//Renders all the UI elements in the state
	public void renderUserInterface(Graphics g){
		for(int i = 0; i < userInterface.size(); i++){
			userInterface.get(i).render(g);
		}
	}
	
	public LinkedList<Entity> getEntities(){
		return entities;
	}
	
	public LinkedList<UIElement> getUserInterface(){
		return userInterface;
	}
}