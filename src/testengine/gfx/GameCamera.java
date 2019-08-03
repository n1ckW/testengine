package testengine.gfx;

import testengine.Game;
import testengine.entities.Entity;
import testengine.tiles.TileLibrary;
import testengine.worlds.World;

public class GameCamera {
	private float xOffset, yOffset;
	private Game game;
	private World world;
	
	public GameCamera(int xOffset, int yOffset, Game game) {
		this.game = game;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void centerOnEntity(Entity entity) {
		xOffset = entity.getX() - game.getWidth() / 2 + entity.getWidth()/2;
		yOffset = entity.getY() - game.getHeight() / 2 + entity.getHeight()/2;

		if (xOffset < 0) xOffset = 0;
		else if (xOffset > world.getWidth() * TileLibrary.TILEWIDTH - game.getWidth()) {
			xOffset = world.getWidth() * TileLibrary.TILEWIDTH - game.getWidth();
		}
		if (yOffset < 0) yOffset = 0;
		else if (yOffset > world.getHeight() * TileLibrary.TILEHEIGHT - game.getHeight()) {
			yOffset = world.getHeight() * TileLibrary.TILEHEIGHT - game.getHeight();
		}
	}

	public float getxOffset() {
		return xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setWorld(World world) {
		this.world = world;		
	}
}
