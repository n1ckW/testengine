package testengine.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import testengine.Game;

public class MouseManager implements MouseListener, MouseMotionListener {
	
	public int mouseX, mouseY;
	private Game game;
	
	public MouseManager(Game game) {
		this.game = game;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		game.getCurrentState().onMouseMove(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		game.getCurrentState().onMousePress(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {	
		game.getCurrentState().onMouseRelease(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub		
	}
}
