/*
 * Author: James Valles 
 * CSC 402
 * PlaySong - Plays a series of frequencies from a file
 */


package group2;
import stdlib.*;


public class PlaySong {
	
	public static void playTone(double frequency, double duration) {
		final int sliceCount = (int) (StdAudio.SAMPLE_RATE * duration);
		final double[] slices = new double[sliceCount+1];
		for (int i = 0; i <= sliceCount; i++) {
			slices[i] = Math.sin(2 * Math.PI * i * frequency / StdAudio.SAMPLE_RATE);
		}
		StdAudio.play(slices);
	}
	//Set frequencies and lengths 
	public static void main(String[] args) {
		StdIn.fromFile("data/group2song.txt");
		double[] values= StdIn.readAllDoubles();	
		for (double value: values) {
			playTone(value, 0.25);
			}
			StdAudio.close();
	        System.exit(0);
		
	}
}