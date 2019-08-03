package testengine.entities;

import testengine.gfx.Animation;
import testengine.gfx.GameCamera;
import testengine.tiles.TileLibrary;
import testengine.utils.Direction;

public abstract class MobileEntity extends Entity {

	protected Direction dir;
	protected float speed;
	protected float xMove, yMove;
	protected Animation up_walk, down_walk, left_walk, right_walk, left_idle, right_idle, up_idle, down_idle, last_idle;
	
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 32, DEFAULT_CREATURE_HEIGHT = 32;

	public MobileEntity(float x, float y, int width, int height, GameCamera gc) {
		super(x, y, width, height, gc);
		this.speed = DEFAULT_SPEED;
	}
	
	protected void move() {
		if(!checkEntityCollisions(xMove, 0f)) moveX();
		if(!checkEntityCollisions(0f, yMove)) moveY();		
	}
	
	protected boolean collisionWithTile(int x, int y)     {
		return entityManager.getWorld().getTile(x, y).isSolid();
	}           
	
	protected void moveX() {    
			while (xMove > 0){
				int tx = (int)(x + xMove + collisionBox.x + collisionBox.width) / TileLibrary.TILEWIDTH;
				if (!collisionWithTile(tx, (int)(y + collisionBox.y) / TileLibrary.TILEHEIGHT) &&
						!collisionWithTile(tx, (int)(y + collisionBox.y + collisionBox.height) / TileLibrary.TILEHEIGHT)) {
					x+=xMove;
					xMove = 0;
				} else xMove--;
			}
			while (xMove < 0) {
				int tx = (int) (x + xMove + collisionBox.x) / TileLibrary.TILEWIDTH;
				if (!collisionWithTile(tx, (int)(y + collisionBox.y) / TileLibrary.TILEHEIGHT) &&
						!collisionWithTile(tx, (int)(y + collisionBox.y + collisionBox.height) / TileLibrary.TILEHEIGHT)) {
					x+=xMove;
					xMove = 0;
				} else xMove++;
			}
		}

	protected void moveY() {
			while (yMove >0) {
				int ty = (int) (y + yMove + collisionBox.y + collisionBox.height) / TileLibrary.TILEHEIGHT;
				if (!collisionWithTile((int)(x + collisionBox.x) / TileLibrary.TILEWIDTH, ty) &&
						!collisionWithTile((int)(x + collisionBox.x + collisionBox.width) / TileLibrary.TILEWIDTH, ty)) {
					y+=yMove;
					yMove = 0;
				} else yMove--;
			}
			while (yMove <0) {
				int ty = (int) (y + yMove + collisionBox.y) / TileLibrary.TILEHEIGHT;
				if (!collisionWithTile((int)(x + collisionBox.x) / TileLibrary.TILEWIDTH, ty) &&
						!collisionWithTile((int)(x + collisionBox.x + collisionBox.width) / TileLibrary.TILEWIDTH, ty)) {
					y+=yMove;
					yMove = 0;
				} else yMove++;
			}
		}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealthMax() {
		return healthMax;
	}

	public void setHealthMax(int healthMax) {
		this.healthMax = healthMax;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}	
	
	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}	
}
