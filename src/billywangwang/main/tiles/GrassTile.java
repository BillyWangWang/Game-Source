package billywangwang.main.tiles;

import java.awt.Graphics;

import billywangwang.main.Game;
import billywangwang.main.tile.TileConstants;

public class GrassTile extends Tile {

	public GrassTile(int xx, int yy) {
		super(TileConstants.ID_GRASS, xx, yy);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(Game.resources.grass, x, y, null);
	}
}