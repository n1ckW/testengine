package testengine.worlds;

import testengine.Game;
import testengine.entities.Building;

public class InteriorWorld extends World {
	private Building building;

	public InteriorWorld(String path, Game game, Building building) {
		super(path, game);
		this.building = building;
	}

	public Building getBuilding() {
		return building;
	}
}
