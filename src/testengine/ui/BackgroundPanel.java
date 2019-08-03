package testengine.ui;

import java.awt.Color;
import java.awt.Graphics;

public class BackgroundPanel extends UIObject{
	private Color color = Color.LIGHT_GRAY;
	public BackgroundPanel(int x, int y, int width, int height) {		
		super(x, y, width, height);
	}
	
	public BackgroundPanel(int x, int y, int width, int height, Color color) {
		this(x, y, width, height);
		this.color = color;
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);
	}

	@Override
	public void onClick() {}

}
