package models.entity.object;

import models.Game;
import models.entity.Entity;

public class OBJ_Bottle extends Entity {

	public OBJ_Bottle(Game gp) {
		super(gp);
		name = "trash";
		type = type_object;
		info = "Dirty Bottle";
		noMirror = true;
		setImage(1, "/objects/bottle", 1);
		collisionOn = false;
		setUsable(false);
	}

	@Override
	public void setAction() {
		super.setAction();
	}

}
