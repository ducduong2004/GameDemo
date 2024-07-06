package views;

import models.Game;

public class UIFactory implements UI_Factory {
    @Override
    public UI_Template createUI(Game gp) {
        // Logic to decide which UI to create based on game state
        if (gp.getGameState() == Game.GameState.WON) {
            return new UI_Win(gp);
        } else if (gp.getGameState() == Game.GameState.LOST) {
            return new UI_Lost(gp);
        } else {
            return new UI(gp);
        }
    }
}
