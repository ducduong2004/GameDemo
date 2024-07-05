package models.entity.object;

import java.awt.Rectangle;

import models.Game;
import models.entity.Entity;

public class OBJ_Shovel extends Entity{

	private final int attackRange = 50;
	private int actionLock = 0;

	public OBJ_Shovel(Game gp) {
		super(gp);
		name = "shovel";
		type = type_object;
		info = "Rusty Shovel";
		noMirror = true;
		setImage(1, "/objects/shovel", 1);
		collisionOn = false;
		setUsable(true);

		solidArea.x = 0;
		solidArea.y = 0;

		solidArea.width = 64;
		solidArea.height = 64;

		solidAreaDefaultX = solidArea.x;

		solidAreaDefaultY = solidArea.y;
	}

	public void setAction() {
		actionLock++;

		

		if(gp.keyH.enterPressed && gp.player.getInventory(gp.player.slot) != null && gp.player.getInventory(gp.player.slot).equals(this)){
			gp.player.usingItem = true;
			attack(); 
			gp.keyH.enterPressed = false;
			gp.player.usingItem = false;
		}
		

	}

	
	private void checkEntity() {
		// CHECK FOR COLLISION WITH Entity
		int monster_index = gp.collisionChecker.checkEntity(this, gp.monster);
		System.out.println(monster_index);
		if(monster_index > -1) {
			Entity monster = gp.monster[monster_index];
			if(monster.type == gp.player.type_monster){
				monster.health--;
			}

			if(monster.health < 0) {
				gp.monster[monster_index] = null;
			}
        }

		


	}

	private void attack() {
		switch (gp.player.direction) {
			case "right":{
				this.setInWorld(worldX + this.attackRange, worldY);
				checkEntity();
				break;
			}
			case "left": {
				this.setInWorld(worldX - this.attackRange, worldY);
				checkEntity();
				break;
			}
			case "up":{
				this.setInWorld(worldX, worldY - this.attackRange);
				checkEntity();
				break;
			}
			case "down":{
				this.setInWorld(worldX, worldY + this.attackRange);
				checkEntity();
				break;
			}
			
           

		}

	}
}