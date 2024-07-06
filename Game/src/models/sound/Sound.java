package models.sound;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	private Clip clip;
	private URL soundURL[] = new URL[20];
	private FloatControl floatControl;

	public int volumeScale = 2;
	public float volume;

	public Sound() {
		soundURL[0] = getClass().getResource("/sounds/theme.wav");
		soundURL[1] = getClass().getResource("/sounds/pickup.wav");
		soundURL[2] = getClass().getResource("/sounds/footstep.wav");
		soundURL[3] = getClass().getResource("/sounds/score.wav");
		soundURL[4] = getClass().getResource("/sounds/pickup.wav");

	}

	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();

		} catch (Exception e) {
		}

	}

	public void playMusic(int i) {
		setFile(i);
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stopMusic() {
		clip.stop();
	}

	public void playSFX(int i) {
		setFile(i);
		clip.start();
	}

	public void checkVolume() {
		switch (volumeScale) {
		case 0:
			volume = -80f;
			break;
		case 1:
			volume = -20f;
			break;
		case 2:
			volume = -10f;
			break;
		case 3:
			volume = -5f;
			break;
		case 4:
			volume = 0f;
			break;
		case 5:
			volume = 6f;
			break;
		}

		floatControl.setValue(volume);
	}


}
