package hw5;
import algs34.LinearProbingHashST;
import stdlib.In;
import stdlib.StdOut;
import stdlib.Stopwatch;
public class testLinearProbing {


	public static void main(String[] args) {
		//int minlen = 2; String file = "data/tinyTale.txt";
		//int minlen = 6; String file = "data/tale.txt";
		int minlen = 12; String file = "data/leipzig1M.txt";
		
		double result2 = standardInOut(minlen, file);
		//double result1 = putTime(minlen, file);
		double result3 = getTime(minlen, file);
		
		//System.out.println("Total time: put + IO: " + result1);
		//System.out.println("Total time: only IO: " + result2);
		
		System.out.println("Total time: get + IO: " + result3);
		System.out.println("Total time: only IO: " + result2);
		
		System.out.println("get time: " + (result3 - result2));	
		//System.out.println("put time: " + (result1 - result2));		
	}
	
	private static double putTime(int minlen, String file) {
		LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();

		In in = new In (file);
		Stopwatch sw = new Stopwatch();
		while (!in.isEmpty()) {
			String key = in.readString();
			if (key.length() < minlen) continue;
			if (st.contains(key)) { st.put(key, st.get(key) + 1); }
			else                  { st.put(key, 1); }
		}
		double time = sw.elapsedTime ();
		return time;

	}
	
	private static double standardInOut(int minlen, String file) {
		LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();

		In in = new In (file);
		Stopwatch standardIOTime = new Stopwatch();
		while (!in.isEmpty()) {
			String key = in.readString();
		}
		double time = standardIOTime.elapsedTime ();
		return time;
		}
	
	private static double getTime (int minlen, String file) {
		LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();

		In in = new In (file);
		Stopwatch sw3 = new Stopwatch();
		while (!in.isEmpty()) {
			String key = in.readString();
		}
		String max = "";
		st.put(max, 0);
		for (String word : st.keys()) {
			if (st.get(word) > st.get(max))
				max = word;
		}
		double time = sw3.elapsedTime ();
		return time;
		}
	
		}

