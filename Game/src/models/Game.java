package models;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import event.CollisionChecker;
import event.KeyHandler;
import models.entity.Entity;
import models.entity.player.Player;
import models.entity.player.Player1;
import models.entity.player.SimpleCharacterFactory;
import models.sound.Sound;
import models.tile.TileManager;
import views.UI;
import views.UI_HUD;
import views.UI_ItemInfo;
import views.UI_Lost;
import views.UI_OptionState;
import views.UI_PlayState;
import views.UI_PlayerSelect;
import views.UI_Template;
import views.UI_Win;

public class Game {

	List<Observer> observers;

	// SCREEN SETTINGS
	private final int originalTitleSize = 32; // 32x32
	private final int scale = 2;
	public final int tileSize = originalTitleSize * scale; // 64x64
	public final int maxScreenCol = 15;
	public final int maxScreenRow = 8;
	public final int screenWidth = tileSize * maxScreenCol; //
	public final int screenHeight = tileSize * maxScreenRow; //

	// SCORE
	public int score;

	// WORLD SETTING
	public final int maxWorldCol = 82;
	public final int maxWorldRow = 83;

	// MAP
	public final int maxMap = 10;
	public int currentMap = 0;

	// FPS
	private final int fps = 60;

	// GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int winTitleState = 3;
	public final int playerSelectState = 4;
	public final int optionState = 5;
	public final int lostState = 6;

	// SYSTEM
	public UI_Template ui_Template;
	public UI ui;
	public UI_Win ui_Win;
	public UI_ItemInfo ui_info;
	public UI_PlayerSelect ui_PlayerSelect;
	public UI_PlayState ui_PlayState;
	public UI_HUD ui_HUD;
	public UI_OptionState ui_OptionState;
	public UI_Lost ui_Lost;

	public KeyHandler keyH;
	public Player player;
	public TileManager tileM = new TileManager(this);

	// ENTITY AND OBJECT
	public Entity[] obj = new Entity[10];
	public Entity monster[] = new Entity[20];
	public CollisionChecker collisionChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);

	// SOUND
	public Sound sfx = new Sound();
	public Sound music = new Sound();;

	// PLAYER
	SimpleCharacterFactory factory = new SimpleCharacterFactory();
	public int playerType = 1;
	public final int playerType_01 = 1;
	public final int playerType_02 = 2;
	public final int playerType_03 = 3;

	public Game() {
		observers = new ArrayList<>();
		ui = new UI(this);
		ui_Win = new UI_Win(this);
		ui_info = new UI_ItemInfo(this);
		ui_PlayState = new UI_PlayState(this);
		ui_PlayerSelect = new UI_PlayerSelect(this);
		ui_HUD = new UI_HUD(this);
		ui_OptionState = new UI_OptionState(this);
		ui_Lost = new UI_Lost(this);
		ui_Template = ui;

		keyH = new KeyHandler(this);
		player = factory.createPlayer(playerType, this, keyH);
		gameSetup();

	}

	
	// RESET GAME
	public void resetGame() {
		score = 10;
		player.staringPosition();
		player.setDefaultValues();
		player.inventory = new Entity[4];
		aSetter.setObject();
		aSetter.setMonster();

	}

	public void gameSetup() {
		score = 10;
		aSetter.setObject();
		aSetter.setMonster();
		music.playMusic(0);
	}

	public void update() {
		// System.out.println(gameState);

		if (gameState == titleState) {
			return;
		}

		if (gameState == pauseState) {
			return;
		}

		player.update();

		for (Entity e : monster) {
			if (e != null)
				e.update();
		}

		for (Entity o : obj) {
			if (o != null)
				o.update();
		}

	}

	public void paintPlayComponent(Graphics2D g2) {
		ui_Template.draw(g2);
		g2.dispose();
	}

	public void setGameState(int state) {
		this.gameState = state;
		if (state == playState)
			ui_Template = ui_PlayState;
		if (state == titleState)
			ui_Template = ui;
		if (state == pauseState)
			;
		if (state == winTitleState)
			ui_Template = ui_Win;
		if (state == playerSelectState)
			ui_Template = ui_PlayerSelect;
		if (state == lostState)
		    ui_Template = ui_Lost;

	}

	// OBSERVER PATTERN
	public void addObserver(Observer o) {
		observers.add(o);
	}

	public void notifyObserver() {
		for (Observer o : observers) {
			o.update();
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
		notifyObserver();
	}

	public void inScore() {
		setScore(score + 10);
	}

	// GETTERS AND SETTERS
	public Player getPlayer() {
		return player;
	}

	public void setPlayer() {
		this.player = factory.createPlayer(playerType, this, keyH);
	}

	public int getPlayerType() {
		return playerType;
	}

	public void setPlayerType(int playerType) {
		this.playerType = playerType;
	}

	public int getScreenWidth() {
		return this.screenWidth;
	}

	public int getScreenHeight() {
		return this.screenHeight;
	}

	public int getFPS() {
		return this.fps;
	}

}
