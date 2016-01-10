package billywangwang.main.tiles;

import java.awt.Graphics;

import billywangwang.main.Game;
import billywangwang.main.tile.TileConstants;

public class StoneTile extends Tile {

	//Constructor
	public StoneTile(int xx, int yy) {
		super(TileConstants.ID_STONE, xx, yy);
	}

	public void tick() {
		//Tests if this tile should be being drawn to screen
		testShouldRender();
	}

	//Renders a stone tile
	public void render(Graphics g) {
		g.drawImage(Game.resources.stone, x, y, null);
	}

}
