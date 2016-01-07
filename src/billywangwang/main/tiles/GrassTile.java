package billywangwang.main.tiles;

import java.awt.Graphics;

import billywangwang.main.Game;

public class GrassTile extends Tile {

	public GrassTile(int xx, int yy) {
		super(Tile.ID_GRASS, xx, yy);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(Game.resources.grass, x, y, null);
	}
}