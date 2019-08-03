package testengine.gfx;

import java.awt.image.BufferedImage;

public class Animation {
	private int speed, index = 0;
	private long lastTime, timer = 0;
	private BufferedImage[] frames;
	
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		lastTime = System.currentTimeMillis();
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	
	public void tick() {
		timer += (System.currentTimeMillis() -lastTime);
		lastTime = System.currentTimeMillis();
		if (timer >= speed) {
			if (index < (frames.length-1)) index++;
			else index = 0;
			timer = 0;
		}
	}
}
