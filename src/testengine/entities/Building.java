package testengine.entities;

import testengine.gfx.GameCamera;
import testengine.states.WorldState;
import testengine.worlds.InteriorWorld;
import testengine.worlds.World;

public abstract class Building extends ImmobileEntity {
	private InteriorWorld interiorWorld;	
	private WorldState worldState;
	private World overWorld;
	private boolean init = false;
	private String worldFilePath;
	
	public Building(float x, float y, int width, int height, GameCamera gc, String worldFilePath) {
		super(x, y, width, height, gc);
		this.worldFilePath = worldFilePath;
	}
	
	@Override
	public void interact() {
		enter();
	}
	
	protected void enter() {
		if (!init)
			init();
		worldState.queueWorldTransition(interiorWorld);
	}
	
	public void exit() {
		worldState.queueWorldTransition(overWorld);
	}

	public InteriorWorld getInteriorWorld() {
		return interiorWorld;
	}
	
	private void init() {
		worldState = entityManager.getWorld().getGame().getWorldState();
		overWorld = worldState.getCurrentWorld();
		interiorWorld = worldState.getWorldLibrary().createInteriorWorld(worldFilePath, this);
		init = true;
	}
}
