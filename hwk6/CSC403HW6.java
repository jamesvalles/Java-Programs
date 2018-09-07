package hwk6;  // change this if you want
import stdlib.*;
import algs41.BreadthFirstPaths;
import algs41.Graph;
import algs41.GraphGenerator;

public class CSC403HW6 {
	int[] eccentricity;  		// the eccentricity of each vertex
	int diameter;        		// the diameter of the graph
	int radius;	        			// the radius of the graph
	boolean [] isAcenter;       // the set of all, this was not needed. 
	int center;

	// Constructor can be linear in G.V() * G.E()
	public CSC403HW6(Graph G) {

		this.eccentricity = new int[G.V()];
		int diameter = Integer.MIN_VALUE;
		int radius = Integer.MAX_VALUE;
		int center = -1;

		// If G.V()==0, then these are the correct values.
		// I suggest that you first get it to work for a connected graph
		// This will require that you traverse the graph starting from some node (say 0)
		// You can then adjust your code so that it throws an exception in the case that all nodes are not visited

		// TODO
		// Compute the eccentricity of each vertex, the diameter & radius. 

		//Looping by vertex number and using BreadthFirstPaths from algs41 file. 
		for (int i = 0; i < G.V(); i++) {
			
			BreadthFirstPaths bfs = new BreadthFirstPaths(G,i);
			int maxIndex = 0;
			int maxDistance = 0;

				
			for(int v = 0; v < G.V(); v++)
			{
				// Here if G is not connected, throw a new IllegalArgumentException().
				if(!bfs.hasPathTo(v))
				{
					throw new IllegalArgumentException("Graph (G) is not connected!");
				}
				if(maxDistance < bfs.distTo(v))
				{
					maxDistance = bfs.distTo(v);
				}                        
			}
			eccentricity[i] = maxDistance;

			//Diameter
			if(diameter < eccentricity[i]) {
				diameter = eccentricity[i];
			}
			//Radius
			
			if(radius > eccentricity[i]) {
				radius = eccentricity[i];
				center = i;
			}
		}


		this.diameter = diameter;
		this.radius   = radius;
		this.center = center; 
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

		Graph G = new Graph(new In("data/tinyCG.txt")); // diameter=2, radius=2, every node is a center
		//Graph G = GraphGenerator.binaryTree (10); // A complete binary tree
		//Graph G = GraphGenerator.path (10); // A path -- diameter=V-1
		//Graph G = GraphGenerator.connected (20, 400); // Random connected graph
		//Graph G = GraphGenerator.complete (6);
		


		StdOut.println(G);
		G.toGraphviz ("g.png");  // comment out if you don't have graphViz installed

		CSC403HW6 edrc = new CSC403HW6(G);
		for (int v = 0; v < G.V(); v++)
			StdOut.format ("eccentricity of %d: %d\n", v, edrc.eccentricity (v));
		StdOut.format ("diameter=%d, radius=%d\n", edrc.diameter(), edrc.radius() );
		for (int i = 0; i < G.V(); i++) {
			if ( edrc.isCenter(i))
				StdOut.format ("center=%d\n", i);
		}
	}
}
