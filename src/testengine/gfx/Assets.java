package testengine.gfx;

import java.awt.image.BufferedImage;

import testengine.utils.Utils;

public class Assets {	

	public static BufferedImage 
			floor_1, floor_2, wall_sandstone;
	
	public static BufferedImage[] 
			player_walk_down,	player_idle_down,
			player_walk_up,		player_idle_up,
			player_walk_left,	player_idle_left,
			player_walk_right,	player_idle_right,
			
			btn_start,
	
			stone,
			
			house;
	
	private static final int WIDTH = 32;
	private static final int HEIGHT = 32;	
	
	public static void init() {
		
		SpriteSheet hsheet = new SpriteSheet(Utils.loadFileAsImage("/textures/building_placeholder.png"));
		house = new BufferedImage[1];
		house[0]= hsheet.crop(0, 0, 64, 64);
		
		SpriteSheet sssheet = new SpriteSheet(Utils.loadFileAsImage("/textures/sandstone_wall.png"));
		wall_sandstone = sssheet.crop(0, 0, WIDTH, HEIGHT);
		
		SpriteSheet fsheet = new SpriteSheet(Utils.loadFileAsImage("/textures/test_tile1.png"));
		floor_1 = fsheet.crop(0, 0, WIDTH, HEIGHT);
		floor_2 = fsheet.crop(WIDTH, 0, WIDTH, HEIGHT);
		
		SpriteSheet sheet_stone = new SpriteSheet(Utils.loadFileAsImage("/textures/ugly_stone.png"));
		stone = new BufferedImage[1];
		stone[0] = sheet_stone.crop(0, 0, WIDTH, HEIGHT);
		
		SpriteSheet btn_start_sheet = new SpriteSheet(Utils.loadFileAsImage("/textures/btn_start.png"));
		btn_start = new BufferedImage[3];
		btn_start[0] = btn_start_sheet.crop(0, 	0, 			2*WIDTH, HEIGHT);
		btn_start[1] = btn_start_sheet.crop(0, 	HEIGHT, 	2*WIDTH, HEIGHT);
		btn_start[2] = btn_start_sheet.crop(0, 	2* HEIGHT,	2*WIDTH, HEIGHT);		
		
		SpriteSheet p_right = new SpriteSheet(Utils.loadFileAsImage("/textures/player_right.png"));
		player_idle_right = new BufferedImage[1];
		player_walk_right = new BufferedImage[4];
		player_idle_right[0] = p_right.crop(0,		 0, WIDTH, HEIGHT);
		player_walk_right[0] = p_right.crop(WIDTH,	 0, WIDTH, HEIGHT);
		player_walk_right[1] = player_idle_right[0];
		player_walk_right[2] = p_right.crop(WIDTH*2, 0, WIDTH, HEIGHT);
		player_walk_right[3] = player_idle_right[0];

		player_idle_down = Utils.rotateImageArray(player_idle_right);
		player_walk_down = Utils.rotateImageArray(player_walk_right);
		
		player_idle_left = Utils.rotateImageArray(player_idle_down);
		player_walk_left = Utils.rotateImageArray(player_walk_down);
		
		player_idle_up = Utils.rotateImageArray(player_idle_left);
		player_walk_up = Utils.rotateImageArray(player_walk_left);
	}
}
