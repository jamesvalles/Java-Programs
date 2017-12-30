/*
 * Author: James Valles 
 * CSC 402
 * WordStatistics - Calculates average and median lengths of words from text file 
 */

package group2;

import java.util.Arrays;
import stdlib.*;


public class WordStatistics {

		// Compute Average
	
	   public static double computeAverage(String[] words) {
		double sum = 0;
	
		int[] wlength = new int[words.length];
	
		for (int i=0; i<words.length; i++) {
			wlength[i] = words[i].length();	
		  
			}
		for (int word: wlength) {
		sum += (double) word;	
			}
		double average = (double) sum / words.length;
		return average;

		}

		// Compute Median 
		
		public static int computeMedian(String[] words) {
		
			int[] wlength = new int[words.length];
		
			for (int i=0; i<words.length; i++) {
				wlength[i] = words[i].length();	
				  
				}
			
			Arrays.sort(wlength);
			
			if (wlength.length % 2 != 0) {
				int middle = wlength.length / 2;
				int median = wlength[middle];
				return median;	
		    		}
			else {
				int middle = (wlength.length / 2) + 1;
				int median = wlength[middle];
				return median;		
			}
	}
				
			//Main 
		
		public static void main(String[] args) {
			StdOut.println("Please enter the pathname or URL of the book file: ");
			String textSource = StdIn.readLine();
			
			final In in = new In(textSource);
	        if (!in.exists ()) {
	        	StdOut.println("Unable to open the text source " + textSource);
	            System.exit (1);
	        		}

	        final String bookText = in.readAll();
	        String[] bookWords = bookText.split("\\s+");
	       
	        double average = computeAverage(bookWords);
	        int median = computeMedian(bookWords);
	        StdOut.println("The average length of the words in " + textSource + " is: " + average);
	        StdOut.println("The median length of the words in " + textSource + " is: " + median);
			}

		}
