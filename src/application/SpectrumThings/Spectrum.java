package application.SpectrumThings;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.sound.sampled.UnsupportedAudioFileException;

import application.Audio.AudioFile;
import application.Audio.AudioPlayer;
import application.Audio.WaveFile;
import application.UI.CoverArt;
import application.UI.DisplayText;
import application.UI.MainDisplay;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class Spectrum {

	public static Group spectrum = new Group();

	public static void createSpectrum() {
		for (int i = 0; i < 63; i++) {
			Rectangle rectangle = new Rectangle(115 + (i * (16.65)), 356, 12, 2); // 1.9453968254
			rectangle.setStrokeType(StrokeType.CENTERED);
			spectrum.getChildren().add(rectangle);
		}
	}

	public static void clearSpectrum() {
		for (int i = 0; i < 63; i++) {
			Rectangle bar = (Rectangle) spectrum.getChildren().get(i);
			bar.setHeight(12);
		}
	}

	public static void disableSpectrum(boolean retry) {
		MainDisplay.root.getChildren().remove(spectrum);
		MainDisplay.root.getChildren().add(MainDisplay.nothing);
		if (!retry) {
			DisplayText.setArtist("No file currently selected");
			DisplayText.setTitle("Press \"O\" to select a file");
		}
		CoverArt.setArt(null);
	}

	public static void enableSpectrum() {
		MainDisplay.root.getChildren().remove(MainDisplay.nothing);
		MainDisplay.root.getChildren().add(spectrum);
	}

	public static void setupSpectrumMovement() {
		/*
		AudioEqualizer EQ = AudioPlayer.player.getAudioEqualizer();
		EQ.getBands().clear();
		// Note to self, gain is gain in volume, not what the current decible level is
		double startHz = 20;
		for (int i = 0; i < 7; i++) {
			System.out.println(startHz);
			EqualizerBand EQB = new EqualizerBand(startHz + ((4.29d)/2), 4.29d, 0);
			startHz = (EQB.getCenterFrequency() + (4.29d));
			EQ.getBands().add(EQB);
		}
		*/
		
		//EQB1.setBandwidth(4.29d);
		//EQB1.setCenterFrequency(22.145d);
		//EQB1.setGain(60);
		
		/*
		EqualizerBand EQB2 = new EqualizerBand();
		EQB2.setBandwidth(4.29);
		EQB2.setCenterFrequency(26.435);
		
		EqualizerBand EQB3 = new EqualizerBand();
		EQB3.setBandwidth(4.29);
		EQB3.setCenterFrequency(30.725);
		
		
		
		EQ.getBands().add(EQB1);
		EQ.getBands().add(EQB2);
		EQ.getBands().add(EQB3);
		EQ.setEnabled(true);
	*/
		AudioPlayer.player.setAudioSpectrumNumBands(63); // 63
		AudioPlayer.player.setAudioSpectrumInterval(0.033d); // 0.0167
		AudioPlayer.player.setAudioSpectrumThreshold(-60);
		//AudioPlayer.player.setAudioSpectrumListener(new SpectrumListener(12000));
		AudioPlayer.player.setAudioSpectrumListener(new SpectrumListener());
		
		WaveFile wav = null;
		try {
			wav = new WaveFile(new File(AudioFile.toFilePath(AudioPlayer.media.getSource())));
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(wav.getAudioFormat().getFrameRate()); // Gets the number of amplitudes in 1 second
		System.out.println(wav.getFramesCount()); // The totoal number of amplitudes
		int amp[] = new int[(int) wav.getAudioFormat().getFrameRate()];
		for (int i = 0; i < (int) wav.getAudioFormat().getFrameRate(); i++) {
		    int amplitude = wav.getSampleInt(i); // Gets the amplitude at a given value
		    amp[i] = amplitude;
		}
		System.out.println(Arrays.toString(amp));
		int greatest = 0;
		int least = 0;
		for (int i = 0; i < amp.length; i++) {
			if (amp[i] < least) {
				least = amp[i];
			}
			if (amp[i] > greatest) {
				greatest = amp[i];
			}
		}
		System.out.println("Largest: " + greatest);
		System.out.println("Smallest: " + least);
		System.out.println("Change: " + (greatest - least));
	}

}
