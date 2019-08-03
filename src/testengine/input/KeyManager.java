package testengine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import testengine.Game;

public class KeyManager implements KeyListener{
	
	private Game game;
	
	public KeyManager(Game game) {
		this.game = game;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		game.getCurrentState().onKeyPress(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		game.getCurrentState().onKeyRelease(e);

	}
	


}
