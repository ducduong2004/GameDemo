package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class SuperObject {
	
	public boolean invisible;
	public boolean pickedUp;
	public int spriteNum = 1;
	int spriteCounter = 0;

	public BufferedImage image;
	public String name;
	public int worldX, worldY;
	public boolean collision = false;
	public Rectangle solidArea = new Rectangle(0,0,64,64);
	public int solidAreaDeFaultX = 0;
	public int solidAreaDeFaultY = 0;
	
	public UtilityTool uTool = new UtilityTool();
	
	
	
	public void setPosition(int worldX, int worldY) {
		this.worldX = worldX;
		this.worldY = worldY;
	}
	
	public void draw(Graphics2D g2, GamePanel gp) {
		if(pickedUp || invisible) {
			return;
		}
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;


		//if object was in the screen then load it, if not do nothing
		if(worldX <= gp.player.worldX + gp.player.screenX + gp.tileSize &&
				worldX >= gp.player.worldX - gp.player.screenX -  gp.tileSize &&
				worldY <= gp.player.worldY + gp.player.screenY +  gp.tileSize &&
				worldY >= gp.player.worldY - gp.player.screenY -  gp.tileSize) {

			if(spriteNum == 1)
				g2.drawImage(image, screenX, screenY, null);
			else
				g2.drawImage(image, screenX, screenY-5, null);


			spriteCounter++;
			if(spriteCounter > 40) {
				spriteNum = (spriteNum == 1) ? 2 : 1;
				spriteCounter = 0;
			}
		}




	}
}
