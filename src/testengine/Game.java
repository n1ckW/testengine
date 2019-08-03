package testengine;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import testengine.display.Display;
import testengine.gfx.Assets;
import testengine.gfx.GameCamera;
import testengine.input.KeyManager;
import testengine.input.MouseManager;
import testengine.states.MenuState;
import testengine.states.State;
import testengine.states.WorldState;

public class Game {
	private int width, height;
	private boolean running;	
	private Display display;
	private KeyManager keyManager;
	private MouseManager mouseManager;	
	private GameCamera gameCamera;	
	private BufferStrategy bs;
	private State currentState;
	private WorldState worldState;
	private MenuState menuState;	
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		running = true;
		Assets.init();
		
		gameCamera = new GameCamera(0,0, this);
		
		worldState = new WorldState(this);
		menuState = new MenuState(this);		
		gameCamera.setWorld(worldState.getCurrentWorld());
		
		currentState = menuState;	
		
		display = new Display(title, width, height);
		display.getCanvas().createBufferStrategy(3);
		bs = display.getCanvas().getBufferStrategy();
		
		keyManager = new KeyManager(this);
		mouseManager = new MouseManager(this);
		
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
	}

	public void tick() {
			currentState.tick();
	}
	
	public void render() {
		Graphics g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		currentState.render(g);
		bs.show();
		g.dispose();
	}
	
	public void terminate() {
		display.getFrame().dispose();
	}	
	
	public WorldState getWorldState() {
		return worldState;
	}

	public MenuState getMenuState() {
		return menuState;
	}

	public State getCurrentState() {
		return currentState;
	}
	
	public void setCurrentState(State state) {
		this.currentState = state;
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
