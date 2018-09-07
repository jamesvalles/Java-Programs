package hw5;
import algs34.SeparateChainingHashST;
import stdlib.In;
import stdlib.StdOut;
import stdlib.Stopwatch;



public class testSeperateChaining {


	public static void main(String[] args) {
		int minlen = 8; String file = "data/leipzig1M.txt";
		
		System.out.println("\nTest SeparateChainingHashST Report\n");
		System.out.println("\nCreating/Populating Hashtable Time & N + 1st Time\n");
		buildInititalHashtable(minlen, file);

		System.out.println("\nget() for hashtable size N Time\n");
		testGet(minlen, file);
		
		System.out.println("\nget() & put() mixrture Time\n");
		getPutMixed(minlen, file);
	}

	private static void buildInititalHashtable(int minlen, String file) {
		SeparateChainingHashST<MyHTClass, Integer> st = new SeparateChainingHashST<>();

		In in = new In (file);
		while (!in.isEmpty()) {
			MyHTClass key = new MyHTClass( in.readString(), 0);
			if (key.getkey().length() < minlen) continue;
			if (st.contains(key)) { st.put(key, st.get(key) + 1); }
			else                  { st.put(key, 1); }
		}

		int reps = 10; 
		for (int N=1024; N < 300000; N*=2) {
			Stopwatch sw = new Stopwatch();
			for (int r = 1; r <= reps; r++) {
				SeparateChainingHashST<MyHTClass, Integer> firstTest = new SeparateChainingHashST<>();

				int count = 1; 
				for (MyHTClass key: st.keys()) {
					firstTest.put(key,  0); 
					if (count ++ == N) break; 
				}
			}
			StdOut.format("Table size: %d   creating/populating time: %.4f\n",  N, (sw.elapsedTime() / reps));		
			StdOut.format("Table size: %d   put N+1st time: %.9f\n\n",  N, (sw.elapsedTime() / (reps * N)));	
		}
	}
	
	
	private static void testGet(int minlen, String file) {
		SeparateChainingHashST<MyHTClass, Integer> st = new SeparateChainingHashST<>();

		In in = new In (file);
		while (!in.isEmpty()) {
			MyHTClass key = new MyHTClass( in.readString(), 0);
			if (key.getkey().length() < minlen) continue;
			if (st.contains(key)) { st.put(key, st.get(key) + 1); }
			else                  { st.put(key, 1); }
		}

		int reps = 1000; 
		double time = 0;
		for (int N=1024; N < 300000; N*=2) {
			Stopwatch sw = new Stopwatch();
			for (int r = 1; r <= reps; r++) {
				SeparateChainingHashST<MyHTClass, Integer> firstTest = new SeparateChainingHashST<>();

				int count = 1; 
				
				for (MyHTClass key: st.keys()) {
				
					st.get(key);
					time = sw.elapsedTime();
					if (count ++ == N) break; 
				}
				
			}
			StdOut.format("Table size: %d   get() size N Time: %.9f\n",  N, (time / (reps)));		
			
		}
	}
		
		private static void getPutMixed(int minlen, String file) {
			SeparateChainingHashST<MyHTClass, Integer> st = new SeparateChainingHashST<>();

			In in = new In (file);
			while (!in.isEmpty()) {
				MyHTClass key = new MyHTClass( in.readString(), 0);
				if (key.getkey().length() < minlen) continue;
				if (st.contains(key)) { st.put(key, st.get(key) + 1); }
				else                  { st.put(key, 1); }
			}

			int reps = 1000; 
			double time = 0;
			for (int N=1024; N < 300000; N*=2) {
				Stopwatch sw = new Stopwatch();
				for (int r = 1; r <= reps; r++) {
					SeparateChainingHashST<MyHTClass, Integer> firstTest = new SeparateChainingHashST<>();

					int count = 1; 
					
					for (MyHTClass key: st.keys()) {
						firstTest.put(key,  0); 
						st.get(key);
						
						if (count ++ == N) break; 
					}
					
				}
				time = sw.elapsedTime();
				StdOut.format("Table size: %d   get()/put() intermixed size N Time: %.4f\n",  N, (time / (reps)));		
				
			}
	}


	
	}


