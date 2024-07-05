package models;

import models.entity.object.OBJ_Shovel;
import models.entity.monster.MON_Cockroach;
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
		gp.obj[6] = new OBJ_Bottle(gp);
		gp.obj[7] = new OBJ_Bottle(gp);
		gp.obj[8] = new OBJ_Shovel(gp);


		gp.obj[0].setPosition(19, 20);
		gp.obj[1].setPosition(5, 5);
		gp.obj[2].setPosition(5, 6);
		gp.obj[3].setPosition(8, 8);
		gp.obj[4].setPosition(75, 75);
		gp.obj[5].setPosition(8, 10);
		gp.obj[6].setPosition(72, 5);
		gp.obj[7].setPosition(53, 80);
		gp.obj[8].setPosition(57, 5);

		

	}

	public void setMonster() {

		gp.monster[0] = new MON_Cockroach(gp);
		gp.monster[1] = new MON_Cockroach(gp);
		gp.monster[2] = new MON_Cockroach(gp);
		gp.monster[3] = new MON_Cockroach(gp);
		gp.monster[4] = new MON_Cockroach(gp);
		gp.monster[5] = new MON_Cockroach(gp);

		gp.monster[0].setPosition(5,8);
		gp.monster[1].setPosition(9,9);
		gp.monster[2].setPosition(75,76);
		gp.monster[3].setPosition(10,10);
		gp.monster[4].setPosition(60,80);
		gp.monster[5].setPosition(50,3);


	}
}
