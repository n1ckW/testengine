package testengine.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import testengine.entities.player.Player;
import testengine.gfx.Animation;
import testengine.gfx.GameCamera;
import testengine.worlds.EntityManager;

public abstract class Entity {
	public static final int DEFAULT_HEALTH = 100;
	
	protected float x, y;
	protected int width, height;
	protected Rectangle collisionBox;
	protected Animation current;
	protected int health, healthMax;
	protected boolean alive = true;
	protected EntityManager entityManager;
	protected GameCamera gameCamera;
	protected boolean invulnerable;
	
	public Entity(float x, float y, int width, int height, GameCamera gc) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		collisionBox = new Rectangle(0,0, width, height);
		this.health = DEFAULT_HEALTH;
		this.healthMax = DEFAULT_HEALTH;
		gameCamera = gc;
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for (Entity e: entityManager.getEntities()) {
			if (e != null && e != this) {
				if (e.getCollisionBox(0f, 0f).intersects(this.getCollisionBox(xOffset, yOffset))) return true;
			}
		}
		return false;
	}
	
	public Rectangle getCollisionBox(float xOffset, float yOffset) {				
		return new Rectangle((int)(x + collisionBox.x + xOffset), (int)(y + collisionBox.y + yOffset), collisionBox.width, collisionBox.height);
	}
	
	public void hurt(int damage) {
		if (!invulnerable) {
			if (damage >= health)
				die();
			else
				health -= damage;
		}
	}
	
	public void interact() {
		
	}
	
	public void interact(Player p) {
		interact();
	}
	
	public void die() {
		alive = false;
		entityManager.queueForRemoval(this);
	}

	public void render(Graphics g) {
		g.drawImage(current.getCurrentFrame(), (int)(x - gameCamera.getxOffset()), (int)(y - gameCamera.getyOffset()), width, height, null);
	}; 	
	
	public void renderCollisionBox(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)(x + collisionBox.x - gameCamera.getxOffset()), (int) (y + collisionBox.y - gameCamera.getyOffset()), collisionBox.width, collisionBox.height);
	}
	
	public abstract void tick();
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealthMax() {
		return healthMax;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public boolean isInvulnerable() {
		return invulnerable;
	}

	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
