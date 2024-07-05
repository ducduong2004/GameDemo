package models.entity.object;

import models.Game;
import models.entity.Entity;

public class OBJ_Shovel extends Entity{

	private final int attackRange = 50;

	public OBJ_Shovel(Game gp) {
		super(gp);
		name = "shovel";
		type = type_object;
		info = "Rusty Shovel";
		noMirror = true;
		setImage(1, "/objects/shovel", 1);
		collisionOn = false;
		setUsable(true);
	}

	public void setAction() {
		if(gp.keyH.enterPressed && gp.player.getInventory(gp.player.slot) != null && gp.player.getInventory(gp.player.slot).equals(this)){
			gp.player.usingItem = true;
			attack(); 
			gp.keyH.enterPressed = false;
			gp.player.usingItem = false;
		}
	}

	
	private void attack() {
		switch (gp.player.direction) {
			case "right":{
				this.setInWorld(worldX + this.attackRange, worldY);
				break;
			}
			case "left": {
				this.setInWorld(worldX - this.attackRange, worldY);
				break;
			}
			case "up":{
				this.setInWorld(worldX, worldY - this.attackRange);
				break;
			}
			case "down":{
				this.setInWorld(worldX, worldY + this.attackRange);
				break;
			}
			
           

		}

	}
}