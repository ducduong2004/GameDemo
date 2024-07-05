package models.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import models.Game;
import utils.UtilityTool;

public class Entity {

	// state
	public Game gp;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public int worldX, worldY; // position on the map
	public String direction = "down";
	public Rectangle solidArea = new Rectangle(0, 0, 64, 64);
	public boolean collisionOn = false;
	protected boolean noMirror;
	private boolean usable;

	// for animation
	protected int frameWidth = 32; // Width of each frame in the sprite sheet
	BufferedImage indieImage; // Sprite Image for Entity
	BufferedImage indieImageMirror; // Sprite Image for Entity but being mirror
	protected BufferedImage[] frames; // Array to store individual frames
	protected BufferedImage[] framesMirror; // Array to store individual frames
	protected BufferedImage currentImage = null;

	// counter and animation
	private int frameDelay = 10; // Delay between frame updates (adjust as needed)
	private int frameDelayCounter = 0; // Counter to track frame delay
	protected int currentFrame; // Index of the current frame
	protected int totalFrames; // Total number of frames in the sprite sheet
	public int spriteCounter = 0;
	public int spriteNum = 1;

	// Character Atributes
	public int health;
	public String name;
	public int thresholdDistance; // Range
	public int speed;
	public int defaultSpeed;
	public String info = "it'll do something";

	// Type
	public int type;
	public final int type_player = 0;
	public final int type_npc = 1;
	public final int type_monster = 2;
	public final int type_object = 3;

	public Entity(Game gp2) {
		this.gp = gp2;
	}

	public int getScreenX() {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		return screenX;
	}

	public int getScreenY() {
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		return screenY;
	}

	public int getLeftX() {
		return worldX + solidArea.x;
	}

	public int getRightX() {
		return worldX + solidArea.width + solidArea.width;
	}

	public int getTopY() {
		return worldY + solidArea.y;
	}

	public int getBottomY() {
		return worldY + solidArea.y + solidArea.height;
	}

	public int getCol() {
		return (worldX + solidArea.x) / gp.tileSize;
	}

	public int getRow() {
		return (worldY + solidArea.y) / gp.tileSize;
	}

	public int getCenterX() {
		int centerX = worldX + frames[0].getWidth() / 2;
		return centerX;
	}

	public int getCenterY() {
		int centerY = worldY + frames[0].getHeight() / 2;
		return centerY;
	}

	public BufferedImage getIndieImage() {
		return indieImage;
	}

	public BufferedImage getCurrentImage() {
		return currentImage;
	}

	public void setAction() {
	}

	public void checkCollision() {

		collisionOn = false;
		gp.collisionChecker.checkTile(this);
		gp.collisionChecker.checkObject(this, false);
		// gp.collisionChecker.checkEntity(this, gp.npc);
		gp.collisionChecker.checkEntity(this, gp.monster);
		// gp.collisionChecker.checkEntity(this,gp.iTile);
		boolean contactPlayer = gp.collisionChecker.checkPlayer(this);
		if (contactPlayer == true) {
			this.conTactPlayer();
		}

	}

	private void conTactPlayer() {
	};

	public void update() {

		setAction();
		checkCollision();

		if (!collisionOn) {
			switch (direction) {
			case "up":
				worldY += speed;
				break;

			case "down":
				worldY -= speed;
				break;

			case "left":
				worldX -= speed;
				break;

			case "right":
				worldX += speed;
				break;
			}
		}
		spriteCounter++;
		if (spriteCounter > 24) {
			if (spriteNum == 1) // Every 12 frames sprite num changes.
			{
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0; // spriteCounter reset
		}

	}

	public boolean inCamera() {
		boolean inCamera = false;
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
				&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
				&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			inCamera = true;
		}
		return inCamera;
	}

	// draw texture and animate
	public void setImage(int frameNum, String imagePath, int scaleNum) {
		UtilityTool uTool = new UtilityTool();

		if (noMirror) {
			try {
				currentImage = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;

		}

		try {
			indieImage = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			indieImageMirror = ImageIO.read(getClass().getResourceAsStream(imagePath + "-mirror.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		totalFrames = frameNum;
		frames = new BufferedImage[totalFrames];
		framesMirror = new BufferedImage[totalFrames];
		for (int i = 0; i < totalFrames; i++) {
			frames[i] = indieImage.getSubimage(i * frameWidth, 0, frameWidth, indieImage.getHeight());
			frames[i] = uTool.scaleImage(frames[i], gp.tileSize * scaleNum, gp.tileSize * scaleNum);
			framesMirror[i] = indieImage.getSubimage(i * frameWidth, 0, frameWidth, indieImage.getHeight());
			framesMirror[i] = uTool.scaleImage(framesMirror[i], gp.tileSize * scaleNum, gp.tileSize * scaleNum);
		}

	}

	// draw method
	public void draw(Graphics g2) {

		int screenX = getScreenX();
		int screenY = getScreenY();

		// if object was in the screen then load it, if not do nothing
		if (inCamera()) {
			if (noMirror) {
				g2.drawImage(currentImage, screenX, screenY, null);
				g2.drawRect(screenX + solidArea.x, screenY + solidArea.x, 64, 64);
				return;
			}

			switch (direction) {
			case "right":
				currentImage = frames[currentFrame];
				break;
			case "left":
				currentImage = framesMirror[currentFrame];
				break;
			case "up":
				currentImage = frames[currentFrame];
				break;
			case "down":
				currentImage = frames[currentFrame];
				break;
			}
			animate();
			g2.drawImage(currentImage, screenX, screenY, null);
			// g2.drawRect(screenX + solidArea.x, screenY + solidArea.x, 64, 64);
		}

	}

	// set position
	public void setPosition(int i, int j) {
		worldX = i * gp.tileSize;
		worldY = j * gp.tileSize;
	}

	public void setInWorld(int worldX, int worldY) {
		this.worldX = worldX;
		this.worldY = worldY;
	}

	// delay to animate
	protected void animate() {
		// Increment frame delay counter
		frameDelayCounter++;
		// Check if it's time to update the frame
		if (frameDelayCounter >= frameDelay) {
			// Reset frame delay counter
			frameDelayCounter = 0;
			// Increment frame counter
			currentFrame++;
			// Wrap around to the beginning if reached the end
			if (currentFrame >= totalFrames) {
				currentFrame = 0;
			}
		}
	}

	public boolean isUsable() {
		return usable;
	}

	public void setUsable(boolean usable) {
		this.usable = usable;
	}

}
