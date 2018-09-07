package hw5;  // change this if you want
import stdlib.*;
import algs41.BreadthFirstPaths;
import algs41.Graph;
import algs41.GraphGenerator;

//  Version 1.0 
//
//This is basically exercise 4.1.16 from the text
//   see the exercise for definitions and hints
//
//  The provided structure follows the design pattern illustrated
//  by the examples in 4.1
//
// you're free to add instance variables and other methods
// you'll probably want to add in code to support bfs or dfs; 
//     feel free to grab and adapt these from the text and/or algs41
//  you might find queue or stack to be useful, if so I'd suggest you use
//  the versions from algs13
//


public class CHWK6 {
	int[] eccentricity;  // the eccentricity of each vertex
	int diameter;        // the diameter of the graph
	int radius;	         // the radius of the graph
	boolean [] isAcenter;       // the set of all 
	int center;

	// Constructor can be linear in G.V() * G.E()
	public CHWK6(Graph G) {

		this.eccentricity = new int[G.V()];
		int diameter = Integer.MIN_VALUE;
		int radius = Integer.MAX_VALUE;
		int center = -1;
		// If G.V()==0, then these are the correct values.
		// If G is not connected, you should throw a new IllegalArgumentException()
		// I suggest that you first get it to work for a connected graph
		// This will require that you traverse the graph starting from some node (say 0)
		// You can then adjust your code so that it throws an exception in the case that all nodes are not visited

		// TODO
		// compute the eccentricity of each vertex, the diameter & radius 

		BreadthFirstPaths bfs = new BreadthFirstPaths(G,0);
bfs.distTo(center);
		for(int i=0; i < bfs.distTo.length;i++) {
			if(!bfs.marked[i]){
				throw new IllegalArgumentException();
			}
		}
	

		//filling eccentricity array with corresponding values

		for(int i = 0;i < G.V(); i++){
			eccentricity[i] = computeEccentricity(G,i);
			radius = min(radius,eccentricity[i]);
			diameter = max(diameter,eccentricity[i]);

		}

		this.diameter = diameter;
		this.radius   = radius;
		this.center = center; 
	}

	private int computeEccentricity(Graph G,int v){

		BreadthFirstPaths bfs = new BreadthFirstPaths(G,v);
		int ec = Integer.MIN_VALUE;
		
		for(int i = 0; i < bfs.distTo.length; i++){
			ec = max(bfs.distTo[i], ec);

		}

		return ec;

	}




	// Do not change the following constant time methods
	public int eccentricity(int v) { return eccentricity[v]; }
	public int diameter()          { return diameter; }
	public int radius()            { return radius; }
	public boolean isCenter(int v) { return eccentricity[v] == radius; }

	public static void main(String[] args) {
		// comment in/out for testing
		//Graph G = new Graph(new In("data/tinyG.txt")); // this is non-connected -- should throw an exception
		//Graph G = GraphGenerator.connected (10, 20, 2); // Random non-connected graph -- should throw an exception

		//Graph G = new Graph(new In("data/tinyCG.txt")); // diameter=2, radius=2, every node is a center
		//Graph G = GraphGenerator.binaryTree (10); // A complete binary tree
		Graph G = GraphGenerator.path (10); // A path -- diameter=V-1
		//Graph G = GraphGenerator.connected (20, 400); // Random connected graph

		StdOut.println(G);
		G.toGraphviz ("g.png");  // comment out if you don't have graphViz installed

		CHWK6 edrc = new CHWK6(G);
		for (int v = 0; v < G.V(); v++)
			StdOut.format ("eccentricity of %d: %d\n", v, edrc.eccentricity (v));
		StdOut.format ("diameter=%d, radius=%d\n", edrc.diameter(), edrc.radius() );
		for (int i = 0; i < G.V(); i++) {
			if ( edrc.isCenter(i))
				StdOut.format ("center=%d\n", i);
		}
	}
}
