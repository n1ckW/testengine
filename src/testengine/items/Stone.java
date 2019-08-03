package testengine.items;

import testengine.gfx.Animation;
import testengine.gfx.Assets;
import testengine.gfx.GameCamera;
import testengine.worlds.EntityManager;

public class Stone extends Item {

	public Stone(float x, float y, EntityManager em, GameCamera gc) {
		super(x, y, 16, 16, em, gc);
		current = new Animation(1000, Assets.stone);
		inventoryIcon = current.getCurrentFrame();
		collisionBox.setBounds(0, 0, 0, 0);
		inventoryName = "Stone";
	}

}
