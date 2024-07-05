package models;

import models.entity.object.OBJ_Shovel;
import models.entity.object.OBJ_Bottle;

public class AssetSetter {
	Game gp;
	
	public AssetSetter(Game game) {
		this.gp = game;
		
	}
	
	public void setObject() {
		gp.obj[0] = new OBJ_Bottle(gp);
		gp.obj[1] = new OBJ_Bottle(gp);
		gp.obj[2] = new OBJ_Bottle(gp);
		gp.obj[3] = new OBJ_Bottle(gp);
		gp.obj[4] = new OBJ_Bottle(gp);
		gp.obj[5] = new OBJ_Shovel(gp);
		
		gp.obj[0].setPosition(19, 20);
		gp.obj[1].setPosition(5,5);
		gp.obj[2].setPosition(5, 6);
		gp.obj[3].setPosition(8,8);
		gp.obj[4].setPosition(75, 75);
		gp.obj[5].setPosition(7,5);
		
		
	}
	
	
	public void setMonster() {
		
		//gp.monster[0] = new TaiSmile(gp);
		//gp.monster[0].setPosition(20,20);

	}
}
