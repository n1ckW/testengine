package testengine.items;

import java.awt.image.BufferedImage;

import testengine.entities.ImmobileEntity;
import testengine.entities.player.Player;
import testengine.gfx.GameCamera;
import testengine.worlds.EntityManager;

public abstract class Item extends ImmobileEntity {
	
	protected BufferedImage inventoryIcon;
	protected String inventoryName;
	protected int currentStackSize = 1, maximumStackSize = 100;
	
	public Item(float x, float y, int width, int height, EntityManager em, GameCamera gc) {
		super(x, y, width, height, gc);
		inventoryName = this.getClass().getName();
	}
	
	@Override
	public void interact(Player p) {
		p.getPlayerInventory().add(this);
		entityManager.queueForRemoval(this);
	}

	public BufferedImage getInventoryIcon() {
		return inventoryIcon;
	}
	
	public String getInventoryName() {
		return inventoryName;
	}

	public int getCurrentStackSize() {
		return currentStackSize;
	}

	public void setCurrentStackSize(int currentStackSize) {
		this.currentStackSize = currentStackSize;
	}

	public int getMaximumStackSize() {
		return maximumStackSize;
	}
}
