package models.tile;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import models.Game;
import utils.UtilityTool;

public class TileManager {

	Game gp;
	public Tile[] tile;
	public int[][][] mapTileNum;

	public TileManager(Game game) {
		this.gp = game;
		tile = new Tile[30];
		mapTileNum = new int[game.maxMap][game.maxWorldCol][game.maxWorldRow];
		getTileImage();
		loadMap("/maps/map01.txt", 0);
		//loadMap("/maps/map2.txt", 1);
	}

	
	//get the tile to be the same size 32x32
	public void getTileImage() {
			setup(0, "hard-floor-1", false);
			setup(1, "hard-floor-2", false);
			setup(2, "hard-floor-3", false);
			setup(3, "hard-floor-4", false);
			setup(4, "hard-floor-5", false);
			setup(5, "hard-floor-6", false);
			setup(6, "hard-floor-7", false);
			setup(7, "hard-floor-8", false);
			setup(8, "wall-1", true);
			setup(9, "wall-2", true);
			setup(10, "boundaries-top", true);
			setup(11, "boundaries-right", true);
			setup(12, "boundaries-bot", true);
			setup(13, "boundaries-left", true);
			setup(14, "boundaries-top-solid", true);
			setup(15, "boundaries-right-solid", true);
			setup(16, "boundaries-bot-solid", true);
			setup(17, "boundaries-left-solid", true);
			setup(18, "boundaries-corner-3", true);
			setup(19, "boundaries-corner-4", true);
			setup(20, "boundaries-corner-1", true);
			setup(21, "boundaries-corner-2", true);
			setup(22, "bin", false);
			
	}
	
	public void setup(int index, String imagePath, boolean collision)	{
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imagePath + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}


	public void loadMap(String filePath, int map) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col=0;
			int row=0;

			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line =  br.readLine();

				while(col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");

					int num = Integer.parseInt(numbers[col]);

					mapTileNum[map][col][row] = num;
					col++;


				}

				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}

			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void draw(Graphics g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow) {
			
			int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

			if(worldX <= gp.player.worldX + gp.player.screenX + gp.tileSize &&
					worldX >= gp.player.worldX - gp.player.screenX -  gp.tileSize &&
					worldY <= gp.player.worldY + gp.player.screenY +  gp.tileSize &&
					worldY >= gp.player.worldY - gp.player.screenY -  gp.tileSize) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);

			}
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
		
		
	}

}
