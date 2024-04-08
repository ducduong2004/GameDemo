package objects;

import entity.Entity;
import main.GamePanel;

public class OBJ_Trash extends Entity {
	 

	public OBJ_Trash(GamePanel gp) {
		super(gp);
		
		name = "trash";
		type = type_object;
		noMirror = true;
		setImage(1, "/objects/bottle", 1);
		collisionOn = false;
		
		

	}

	
	


}
