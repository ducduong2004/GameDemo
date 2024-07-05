
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

import controller.GameController;
import models.Game;
import views.GameView;

public class Main {

	public static JFrame window;
	static int count = 1;

	Main() {
		window = new JFrame("Lethal 2D" + " " + count);
		count++;
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set close operation
		window.setPreferredSize(new Dimension(1920 / 2, 1080 / 2));

		// turn this on if you want full screen game
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);

		window.setResizable(false);
		window.setLayout(new FlowLayout());

		Game game = new Game();
		GameController gameController = new GameController(game, null);
		GameView view = new GameView(gameController);
		gameController.setGameView(view);
		gameController.addObserver();

		window.add(view);

		window.setVisible(true);
		window.pack();
		window.setLocationRelativeTo(null);

		gameController.startGameThread();
	}

	public static void main(String[] args) {

		System.out.println("Present Project Directory : " + System.getProperty("user.dir"));

		List<Main> windows = new LinkedList<>();
		for (int i = 0; i < 1; i++) {
			windows.add(new Main());
		}
	}
}
