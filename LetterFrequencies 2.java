/*
 * Author: James Valles 
 * Letter Frequency - Counts 
 */

package group3;
import java.util.*;
import stdlib.*;



public class LetterFrequencies {
	
	 public static void main(String[] args) {
	       
	    	// Reads in file 
		 
		StdOut.println("Please enter the pathname or URL of the book file: ");
			String textSource = StdIn.readLine();
		
		final In in = new In(textSource);
	        if (!in.exists ()) {
        	StdOut.println("Unable to open the text source " + textSource);
            System.exit (1);
        		}
	    final String s = in.readAll();
	    
	    //Converts string to lowercase
		
	    String l_string =  s.toLowerCase();
		
	    
	    // Create empty counter array
	    int[] counter = new int[26];
	    
	    
	    // Gets character from string
	    char ch; 
      	for (int i = 0; i < l_string.length(); i++) {
      	 ch = l_string.charAt(i);
	    
      	// Letter counter
      	 
	    if (ch >= 'a' && ch <= 'z') {
  
	         int index = ch - 'a';
	         counter[index] += 1;
	         }
      	}
      	
	    // Prints results 
	    for (ch = 'a'; ch <= 'z'; ch++) {
	        int index = ch - 'a';
	        System.out.printf("%c \t %,10d \n", ch, counter[index]);
	        }       
	   }
  }

