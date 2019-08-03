package testengine.entities.player;

import java.awt.Rectangle;

import testengine.entities.Entity;
import testengine.entities.MobileEntity;
import testengine.gfx.Animation;
import testengine.gfx.Assets;
import testengine.gfx.GameCamera;
import testengine.items.Inventory;
import testengine.tiles.TileLibrary;
import testengine.utils.Direction;
import testengine.worlds.EntityManager;

public class Player extends MobileEntity {

	private int busy = 0;
	private boolean rooted = false;
	private Inventory playerInventory = new Inventory();
	private PlayerAction queuedAction;
	public boolean up, down, left, right;
	
	public Player(float x, float y, GameCamera gc) {
		super(x, y, TileLibrary.TILEWIDTH/2, TileLibrary.TILEHEIGHT/2, gc);
		collisionBox.x = 6;
		collisionBox.y = 6;
		collisionBox.width = 19;
		collisionBox.height = 19;
		up_walk = new Animation(250, Assets.player_walk_up);
		up_idle = new Animation(250, Assets.player_idle_up);
		down_walk = new Animation(250, Assets.player_walk_down);
		down_idle = new Animation(250, Assets.player_idle_down);
		left_walk = new Animation(250, Assets.player_walk_left);
		left_idle = new Animation(250, Assets.player_idle_left);
		right_walk = new Animation(250, Assets.player_walk_right);
		right_idle = new Animation(250, Assets.player_idle_right);
		last_idle = down_idle;
		current = last_idle;
		dir = Direction.DOWN;
	}

	@Override
	public void tick() {
		if (queuedAction != null && (busy == 0)) {
			queuedAction.execute();
			busy = 10;
			queuedAction = null;
		} else if (!rooted) {
			move();
			if (busy > 0) 
				busy--;
		}
		current.tick();
	}
	
	@Override
	protected void move() {
			getInput();
			if (xMove == 0 && yMove == 0) current = last_idle;
			if (xMove > 0) {
				current = right_walk;
				last_idle = right_idle;
				dir = Direction.RIGHT;
			}
			else if (xMove < 0) {
				current = left_walk;
				last_idle = left_idle;
				dir = Direction.LEFT;
			}
			if (yMove < 0) {
				current = up_walk;
				last_idle = up_idle;
				dir = Direction.UP;
			}
			else if (yMove > 0) {
				current = down_walk;
				last_idle = down_idle;
				dir = Direction.DOWN;
			}
			current.tick();
			synchronized (gameCamera) {
				super.move();
			}
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;
		if (up) yMove = -speed;
		if (down) yMove = speed;
		if (left) xMove = -speed;
		if (right) xMove = speed;		
	}
	
	public void queueMeleeAttack() {
		if (queuedAction == null)
			queuedAction = melee_attack;
	}
	
	public void queueInteraction() {
		if (queuedAction == null)
			queuedAction = interaction;
	}

	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	@Override
	public void die() {
		System.out.println("you died");
		super.die();
	}
	
	private void executeInteraction() {
		int size = 20;
		Rectangle bounds = getCollisionBox(0,0);
		Rectangle hitbox = createMeleeHitbox(bounds, size);
		Entity tar = null;
		for (Entity e: entityManager.getEntities()) {
			if (e == null || e == entityManager.getPlayer())
				continue;
			Rectangle target = new Rectangle((int)e.getX(), (int)e.getY(), e.getWidth(), e.getHeight());
			if (target.intersects(hitbox)) {
				tar = e;
				break;
			}
		}
		if (tar != null) {
			System.out.println("interaction connected with entity " + tar.getClass().getName());
			tar.interact(this);
		}
	}
	
	private void executeMeleeAttack() {
		int size = 20;
		Rectangle bounds = getCollisionBox(0,0);
		Rectangle hitbox = createMeleeHitbox(bounds, size);
		Entity tar = null;
		for (Entity e: entityManager.getEntities()) {
			if (e == null || e == entityManager.getPlayer())
				continue;
			Rectangle target = e.getCollisionBox(0, 0);
			if (target.intersects(hitbox)) {
				tar = e;
				break;
			}
		}
		if (tar != null) {
			System.out.println("melee attack connected with entity " + tar.getClass().getName());
			tar.hurt(20);
		}
	}
	
	private interface PlayerAction {
		public abstract void execute();
	}
	
	private PlayerAction melee_attack = new PlayerAction() {
		@Override
		public void execute() {
			executeMeleeAttack();
		}
	};
	
	private PlayerAction interaction = new PlayerAction() {
		@Override
		public void execute() {
			executeInteraction();
		}		
	};
	
	private Rectangle createMeleeHitbox(Rectangle bounds, int size) {
		Rectangle hitbox = new Rectangle(0, 0, size, size);
		switch (dir) {
		case UP:
			hitbox.x = bounds.x + bounds.width/2 - size/2;
			hitbox.y = bounds.y - size;
			break;
		case DOWN:
			hitbox.x = bounds.x + bounds.width/2 - size/2;
			hitbox.y = bounds.y + bounds.height;
			break;
		case LEFT:
			hitbox.x = bounds.x - size;
			hitbox.y = bounds.y + bounds.height/2 - size/2;
			break;
		case RIGHT:
			hitbox.x = bounds.x + bounds.width;
			hitbox.y = bounds.y + bounds.height/2 - size/2;
			break;
		}
		return hitbox;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;		
	}

	public Inventory getPlayerInventory() {
		return playerInventory;
	}
}