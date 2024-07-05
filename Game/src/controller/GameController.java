package controller;

import java.awt.Graphics2D;
import java.awt.event.KeyListener;

import models.Game;
import views.GameView;

public class GameController implements Runnable {

	Game game;
	Thread gameThread;
	GameView gameView;

	public GameController(Game game, GameView gameView) {

		this.game = game;
		this.gameView = gameView;

	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / game.getFPS();
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if (delta >= 1) {
				game.update();
				gameView.repaint();
				delta--;
			}
		}
	}

	public Game getGame() {
		return game;
	}

	public boolean noItem() {
		return game.score > 30;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

	public void addObserver() {
		game.addObserver(gameView);
	}

	public void won() {
		game.setGameState(game.winTitleState);
	}

	public void start() {
		game.setGameState(game.titleState);
	}

	public int getScreenWidth() {
		return game.getScreenWidth();
	}

	public int getScreenHeight() {
		return game.getScreenHeight();
	}

	public KeyListener getkeyH() {
		return game.keyH;
	}

	public void paintPlayComponent(Graphics2D g2) {
		game.paintPlayComponent(g2);
	}
	
	
	
	

}
