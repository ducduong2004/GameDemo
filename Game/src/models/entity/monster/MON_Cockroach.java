package models.entity.monster;

import java.awt.image.BufferedImage;
import java.util.Random;

import models.Game;
import models.entity.Entity;

public class MON_Cockroach extends Entity {
	private int actionLock = 0;
	public BufferedImage left1, left2, right1, right2;
	BufferedImage currentImage = right1;

	public MON_Cockroach(Game gp2) {
		super(gp2);

		name = "Giant Cockroach";
		type = type_monster;
		speed = 1;
		direction = "right";
		health = 6;

		solidArea.x = 12;
		solidArea.y = 28;

		solidArea.width = 40;
		solidArea.height = 18;

		solidAreaDefaultX = solidArea.x;

		solidAreaDefaultY = solidArea.y;

		noMirror = false;

		setImage(0, "/entity/enemy/giant_cockroach1", type_monster);
		setImage(1, "/entity/enemy/giant_cockroach2", type_monster);

		setImage(0, "/entity/enemy/giant_cockroach1", type_monster);
		setImage(1, "/entity/enemy/giant_cockroach2", type_monster);
	}

	@Override
	public void update() {
		setAction();
		collisionOn = false;
		gp.collisionChecker.checkTile(this);
		gp.collisionChecker.checkObject(this, false);

		


		// COLLISION = FALSE => CAN MOVE
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

	}

	@Override
	public void setAction() {
		actionLock++;

		if (actionLock == 60) {
			Random random = new Random();
			int i = random.nextInt(100) + 1;

			if (i <= 25) {
				direction = "up";
			}
			if (i > 25 && i <= 50) {
				direction = "down";
			}
			if (i > 50 && i <= 75) {
				direction = "left";
			}
			if (i > 75 && i <= 100) {
				direction = "right";
			}
			actionLock = 0;
		}

	}

	

}
