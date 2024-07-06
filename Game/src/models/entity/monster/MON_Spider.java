package models.entity.monster;

import models.Game;

public class MON_Spider extends Monster {

    public MON_Spider(Game gp2) {
        super(gp2);
        name = "Spider";
		type = type_monster;
		speed = 2;
		direction = "right";
		health = 10;

		solidArea.x = 6;
		solidArea.y = 12;

		solidArea.width = 29*2;
		solidArea.height = 18*2;

		solidAreaDefaultX = solidArea.x;

		solidAreaDefaultY = solidArea.y;

        noMirror = false;

		setImage(0, "/entity/enemy/spider1", type_monster);

		setImage(0, "/entity/enemy/spider1", type_monster);
		
    }

}
