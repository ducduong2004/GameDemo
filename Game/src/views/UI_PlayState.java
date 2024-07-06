package views;

import java.awt.Graphics2D;

import models.Game;
import models.entity.Entity;

public class UI_PlayState implements UI_Template {

	Game gp;

	public UI_PlayState(Game gp) {
		this.gp = gp;
	}

	@Override
	public void draw(Graphics2D g2) {
		// DRAW IN THE EXACT PRIORITY
		// Map -> player -> object -> monster -> light -> HUD

		// DRAW TILEMAP
		gp.tileM.draw(g2);

		// DRAW PLAYER
		gp.player.draw(g2);

		// DRAW ENTITY
		for (Entity e : gp.monster) {
			if (e != null)
				e.draw(g2);
		}

		// DRAW OBJECT
		for (Entity s : gp.obj) {
			if (s != null) {
				s.draw(g2);
			}
		}

		// DRAW LIGHT
		if(gp.getBrookenLight()) {
			gp.ui_LightOff.draw(g2);
		}

		// SHOW OBJECT INFOMATION
		if (gp.collisionChecker.checkObject(gp.player, true) != -1) {
			gp.ui_info.draw(g2);
		}

		// DRAW HUD
		gp.ui_HUD.draw(g2);

		if (gp.gameState == gp.optionState) {
			gp.ui_OptionState.draw(g2);
		}
		

	}

}
