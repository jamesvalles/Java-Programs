/*
 * Author: James Valles 
 * DrawBullsEye (Drawing Pictures - 10 Points) 
 * CSC 402 
 */

package group1;
import stdlib.*;

public class DrawBullsEye {

	public static void main(String[] args) {
	
		// This draws the BullsEye	
	        StdDraw.setPenColor(StdDraw.GREEN);
	        StdDraw.filledCircle(0.5, 0.5, 0.5);
	        StdDraw.setPenColor(StdDraw.BLUE);
	        StdDraw.filledCircle(0.5, 0.5, 0.375);
	        StdDraw.setPenColor(StdDraw.RED);
	        StdDraw.filledCircle(0.5, 0.5, 0.25);
	        StdDraw.setPenColor(StdDraw.YELLOW);
	        StdDraw.filledCircle(0.5, 0.5, 0.125);       
	 
	}
}
