package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {

	public BufferedImage left1, left2 , right1, right2;
	KeyHandler keyH;
	Entity inventory[] = new Entity[2];
	public Entity mainHand = null;

	public final int screenX;
	public final int screenY;

	public int spriteCounter = 0;
	public int spriteNum = 1;
	boolean canInteract = true;


	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;
		

		type = 0;
		name = "Player";
		direction = "right";
		worldX = 5 * gp.tileSize;
		worldY = 3 * gp.tileSize;
		
		//get player middle of the screen
		screenX = gp.screenWidth/2 - (gp.tileSize / 2);
		screenY = gp.screenHeight/2 - (gp.tileSize / 2);

		//setSize for collision
		solidArea.x = 11;
		solidArea.y  = 40;
		solidArea.width = 35;
		solidArea.height = 25;
		solidAreaDefaultX = 11;
		solidAreaDefaultY = 40 ;
		setDefaultValues();
		currentImage = right1;
	}

	public void setDefaultValues()
	{
		
		//Default Starting Positions
		worldX = gp.tileSize * 5;
		worldY = gp.tileSize * 5;

		health = 10;
		thresholdDistance = 100;
		defaultSpeed = 6;
		speed = defaultSpeed;
		direction = "down";

		getPlayerImage();
	}


	// setup các hướng của người chơi
	public void getPlayerImage() {
		right1 = setup("right1");
		right2 = setup("right2");
		left1 = setup("left1");
		left2 = setup("left2");
		
	}


	public void setDefaultPositions()
	{
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		direction = "down";
	}

	public void update() {

		// CHECK TILE COLLISION
		collisionOn =false;
		gp.collisionChecker.checkTile(this);
		

		// CHECK OBJECT COLLISION
		int objIndex = gp.collisionChecker.checkObject(this, true);

		if(keyH.interaction) {
			if(gp.collisionChecker.onBin(this) && mainHand != null && mainHand.type == type_object) {
				for(int i = 0; i < gp.obj.length;i++) {
					
					if(gp.obj[i]!= null)
						if(gp.obj[i].equals(mainHand)){
							mainHand = null;
							gp.obj[i] = null;
							
						}
				}
				return;
			}
			pickUpObject(objIndex);
		}

		holdingItem(mainHand);


		// MOVEMENT
		if(keyH.leftPressed == true || keyH.rightPressed == true || keyH.downPressed == true || keyH.upPressed == true) {
			if(keyH.upPressed) {
				direction = "up";
			} 
			if (keyH.downPressed) {
				direction = "down";
			}
			if(keyH.leftPressed) {
				direction = "left";
			} 
			if (keyH.rightPressed) {
				direction = "right";
			}


			// COLLISION = FALSE => CAN MOVE
			if(!collisionOn) {
				if(direction == "up") worldY -= speed;  
				if(direction == "down") worldY += speed; 
				if(direction == "left")worldX -= speed;
				if(direction == "right") worldX += speed;
			}


			spriteCounter++;
			if(spriteCounter > 10) {
				spriteNum = (spriteNum == 1) ? 2 : 1;
				spriteCounter = 0;
			}
		}

	}



	// player pick up an item
	public void pickUpObject(int objIndex) {
		if(!canInteract)
			return;
		//check if player is near with Collision Checker
		if(objIndex != -1) {
			//fill up the empty hand
			for(Entity obj : inventory) {
				if(mainHand == null ) {
					System.out.println("pick");
					obj = gp.obj[objIndex];
					mainHand = obj;
					canInteract = false;
					startCooldownTimer();
				}
				else {
					System.out.println("drop");
					mainHand = null;
//					dropObject();
					canInteract = false;
					startCooldownTimer();
				}
				return;
			}
		}
	}

	// Method to start cooldown timer
	private void startCooldownTimer() {
		new Thread(() -> {
			try {
				Thread.sleep(500); // Cooldown period (in milliseconds)
				canInteract = true; // Reset flag after cooldown
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	
	
	public void holdingItem(Entity so) {
		if(so == null) {
			return;
		}
		so.setInWorld(worldX, worldY);
	}


	//player drop obj
//	private void dropObject() {
//		if(mainHand == null || !canInteract) 
//			return;
//
//		switch (direction) {
//		case "up": 
//			mainHand.setInWorld(worldX, worldY );
//			mainHand = null;
//			
//			break;
//		case "down":
//			mainHand.setInWorld(worldX, worldY );
//			mainHand = null;
//			break;
//		case "right":
//			mainHand.setInWorld(worldX , worldY);
//			mainHand = null;
//			break;
//		case "left":
//			mainHand.setInWorld(worldX, worldY);
//			mainHand = null;
//			break;
//		}
//
//
//
//	}

	public BufferedImage setup(String imagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imagePath + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch ( IOException e) {
			e.getStackTrace();
		}

		return image;

	}

	public void draw(Graphics g2) {

		switch (direction) {
		case"down":
			if(currentImage.equals(right1) || currentImage.equals(right2)) {
				if(spriteNum == 1) 
					currentImage = right1;
				if(spriteNum == 2)
					currentImage = right2;
			} else {
				if(spriteNum == 1) 
					currentImage = left1;
				if(spriteNum == 2)
					currentImage = left2;
			}
			break;
		case"up":
			if(currentImage.equals(right1) || currentImage.equals(right2)) {
				if(spriteNum == 1) 
					currentImage = right1;
				if(spriteNum == 2)
					currentImage = right2;
			} else {
				if(spriteNum == 1) 
					currentImage = left1;
				if(spriteNum == 2)
					currentImage = left2;
			}
			break;
		case "right": {
			if(spriteNum == 1) 
				currentImage = right1;
			if(spriteNum == 2)
				currentImage = right2;

			break;
		}
		case "left": {
			if(spriteNum == 1) 
				currentImage = left1;
			if(spriteNum == 2)
				currentImage = left2;				
			break;
		}
		};

		g2.drawImage(currentImage, screenX, screenY, null);
		g2.drawRect(screenX, screenY, 64, 64);


	}





}
