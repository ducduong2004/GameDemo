package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import models.Game;

public class UI_PlayerSelect implements UI_Template {

	Game gp;
	private Graphics2D g2;
	public int titleScreenState = 4;

	public UI_PlayerSelect(Game gp) {
		this.gp = gp;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;

		// TITLE STATE
		drawTitleScreen();
	}

	private void drawTitleScreen() {

		BufferedImage img1 = null;
		BufferedImage img2 = null;

		try {
			img1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
			img2 = ImageIO.read(getClass().getResourceAsStream("/player/right1_2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		g2.setColor(new Color(0, 0, 0)); // FILL BACKGROUND BLACK
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		// TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
		String text = "Choose your Character\n";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 3 / 2;

		// SHADOW
		g2.setColor(Color.gray);
		g2.drawString(text, x + 2, y + 2);

		// MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y);

		x = gp.screenWidth * 1 / 3 - (gp.tileSize * 1) / 2;
		y += gp.tileSize * 1;
		g2.drawImage(img1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

		x = gp.screenWidth * 2 / 3 - (gp.tileSize * 1) / 2;
		g2.drawImage(img2, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

		y += gp.tileSize * 2;

		g2.drawString("2", x, y);

		x = gp.screenWidth * 1 / 3 - (gp.tileSize * 1) / 2;
		g2.drawString("1", x, y);

	}

	public int getXforCenteredText(String text) {
		int textLenght;
		textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // Gets width of text.
		int x = gp.screenWidth / 2 - textLenght / 2;
		return x;
	}

}