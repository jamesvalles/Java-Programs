// CSC 403 - James Valles
// version 1.0
//  Complete the implementation of the SortedArrayST 
//     by completing the TO DO items
//   This is a simple variation of the example from 3.2
//
//   You may not change the other methods without permission
//      if you want to do this, your probably on the wrong track
//
//   You may add additional methods for modularity
//   You may not use other Java data structures (e.g. ArrayList, HashSet, etc)

package hw2;   // change the package if you want

import java.util.Arrays;
import javax.management.RuntimeErrorException;
import stdlib.StdOut;

public class SortedArrayST<Key extends Comparable<Key>, Value> {
	private static final int MIN_SIZE = 2;
	private Key[] keys;      // the keys array
	private Value[] vals;    // the values array
	private int N = 0;       // size of the symbol table, 
	// may be different from the size of the arrays

	/**
	 * Initializes an empty symbol table.
	 */
	public SortedArrayST() {
		this(MIN_SIZE);
	}

	/**
	 * Initializes an empty symbol table of given size.
	 */
	@SuppressWarnings("unchecked")
	public SortedArrayST(int size) {
		keys = (Key[])(new Comparable[size]);
		vals = (Value[])(new Object[size]);
	}

	/**
	 * Initializes a symbol table with given sorted key-value pairs.
	 * If given keys list is not sorted in (strictly) increasing order,
	 * then the input is discarded and an empty symbol table is initialized.
	 */
	public SortedArrayST(Key[] keys, Value[] vals) {
		this(keys.length < MIN_SIZE ? MIN_SIZE : keys.length);
		N = (keys.length == vals.length ? keys.length : 0);
		int i;
		for (i = 1; i < N && keys[i].compareTo(keys[i - 1]) > 0; i++);
		if (i < N) { // input is not sorted
			System.err.println("SortedArrayST(Key[], Value[]) constructor error:");
			System.err.println("Given keys array of size " + N + " was not sorted!");
			System.err.println("Initializing an empty symbol table!");
			N = 0;
		} else {
			for (i = 0; i < N; i++) {
				this.keys[i] = keys[i];
				this.vals[i] = vals[i];
			}
		}
	}


	/**
	 * Returns the keys array of this symbol table.
	 */
	public Comparable<Key>[] keysArray() {
		return keys;
	}


	/**
	 * Returns the values array of this symbol table.
	 */
	public Object[] valsArray() {
		return vals;
	}


	/**
	 * Returns the number of keys in this symbol table.
	 */
	public int size() {
		return N;
	}


	/**
	 * Returns whether the given key is contained in this symbol table at index r.
	 */
	private boolean checkFor(Key key, int r) {
		return (r >= 0 && r < N && key.equals(keys[r]));
	}


	/**
	 * Returns the value associated with the given key in this symbol table.
	 */
	public Value get(Key key) {
		int r = rank(key);
		if (checkFor(key, r)) return vals[r];
		else return null;
	}


	/**
	 * Inserts the specified key-value pair into the symbol table, overwriting the old 
	 * value with the new value if the symbol table already contains the specified key.
	 * Deletes the specified key (and its associated value) from this symbol table
	 * if the specified value is null.
	 */
	public void put(Key key, Value val) {
		int r = rank(key);
		if (!checkFor(key, r)) {
			shiftRight(r);
			keys[r] = key;
		}
		vals[r] = val;
	}


	/**
	 * Removes the specified key and its associated value from this symbol table     
	 * (if the key is in this symbol table).    
	 */
	public void delete(Key key) {
		int r = rank(key);
		if (checkFor(key, r)) {
			shiftLeft(r);
		}
	}

	public boolean contains(Key key) {
		return ( this.get(key)!= null);
	}


	/**
	 * Shifts the keys (and values) at indices r and above to the right by one
	 * The key and value at position r do not change.
	 * This function must resize the keys,vals arrays as needed
	 * 
	 */
	private void shiftRight(int r) {
		if(N >= keys.length)
		{
			Key[] tempKeys = (Key[]) new Comparable[N + 1];      // the keys array
			Value[] tempVals = (Value[]) new Object[N + 1];    // the values array
			for(int i = 0; i< N; i++)
			{
				tempKeys[i] = keys[i];
				tempVals[i] = vals[i];
			}
			keys = tempKeys;
			vals = tempVals;
		}
		for(int i = N; i >= r;i--)
		{
			keys[i] = keys[i-1];
			vals[i] = vals[i-1];
		}
		N++; // TODO1

	}



	/**
	 * Shifts the keys (and values) at indices x > r to the left by one
	 * in effect removing the key and value at index r 
	 */
	private void shiftLeft(int r) {
		for(int i = r; i < N - 1; i++)
		{
			keys[i] = keys[i + 1];
			vals[i] = vals[i + 1];
		}
		N--;

	}



	/**
	 * rank returns the number of keys in this symbol table that is less than the given key. 
	 */
	public int rank(Key key) {
		int hi = N-1; 
		int r = 0; 
		while (r <= hi) {
			int midPoint = r + (hi-r) / 2;
			int cmp = key.compareTo(keys[midPoint]);
			if (cmp < 0) hi = midPoint - 1;
			else if (cmp > 0) r = midPoint + 1; 
			else return midPoint; 
		}
		return r; 
	}
	// TODO3 : logarithmic time implementation


	/**
	 * Linear time implementation of rank
	 */
	private int linearTimeRank(Key key) {
		int r;
		for (r = 0; r < N && key.compareTo(keys[r]) > 0; r++);
		return r;
	}



	// Compare two  ST for equality
	// TODO4
	public boolean equals(Object x) {
		@SuppressWarnings("unchecked")
		SortedArrayST<Key,Value> that = (SortedArrayST<Key,Value>) x;
		
		if (this.N != that.N) return false;
		for(int i=0;i<N;i++)
		{
			if(!(keys[i].equals(that.keys[i]) && (vals[i].equals(that.vals[i]))))
				return false;

		}		
		return true;
	}

	/**
	 * floor returns the largest key in the symbol table that is less than or equal to key.
	 * it returns null if there is no such key.
	 */
	public Key floor(Key key) {

		int r = rank(key); 

		int back = keys.length - 1;

		if (r > keys.length){
			return keys[back];
		}
		if(r >= 0 && r < keys.length) {
			if(key.compareTo(keys[r]) < 0) {
				if (r == 0 ) {
					return null;
				}
				return keys[r-1];}
			else if (key.compareTo(keys[r]) == 0) {
				return keys[r];
			}		
		}
		return null;
	}
	// TODO5





	/**
	 * countRange returns the number of keys within the range (key1, key2) (inclusive)
	 * note that keys may not be in order (key1 may be larger than key2)
	 */
	public int countRange(Key key1, Key key2) {
		int start = rank(key1);
		int end = rank(key2);
		int count = 0; 

		if(key1.compareTo(key2) > 0 ) {
			start = rank(key2);
			end = rank(key1);
		}

		if((checkFor(key2, end)) || checkFor(key2, start)){
			for (int i = start; i < end + 1 ; i++) {
				count++;
			}
		}
		else {
			for (int i = start; i < end   ; i++) {
				count++;
			}
		}
		return count; 
		// TODO6
	}


	/*
	 *    a Utility function used by the testing framework to
	 *    build and return a symbol table from a pair of strings.
	 *    The individual characters of each string are extracted as substrings of length 1
	 *    and then stored in parallel arrays.
	 *    The parallel arrays are used as input to the SortArrayST constructor
	 *    The characters in the keyData need to be in sorted order.
	 *    
	 */
	public static SortedArrayST<String,String> from (String keyData, String valData) {
		int n = keyData.length();
		if ( n != valData.length()) throw new NullPointerException(" from: mismatch sizes");
		String[] keys = new String[n];
		String[] vals = new String[n];
		for (int i=0; i < n; i++ ) {
			keys[i] = keyData.substring(i, i+1);  // ith key is ith char-string of keyData string
			vals[i] = valData.substring(i, i+1);  // ith key is ith char-string of valData string
		}
		return new SortedArrayST(keys,vals);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("\n **Rank tests** \n");
		// Testing the rank function
		testRank("A",0,"BDFK","1234"); // testing rank if Key is not in ST, but should be at very beginning before first element
		testRank("B",0,"BDFK","1234"); // testing rank if Key is in ST at very beginning
		testRank("C",1,"BDFK","1234"); // testing rank for Key not in ST 
		testRank("D",1,"BDFK","1234"); // testing rank for Key in ST 
		testRank("K",3,"BDFK","1234"); // testing rank for Key at the very end of ST 
		testRank("Z",4,"BDFK","1234"); // testing rank for Key not in ST, but should be at the very end

		System.out.println("\n **Delete tests, which also tests shiftLeft()** \n");
		// Testing the delete function  (actually testing your shiftLeft implementation)	
		testDelete("ABDFK","12345", "A","BDFK","2345");   // test delete key at very beginning
		testDelete("ABDFK","12345", "B","ADFK","1345");   // test delete key near beginning
		testDelete("ABDFK","12345", "K","ABDF","1234");   //test delete key at very end
		// TO DO   add two  additional test cases
		testDelete("ABDFK","12345", "Z","ABDFK","12345"); // test deleting key that is not in ST
		testDelete("ABDFK","12345", "F","ABDK","1235");   // test deleting key near end


		// TO DO   add two  additional test cases
		//    include comments to describe what your case is checking for

		System.out.println("\n **Put tests, which also tests shiftRight()** \n");
		testPut("AEIOU","13456", "B","2", "ABEIOU","123456"); //Test for adding key between two keys
		// TO DO   add three  additional test cases
		testPut("AEIOU","13456", "Z","8", "AEIOUZ","134568"); // test adding key at very end of ST
		testPut("AEIOU","13456", "A","1", "AEIOU","13456");   // test adding duplicate key-value
		testPut("AEIOU","13457", "P","6", "AEIOPU","134567"); // test adding key between two keys at end

		System.out.println("\n **Floor Tests** \n");
		testFloor("U", "U", "BEIOU", "12345"); 	// test floor of key which in ST at very end
		testFloor("B", "B", "BEIOU", "12345");   // test floor of key which is a key in ST at very beginning
		testFloor("P", "O", "BEIOU", "12345");   // test floor of key not in ST almost near beginning
		testFloor("F", "E", "BEIOU", "12345");   // testing floor of key not in ST almost near beginning
		testFloor("Z", "U", "BEIOU", "12345");   // testing floor of key not in ST but at very end of ST
		testFloor("A", null, "BEIOU", "12345");  // testing floor of key not in ST, which should return a null for floor

		System.out.println("\n **Range Count Tests** \n");
		rangeCountTest("1", "8", 5, "13568", "12345"); // testing rangeCount of entire ST 
		rangeCountTest("8", "1", 5, "13568", "12345"); //testing rangeCount of entire where first key is greater than second key
		rangeCountTest("1", "1", 1, "13568", "12345"); // testing rangeCount where key1 is the same as key2
		rangeCountTest("2", "7", 3, "13568", "12345"); //testing rangeCount of two keys not in ST
		rangeCountTest("7", "2", 3, "13568", "12345"); //testing rangeCount of two keys not in ST, where key1 is larger than key2
		rangeCountTest("0", "9", 5, "13568", "12345"); //testing rangeCount of entire symbol table, with two keys not in ST
		rangeCountTest("9", "0", 5, "13568", "12345"); //testing rangeCount of entire symbol table, with two keys not in ST, but where key1 is greater than key2

		System.out.println("\n **Equal Tests** \n");
		equalsTest("AEIOU","13456","AEIOU","13456", true); 		// testing equality of two ST when both ST are the same
		equalsTest("AEIOUZ","134567","AEIOU","13456", false);		// testing equality of two ST when vals in ST are different
		equalsTest("AEIOZ","13456","AEIOU","13456", false);		// testing equality of two ST when keys in ST are different
		equalsTest("AEIOZ","13456","AEIOU","13457", false);		// testing equality of two ST when both vals and keys in ST are different
		equalsTest("AEIOZ","13456","","", false);				// testing equality of two ST when one ST is completely empty



	}

	/*
	 * Test the rank function. 
	 * build a symbol table from the input key,val strings
	 * (keyData characters must be in sorted order)
	 * then call the rank function, compare to the expected answer
	 */
	public static void testRank(String theKey, int expected, String keyData, String valData) {
		SortedArrayST<String, String> x = from(keyData,valData);
		int actual = x.rank(theKey);
		if ( actual == expected)  // test passes
			StdOut.format("rankTest: **Correct**  String %s Key %s rank: %d\n", keyData, theKey, actual);
		else
			StdOut.format("rankTest: **Error**  String %s Key %s rank: %d\n", keyData, theKey, actual);

	}
	/*
	 * Test the Put function. 
	 * build a symbol table from the input key,val strings
	 * (keyData characters must be in sorted order)
	 * then call the rank function, compare to the expected answer
	 */
	public static void testPut(String keyInData, String valInData, 
			String putKey, String putVal, 
			String keyOutData, String valOutData) {
		SortedArrayST<String, String> actual = from(keyInData,valInData);
		SortedArrayST<String, String> expected = from(keyOutData, valOutData);
		actual.put(putKey, putVal);


		if ( actual.equals(expected))  // test passes
			StdOut.format("testPut: **Correct**  Before %s put:%s After: %s\n", keyInData, putKey, keyOutData);
		else
			StdOut.format("testPut: **Error**  Before %s put:%s After: %s\n", keyInData, putKey, keyOutData);

	}
	/*
	 * Test the delete function. 
	 * build a symbol table from the input key,val strings
	 * (keyData characters must be in sorted order)
	 * then call the rank function, compare to the expected answer
	 */
	public static void testDelete(String keyInData, String valInData, String delKey, 
			String keyOutData, String valOutData) {
		SortedArrayST<String, String> actual = from(keyInData,valInData);
		SortedArrayST<String, String> expected = from(keyOutData, valOutData);
		actual.delete(delKey);


		if ( actual.equals(expected))  // test passes
			StdOut.format("testDelete: **Correct**  Before %s delete:%s After: %s\n", keyInData, delKey, keyOutData);
		else
			StdOut.format("testDelete: **Error**  Before %s delete:%s After: %s\n", keyInData, delKey, keyOutData);

	}


	//floor Test

	public static void testFloor(String theKey, String expected, String keyData, String valData) {
		SortedArrayST<String, String> x = from(keyData,valData);
		String actual = x.floor(theKey);
		if ( actual == null){
			if ( actual == expected)  // test passes
				StdOut.format("floorTest: **Correct** Key Array: %s, Floor of %s should return a floor of: %s, Returned: %s\n", keyData, theKey, expected, actual);
		}

		else if ( actual.equals(expected))  // test passes
			StdOut.format("floorTest: **Correct** Key Array: %s, Floor of %s should return a floor of: %s, Returned: %s\n", keyData, theKey, expected, actual);
		else
			StdOut.format("floorTest: **Error** Key Array: %s, Floor of %s should return a floor of: %s, Returned: %s\n", keyData, theKey, expected, actual);

	}

	//rangeCount Test

	public static void rangeCountTest(String theKey, String theKey2, int expected, String keyData, String valData) {
		SortedArrayST<String, String> x = from(keyData,valData);
		int actual = x.countRange(theKey, theKey2);
		if ( actual == expected)  // test passes
			StdOut.format("rangeCountTest: **Correct** Key Array: %s, Range: %s to %s has a range count of %d\n", keyData, theKey, theKey2, actual);
		else

			StdOut.format("rangeCountTest: **Error**  Key Array: %s, Range: %s to %s has a range count of %d\n", keyData, theKey, theKey2, actual);

	}

	//equals Test 

	public static void equalsTest(String keyInData, String valInData,  String keyOutData, String valOutData, Boolean answer) {
		SortedArrayST<String, String> actual = from(keyInData,valInData);
		SortedArrayST<String, String> expected = from(keyOutData, valOutData);
		Boolean result = actual.equals(expected);
		if (result == answer)  // test passes
			StdOut.format("testEquality: **Correct** Symbol tables match: %s - %s, %s and %s, %s \n", result,  keyInData, valInData,  keyOutData, valOutData);
		else
			StdOut.format("testEquality: **Error**  Symbol tables match: %s - %s, %s and %s, %s \n", result,  keyInData, valInData,  keyOutData, valOutData);

	}


}