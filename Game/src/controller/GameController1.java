package controller;

import models.Subject;
import views.Observer;

public class GameController1 extends Subject {
    
    private boolean gameRunning;
    private int score;

    public GameController1() {
        this.gameRunning = false;
        this.score = 0;
    }

    // Additional implementation for GameController
    public void startGame() {
        this.gameRunning = true;
        notifyObservers(); // Notify observers when game starts
    }

    public void endGame() {
        this.gameRunning = false;
        notifyObservers(); // Notify observers when game ends
    }

    public void incrementScore(int points) {
        this.score += points;
        notifyObservers(); // Notify observers when score changes
    }

    public int getScore() {
        return this.score;
    }

    public boolean isGameRunning() {
        return this.gameRunning;
    }

    // Other methods specific to GameController
}
