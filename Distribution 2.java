///*
// * Author: James Valles 
// * CSC 402
// * Distribution - Calculates word mean, standard deviation, and normal distribution percentages from txt file
// */
//
//package group2;
//import stdlib.*;
//
//public class Distribution {
//	
//	//This function computes the Mean 
//	public static double computeAverage(double[] numbers) {
//		double sum = 0;
//		
//		for (double number: numbers) {
//			sum += number;
//		}
//		double average = (double) sum / numbers.length;
//		return average;
//	}
//	
//	//This function computes Standard Deviation
//	public static double computeStandardDeviation(double[] numbers) {
//		double average = computeAverage(numbers);
//		double sum = 0.0;
//		for (double number: numbers) {
//			sum += Math.pow(average - number, 2);
//		}
//		double standardDeviation = Math.sqrt(sum / numbers.length);
//		return standardDeviation;
//	}
//
//			
//	//This function computes SD Percentage
//	public static double standardDeviationPer(double[] numbers, int sdr) {
//		double average = computeAverage(numbers);
//		double standardDeviation = computeStandardDeviation(numbers);
//		
//		double total = numbers.length;
//		double low = average -  (sdr*standardDeviation);	
//		double high = average + (sdr*standardDeviation);
//
//		int count = 0; 
//		for (double number: numbers) {
//			if (number >= low && number <=high ) {
//				count += 1; 
//				}		
//			}
//		double percent =  ((count / total) * 100);  
//		return percent;		
//	}
//
//
//	
//	//This is main
//	public static void main(String[] args) {
//		String sourcetext = "data/normaldistribution.txt";
//		StdIn.fromFile(sourcetext);
//		double[] values = StdIn.readAllDoubles();
//		
//		
//		//Call functions 
//		double average = computeAverage(values);
//		double standardDeviation = computeStandardDeviation(values);
//		double sd1 = standardDeviationPer(values, 1); 
//		double sd2 = standardDeviationPer(values, 2); 
//		double sd3 = standardDeviationPer(values, 3); 
//		
//		//This is what prints to the screen  
//		StdOut.println("Statistics on the file: " + sourcetext);
//		StdOut.println("The mean is: " + average);
//		StdOut.println("The standard deviation is: " + standardDeviation);
//		StdOut.println("Percentage of values 1 SD away from mean: " + sd1);
//		StdOut.println("Percentage of values 2 SD away from mean: " + sd2);
//		StdOut.println("Percentage of values 3 SD away from mean: " + sd3);
//			
//	}
//
//}
