package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class TaiSmile extends Entity{


	public int worldX, worldY;
	BufferedImage indieImage;
	BufferedImage indieImageMirror;
	boolean canInteract;
	int frameDelay = 10; // Delay between frame updates (adjust as needed)
	int frameDelayCounter = 0; // Counter to track frame delay



	public TaiSmile(GamePanel gp) {
		super(gp);

		type = 2;
		name = "Tai Smile";
		speed = 6;
		thresholdDistance = 10; 
		direction = "down";


		// collision
		solidArea.x = 100;
		solidArea.y = 250;
		solidArea.width = 64;
		solidArea.height = 64;

		solidAreaDefaultX = solidArea.x ;
		solidAreaDefaultY = solidArea.y ;

		getImage(4, "tai-smile");
	}


	public void setAction() {
	    // Get positions
	    int entityPosX = getLeftX();
	    int entityPosY = getTopY();
	    int playerPosX = gp.player.getLeftX();
	    int playerPosY = gp.player.getTopY();


	    // Calculate distances
	    int distanceX = playerPosX - entityPosX;
	    int distanceY = playerPosY - entityPosY;

	    // Check if the monster can move
	    if (!collisionOn) {
	        // Move towards the player if not within threshold distance
	        if (Math.abs(distanceY) > thresholdDistance) {
	            if (distanceY > 0) {
	                direction = "up";
	            }
	            if (distanceY < 0) {
	                direction = "down";
	            }
	        }
	        if (Math.abs(distanceX) > thresholdDistance) {
	            if (distanceX < 0) {
	                direction = "left";
	            }
	            if (distanceX > 0) {
	                direction = "right";
	            }
	        }
	        if (Math.abs(distanceY) <= thresholdDistance && Math.abs(distanceX) <= thresholdDistance) {
	            // Add debug statement for reaching the player
	        	
	        	//
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        }
	    }
	}






	// tai smile can co class draw rieng biet boi vi texture cua tai smile la 100x100 ( vi ngu )
	private void getImage(int frameNum, String imagePath) {
		UtilityTool uTool = new UtilityTool();

		try {
			indieImage = ImageIO.read(getClass().getResourceAsStream("/entity/enemy/" + imagePath + ".png"));
			indieImageMirror = ImageIO.read(getClass().getResourceAsStream("/entity/enemy/" + imagePath + "-mirror.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		totalFrames = frameNum;
		frames = new BufferedImage[totalFrames];
		framesMirror = new BufferedImage[totalFrames];


		frameWidth = 100; //image cua tai smile 100x100
		for (int i = 0; i < totalFrames; i++) {
			frames[i] = indieImage.getSubimage(i * frameWidth, 0, frameWidth , frameWidth);
			frames[i] = uTool.scaleImage(frames[i], gp.tileSize * 5 , gp.tileSize * 5);
			framesMirror[i] = indieImageMirror.getSubimage(i * frameWidth, 0, frameWidth , frameWidth);
			framesMirror[i] = uTool.scaleImage(framesMirror[i], gp.tileSize * 5, gp.tileSize * 5);
		}
	}
}
