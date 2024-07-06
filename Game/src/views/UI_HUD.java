package views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import models.Game;
import models.entity.Entity;

public class UI_HUD implements UI_Template {

	private Game gp;
	public int slotX = 0;

	public UI_HUD(Game gp) {
		this.gp = gp;
	}


	// DRAW SCORE
	@Override
	public void draw(Graphics2D g2) {

		String text = "Task:\n Cleaning the room?";
		int x = gp.screenWidth - gp.tileSize * 4;
		int y = gp.screenHeight * 1 / 12;

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15F));

		g2.setColor(Color.white);
		g2.drawString(text, x, y);

		String score = gp.score + "/ 60";

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15F));

		g2.setColor(Color.white);
		g2.drawString(score, x, y + 32);

		// DRAW HP

		text = "HP : " + gp.player.health + " / " + gp.player.maxHealth;
		x = gp.tileSize / 2;
		y = gp.tileSize / 2;
		g2.setColor(Color.darkGray);
		g2.drawString(text, x + 1, gp.screenHeight - y + 1);

		g2.setColor(Color.white);
		g2.drawString(text, x, gp.screenHeight - y);

		
		// DRAW INVENTORY SLOT
		int slotStartX = (int) (gp.screenWidth / 2 - gp.tileSize * 2.5);
		int slotStartY = (int) (gp.screenHeight - gp.tileSize * 1.5);

		g2.setColor(new Color(0f, 0f, 0f, .2f));
		g2.fillRect(slotStartX, slotStartY, gp.tileSize, gp.tileSize);
		slotStartX += gp.tileSize * 1.5;
		g2.fillRect(slotStartX, slotStartY, gp.tileSize, gp.tileSize);
		slotStartX += gp.tileSize * 1.5;
		g2.fillRect(slotStartX, slotStartY, gp.tileSize, gp.tileSize);
		slotStartX += gp.tileSize * 1.5;
		g2.fillRect(slotStartX, slotStartY, gp.tileSize, gp.tileSize);

		slotStartX = (int) (gp.screenWidth / 2 - gp.tileSize * 2.5);
		slotStartY = (int) (gp.screenHeight - gp.tileSize * 1.5);

		g2.setColor(Color.LIGHT_GRAY);
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(slotStartX, slotStartY, gp.tileSize, gp.tileSize);
		slotStartX += gp.tileSize * 1.5;
		g2.drawRect(slotStartX, slotStartY, gp.tileSize, gp.tileSize);
		slotStartX += gp.tileSize * 1.5;
		g2.drawRect(slotStartX, slotStartY, gp.tileSize, gp.tileSize);
		slotStartX += gp.tileSize * 1.5;
		g2.drawRect(slotStartX, slotStartY, gp.tileSize, gp.tileSize);

		slotStartX = (int) (gp.screenWidth / 2 - gp.tileSize * 2.5);

		// DRAW PLAYER'S INVENTORY
		for (int i = 0; i < 4; i++) {
			if (gp.player.inventory[i] != null) {
				g2.drawImage(gp.player.inventory[i].getCurrentImage(), slotStartX + 16, slotStartY + 16, null);
				slotStartX += gp.tileSize * 1.5;
			} else {
				slotStartX += gp.tileSize * 1.5;
			}

		}
		slotStartX = (int) (gp.screenWidth / 2 - gp.tileSize * 2.5);

		// CURSOR
		int cursorX = (int) (slotStartX + (gp.tileSize * slotX * 1.5));
		int cursorY = slotStartY;
		int cursorWidth = gp.tileSize;
		int cursorHeight = gp.tileSize;

		// DRAW CURSOR
		g2.setColor(new Color(1f, 1f, 1f, .1f));
		g2.fillRect(cursorX, cursorY, cursorWidth, cursorHeight);

		g2.setColor(Color.white);
		g2.drawRect(cursorX, cursorY, cursorWidth, cursorHeight);

	}

}
