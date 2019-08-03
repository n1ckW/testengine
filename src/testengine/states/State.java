package testengine.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface State{		
	
	public abstract void tick();		
	public abstract void render(Graphics g); 
	
	public abstract void onMousePress(MouseEvent e);
	public abstract void onMouseRelease(MouseEvent e);
	public abstract void onMouseMove(MouseEvent e);
	
	public abstract void onKeyPress(KeyEvent e);
	public abstract void onKeyRelease(KeyEvent e);
}
