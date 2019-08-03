package testengine.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {	
	protected BufferedImage texture;
	protected final int id;
	protected boolean solid = false;
	
	public Tile(BufferedImage texture, boolean solid, int id) {
		if (TileLibrary.tiles[id] != null) {
			System.out.println("duplicate tile id!");
			System.exit(-1);
		}
		this.texture = texture;
		this.id = id;
		this.solid = solid;
		TileLibrary.tiles[id] = this;
	}
	
	public int getID() {
		return id;
	}
	
	public void tick() {
		return;
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TileLibrary.TILEWIDTH, TileLibrary.TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return solid;
	}
	
}
