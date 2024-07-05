package models.entity.player;

import event.KeyHandler;
import models.Game;
import models.entity.skill.HealingSkillBehavior;

public class Player1 extends Player {

	private static Player1 instance ;
	private final int playerType = 1;

	private Player1(Game game, KeyHandler keyH) {
		super(game, keyH);
		skillBehavior = new HealingSkillBehavior(this);
	}
	
	public static Player getInstance(Game game, KeyHandler keyH) {
		if(instance == null) {
			return instance = new Player1(game, keyH);
		}
		return instance;
	}

	public int getPlayerType() {
		return playerType;
	}

	@Override
	public void getPlayerImage() {

		right1 = setup("right1");
		right2 = setup("right2");
		left1 = setup("left1");
		left2 = setup("left2");

	}

}
