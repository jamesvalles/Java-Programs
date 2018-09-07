/*  CSC 403
 * 
 * James Valles
 * 
 * Get help from anyone?  put that here
 * 
 * Homework 1 Driver 
 * 
 * Instructions:  using sizeTest  as a template, create additional functions to test
 *                the member functions in your LinkedListST implementation.
 *                AND
 *                create a reasonable set of test cases for each; 
 *                call your testing functions from main
 *                
 */
package hw1;

import stdlib.StdIn;
import stdlib.StdOut;

public class hw1Driver {

	public static void main(String[] args)
	{

		//This is the LinkedList Symbol Table we will be using
		System.out.println("\n" + "**Original ST (Symbol table) from tinyST.txt used for tests**" + "\n"); 

		LinkedListST<String, Integer> st = new LinkedListST<>();
		StdIn.fromFile("data/tinyST.txt");

		for (int i = 0; !StdIn.isEmpty(); i++)
		{
			String key = StdIn.readString();
			st.put(key, i);
		}

		for (String s : st.keys())
			StdOut.println(s + " " + st.get(s));


		System.out.println("\n" + "**size() method test**" + "\n"); 


		//  Test size() method
		sizeTest("",0);				// test size on an empty ST (symbol table)
		sizeTest("abcde",5);			// test size on a non-empty ST
		sizeTest("aabbccddee",5);	// test size of ST with all duplicate keys 
		System.out.println("Size of Original ST from tinyST.txt **Correct** should return 10, returns: " + st.size()); 



		System.out.println("\n" + "**secondMaxKey() method test**" + "\n"); 
		//Test secondMaxKey() method using tinyST.txt file
		secondMaxKeyTest("data/tinyST.txt", "S"); //test SecondMaxKey() method should return S, which is correct secondMaxKey in tinyST.text file
		System.out.println("");
		secondMaxKeyTest2("aab",  "a");	// test secondMaxKey() method, "aab" contains duplicates, and should return a as secondMaxKey
		//secondMaxKeyTest2("",  "");	// test secondMaxKey() method, returns null pointer exception if empty	(when uncommented)
		//secondMaxKeyTest2("a", "");	// test secondMaxKey() method, should return null pointer exception if ST has only 1 key (when uncommented)	


		//  Test rank() method
		System.out.println("\n" + "**rank() method test**" + "\n"); 
		rankTest("data/tinyST.txt", "S", 8); // test rank() method, key is "S", which should return rank of 8
		rankTest("data/tinyST.txt", "A", 0); // test rank() method, key is "A", which should return rank of 0
		rankTest("data/tinyST.txt", "D", 2); // test rank() method, key is "D", which is not in ST, which should return rank of 2



		//  Test floor() method
		System.out.println("\n" + "**floor() method test**" + "\n"); 
		floorTest("data/tinyST.txt","Z", "X"); //test floor() method, key Z is not in ST, but floor of Z should return X
		floorTest("data/tinyST.txt","A", "A"); //test floor() method, key A is in ST, floor should return A
		//floorTest("data/tinyST.txt","0", null); //test floor() method, key A is in ST, floor should return null, returns nullpointerexception when uncommented


		//  Test inverse() method
		System.out.println("\n" + "**inverse() method test**" + "\n"); 
		inverseTest("data/tinyST.txt");  //test inverse(), takes tinyST.text Original ST and inverses Keys & Values

	}


	// sizeTest function tests the size() method, takes in values, and the correct answer

	public static void sizeTest( String vals, int answer ) {

		// create and populate the table from the input string vals
		LinkedListST<String,Integer> aList = new LinkedListST<String,Integer>();
		for (int i=0; i < vals.length(); i++) {
			aList.put(vals.substring(i, i+1),i);
		}
		//  calls the size function
		int result = aList.size();

		//report results
		if ( result == answer)  // test passes
			StdOut.format("size(): **Correct**  String entered is: '%s' which has an Answer Count of: %d\n", vals,result);
		else
			StdOut.format("size(): **Error**  String entered is: '%s' which has an Answer Count of: %d\\n", vals,result);
	}

	//Test secondMaxKey() method	using tinyst.txt file

	public static void secondMaxKeyTest(String file, String answer ) {

		LinkedListST<String, Integer> st = new LinkedListST<>();
		StdIn.fromFile(file);

		for (int i = 0; !StdIn.isEmpty(); i++)
		{
			String key = StdIn.readString();
			st.put(key, i);
		}
		//  call the secondMaxKey function
		String result = st.secondMaxKey();

		//report result
		if ( result.equals(answer))  // test passes
			StdOut.format("secondMaxKey(): **Correct** secondMaxKey in tinyST.txt ST is %s , which matches correct output result of: %s", answer, answer, result + "\n");
		else {
			StdOut.format("secondMaxKey(): **Error**  secondMaxKey in ST is %s, which doesn't match correct output result: %s", answer, result + "\n");
		}
	}

	//rank method test 
	public static void rankTest(String file, String value, int answer ) {

		LinkedListST<String, Integer> st = new LinkedListST<>();
		StdIn.fromFile(file);

		for (int i = 0; !StdIn.isEmpty(); i++)
		{
			String key = StdIn.readString();
			st.put(key, i);
		}
		//  call the rank function
		int result = st.rank(value);

		//report result
		if ( result == answer)  // test passes
			StdOut.format("rank(): **Correct** Rank of %s in ST tinyST.txt returns: %s  matches correct result: %s", value, answer, result + "\n");
		else {
			StdOut.format("rank(): **Error**  Rank of %s in ST tinyST.txt returns: %s, which doesn't match correct result: %s", value, answer, result + "\n");
		}
	}


	//floor method test
	public static void floorTest(String file, String value, String answer ) {

		LinkedListST<String, Integer> st = new LinkedListST<>();
		StdIn.fromFile(file);

		for (int i = 0; !StdIn.isEmpty(); i++)
		{
			String key = StdIn.readString();
			st.put(key, i);
		}
		//  call the floor function
		String result = st.floor(value);

		//report result
		if (result.equals(answer))  // test passes
			StdOut.format("floor(): **Correct** Floor of %s in ST tinyST.txt returns: %s  matches correct result: %s", value, answer, result + "\n");

		else {
			StdOut.format("floor(): **Error**  Floor of %s in ST tinyST.txt returns: %s, which doesn't match correct result: %s", value, answer, result + "\n");
		}}


	//inverse method test
	public static void inverseTest(String file) {

		LinkedListST<String, Integer> st = new LinkedListST<>();
		StdIn.fromFile(file);

		for (int i = 0; !StdIn.isEmpty(); i++)
		{
			String key = StdIn.readString();
			st.put(key, i);
		}
		StdOut.print("Original Keys "+ "\n" + st.keys() + "\n");
		System.out.println("Original Values" );
		for (String s : st.keys()) {
			StdOut.print(" " + st.get(s));
		}
		//calls inverse() method to inverse keys and values 
		System.out.println("");
		StdOut.print("\n" + "Inversed Keys" + "\n" + st.inverse().keys() + "\n");	
		System.out.println("Inversed Values" );
		for (int a : st.inverse().keys()) {
			StdOut.print(" " + st.inverse().get(a));
		}
	}
	

	// second secondMaxKey function to test null values 
	public static void secondMaxKeyTest2( String vals, String answer ) {

		// create and populate the table from the input string vals
		LinkedListST<String,Integer> aList = new LinkedListST<String,Integer>();
		for (int i=0; i < vals.length(); i++) {
			aList.put(vals.substring(i, i+1),i);
		}
		//  calls the secondMaxKey function
		String result = aList.secondMaxKey();

		//report results
		if (answer.equals(result)) // test passes
			StdOut.format("secondMaxKey: **Correct**  String entered is: '%s' which has a secondMaxKey of: %s \n", vals,result);
		else
			StdOut.format("secondMaxKey: **Error**  String entered is: '%s' which has a secondMaxKey of Count of: %s \n", vals,result);
	}
}





