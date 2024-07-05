package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import models.Game;

public class UI implements UI_Template {

	Game gp;
	private Graphics2D g2;
	public int titleScreenState = 0;
	public int commandNum = 0;

	public UI(Game gp) {
		this.gp = gp;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;

		// TITLE STATE
		drawTitleScreen();

	}

	private void drawTitleScreen() {
		g2.setColor(new Color(0, 0, 0)); // FILL BACKGROUND BLACK
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		// MAIN MENU
		if (titleScreenState == gp.gameState) {

			// TITLE NAME
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
			String text = "Lethal 2D Demo\n";
			int x = getXforCenteredText(text);
			int y = gp.tileSize * 3 / 2;

			// SHADOW
			g2.setColor(Color.gray);
			g2.drawString(text, x + 2, y + 2);

			// MAIN COLOR
			g2.setColor(Color.white);
			g2.drawString(text, x, y);

			// IMAGE
			x = gp.screenWidth / 2 - (gp.tileSize * 1) / 2;
			y += gp.tileSize * 1;
			g2.drawImage(gp.player.left1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

			// MENU
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

			text = "NEW GAME";
			x = getXforCenteredText(text);
			y += gp.tileSize * 3;
			g2.drawString(text, x, y);
			if (commandNum == 0) {
				g2.drawString(">", x - gp.tileSize, y);
			}

			text = "CHARACTER";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 1) {
				g2.drawString(">", x - gp.tileSize, y);
			}

			text = "QUIT";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y);
			}
		}

	}

	public int getXforCenteredText(String text) {
		int textLenght;
		textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // Gets width of text.
		int x = gp.screenWidth / 2 - textLenght / 2;
		return x;
	}
}