package testengine.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class UIObject {
	
	protected int x, y; 
	protected int width, height;
	protected boolean hovering, pressed;
	protected Rectangle bounds;
	
	public UIObject(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle((int)x, (int)y, width, height);
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void onClick();
	
	public void onMouseMove(MouseEvent e) {
		if (bounds.contains(e.getX(), e.getY()))
			hovering = true;
		else {
			hovering = false;
			pressed = false;
		}
	}
	
	public void onMousePress(MouseEvent e) {
		if (hovering)
			pressed = true;
	}

	public void onMouseRelease(MouseEvent e) {
		if (pressed) {
			onClick();
			pressed = false;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public boolean isHovering() {
		return hovering;
	}

}
