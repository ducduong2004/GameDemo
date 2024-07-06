package views;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Shape;


import models.Game;
import models.entity.player.Player;

public class UI_LightOff implements UI_Template {

	Game game;
    BufferedImage darknessFilter;

    public UI_LightOff(Game game, int circleSize) {
		this.game = game;

        try {
            
            Player player = game.player;
			darknessFilter = new BufferedImage(game.screenWidth,game.screenHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = (Graphics2D) darknessFilter.createGraphics();
			
			//set the black retangle cover the whole screen to make darkness
			Area screenArea = new Area(new Rectangle2D.Double(0,0,game.screenWidth,game.screenHeight));
			
			//get the center coordinate of the light circle
			int centerX = player.screenX + ( game.tileSize ) / 2;
			int centerY = player.screenY + ( game.tileSize ) / 2;
			
			//get the topleft coordinate of the light circle
			double x = centerX - (circleSize/2);
			double y = centerY - (circleSize/2);	
			
			//create the circle light
			Shape circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);
			
			//subtract the light circle from the screen rectangle
			Area lightArea = new Area(circleShape);
			screenArea.subtract(lightArea);
			
			Color[] opacity = new Color[3];
			float[]	distance = new float[3];
			
			
			opacity[0] = new Color(0,0,0,0.2f);
			opacity[1] = new Color(0,0,0,0.8f);
			opacity[2] = new Color(0,0,0,0.9f);
			
			
			distance[0] = 0.7f;
			distance[1] = 0.8f;
			distance[2] = 1.0f;

			
			//create a gradaiton pain setting for the ligh circle
			RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, circleSize/2, distance, opacity);
			
			
			//set Gradient data;
			g2.setPaint(gPaint);
			
			g2.fill(lightArea);
			
	        g2.setColor(new Color(0, 0, 0,0.8f));
			g2.fill(screenArea);
			
			g2.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }



    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(darknessFilter, 0, 0, null);
    }



}
