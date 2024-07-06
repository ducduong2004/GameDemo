package models.entity.objbehavior;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import models.entity.object.OBJ;

public class NonRecyclableTrashBehavior implements TrashBehaviorStrategy {


	@Override
	public void performAction(OBJ obj) {
		
		System.out.println(obj.type + " can not be recycled.");
	}

	 
}