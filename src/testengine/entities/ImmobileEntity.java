package testengine.entities;

import testengine.gfx.GameCamera;

public abstract class ImmobileEntity extends Entity{

	public ImmobileEntity(float x, float y, int width, int height, GameCamera gc) {
		super(x, y, width, height, gc);
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
