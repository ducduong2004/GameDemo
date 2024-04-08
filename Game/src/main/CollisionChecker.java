package main;

import java.util.ArrayList;

import entity.Entity;
import entity.Player;
import objects.SuperObject;

public class CollisionChecker {
	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		super();
		this.gp = gp;
	}


	// check if player is being block by a wall or a solid Block or not
	public void checkTile(Entity entity) {


		// set edge for Entity
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;                                // solidArea.x = 8
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;      // solidArea.y = 16
		int entityTopWorldY = entity.worldY + entity.solidArea.y;                                 // solidArea.width = 32
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;    // solidArea.height = 32


		// set coordinate for block around entity
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		//2 side tile
		int tileNum1, tileNum2;

		String direction = entity.direction;

		switch (direction)
		{
		case "up" :
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 =  gp.tileM.mapTileNum[entityLeftCol][entityTopRow];  //Check Left Hand
			tileNum2 =  gp.tileM.mapTileNum[entityRightCol][entityTopRow]; //Check Right Hand
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
			break;

		case "down" :
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			tileNum1 =  gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];  //Check Left Hand
			tileNum2 =  gp.tileM.mapTileNum[entityRightCol][entityBottomRow]; //Check Right Hand
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
			break;

		case "left" :
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 =  gp.tileM.mapTileNum[entityLeftCol][entityTopRow];  //Check Left Hand
			tileNum2 =  gp.tileM.mapTileNum[entityLeftCol][entityBottomRow]; //Check Right Hand
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
			break;

		case "right" :
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum1 =  gp.tileM.mapTileNum[entityRightCol][entityTopRow];  //Check Left Hand
			tileNum2 =  gp.tileM.mapTileNum[entityRightCol][entityBottomRow]; //Check Right Hand
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true)
			{
				entity.collisionOn = true;
			}
			break;
		}

	}



	// check if player or an entity touching an object or not, if yes return the index of the object in the Object array that contain all the object
	public int checkObject(Entity entity, boolean player) {

		int index = -1;
		for(int i = 0; i < gp.obj.length; i++) {

			Entity obj = gp.obj[i];

			if(obj != null) {

				//get entity's solidArea position
				//set collision always update by plusing it ( stupid method djt me may )
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;

				//get the object's solid area position
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

				if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
					index = i;
				}

				//!!!!! DO NOT CHANGE THIS, IT WILL CAUSE BUG WITH THE ENTITY AND OBJ SOLID AREA VALUE
				//sau khi update xong thi set nguoc ve binh thuonwg ( li do method nay ngu )
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				// set obj position back to normal
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;

			}

		}

		return index;




	}




	public int checkEntity(Entity entity1, Entity[] entityList) {

		int index = -1;

		for(Entity target : entityList) {
			if(target != null) {
				//get entity's solidArea position
				//set collision always update by plusing it ( dumb method )
				entity1.solidArea.x = entity1.worldX + entity1.solidArea.x;
				entity1.solidArea.y = entity1.worldY + entity1.solidArea.y;

				//get the object's solid area position
				target.solidArea.x = target.worldX + target.solidArea.x;
				target.solidArea.y = target.worldY + target.solidArea.y;

				if(entity1.solidArea.intersects(target.solidArea) && !entity1.equals(target)) {
					entity1.collisionOn = true;
					index = target.type;
				}
				entity1.solidArea.x = entity1.solidAreaDefaultX;
				entity1.solidArea.y = entity1.solidAreaDefaultY;
				// set obj position back to normal
				target.solidArea.x = target.solidAreaDefaultX;
				target.solidArea.y = target.solidAreaDefaultY;
			}

		}

		return index;

	}


	public boolean checkPlayer(Entity entity) {

		boolean contactPlayer = false;
		Entity target = gp.player;

		//set collision always update by plusing it ( dumb method )
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;

		//get the object's solid area position
		target.solidArea.x = target.worldX + target.solidArea.x;
		target.solidArea.y = target.worldY + target.solidArea.y;

		switch(entity.direction) {
		case "up": entity.solidArea.y -= entity.speed; break;
		case "down": entity.solidArea.y += entity.speed; break;
		case "left": entity.solidArea.y -= entity.speed; break;
		case "right": entity.solidArea.y += entity.speed; break;
		}
		if(entity.solidArea.intersects(target.solidArea)) {
			entity.collisionOn = true;
			contactPlayer = true;
		}

		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		// set obj position back to normal
		target.solidArea.x = target.solidAreaDefaultX;
		target.solidArea.y = target.solidAreaDefaultY;

		return contactPlayer;

	}

	public boolean onBin(Entity entity) {
		if(!(entity instanceof Player)) 
			return false;

		int entityCol = entity.getCol();
		int entityRow = entity.getRow();

		int tileNum = 4;

		if(tileNum == gp.tileM.mapTileNum[entityCol][entityRow]) {
			return true;
		}
		return false;
	}


}









