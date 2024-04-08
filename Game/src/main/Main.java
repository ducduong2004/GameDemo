package main;

import javax.swing.JFrame;

public class Main {
	
	public static JFrame window;
    
    public static void main(String[] args) {
        
        window = new JFrame("Hinh vuong di chuyen");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set close operation
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setResizable(false);
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gamePanel.gameSetup();
        gamePanel.startGameThread();
        
    }
    
  
}
