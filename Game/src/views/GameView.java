package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import controller.GameController;
import models.Observer;

public class GameView extends JPanel implements Observer {

	GameController gameController;

	public GameView(GameController gameController) {
		this.gameController = gameController;
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.setPreferredSize(new Dimension(gameController.getScreenWidth(), gameController.getScreenHeight()));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(gameController.getkeyH());
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		gameController.paintPlayComponent(g2);
	}

	@Override
	public void update() {
		if(gameController.isHalfScore()) {
			gameController.setBrookenLight(true);
		}
		if (gameController.isMaxScore())
			gameController.won();
	}

}
