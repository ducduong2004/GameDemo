package tile;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int[][] mapTileNum;

	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/maps/map01.txt");
	}

	
	//get the tile to be the same size 32x32
	public void getTileImage() {
			setup(0, "hard-floor-1", false);
			setup(1, "hard-floor-2", false);
			setup(2, "hard-floor-3", false);
			setup(3, "hard-floor-4", true);
			setup(4, "bin", false);
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


	public void loadMap(String filePath) {
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

					mapTileNum[col][row] = num;
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
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
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
