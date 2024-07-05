package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import models.Game;

public class UI_Win implements UI_Template {

	Game gp;
	private Graphics2D g2;
	public int titleScreenState = 0;
	public int commandNum = 0;

	public UI_Win(Game gp) {
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

		// TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
		String text = "YOU HAVE WON\n";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 3 / 2;

		// SHADOW
		g2.setColor(Color.gray);
		g2.drawString(text, x + 2, y + 2);

		// MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y);

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
		// SCORE
		text = "your score: " + gp.getScore();
		x = getXforCenteredText(text);
		y += gp.tileSize * 3;
		g2.drawString(text, x, y);

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

		// MAIN MENU
		text = "MAIN MENU";
		x = getXforCenteredText(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}

		// MAIN MENU
		text = "QUIT";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}
	}

	public int getXforCenteredText(String text) {
		int textLenght;
		textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // Gets width of text.
		int x = gp.screenWidth / 2 - textLenght / 2;
		return x;
	}

}