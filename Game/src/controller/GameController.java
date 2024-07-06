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


	// GETTER AND SETTER
	public int getScreenWidth() {
		return game.getScreenWidth();
	}

	public int getScreenHeight() {
		return game.getScreenHeight();
	}

	public KeyListener getkeyH() {
		return game.keyH;
	}

	public Game getGame() {
		return game;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

	public void addObserver() {
		game.addObserver(gameView);
	}

	//CHECK IF THE SCORE IS MAX
	public boolean isMaxScore() {
		return game.score > 60;
	}

	//CHECK IF THE SCORE IS HALF 
	public boolean isHalfScore() {
		return game.getScore() == 30;
	}
	
	// SET GAMESTATE TO WIN_STATE
	public void won() {
		game.setGameState(game.winTitleState);
	}

	// TURN ON BROOKEN LIGHT
	public void setBrookenLight(boolean b) {
		game.setBrookenLight(b);
	}

	// PAINT THE GRAPHICS
	public void paintPlayComponent(Graphics2D g2) {
		game.paintPlayComponent(g2);
	}

	
	


}
