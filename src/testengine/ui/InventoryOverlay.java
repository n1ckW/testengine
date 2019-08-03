package testengine.ui;

import java.awt.Color;
import java.util.LinkedList;
import testengine.Game;
import testengine.items.Inventory;
import testengine.items.Item;

public class InventoryOverlay extends LinkedList<UIObject> {
	private Inventory inventory;
	public final int iovWidth, iovHeight;
	
	private ItemSlot selected;
	private ItemSlot[] slots;
	
	public InventoryOverlay(Inventory inventory, Game game) {
		super();
		this.inventory = inventory;
		inventory.setOverlay(this);
		iovWidth = (int)(game.getWidth() * (0.66));
		iovHeight = (int)(game.getHeight() * (0.66));
		BackgroundPanel bgPanel = new BackgroundPanel((int)(game.getWidth()/2 - iovWidth/2), (int)(game.getHeight()/2 - iovHeight/2), iovWidth, iovHeight);
		add(new BackgroundPanel(bgPanel.getX()-3, bgPanel.getY()-3, bgPanel.getWidth()+6, bgPanel.getHeight()+6, Color.BLACK));
		add(bgPanel);
		
		slots = new ItemSlot[inventory.getSize()];
		int i = 0;
		int rowCount = 0;
		int row = 0;
		while (i < slots.length) {
			rowCount = 0;
			while (rowCount < 5) {
				slots[i] = new ItemSlot(bgPanel.getX()+16 + rowCount*36, bgPanel.getY()+16 + row*36, this, i);
				add(slots[i]);
				i++;
				rowCount++;
				if (i >= slots.length)
					break;
			}
			row++;
		}
		
	}
	
	public void slotClicked(ItemSlot is) {
		if (selected == null) {
			selected = is;
			selected.setHighlighted(true);
		}
		else if (selected == is) {
			selected.setHighlighted(false);
			selected = null;
		}
		else {
			Item i1 = inventory.getItemBySlot(selected.getId());
			Item i2 = inventory.getItemBySlot(is.getId());
			inventory.clearSlot(selected.getId());
			inventory.clearSlot(is.getId());
			inventory.putItemInSlot(i1, is.getId());
			inventory.putItemInSlot(i2, selected.getId());
			selected.setHighlighted(false);
			selected = null;
		}
	}
	
	public void update() {
		for (int i = 0; i < slots.length; i++) {
			slots[i].setItem(inventory.getItemBySlot(i));
		}
	}
}