package models.entity.monster;

import java.awt.image.BufferedImage;
import java.util.Random;

import models.Game;
import models.entity.Entity;
import models.entity.skill.SkillBehavior;

public abstract class Monster extends Entity{
	
	private int actionLock = 0;
	public BufferedImage left1, left2, right1, right2;
	BufferedImage currentImage = right1;
	public int timeHeal = 0;

	private SkillBehavior autoHealBehavior;
	private SkillBehavior speedupBehavior;

    public Monster(Game gp2) {
        super(gp2);
        this.autoHealBehavior = new MonsterHealingBehavior(this);
		this.speedupBehavior = new MonsterSpeedUp(this);
    }
    @Override
    public void update() {
		setAction();
		autoHealBehavior.useSkill();
		if(this instanceof MON_Spider){
			speedupBehavior.useSkill();
		}
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
    }}
