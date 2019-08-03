package testengine.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Collection;

import testengine.Game;
import testengine.entities.player.Player;
import testengine.ui.InventoryOverlay;
import testengine.ui.UIManager;
import testengine.ui.UIObject;
import testengine.worlds.World;
import testengine.worlds.WorldLibrary;

public class WorldState implements State {
	private World currentWorld, newWorld = null;
	private Player player;
	private Game game;
	private WorldLibrary worldLibrary;
	private UIManager uiManager;
	private Collection<UIObject> inventoryOverlay;
	private boolean inventoryOverlayActive, inventoryToggle = false;	
	private boolean[] keys = new boolean[256];	

	
	public WorldState(Game game) {
		this.game = game;
		worldLibrary = new WorldLibrary(game);
		currentWorld = worldLibrary.getOverWorld();
		player = new Player(0, 0, game.getGameCamera());
		currentWorld.addPlayer(player);
		uiManager = new UIManager();
		inventoryOverlay = new InventoryOverlay(player.getPlayerInventory(), game);
	}
	
	private void setActiveWorld (World newActiveWorld) {
		currentWorld.removePlayer();
		this.currentWorld = newActiveWorld;
		currentWorld.addPlayer(player);
	}
	
	public void queueWorldTransition(World world) {
		newWorld = world;
	}

	@Override
	public void tick() {
		player.up = keys[KeyEvent.VK_W];
		player.down = keys[KeyEvent.VK_S];
		player.left = keys[KeyEvent.VK_A];
		player.right = keys[KeyEvent.VK_D];
		if (newWorld != null) {
			setActiveWorld(newWorld);
			newWorld = null;
		}
		if (inventoryToggle) {
			toggleInventoryOverlay();
			inventoryToggle = false;
		}
		uiManager.tick();
		currentWorld.tick();
	}

	@Override
	public void render(Graphics g) {
		currentWorld.render(g);
		uiManager.render(g);
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE: 
			game.setCurrentState(game.getMenuState());
			break;
		case KeyEvent.VK_SPACE:
			player.queueMeleeAttack();
			break;
		case KeyEvent.VK_E:
			player.queueInteraction();
			break;
		case KeyEvent.VK_1:
			queueWorldTransition(worldLibrary.getOverWorld());
			break;
		case KeyEvent.VK_2:
			setInventoryToggle();
			break;
		case KeyEvent.VK_3: 
			player.getPlayerInventory().output();
			break;
		}
	}
	
	@Override
	public void onMousePress(MouseEvent e) {
		uiManager.onMousePress(e);		
	}

	@Override
	public void onMouseRelease(MouseEvent e) {
		uiManager.onMouseRelease(e);
	}

	@Override
	public void onMouseMove(MouseEvent e) {
		uiManager.onMouseMove(e);
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	private void setInventoryToggle() {
		inventoryToggle = true;
	}
	
	private void toggleInventoryOverlay() {
		if (inventoryOverlayActive) {
			uiManager.removeObjects(inventoryOverlay);
			inventoryOverlayActive = false;
		}
		else {
			uiManager.addObjects(inventoryOverlay);
			inventoryOverlayActive = true;
		}
	}
	
	public World getCurrentWorld() {
		return currentWorld;
	}

	public WorldLibrary getWorldLibrary() {
		return worldLibrary;
	}
}
