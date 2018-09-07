package hw3;
/**
 * Version 1.0
 * James Valles
 * Your homework is to complete the methods marked ToDoX.
 * You must not change the declaration of any method.
 */


/**
 *  The B(inary)S(earch)T(ree) class represents a symbol table of
 *  generic key-value pairs.  It supports put, get, and delete methods.
 *  
 *  the book's recursive versions of get and put have been renamed 
 *  rGet  and rPut 
 *  to facilitate testing of your non-recursive versions
 *  
 */


public class BST403<Key extends Comparable<Key>, Value> {
	private Node root;             // root of BST

	private class Node {
		private Key key;           // sorted by key
		private Value val;         // associated data
		private Node left, right;  // left and right subtrees

		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;
		}

		/**
		 * Appends the preorder string representation of the sub-tree to the given StringBuilder.
		 */
		public void buildString(StringBuilder s) {
			s.append(left == null ? '[' : '(');
			s.append(key + "," + val);
			s.append(right == null ? ']' : ')');
			if (left != null) left.buildString(s);
			if (right != null) right.buildString(s);
		}
	}
	/**
	 * Returns the preorder string representation of the BST.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		root.buildString(s);
		return s.toString();
	}

	/**
	 * Initializes an empty symbol table.
	 */
	public BST403() {
	}

	/* 
	 * return the size of the tree
	 */
	public int size() {
		return (size(root));
	}

	private int size(Node node) {
		if(node == null) {
			return 0; }
		else {
			return size(node.left) + 1 + size(node.right); 
		}	
	}// TOdo 0


	/**
	 * Returns the value associated with the given key.
	 * Returns null if the key is not in the table
	 * 
	 * ToDo 1   write a non-recursive implementation of get
	 * 
	 */
	public Value get(Key key) {
		Node x = root;
		while (x != null)
		{
			int cmp = key.compareTo(x.key);

			if (cmp == 0) {
				return x.val;
			}
			else if (cmp < 0) {
				x = x.left;
			}
			else if (cmp > 0) {
				x = x.right;
			}
		}
		return null;  //Todo 1
	}


	/**
	 * Inserts the specified key-value pair into the symbol table, overwriting the old 
	 * value with the new value if the symbol table already contains the specified key.
	 * 
	 * ToDo 2   write a non-recursive implementation of put
	 * 
	 * 
	 */
	public void put(Key key, Value val) {
		Node x = root;
		Node newNode = new Node(key, val);
		Node parentNode = null;
	

		if (root == null) {
			root = newNode;
			return;
		}

		while (x != null) {
			parentNode = x;
			int res = key.compareTo(x.key);
			if (res < 0) {
				x = x.left;
			}
			else if	(res > 0) {
				x = x.right;
			}
			else {
				x.val = val;
				return;
			}  
		}
		int cmp = key.compareTo(parentNode.key);

		if (cmp < 0) { 
			parentNode.left  = newNode;
		}
		else   {
			parentNode.right = newNode;
		}
	}


	/**
	 * deletes the key (&value) from the table if the key is present
	 * using the the dual of the Hibbard approach from the text. That is, 
	 * for the two-child scenario, delete the node by replacing it 
	 * with it's predecessor (instead of its successor)
	 * 
	 * ToDo 3:  implement a version of delete meeting the above spec
	 * delete max // mirror 
	 */
    public void delete(Key key) {
        root=delete(root,key);
        return;  // ToDo 3
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        return x;
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            Node t = x;
            x = max(t.left); 
            x.left = deleteMax(t.left);
            x.right = t.right;
        }
        return x;
    }



	/*
	 * equals determines if two BST403s are exactly the same:
	 * same key-value pairs, same structure
	 * recursion might be a good choice
	 * 
	 * NOT ToDo , but maybe think about how you would do it.
	 * 

	public boolean equals(BST403<Key,Value> x) {
		return false;  
	}
	 */

	/**
	 * Returns the number of leaf nodes in the tree
	 * 
	 * ToDo 4
	 */// ToDo 4
	public int numLeaves() {
		return numLeaves(root);
	}

	private int numLeaves(Node node) {
		if (node == null) {
			return 0; }
		
		if ( node.right == null && node.left == null ) {
			return 1; }
		else {
			return numLeaves(node.left) + numLeaves(node.right);
		}
}
	
	public int heightTree() {
		return heightTree(root);
	}

	
	private int heightTree(Node x) {
		if(x == null) {
			return -1; 
		}
		if (x.left == null && x.right==null) {
			return 0; 
		}
		
		return (1 + Math.max(heightTree(x.left), heightTree(x.right)));
	}

	/**
	 * Returns the length of the shortest path from root to a null node.
	 * 
	 * ToDo 5
	 */
    public int lenShortestPathToNull() {
        Node x = root;
        return lenShortestPathToNull(x);
        // ToDo 5
    }

    public int lenShortestPathToNull(Node x) {
    	int leftPath;
    	int rightPath;
    	if (x == null) {
    		return 0;
    	}
    	
    	if (x.left == null || x.right == null) {
    		return 0;
    	}

    	if(x.left != null) {
    		leftPath = lenShortestPathToNull(x.left); 
    	}
    	else {
    		leftPath = Integer.MAX_VALUE;
    	}


    	if(x.right != null) {
    		rightPath = lenShortestPathToNull(x.right); 
    	}
    	else {
    		rightPath = Integer.MAX_VALUE;
   	}
 	return 1 + Math.min(leftPath, rightPath);

    }
        	
	/**
	 * Verifies that 'this' is a valid binary search tree
	 * useful to verify that your version of delete works correctly
	 * ToDo 6
	 */
    public boolean isValidBST() {
        return isValidBST(root, null, null);//ToDo 6
    }

    private boolean isValidBST(Node x, Key minimum, Key maximum) {

    	
        if (x == null) {
            return true;
        }
        
   
        if (minimum != null && x.key.compareTo(minimum) <= 0) {
            return false;
        }
        
        if (maximum != null && x.key.compareTo(maximum) >= 0) {
            return false;
        }
        
        return isValidBST(x.left, minimum, x.key) && isValidBST(x.right, x.key, maximum);
    }





	/*****************************************************
	 * 
	 * Utility functions 
	 */


	public Value rGet(Key key) {
		return rGet(root, key);
	}
	private Value rGet(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) return rGet(x.left, key);
		else if (cmp > 0) return rGet(x.right, key);
		else              return x.val;
	}

	public void rPut(Key key, Value val) {
		if (key == null) throw new NullPointerException("first argument to put() is null");
		root = rPut(root, key, val);
	}

	private Node rPut(Node x, Key key, Value val) {
		if (x == null) return new Node(key, val);
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = rPut(x.left,  key, val);
		else if (cmp > 0) x.right = rPut(x.right, key, val);
		else              x.val   = val;
		return x;
	}

}