package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import entity.TaiSmile;
import enviroment.EnviromentManager;
import enviroment.Lighting;
import objects.FlashLight;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	//SCREEN SETTINGS
	final int originalTitleSize = 32; // 32x32
	final int scale = 2;
	public final int tileSize = originalTitleSize * scale; // 64x64

	public final int maxScreenCol = 30;
	public final int maxScreenRow = 17;
	public final int screenWidth = tileSize * maxScreenCol; // 1920px
	public final int screenHeight = tileSize * maxScreenRow; // 1080px




	//WORLD SETTING
	public final int maxWorldCol = 82;
	public final int maxWorldRow = 83;

	//FPS
	final int fps = 60;




	//SYSTEM
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	public CollisionChecker collisionChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	Thread gameThread;



	// Entity and Object
	public Player player = new Player(this, keyH);
	public TaiSmile taiSmile = new TaiSmile(this);
	public FlashLight flashlight = new FlashLight(this);
	public Entity[] obj = new Entity[10];
	public Entity monster[] = new Entity[20];
	public ArrayList<Entity> entityList = new ArrayList<>();
	EnviromentManager eManage = new Lighting(this, 250);






	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);

	}


	public void gameSetup() {
		eManage.setup();
		aSetter.setObject();
		aSetter.setMonster();
		//setFullScreen();
	}

	public void setFullScreen() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Main.window);


	}


	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();

	}

	@Override
	public void run() {

		double drawInterval = 1000000000 / fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while(gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if(delta>=1) {
				update();
				repaint();
				delta--;
			}
		}
	}

	public void update() {
		
		
		
		
		player.update();
		
		for(Entity e : monster) {
			if(e != null)
				e.update();
		}
		
		for(Entity o : obj) {
			if(o != null)
				o.update();
		}
		
		
		
		
	}

	public void paintComponent(Graphics g) {
		
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		
		// important!!!!
		//DRAW IN THE EXACT PRIORITY

		//DRAW TILEMAP
		tileM.draw(g2);


		//DRAW PLAYER
		player.draw(g2);
		
		for(Entity e : monster) {
			if(e != null)
				e.draw(g2);
		}
		
		//DRAW OBJECT
		for(Entity s : obj) {
			if(s != null) {
				s.draw(g2);
			}
		}

		//FLASHLIGHT 
		flashlight.draw(g2);

		//DRAW ENVIROMENT
		eManage.draw(g2);
		g2.dispose();
	}

}
