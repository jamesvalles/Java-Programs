/*
 * Author: James Valles 
 * CSC 402
 * Count Days - Counts days between two dates 
 */

package group3;
import stdlib.StdIn;
import stdlib.StdOut;

public class CountDays {

	public static void main(String[] args) {
		StdOut.print("Enter your start date: ");
		Group3Date startDate = new Group3Date(StdIn.readString());
		StdOut.print("Enter your end date: ");
		Group3Date endDate = new Group3Date(StdIn.readString());
		int count = startDate.countDaysUntil(endDate);
		StdOut.print(count + " and ");
		count = endDate.countDaysUntil(startDate);
		StdOut.println(count);
		

	}

}
