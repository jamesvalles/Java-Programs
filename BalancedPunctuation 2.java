/*
 * Author: James Valles 
 * CSC 402
 * Balanced Punctuation - Check to see if punctuation is balanced
 */


package group3;
import stdlib.*;
import java.util.*;

public class BalancedPunctuation {

	public static void main(String[] args) {
		StdOut.print("Please enter a string: ");
		String expression = StdIn.readLine();

		
		String tokens = expression;
		Stack<Character> new_stack = new Stack <Character> ();
		
		
		// Add each Character to Stack 
		for (int i = 0; i < tokens.length(); i++) {
			if (tokens.charAt(i) == '(' || tokens.charAt(i) == '{' || tokens.charAt(i) =='[') {
			    new_stack.push(tokens.charAt(i)); } 
			
		//Check to see if there is a match 
			else if (tokens.charAt(i) == ')' || tokens.charAt(i) == '}' || tokens.charAt(i) ==']') {
				if (new_stack.size() == 0) throw new RuntimeException ("Unbalanced String!");
				char test = new_stack.pop(); 
				if (  test == '(' && tokens.charAt(i) != ')') throw new RuntimeException ("Unbalanced String!");
				if (  test == '{' && tokens.charAt(i) != '}') throw new RuntimeException ("Unbalanced String!"); 
				if (  test == '[' && tokens.charAt(i) != ']') throw new RuntimeException ("Unbalanced String!");
			}
			
			}
		if (new_stack.size() > 0) {
			throw new RuntimeException ("Unbalanced String!");
		}else {
			System.out.println("Balanced Strings!");
		}
		}
	}    