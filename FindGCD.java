/*
 * Author: James Valles 
 * FindGCD - Calculating the GCD
 */

package group1;
import stdlib.StdIn;
import stdlib.StdOut;


public class FindGCD {

	//Euclid's algorithm
	public static int gcd(int p, int q)
	{
		if (q==0) return p; 
		int r = p % q;
		return gcd(q,r);			
	}
	
	//Prompt user for number inputs and return result
	 public static void main(String[] args) {
		StdOut.print("Please enter a positive integer: ");
		int p = Integer.parseInt(StdIn.readLine());
		StdOut.print("Please enter a positive integer: ");
		int q = Integer.parseInt(StdIn.readLine());
		StdOut.printf("The greatest common divisor (gcd) is:  %,d", gcd(p, q));
	}

}
