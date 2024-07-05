package models.entity.object;

import models.Game;
import models.entity.Entity;

public class OBJ_Shovel extends Entity{

	public OBJ_Shovel(Game gp2) {
		super(gp2);
		name = "shovel";
		type = type_object;
		info = "Rusty Shovel";
		noMirror = true;
		setImage(1, "/objects/shovel", 1);
		collisionOn = false;
		setUsable(true);
	}

}
