package testengine.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageButton extends UIObject {
	private BufferedImage img_idle, img_hovering, img_pressed;
	private ClickListener clicker;
	
	public ImageButton(int x, int y, int width, int height, BufferedImage[] images, ClickListener clicker) {
		super(x, y, width, height);
		this.img_idle = images[0];
		this.img_hovering = images[1];
		this.img_pressed = images[2];
		this.clicker = clicker;
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		if (pressed)
			g.drawImage(img_pressed, (int)x, (int)y, width, height, null);
		else if (hovering) 
			g.drawImage(img_hovering, (int)x, (int)y, width, height, null);
		else 
			g.drawImage(img_idle, (int)x, (int)y, width, height, null);
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}

}
