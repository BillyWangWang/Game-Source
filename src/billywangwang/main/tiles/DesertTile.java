package billywangwang.main.tiles;

import java.awt.Graphics;

import billywangwang.main.Game;
import billywangwang.main.tile.TileConstants;

public class DesertTile extends Tile {

	//Constructor
	public DesertTile(int xx, int yy) {
		super(TileConstants.ID_DESERT, xx, yy);
	}

	//Renders a desert tile
	public void render(Graphics g) {
		g.drawImage(Game.resources.desert, x, y, null);
	}

}
