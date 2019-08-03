package testengine.worlds;

import testengine.Game;
import testengine.entities.Building;
import testengine.entities.buildings.TestBuilding;
import testengine.entities.immobile.Rock;
import testengine.gfx.GameCamera;
import testengine.tiles.TileLibrary;

public class WorldLibrary {
	private Game game;
	private World overWorld;
	private GameCamera gameCamera;
	
	public WorldLibrary(Game game) {
		this.game = game;
		gameCamera = game.getGameCamera();
		init();
	}
	
	public void init() {
		overWorld = new World("/worlds/world1.w", game);
		overWorld.getEntityManager().queueForAddition(new Rock(7*TileLibrary.TILEWIDTH, 7*TileLibrary.TILEHEIGHT, gameCamera));
		overWorld.getEntityManager().queueForAddition(new TestBuilding(8*TileLibrary.TILEWIDTH, 8*TileLibrary.TILEHEIGHT, gameCamera));
	}

	public World getOverWorld() {
		return overWorld;
	}
	
	public World createWorld(String path) {
		return new World(path, game);
	}
	
	public InteriorWorld createInteriorWorld(String path, Building building) {
		return new InteriorWorld(path, game, building);
	}

}
