package models.entity.monster;

import java.awt.image.BufferedImage;
import java.util.Random;

import models.Game;
import models.entity.Entity;
import models.entity.skill.HealingSkillBehavior;
import models.entity.skill.SkillBehavior;



public class MON_Cockroach extends Monster {

	SkillBehavior skillBehavior;


	public MON_Cockroach(Game gp2) {
		super(gp2);
		this.skillBehavior = new MonsterHealingBehavior(this) ;

		

		//INIT
		name = "Giant Cockroach";
		type = type_monster;
		speed = 1;
		direction = "right";
		maxHealth = 6;

		health = maxHealth;

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

	

	}



	


