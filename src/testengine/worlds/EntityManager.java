package testengine.worlds;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import testengine.Launcher;
import testengine.entities.Entity;
import testengine.entities.player.Player;

public class EntityManager {
	
	private Comparator<Entity> comp = new Comparator<Entity>() {
		@Override
		public int compare(Entity o1, Entity o2) {
			if ((o1.getY() + o1.getHeight()) < (o2.getY() + o2.getHeight())) {
				return -1;
			} else if ((o1.getY() + o1.getHeight()) > (o2.getY() + o2.getHeight()))  {
				return 1;
			} else return 0;
		}
	};
	
	private Player player;
	private World world;
	private ArrayList<Entity> entities;
	private ArrayList<Entity> removalQueue, additionQueue;
	
	
	public EntityManager(World world) {
		this.world = world;
		entities = new ArrayList<Entity>();
		removalQueue = new ArrayList<Entity>();
		additionQueue = new ArrayList<Entity>();
	}
	
	public void tick() {
		removeQueuedEntities();
		addQueuedEntities();
		entities.sort(comp);
		Entity e;
		int j = entities.size();
		for (int i = 0; i < j; i++) {
			e = entities.get(i);
			if (e != null)
				e.tick();
			}
		}
	
	public void queueForRemoval(Entity e) {
		removalQueue.add(e);
	}
	
	private void removeQueuedEntities() {
		for (Entity e: removalQueue) {
			entities.remove(e);
			e.setEntityManager(null);
		}
		removalQueue.clear();
	}
	
	public void render(Graphics g) {
		Entity e;
		int j = entities.size();
		for (int i = 0; i < j; i++) {
			e = entities.get(i);
			if (e != null) {
				e.render(g);
			}
		}
		
		if (Launcher.DEBUG) {
			for (int i = 0; i < j; i++) {
				e = entities.get(i);
				if (e != null) {
					e.renderCollisionBox(g);
				}
			}
		}
		
	}
	
	public void queueForAddition(Entity e) {
		additionQueue.add(e);
	}
	
	private void addQueuedEntities() {
		for (Entity e: additionQueue) {
			entities.add(e);
			e.setEntityManager(this);
		}
		additionQueue.clear();
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public World getWorld() {
		return world;
	}

	public void addPlayer(Player player) {
		this.player = player;
		queueForAddition(player);
		player.setEntityManager(this);
	}	
	
	public void removePlayer() {
		queueForRemoval(player);
		player = null;
	}
	
	
}
