package javacode.SpectrumAlg;

import javacode.Main;
import javacode.Audio.AudioFile;
import javacode.Audio.AudioPlayer;
import javacode.UI.CoverArt;
import javacode.UI.Spectrum;
import javacode.UI.SpectrumDebug;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * The original spectrum algorithm.<br >
 * <br >
 * Yeah it's really simple, but its not accurate. That being said, if the
 * official one never gets finished, there's always this {@code Fallback}.<br >
 * <br >
 * <i>See what I did there? Look I'm funny.</i>
 * 
 * @author Spud
 *
 */
public class Default implements AudioSpectrumListener {

	@Override
	public void spectrumDataUpdate(double timestamp, double duration, float[] magnitudes, float[] phases) {

		if (timestamp > 16.00d && timestamp < 16.07d) {
			String src = AudioFile.toFilePath(AudioPlayer.media.getSource());
			CoverArt.setArt(src);
		}

		if ((AudioPlayer.media.getDuration().toSeconds() - timestamp) < 35.0d) {
			CoverArt.setArt(null);
		}

		for (int i = 0; i < magnitudes.length; i++) {
			// https://stackoverflow.com/questions/51769033/performance-issues-with-resizing-rectangles-in-javafx-on-windows-10?noredirect=1#comment90498621_51769033
			float height = ((63 - magnitudes[i] * -1) * 4);
			Rectangle bar = (Rectangle) Spectrum.spectrum.getChildren().get(62 - i);
			
			//Spectrum.spectrum.getChildren().parallelStream().forEach(Consumer<? super javafx.scene.shape.Rectangle> c -> {
				
			//});
			
			if (height != bar.getHeight()) {
				bar.setHeight(height);
				if (Main.debug) {
					try {
						Text text = (Text) SpectrumDebug.spectrumText.getChildren().get(i);
						text.setText(String.valueOf(String.format("%.2f", bar.getHeight())));
						text.setX(bar.getX());
						text.setY(bar.getY() - 15);
					} catch (IndexOutOfBoundsException foo) {
						// Do nothing
					}
				}
			}
			
		}

	}

}
