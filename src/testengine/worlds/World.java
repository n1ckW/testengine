package testengine.worlds;

import java.awt.Graphics;

import testengine.Game;
import testengine.entities.player.Player;
import testengine.gfx.GameCamera;
import testengine.tiles.Tile;
import testengine.tiles.TileLibrary;
import testengine.utils.Utils;

public class World {
	private Game game;
	private EntityManager entityManager;
	private Player player;
	private GameCamera gameCamera;

	private int width, height;
	private int spawnX, spawnY; 
	private float lastPlayerX = -10, lastPlayerY = -10;
	private int[][] tiles;
	
	public World(String path, Game game) {
		this.game = game;
		loadWorld(path);
		entityManager = new EntityManager(this);
		gameCamera = game.getGameCamera();
	}
	
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		tiles = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int i = 4 + x + y*width;
				tiles[x][y] = Utils.parseInt(tokens[i]);
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		if (x<0 || x>=width || y<0 || y>=height) return TileLibrary.wallTile_1;
		Tile t = TileLibrary.tiles[tiles[x][y]];
		if (t == null) return TileLibrary.floorTile_1;
		return t;		
	}
	
	public void tick() {
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		synchronized (gameCamera) {
			if (player != null)
				gameCamera.centerOnEntity(player);
			float xoffset = gameCamera.getxOffset();
			float yoffset = gameCamera.getyOffset();
			
			int xStart = Math.max(0, (int)((xoffset - game.getWidth()) / TileLibrary.TILEWIDTH) - 1);
			int xEnd = Math.min(width, (int)((xoffset + game.getWidth()) / TileLibrary.TILEWIDTH + 1));		
			int yStart = Math.max(0, (int)((yoffset - game.getHeight()) / TileLibrary.TILEHEIGHT) - 1);
			int yEnd = Math.min(height, (int)((yoffset + game.getHeight()) / TileLibrary.TILEHEIGHT) + 1);
			
			for (int x = xStart; x < xEnd ; x++) {
				for (int y = yStart; y < yEnd; y++) {
					TileLibrary.tiles[tiles[x][y]].render(g, (int)(x * TileLibrary.TILEWIDTH - xoffset), (int)((y * TileLibrary.TILEHEIGHT) - yoffset));
				}				
			}
			entityManager.render(g);
		}
	}
	
	public void addPlayer(Player newPlayer) {
		player = newPlayer;
		
		if (lastPlayerX == -10)
			player.setX(spawnX * TileLibrary.TILEWIDTH);
		else 
			player.setX(lastPlayerX);
		
		if (lastPlayerY == -10) 
			player.setY(spawnY * TileLibrary.TILEHEIGHT);
		else 
			player.setY(lastPlayerY);
		
		entityManager.addPlayer(player);
	}
	
	public void removePlayer() {
		entityManager.removePlayer();
		lastPlayerX = player.getX();
		lastPlayerY = player.getY();
		player = null;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getSpawnX() {
		return spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Player getPlayer() {
		return player;
	}

	public float getLastPlayerX() {
		return lastPlayerX;
	}

	public void setLastPlayerX(float lastPlayerX) {
		this.lastPlayerX = lastPlayerX;
	}

	public float getLastPlayerY() {
		return lastPlayerY;
	}

	public void setLastPlayerY(float lastPlayerY) {
		this.lastPlayerY = lastPlayerY;
	}

	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}

	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}
	
	public Game getGame() {
		return game;
	}
}
