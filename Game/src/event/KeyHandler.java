package controller.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.text.html.parser.Entity;

import models.Game;

public class KeyHandler implements KeyListener {
	Game gp;

	public boolean upPressed;
	public boolean downPressed;
	public boolean leftPressed;
	public boolean rightPressed;
	public boolean enterPressed;
	public boolean interaction;

	public KeyHandler(Game gp) {
		this.gp = gp;
	}

	public boolean isUpPressed() {
		return upPressed;
	}

	public boolean isDownPressed() {
		return downPressed;
	}

	public boolean isLeftPressed() {
		return leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public boolean isInteractionPressed() {
		return interaction;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Unused
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		// TITLE STATE
		if (gp.gameState == gp.titleState) {
			titleState(code);
		}

		// WIN STATE
		else if (gp.gameState == gp.winTitleState) {
			winTitleState(code);
		}

		// PLAYER SELECT STATE
		else if (gp.gameState == gp.playerSelectState) {
			playerSelectState(code);
		}

		// PAUSE STATE
		else if (gp.gameState == gp.pauseState) {
			pauseState(code);
		}

		// OPTION STATE
		else if (gp.gameState == gp.optionState) {
			optionState(code);
		}

		// PLAY STATE
		else if (gp.gameState == gp.playState) {
			playState(code);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			upPressed = false;
		} else if (code == KeyEvent.VK_A) {
			leftPressed = false;
		} else if (code == KeyEvent.VK_S) {
			downPressed = false;
		} else if (code == KeyEvent.VK_D) {
			rightPressed = false;
		} else if (code == KeyEvent.VK_E) {
			interaction = false;
		}
	}

	public void titleState(int code) {
		if (code == KeyEvent.VK_W && gp.ui.commandNum > 0)
			gp.ui.commandNum--;
		if (code == KeyEvent.VK_S && gp.ui.commandNum < 2)
			gp.ui.commandNum++;
		if (code == KeyEvent.VK_ENTER) {
			if (gp.ui.commandNum == 0) {
				gp.setGameState(gp.playState);
			}
			if (gp.ui.commandNum == 1) {
				gp.setGameState(gp.playerSelectState);
			}
			if (gp.ui.commandNum == 2) {
				System.exit(0);
			}
		}
	}

	public void winTitleState(int code) {
		if (code == KeyEvent.VK_W && gp.ui_Win.commandNum > 0)
			gp.ui_Win.commandNum--;
		if (code == KeyEvent.VK_S && gp.ui_Win.commandNum < 1)
			gp.ui_Win.commandNum++;
		if (code == KeyEvent.VK_ENTER) {
			if (gp.ui_Win.commandNum == 0) {
				gp.setGameState(gp.titleState);
				gp.resetGame();
			}
			if (gp.ui_Win.commandNum == 1) {
				System.exit(0);
			}
		}

	}

	public void playerSelectState(int code) {
		if (code == KeyEvent.VK_1) {
			gp.playerType = gp.playerType_01;
			gp.setPlayer();
			gp.setGameState(gp.titleState);
		}
		if (code == KeyEvent.VK_2) {
			gp.playerType = gp.playerType_02;
			gp.setPlayer();
			gp.setGameState(gp.titleState);
		}
		if (code == KeyEvent.VK_3) {
			gp.playerType = gp.playerType_03;
			gp.setPlayer();
			gp.setGameState(gp.titleState);
		}
	}

	
	public void pauseState(int code) {
		if (code == KeyEvent.VK_SPACE) {
			gp.gameState = gp.playState;
		}
	}


	// MAIN TITLE SELECTION
	private void optionState(int code) {
		if (code == KeyEvent.VK_ESCAPE) {
			gp.setGameState(gp.playState);
		}
		if (code == KeyEvent.VK_W && gp.ui_OptionState.commandNum > 0) {
			gp.ui_OptionState.commandNum--;
		} else if (code == KeyEvent.VK_S && gp.ui_OptionState.commandNum < 3) {
			gp.ui_OptionState.commandNum++;
		} else if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}

		else if (code == KeyEvent.VK_A) {
			if (gp.ui_OptionState.commandNum == 0 && gp.music.volumeScale > 0) {
				gp.music.volumeScale--;
				gp.music.checkVolume();
			} else if (gp.ui_OptionState.commandNum == 1 && gp.sfx.volumeScale > 0) {
				gp.sfx.volumeScale--;
				gp.sfx.playSFX(3);
			}

		} else if (code == KeyEvent.VK_D) {
			if (gp.ui_OptionState.commandNum == 0 && gp.music.volumeScale < 5) {
				gp.music.volumeScale++;
				gp.music.checkVolume();
			} else if (gp.ui_OptionState.commandNum == 1 && gp.sfx.volumeScale < 5) {
				gp.sfx.volumeScale++;
				gp.sfx.playSFX(3);
			}

		}
	}

	// PLAYER MOVEMENT
	public void playState(int code) {
		if (code == KeyEvent.VK_W) {
			upPressed = true;
		} else if (code == KeyEvent.VK_A) {
			leftPressed = true;
		} else if (code == KeyEvent.VK_S) {
			downPressed = true;
		} else if (code == KeyEvent.VK_D) {
			rightPressed = true;
		} else if (code == KeyEvent.VK_E) {
			interaction = true;
		}

		if (code == KeyEvent.VK_F) {
			gp.player.skillBehavior.useSkill();
		}

		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}

		// HOT BAR SELECTION
		if (code == KeyEvent.VK_1) {
			gp.ui_HUD.slotX = 0;
			gp.player.slot = 0;
		} else if (code == KeyEvent.VK_2) {
			gp.ui_HUD.slotX = 1;
			gp.player.slot = 1;
		} else if (code == KeyEvent.VK_3) {
			gp.ui_HUD.slotX = 2;
			gp.player.slot = 2;
		} else if (code == KeyEvent.VK_4) {
			gp.ui_HUD.slotX = 3;
			gp.player.slot = 3;
		}

		// PAUSE
		if (code == KeyEvent.VK_SPACE) {
			gp.setGameState(gp.pauseState);
		}

		// OPTION
		if (code == KeyEvent.VK_ESCAPE) {
			gp.setGameState(gp.optionState);
		}
	}

}
