package models.entity;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.input.KeyHandler;
import models.Game;
import models.entity.object.OBJ_Bottle;
import models.sound.Sound;
import utils.UtilityTool;

public abstract class Player extends Entity {

	public SkillBehavior skillBehavior;

	public BufferedImage left1, left2, right1, right2;
	KeyHandler keyH;
//	public Entity mainHand = null;
	public final int screenX;
	public final int screenY;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	boolean canInteract = true;
	public boolean usingItem = false;

	public Entity[] inventory = new Entity[4];
	public int slot = 0;
	public Entity onHand = null;

	public boolean invincible = false;
	public int invincibleCounter = 0;
	

	public Player(Game game, KeyHandler keyH) {
		super(game);
		this.keyH = keyH;

		maxHealth = 10;
		health = maxHealth;

		type = 0;
		name = "Player";
		direction = "right";
		worldX = 5 * game.tileSize;
		worldY = 3 * game.tileSize;

		// get player middle of the screen
		screenX = game.screenWidth / 2 - (game.tileSize / 2);
		screenY = game.screenHeight / 2 - (game.tileSize / 2);

		// setSize for collision
		solidArea.x = 11;
		solidArea.y = 40;
		solidArea.width = 35;
		solidArea.height = 25;
		solidAreaDefaultX = 11;
		solidAreaDefaultY = 40;
		setDefaultValues();
		currentImage = right1;

	}

	public void setDefaultValues() {
		// Default Starting Positions
		worldX = gp.tileSize * 5;
		worldY = gp.tileSize * 5;

		health = 10;
		thresholdDistance = 100;
		defaultSpeed = 6;
		speed = defaultSpeed;
		direction = "down";

		getPlayerImage();
	}

	public void staringPosition() {
		worldX = gp.tileSize * 5;
		worldY = gp.tileSize * 5;
	}

	public abstract int getPlayerType();

	// setup các hướng của người chơi
	public abstract void getPlayerImage();

	public void setDefaultPositions() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		direction = "down";
	}

	public void update() {
		// CHECK TILE COLLISION
		collisionOn = false;
		gp.collisionChecker.checkTile(this);

		// CHECK OBJECT COLLISION
		int objIndex = gp.collisionChecker.checkObject(this, true);

		// CHECK MONSTER COLLISION
		int monsterIndex = gp.collisionChecker.checkEntity(this, gp.monster);
		contactMonster(monsterIndex);

		if (keyH.interaction) {
//			System.out.println("Key interaction detected"); // Debugging statement
			if (gp.collisionChecker.onBin(this) && inventory[slot] != null && inventory[slot].type == type_object) {
				System.out.println("Collision with bin detected"); // Debugging statement
				for (int i = 0; i < gp.obj.length; i++) {
					if (gp.obj[i] != null && gp.obj[i].equals(inventory[slot])) {
						gp.obj[i] = null;
						inventory[slot] = null;
						gp.inScore();
						System.out.println("Object removed from hand and score incremented"); // Debugging statement

						gp.sfx.playSFX(3);
						break;
					}
				}
				return;
			}
			pickUpObject(objIndex);

		}

		// keep Item on playerr position
		if(!usingitem()) {
			holdingItem(inventory[slot]);
		}
			
		

		// MOVEMENT
		if (keyH.leftPressed == true || keyH.rightPressed == true || keyH.downPressed == true
				|| keyH.upPressed == true) {
			if (keyH.upPressed) {
				direction = "up";
			}
			if (keyH.downPressed) {
				direction = "down";
			}
			if (keyH.leftPressed) {
				direction = "left";
			}
			if (keyH.rightPressed) {
				direction = "right";
			}

			// COLLISIONON = FALSE => CAN MOVE
			if (!collisionOn) {
				if (direction == "up")
					worldY -= speed;
				if (direction == "down")
					worldY += speed;
				if (direction == "left")
					worldX -= speed;
				if (direction == "right")
					worldX += speed;
			}

			spriteCounter++;
			if (spriteCounter > 10) {
				spriteNum = (spriteNum == 1) ? 2 : 1;
				spriteCounter = 0;
			}

		}

		if (invincible == true) {
			invincibleCounter++;
			if (invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}

	}

	public void contactMonster(int monsterIndex) {
		if (monsterIndex != -1) {

			if (invincible == false) {
				health -= 1;
				invincible = true;
			}

		}
	}


	private boolean usingitem() {
		return usingItem;
	}

	// player pick up an item
	public void pickUpObject(int objIndex) {
		if (!canInteract)
			return;
		// check if player is near with Collision Checker
		if (objIndex != -1) {
			// fill up the empty hand
			if (inventory[slot] == null) {
				System.out.println("pick");

				inventory[slot] = gp.obj[objIndex];
				canInteract = false;
				startCooldownTimer();

				gp.sfx.playSFX(1);
			}

			else {
				System.out.println("drop");
				inventory[slot] = null;

				canInteract = false;
				startCooldownTimer();

				gp.sfx.playSFX(1);
			}
			return;
		}
	}

	// holding item
	public void holdingItem(Entity item) {

		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] != null) {
				inventory[i].setPosition(80, 80);
			}
		}
		if (item != null) {
			item.setInWorld(worldX + 16, worldY + 16);
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

	public BufferedImage setup(String imagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imagePath + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			e.getStackTrace();
		}

		return image;

	}

	public void draw(Graphics2D g2) {

		switch (direction) {
		case "down":
			if (currentImage.equals(right1) || currentImage.equals(right2)) {
				if (spriteNum == 1)
					currentImage = right1;
				if (spriteNum == 2)
					currentImage = right2;
			} else {
				if (spriteNum == 1)
					currentImage = left1;
				if (spriteNum == 2)
					currentImage = left2;
			}
			break;
		case "up":
			if (currentImage.equals(right1) || currentImage.equals(right2)) {
				if (spriteNum == 1)
					currentImage = right1;
				if (spriteNum == 2)
					currentImage = right2;
			} else {
				if (spriteNum == 1)
					currentImage = left1;
				if (spriteNum == 2)
					currentImage = left2;
			}
			break;
		case "right": {
			if (spriteNum == 1)
				currentImage = right1;
			if (spriteNum == 2)
				currentImage = right2;

			break;
		}
		case "left": {
			if (spriteNum == 1)
				currentImage = left1;
			if (spriteNum == 2)
				currentImage = left2;
			break;
		}
		
		}

		if (invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		}

		g2.drawImage(currentImage, screenX, screenY, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		//g2.drawRect(screenX, screenY, 64, 64);

	}

	public Entity getInventory(int x) {
		return this.inventory[x];
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
