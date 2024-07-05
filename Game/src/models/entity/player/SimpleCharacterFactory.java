package models.entity.player;

import controller.input.KeyHandler;
import models.Game;

public class SimpleCharacterFactory {
	
	public Player createPlayer(int type, Game game, KeyHandler keyH) {
		Player player = null;
		
		if(type == 1) {
			System.out.println(2);
			player = Player1.getInstance(game, keyH);
		} else if(type == 2) {
			System.out.println(2);
			player = Player2.getInstance(game, keyH);
		} else if(type == 3) {
			System.out.println(2);
			player = Player3.getInstance(game, keyH);
		} 
			
		
		return player;
	}
}
