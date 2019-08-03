package testengine.ui;

import java.awt.Color;
import java.awt.Graphics;

import testengine.items.Item;

public class ItemSlot extends UIObject {
	private Item item;
	private InventoryOverlay iov;
	private int id;
	private boolean highlighted = false;

	public ItemSlot(int x, int y, InventoryOverlay iov, int id) {
		super(x, y, 32, 32);
		this.iov = iov;
		this.id = id;
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		if (highlighted) {
			g.setColor(new Color(200, 50, 50));
			g.fillRect(x-2, y-2, width+4, height+4);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(x-1, y-1, width+2, height+2);
		}
		g.setColor(Color.WHITE);
		g.fillRect((int)x, (int)y, width, height);
		if (item!= null)
			g.drawImage(item.getInventoryIcon(), (int)x, (int)y, width, height, null);
	}

	@Override
	public void onClick() {
		iov.slotClicked(this);
	}
	
	public void setItem(Item i) {
		item = i;
	}
	
	public Item getItem() {
		return item;
	}

	public int getId() {
		return id;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlight) {
		this.highlighted = highlight;
	}

}
