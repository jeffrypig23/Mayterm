package application;

import java.util.Stack;

import javafx.collections.MapChangeListener;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class AudioPlayer {

	public static Media media;
	public static MediaPlayer player;
	public static Stack<String> queue = new Stack<String>();
	public static boolean isPlaying = false;
	public static double volume = 0.75d;

	public static void play() {
		if (queue.isEmpty()) {
			Display.root.getChildren().remove(Display.bars);
			Display.root.getChildren().add(Display.nothing);
			isPlaying = false;
		} else {
			media = new Media(String.format("file://%s", queue.pop().replace(" ", "%20")));
			media.getMetadata().addListener((MapChangeListener<String, Object>) change -> {
				// TODO Auto-generated method stub
				/*
				 * Key: artist 
				 * Changed value: Rezonate  
				 * Key: raw metadata 
				 * Changed value: {ID3=java.nio.HeapByteBufferR[pos=116 lim=10316 cap=10316]} 
				 * Key: album
				 * Changed value: Highlight - Single 
				 * Key: title 
				 * Changed value: Highlight 
				 */
				if (change.wasAdded()) {
					System.out.println(
							String.format("Key: %s\nChanged value: %s", change.getKey(), change.getValueAdded()));
					if (change.getKey().equals("title")) {
						// TODO: Display is not updating :(
						Display.title.setText(change.getValueAdded().toString());
						Display.title.setX(1012 - Display.title.getLayoutBounds().getWidth());
					}
					if (change.getKey().equals("artist")) {
						Display.author.setText(change.getValueAdded().toString());
						Display.author.setX(1012 - Display.author.getLayoutBounds().getWidth());
					}
				}
			});
			player = new MediaPlayer(media);
			player.setVolume(volume);

			player.play();
			isPlaying = true;

			Display.root.getChildren().remove(Display.nothing);
			Display.root.getChildren().add(Display.bars);

			player.setOnEndOfMedia(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					play();
					return;

				}

			});
			player.setAudioSpectrumNumBands(63); // 7
			player.setAudioSpectrumInterval(0.0167d);

			// player.setAudioSpectrumThreshold(-100);
			player.setAudioSpectrumListener(new AudioSpectrumListener() {

				@Override
				public void spectrumDataUpdate(double timestamp, double duration, float[] magnitudes, float[] phases) {
					// TODO Auto-generated method stub
					/*
					 * System.out.println(String.format("timestamp: %s\nmagnitides: %s", timestamp,
					 * Arrays.toString(magnitudes)));
					 */

					for (int i = 0; i < 63; i++) { // 7
						Rectangle bar = (Rectangle) Display.bars.getChildren().get(i);
						bar.setHeight((63 - magnitudes[i] * -1) * 4);
						/*
						 * Group tests = ((Group) Display.bars); Rectangle test = (Rectangle)
						 * tests.getChildren().get(57 - (i * 9)); if ((60 - magnitudes[i] * -1) < 2) {
						 * test.setHeight(2); } else { test.setHeight(Math.pow(60 - magnitudes[i], 2) /
						 * 50); }
						 */
					}

					/*
					 * for (int i = 0; i < 7; i++) { Group tests = ((Group) Display.bars); Rectangle
					 * test = (Rectangle) tests.getChildren().get(56 - (i * 9)); if ((60 -
					 * magnitudes[i] * -1) < 2) { test.setHeight(2); } else { test.setHeight((60 -
					 * magnitudes[i])); } }
					 * 
					 * for (int i = 0; i < 7; i++) { Group tests = ((Group) Display.bars); Rectangle
					 * test = (Rectangle) tests.getChildren().get(58 - (i * 9)); if ((60 -
					 * magnitudes[i] * -1) < 2) { test.setHeight(2); } else { test.setHeight((60 -
					 * magnitudes[i])); } }
					 */

				}

			});

		}
	}

	public static void pickSong() {

		FileChooser pickFile = new FileChooser();
		ExtensionFilter fileFilter = new ExtensionFilter("Music", "*.mp3", "*.m4a", "*.mp4", "*.m4v", "*.wav", "*.m3u8",
				"*.flv", "*.fxm", "*.aif", "*.aiff");
		pickFile.getExtensionFilters().addAll(fileFilter);
		String filePath;
		try {
			filePath = pickFile.showOpenDialog(null).getAbsolutePath().toString();
		} catch (NullPointerException a) {
			filePath = "";
		}

		if (!filePath.isEmpty()) {
			System.out.println(filePath);
			queue.addElement(filePath);
			System.out.println(queue.peek());
			if (!isPlaying) {
				play();
			}
		}

	}

}
