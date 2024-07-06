package models.entity.object;

import models.Game;
import models.entity.Entity;
import models.entity.objbehavior.RecyclableTrashBehavior;

public class OBJ_Bottle extends OBJ {

	public OBJ_Bottle(Game gp) {
		super(gp);
		trashBehaviorStrategy = new RecyclableTrashBehavior();
		name = "trash";
		type = type_object;
		info = "Dirty Bottle";
		noMirror = true;
		setImage(1, "/objects/bottle", 1);
		collisionOn = false;
		setUsable(false);
	}


}
