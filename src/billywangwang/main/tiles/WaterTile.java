package billywangwang.main.tiles;

import java.awt.Graphics;

import billywangwang.main.Game;
import billywangwang.main.tile.TileConstants;

public class WaterTile extends Tile {

	//Constructor
	public WaterTile(int xx, int yy) {
		super(TileConstants.ID_WATER, xx, yy, true);
	}

	//Renders a water tile
	public void render(Graphics g) {
		g.drawImage(Game.resources.water, x, y, null);
	}

}
