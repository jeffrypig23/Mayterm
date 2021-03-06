package javacode.Audio;

import javacode.Debugger;
import javacode.Main;
import java.util.Stack;

import javacode.Database.Database;
import javacode.UI.Artist;
import javacode.UI.Genre;
import javacode.UI.PauseText;
import javacode.UI.Settings;
import javacode.UI.Spectrum;
import javacode.UI.Title;
import javacode.UI.Volume;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Class responsible for the player actions such as: Pausing, playing, skipping,
 * stopping, and rotating the track order one a track has finished.
 * 
 * @author Spud
 *
 */
public class AudioPlayer {

	public static Media media;
	public static MediaPlayer player;
	public static Stack<String> queue = new Stack<String>(); // TODO 2: Maybe use Queue instead of Stack
	public static boolean isPlaying = false, isPaused = false, up = false;

	/**
	 * Plays the current track in the queue.
	 */
	public static void play() {
		// TODO: Cleanup

		if (!isPaused && !isPlaying) {

			player = new MediaPlayer(media);
			player.setVolume(Volume.volumeValue);
			
			String path = AudioFile.toFilePath(media.getSource());
			
			Title.setTitle(path);
			Artist.setArtist(path);

			if (Database.isInDatabase(AudioFile.toFilePath(media.getSource()))) {
				Debugger.d(AudioPlayer.class, "Already in database");
				Title.setTitle(Database.getTitle(path));
				Artist.setArtist(Database.getArtist(path));
				Genre.setGenre(Genre.genre.valueOf(Database.getGenre(path)).getColor());
			} else {
				Debugger.d(AudioPlayer.class, "Adding to database");
				Title.setTitle(getMetadata.getTitle(media.getSource()));
				Artist.setArtist(getMetadata.getArtist(media.getSource()));
				Genre.setGenre(Genre.genre.ELECTRONIC.getColor());
				Database.addTrack(path, Genre.genre.ELECTRONIC, getMetadata.getTitle(media.getSource()), getMetadata.getArtist(media.getSource()));
			}
			
			PauseText.fade();

			try {
				Spectrum.enableSpectrum();
			} catch (IllegalArgumentException Duplicate) {
				Spectrum.disableSpectrum(true);
				Spectrum.enableSpectrum();
			}

			player.setOnEndOfMedia(new Runnable() {
				@Override
				public void run() {
					if (!queue.isEmpty()) {
						rotate();
					} else {
						stop();
						isPlaying = false;
					}
				}
			});

			Settings.enableButtons(false);
			Spectrum.setupSpectrumMovement();

			player.play();
			isPlaying = true;
			isPaused = false;
			Main.mainStage.setTitle(
					String.format("Now playing: %s - %s", Database.getArtist(AudioFile.toFilePath(media.getSource())),
							Database.getTitle(AudioFile.toFilePath(media.getSource()))));

		} else if (isPaused && !isPlaying) {
			pause();
		}

	}

	/**
	 * Pauses the current track, or resumes it if it's already paused.
	 */
	public static void pause() {
		try {
			if (isPlaying) {
				player.pause();
				isPaused = true;
				isPlaying = false;
				Spectrum.clearSpectrum();
				Main.mainStage.setTitle(
						String.format("Paused: %s - %s", Database.getArtist(AudioFile.toFilePath(media.getSource())),
								Database.getTitle(AudioFile.toFilePath(media.getSource()))));
			} else {
				player.play();
				isPaused = false;
				isPlaying = true;
				Main.mainStage.setTitle(String.format("Now playing: %s - %s",
						Database.getArtist(AudioFile.toFilePath(media.getSource())),
						Database.getTitle(AudioFile.toFilePath(media.getSource()))));
			}
		} catch (NullPointerException NPE) {
			return;
		}
	}

	/**
	 * Skips the current track.
	 */
	public static void skip() {
		player.stop();
		isPaused = false;
		isPlaying = false;
		if (!queue.isEmpty()) {
			rotate();
		} else {
			stop();
		}
	}

	/**
	 * Stops the player
	 */
	public static void stop() {
		try {
			player.stop();
			isPlaying = false;
			isPaused = false;
			Spectrum.disableSpectrum(false);
			player.dispose();
			Settings.enableButtons(true);
			Main.mainStage.setTitle("");
		} catch (NullPointerException NPE) {
			return;
		}
	}

	/**
	 * Rotates the track queue, and then tries to play what's next
	 */
	public static void rotate() {
		stop();
		media = new Media(queue.get(0));
		queue.remove(0);
		play();
	}

}
