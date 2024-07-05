package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import models.Game;

public class UI_ItemInfo implements UI_Template {

	Game gp;
	private Graphics2D g2;
	public int titleScreenState = 1;

	public UI_ItemInfo(Game gp) {
		this.gp = gp;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;

		// TITLE STATE
		if (gp.gameState != gp.playState) {
			return;
		}

		int objIndex = gp.collisionChecker.checkObject(gp.player, true);
		{
			drawTitleScreen(objIndex);
		}
	}

	private void drawTitleScreen(int i) {

		// TITLE NAME

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 12F));
		String text = gp.obj[i].info;
		int x = gp.tileSize / 2;
		int y = gp.tileSize / 2;
		int textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // Gets width of text.
		int textHeight = (int) g2.getFontMetrics().getStringBounds(text, g2).getHeight(); // Gets height of text.

//		g2.setColor(new Color(0, 0, 0)); // FILL BACKGROUND BLACK
//		g2.fillRect(gp.tileSize - 3, gp.tileSize - textHeight, textLenght + 5, textHeight + 5);

		// SHADOW
		g2.setColor(Color.darkGray);
		g2.drawString(text, x + 1, y + 1);

		// MAIN COLOR
		g2.setColor(Color.lightGray);
		g2.drawString(text, x, y);

		// USE
		text = "Pickup / Drop : [ E ]";
		// shadow
		g2.setColor(Color.darkGray);
		g2.drawString(text, x + 1, y + textHeight + 1);
		//
		g2.setColor(Color.lightGray);
		g2.drawString(text, x, y + textHeight);

		// ATTACKABLE ETC..
		if (gp.obj[i].isUsable()) {
			text = " Attack : [ Enter ]";
			// shadow
			g2.setColor(Color.darkGray);
			g2.drawString(text, x + 1, y + textHeight * 2 + 1);
			//
			g2.setColor(Color.lightGray);
			g2.drawString(text, x, y + textHeight * 2);
		}

	}
}
