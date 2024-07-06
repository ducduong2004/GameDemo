package models.entity.objbehavior;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import models.entity.object.OBJ;

public class RecyclableTrashBehavior implements TrashBehaviorStrategy {

	@Override
	public void performAction(OBJ obj) {
		// TODO Auto-generated method stub
		System.out.println(obj.type + " can be recycled.");
	}

	 
}
