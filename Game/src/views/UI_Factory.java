package views;


import models.Game;

public interface UI_Factory {
    UI_Template createUI(Game gp);
}

