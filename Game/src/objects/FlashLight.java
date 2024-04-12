package objects;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class FlashLight extends Entity {
	
	int cursorX;
	int cursorY;
	
	GamePanel gp;
	
	BufferedImage image;
	
	public FlashLight(GamePanel gp) {
		super(gp);
		UtilityTool uTool = new UtilityTool();

		this.gp = gp;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/entity/FlashLight.png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		worldX = gp.screenWidth/2 - 16;
		worldY = gp.screenHeight/2 - 10;
		
	}
	
	
	
	public void draw(Graphics2D g2) {
		
		// GET THE Cursor coordinate
		cursorX = MouseInfo.getPointerInfo().getLocation().x;
		cursorY = MouseInfo.getPointerInfo().getLocation().y;
		
		//calculate the distance
		float xDistance = cursorX - (gp.screenWidth / 2) ;
		float yDistance = cursorY - (gp.screenHeight / 2);
		
//		System.out.println("cursorX: " + cursorX);
//		System.out.println("cursorY: " + cursorY);
//		
//		System.out.println("xDistance: " + xDistance);
//		System.out.println("yDistance: " + yDistance);
//		
//		System.out.println(Math.atan2(yDistance, xDistance));

		//calculate the angle
		double rotationAngle = Math.toDegrees(Math.atan2(yDistance, xDistance));
		double rotationRequired = Math.toRadians(rotationAngle);
		double locationX = image.getWidth() / 2;
		double locationY = image.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		// Drawing the rotated image at the required drawing locations
		g2.drawImage(op.filter(image, null), worldX, worldY, null);
		
	}
	
	
	
	
}
