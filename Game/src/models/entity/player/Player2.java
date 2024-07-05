package models.entity.player;

import controller.input.KeyHandler;
import models.Game;
import models.entity.skill.LightFootSkillBehavior;

public class Player2 extends Player {

	private static Player2 instance ;
	private final int playerType = 2;
	private Player2(Game game, KeyHandler keyH) {
		super(game, keyH);
		skillBehavior = new LightFootSkillBehavior(this);

	}
	
	public static Player getInstance(Game game, KeyHandler keyH) {
		if(instance == null) {
			return instance = new Player2(game, keyH);
		}
		return instance;
	}

	public int getPlayerType() {
		return playerType;
	}

	@Override
	public void getPlayerImage() {

		right1 = setup("right1_2");
		right2 = setup("right2_2");
		left1 = setup("left1_2");
		left2 = setup("left2_2");

	}

}
