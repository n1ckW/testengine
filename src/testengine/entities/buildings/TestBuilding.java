package testengine.entities.buildings;

import testengine.entities.Building;
import testengine.gfx.Animation;
import testengine.gfx.Assets;
import testengine.gfx.GameCamera;

public class TestBuilding extends Building {

	public TestBuilding(float x, float y, GameCamera gc) {
		super(x, y, 128, 128, gc, "/worlds/room_test1.w");
		this.current = new Animation(1000, Assets.house);
	}
	

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void hurt(int i) {
		
	}

}
