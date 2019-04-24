package javacode.UI;

import javacode.GenreColors;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;

public class AlbumArt extends Rectangle {

	public AlbumArt() {

	}

	private AlbumArt(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	public AlbumArt createAlbumArt() {
		AlbumArt albumArt = new AlbumArt(1034, 198, 126, 126);

		albumArt.setColor(GenreColors.ELECTRONIC);
		return albumArt;
	}

	/**
	 * TODO Documentation
	 *
	 * @param color
	 */
	public void setColor(Color color) {
		this.setFill(color);
	}

	/**
	 * TODO Documentation
	 *
	 * @param color
	 */
	public void setColor(GenreColors color) {
		this.setColor(color.getColor());
	}

	/**
	 * TODO Documentation
	 *
	 * @param image
	 */
	public void setImage(Image image) {
		if (image != null) {
		monstercatPath.setContent("m 132.20444,165.70414 c 7.67389,-22.06285 4.37427,-33.89385 -3.31967,-40.45302 13.55041,-3.2729 25.31237,-6.52687 26.64091,-8.22958 6.42553,-8.26521 -0.0412,-29.880401 -12.18513,-27.566318 -4.2629,0.811822 -13.80209,5.225061 -24.36469,10.608258 3.06689,-4.955571 5.18609,-10.526965 6.15938,-16.501485 6.94782,-2.273993 12.36442,-9.961246 12.4034,-18.167443 0.007,-1.185995 -0.10468,-2.383126 -0.35302,-3.572462 -1.47219,-7.039133 -5.79077,-12.978018 -10.96238,-15.377848 -1.35081,-3.279583 -3.37535,-7.286354 -6.31974,-11.338783 -2.4054,-3.348627 -5.50792,-6.720639 -9.37437,-9.675048 -1.36529,-5.656028 -2.83748,-11.065948 -4.35979,-15.416825 -0.9822,-2.80073 -1.97888,-5.160471 -3.0112,-6.895476 C 102.09019,1.44324 101.06789,0.10245301 99.278324,0 99.162509,0 99.02108,0.01002001 98.855152,0.03341001 96.154647,0.58576101 94.289349,3.336379 92.263692,6.931113 c -1.492239,2.747278 -2.944387,6.109268 -4.295196,9.583733 -2.017863,-0.227177 -4.104769,-0.361924 -6.307491,-0.355242 -5.122608,0.0033 -10.72741,0.65703 -16.87454,2.11586 -5.624847,1.334106 -10.561483,3.036816 -14.932404,5.015702 -1.429876,-1.840798 -2.876456,-3.575803 -4.282946,-5.074723 -2.799617,-2.888706 -5.290764,-5.029065 -7.985701,-5.123723 -0.207132,0.0033 -0.414264,0.02561 -0.621395,0.05568 -1.478875,0.29288 -2.238357,1.502261 -2.807412,2.86532 -1.565737,3.887615 -2.034567,11.27308 -2.106951,19.75211 -1.790686,1.925433 -3.355309,3.911 -4.673824,5.91884 -6.385443,9.718479 -7.637141,19.771042 -7.637141,26.302368 -0.0067,0.20045 0,0.387536 0.0078,0.581304 -1.895365,3.372013 -3.022339,7.542485 -3.039043,11.879998 -0.0067,0.049 0,0.105793 0.0045,0.158132 0.139202,10.208468 8.914453,18.782156 17.719772,18.844518 0.0067,0 0.01336,0 0.02005,0.0067 2.076883,-0.0067 4.13929,-0.542329 6.060268,-1.640349 4.409898,8.146069 11.269739,14.763139 19.561683,18.939179 -19.709793,-0.81739 -43.201408,-1.36083 -45.699236,0.47662 -8.554756,6.29302 -8.554756,28.75677 3.805207,29.66103 5.258469,0.38865 19.458117,-2.34081 34.047529,-5.58365 -3.099178,4.45667 -5.684982,9.40222 -7.41776,14.68407 -20.949241,-2.61587 -34.524154,-1.76173 -41.9485952,2.54015 l 0.013363,0.02 c -1.7060513,0.99 -2.86643353,2.81298 -2.8742288,4.9333 0,3.17267 2.5679859,5.73843 5.7328671,5.73843 1.0512484,-0.003 2.0145215,-0.30513 2.8586383,-0.79178 l 0.010022,0.0134 c 3.8141156,-2.20272 12.9256776,-3.77403 34.0285966,-1.18043 -0.683756,11.48578 3.312992,23.8992 15.218602,35.94735 -26.242232,-12.90341 -42.79828,-4.32526 -30.988441,21.80561 13.498074,3.862 54.272924,-0.54233 58.310876,-3.29963 2.366422,-1.63033 -8.514666,-27.46832 -10.926747,-49.25165 1.735005,0.20935 3.394285,0.33853 4.958908,0.33185 6.905499,0.007 12.147264,-2.10695 15.64066,-6.29969 5.849798,-7.0202 4.349763,-17.38569 2.767322,-23.34462 27.067416,5.31415 13.607206,43.15129 17.537136,45.1402 10.34879,5.21615 36.11551,1.03232 49.00444,-4.34753 7.43335,-28.02179 -8.10152,-34.91392 -31.94838,-18.2454 z M 32.798002,61.099356 c -0.667053,-0.138087 -1.347469,-0.220494 -2.011181,-0.213813 -1.433217,0.02004 -2.809639,0.351901 -4.098087,0.923183 0.652576,-4.672709 2.204949,-10.114924 5.465601,-15.450232 0.140315,5.14488 0.383082,10.238535 0.643667,14.740862 z M 66.284271,24.599657 c 5.707254,-1.35415 10.809818,-1.942136 15.377848,-1.942136 1.393127,0.0067 2.72946,0.08241 4.022361,0.184859 -1.008931,2.96109 -1.906501,5.797456 -2.629234,8.202855 -0.880866,-0.04677 -1.758391,-0.128065 -2.639257,-0.124724 -7.808637,-0.0067 -15.14399,1.972204 -21.552819,5.438874 -1.457716,-2.307401 -3.151517,-4.923272 -4.982293,-7.552507 -0.03118,-0.04677 -0.0657,-0.09577 -0.09577,-0.144769 3.648188,-1.564623 7.783024,-2.932137 12.499165,-4.062452 z M 86.031927,159.17727 c -1.782891,2.11363 -6.059155,2.68157 -12.10606,1.66373 0.692665,-8.66277 3.924363,-15.43352 11.670638,-17.72422 1.474421,3.89764 3.379808,12.52812 0.435422,16.06049 z M 111.4434,99.409787 c -2.18824,-0.209359 -4.29185,-0.311811 -6.32753,-0.354129 L 99.844039,86.694581 97.528842,99.205996 c -8.741843,0.581305 -15.703022,2.474444 -20.962605,4.558014 l -7.59371,-9.314245 1.271743,12.292035 c -3.971135,2.20718 -5.952248,4.03573 -5.952248,4.03573 -6.648255,-6.68835 -11.98245,-18.22313 -14.411234,-23.262218 3.040156,-2.11586 6.247355,-3.976703 9.54921,-5.619279 l 8.227355,9.122698 -1.879775,-11.975768 c 9.578165,-3.860888 19.521593,-6.089222 28.251186,-7.360965 l 5.532417,10.106015 0.918729,-10.897793 c 6.15047,-0.638098 11.35548,-0.808481 14.8422,-0.825185 0.0813,14.643979 -2.30629,24.62527 -3.87871,29.344752 z m 1.67933,-62.436803 c 0.52896,0.650349 1.04456,1.29847 1.52787,1.952159 1.67821,2.310742 3.00452,4.611462 4.06022,6.754048 -1.14813,0.338538 -2.27287,0.89757 -3.33303,1.69937 -0.0679,-0.09243 -0.16259,-0.163701 -0.23497,-0.259471 -0.58019,-3.089156 -1.27063,-6.543576 -2.02009,-10.146106 z");
}
