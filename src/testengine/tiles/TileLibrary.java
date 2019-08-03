package testengine.tiles;

import testengine.gfx.Assets;

public class TileLibrary {
	public static final int TILEHEIGHT = 64;
	public static final int TILEWIDTH = 64;
	
	public static Tile[] tiles = new Tile[16];
	
	public static Tile floorTile_1 = new Tile(Assets.floor_1, false, 0);
	public static Tile floorTile_2 = new Tile(Assets.floor_2, false, 1);
	public static Tile wallTile_1 = new Tile(Assets.wall_sandstone, true, 2);
}
