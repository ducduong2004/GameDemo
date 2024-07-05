package views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;

import models.Game;

public class UI_OptionState implements UI_Template {

	private Game gp;
	private Graphics2D g2;

	public int commandNum = 0;

	public UI_OptionState(Game gp) {
		this.gp = gp;
	}

	@Override
	public void draw(Graphics2D g2) {
		this.g2 = g2;

		int windowX = gp.tileSize * 5;
		int windowY = gp.tileSize;
		int windowWidth = gp.tileSize * 5;
		int windowHeight = gp.tileSize * 5;
		drawWindow(windowX, windowY, windowWidth, windowHeight);
		gp.keyH.enterPressed = false;
	}

	public void drawWindow(int windowX, int windowY, int windowWidth, int windowHeight) {
		int x = 0;
		int y = 0;
		// BACKGROUND
		g2.setColor(new Color(0f, 0f, 0f, 0.8f));
		g2.fillRect(windowX, windowY, windowWidth, windowHeight);

		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(windowX, windowY, windowWidth, windowHeight);

		// TITLE
		String text = "Options";
		x = getXforCenteredText(text, gp.screenWidth);
		y += gp.tileSize * 1.5;

		g2.setColor(Color.white);
		g2.drawString(text, getXforCenteredText(text, gp.screenWidth), y);

		x = (windowX + gp.tileSize / 2) + 16;

		// MUSIC
		text = "Music";
		y += gp.tileSize;

		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - 16, y);
		}
		// SFX
		text = "SFX";
		y += gp.tileSize / 2;

		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x - 16, y);
		}
		// MAIN MENU
		text = "Main Menu";
		y += gp.tileSize / 2;

		g2.drawString(text, x, y);
		if (commandNum == 2) {
			g2.drawString(">", x - 16, y);
			if (gp.keyH.enterPressed) {
				gp.setGameState(gp.titleState);
			}
		}
		// BACK
		text = "Back";
		y += gp.tileSize;

		g2.drawString(text, x, y);
		if (commandNum == 3) {
			g2.drawString(">", x - 16, y);
			if (gp.keyH.enterPressed) {
				gp.setGameState(gp.playState);
			}
		}

		//////////
		g2.setStroke(new BasicStroke(1));

		x += gp.tileSize * 2;
		y = (int) (gp.tileSize * 2 - 14);

		// MUSIC VOLUME
		y += gp.tileSize / 2;
		g2.drawRect(x, y, 100, 16);
		g2.fillRect(x, y, 20 * gp.music.volumeScale, 16);

		// SFX VOLUME
		y += gp.tileSize / 2;
		g2.drawRect(x, y, 100, 16);
		g2.fillRect(x, y, 20 * gp.sfx.volumeScale, 16);
	}

	public int getXforCenteredText(String text, int width) {
		int textLenght;
		textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // Gets width of text.
		int x = width / 2 - textLenght / 2;
		return x;
	}
}
