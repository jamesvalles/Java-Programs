/*  CSC 403
 * 
 * James Valles
 * 
              
 */
package hw1;

import algs13.Queue;
import stdlib.StdOut;

/**
 * Complete the methods marked TODO.
 * You must not change the declaration of any method.
 */

/**
 *  The LinkedListST class represents an (unordered) symbol table of
 *  generic key-value pairs.  It supports put, get, and delete methods.
 */
public class LinkedListST<Key extends Comparable<Key>, Value extends Comparable<Value>> {
	private Node first;      // the linked list of key-value pairs

	// a helper linked list data type
	private class Node {
		private Key key;
		private Value val;
		private Node next;

		public Node(Key key, Value val, Node next)  {
			this.key  = key;
			this.val  = val;
			this.next = next;
		}
	}

	/**
	 * Initializes an empty symbol table.
	 */
	public LinkedListST() {
	}

	/**
	 * Returns the value associated with the given key in this symbol table.
	 */
	public Value get(Key key) {
		if (key == null) throw new NullPointerException("argument to get() is null"); 
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key))
				return x.val;
		}
		return null;
	}

	/**
	 * Inserts the specified key-value pair into the symbol table, overwriting the old 
	 * value with the new value if the symbol table already contains the specified key.
	 * Deletes the specified key (and its associated value) from this symbol table
	 * if the specified value is null.
	 */
	public void put(Key key, Value val) {
		if (key == null) throw new NullPointerException("first argument to put() is null"); 
		if (val == null) {
			delete(key);
			return;
		}

		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.val = val;
				return;
			}
		}
		first = new Node(key, val, first);
	}

	/**
	 * Removes the specified key and its associated value from this symbol table     
	 * (if the key is in this symbol table).    
	 */
	public void delete(Key key) {
		if (key == null) throw new NullPointerException("argument to delete() is null"); 
		first = delete(first, key);
	}

	// delete key in linked list beginning at Node x
	// warning: function call stack too large if table is large
	private Node delete(Node x, Key key) {
		if (x == null) return null;
		if (key.equals(x.key)) {
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}

	/**
	 * size returns the number of key-value pairs in the symbol table.
	 * it returns 0 if the symbol table is empty.
	 */

	public int size () {
		int size = 1; 
		Node x = first; 

		if(x == null) {
			return 0; 
		}  	
		while(x.next != null) {
			x = x.next; //resets count
			size++; 		
		}
		return size;
	}
	// ToDo

	/**
	 * secondMaxKey returns the second maximum key in the symbol table.
	 * it returns null if the symbol table is empty or if it has only one key.
	 *  See if you can write it with only one loop
	 */
	public Key secondMaxKey () {

		Node currentNode = first;
		Node nextNode = first.next; 
		Key currentKey = currentNode.key;
		Key nextKey = nextNode.key; 

		Key maxKey = null;
		Key secondMaxKey = null; 

		if(currentNode == null || nextNode == null ) {
			return null; 
		}

		if(currentKey.compareTo(nextKey) > 0) {
			maxKey = currentKey;
			secondMaxKey = nextKey; } 
		else {
			maxKey = nextKey; 
			secondMaxKey = currentKey; 
		}

		for (Node i = nextNode.next; i != null; i = i.next) {
			if(i.key.compareTo(maxKey) > 0) {
				secondMaxKey = maxKey; 
				maxKey = i.key;
			}
			else if (i.key.compareTo(secondMaxKey) > 0 && i.key.compareTo(maxKey) < 0 ){
				secondMaxKey = i.key; }
		}
		return secondMaxKey;
	}


	/**
	 * rank returns the number of keys in this symbol table that is less than the given key.
	 * your implementation should be recursive. 
	 */
	public int rank (Key key) {
		return rankRecursive (first, key);
	}

	private int rankRecursive (Node x, Key key) {
		if(x != null)
		{
			if (x.key.compareTo(key) < 0){
				return rankRecursive(x.next,key) + 1;
			}
			else
				return rankRecursive(x.next,key);
		}
		return 0;
	}



	/**
	 * floor returns the largest key in the symbol table that is less than or equal to the given key.
	 * it returns null if there is no such key.
	 */    
	public Key floor(Key key) {
		Key maxKey = null;

		for (Node x = first; x != null; x = x.next) {
			int temp = x.key.compareTo(key);
			if (temp == 0) {
				return x.key;
			} else if (temp < 0) {
				if (maxKey == null) {
					maxKey = x.key;

				} else {
					if (x.key.compareTo(maxKey) > 0) {
						maxKey = x.key;
					}
				}
			}
		}
		return maxKey; //TODO
	}




	/**
	 * inverse returns the inverse of this symbol table.
	 * if the symbol table contains duplicate values, you can use any of the keys for the inverse
	 */
	public LinkedListST<Value, Key> inverse () {
		LinkedListST<Value,Key> invertedList = new LinkedListST<Value,Key>();
		for (Node x = first; x!= null; x= x.next) {
			invertedList.put(x.val, x.key);
		}
		return invertedList; 
		// TODO
	}


	public Iterable<Key>  keys() {
		Queue<Key> theKeys = new Queue<Key>();
		for ( Node temp = first; temp != null; temp=temp.next) {
			theKeys.enqueue(temp.key);
		}
		return theKeys;
	}
}