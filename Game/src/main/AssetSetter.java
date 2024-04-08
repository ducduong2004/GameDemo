package main;

import entity.TaiSmile;
import objects.OBJ_Trash;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
		
	}
	
	public void setObject() {
		gp.obj[0] = new OBJ_Trash(gp);
		gp.obj[0].setPosition(20, 20);
	}
	
	
	public void setMonster() {
		
		gp.monster[0] = new TaiSmile(gp);
		gp.monster[0].setPosition(20,20);

	}
}
