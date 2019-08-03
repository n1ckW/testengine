package testengine.items;

import testengine.ui.InventoryOverlay;

public class Inventory {
	
	private InventoryOverlay iov;
	private Item[] items;
	private int size = 40, full, empty;

	public Inventory() {
		items = new Item[size];
		full = 0;
		empty = 50;
	}
	
	public void setOverlay(InventoryOverlay iov) {
		this.iov = iov;
	}
	
	/**
	 * add an item to this inventory
	 * @param e 
	 * the item to be added
	 * @return
	 * TRUE if the item was added successfully, FALSE if the item was not added
	 */
	public boolean add(Item e) {
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null) {
				e.getEntityManager().queueForRemoval(e);
				items[i] = e;
				full++;
				empty--;
				iov.update();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * get the item in a given slot
	 * @param i the slot index
	 * @return the item in slot i
	 */
	public Item getItemBySlot(int i) {
		return items[i];
	}
	
	/**
	 * clears the specified slot
	 * @param i the slot to clear
	 * @return TRUE if an item was removed, FALSE if the slot was already empty
	 */
	public boolean clearSlot(int i) {
		boolean rv = (items[i] != null);
		items[i] = null;
		iov.update();
		return rv;
	}
	
	/**
	 * attempts to put an item into the specified slot
	 * @param e the item to store
	 * @param i the slot index
	 * @return TRUE if item is successfully stored, FALSE if the attempt failed
	 */
	public boolean putItemInSlot(Item e, int i) {
		if (items[i] == null) {
			items[i] = e;
			iov.update();
			return true;
		}
		return false;
	}

	/**
	 * debug method for text output
	 */
	public void output() {
		for (int i=0; i < items.length; i++) {
			if (items[i] != null) {
				System.out.println("slot: "+i+", item name: "+items[i].getInventoryName());
			}
		}
	}

	public int getSize() {
		return size;
	}

	public int getFull() {
		return full;
	}

	public int getEmpty() {
		return empty;
	}
}