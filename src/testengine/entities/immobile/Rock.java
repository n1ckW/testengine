package testengine.entities.immobile;

import testengine.entities.ImmobileEntity;
import testengine.gfx.Animation;
import testengine.gfx.Assets;
import testengine.gfx.GameCamera;
import testengine.items.Stone;
import testengine.tiles.TileLibrary;

public class Rock extends ImmobileEntity {



	public Rock(float x, float y, GameCamera gc) {
		super(x, y, TileLibrary.TILEWIDTH, TileLibrary.TILEHEIGHT, gc);
		current = new Animation(1000, Assets.stone);
		collisionBox.x = 8;
		collisionBox.y = 8;
		collisionBox.width = 48;
		collisionBox.height = 48;
		health = 60;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void die() {
		super.die();
		entityManager.queueForAddition(new Stone(x + width/2, y + height/2, entityManager, gameCamera));
	}

}
