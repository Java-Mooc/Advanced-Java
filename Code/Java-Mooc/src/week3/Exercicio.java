/**
 * Source code from the Java Parallel Programming Coursera course.
 */
package week3;

import java.util.concurrent.ForkJoinPool;

public class Exercicio {
	public static void main(String args[]) {
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");
		double[] array = createLargeArray();
		for(int i = 0; i < 5; i++) {
			System.out.println("Run " + (i+1) + ":");
			long t1 = System.nanoTime();
			double res = mediaSeq(array);
			t1 = System.nanoTime() - t1;
			printResultados("Seq", res, t1);
			
			long t2 = System.nanoTime();
			res = mediaAsync(array);
			t2 = System.nanoTime() - t2;
			printResultados("Async", res, t2);
			System.out.println();
		}
	}
	   
	private static void printResultados(String string, double res, long t1) {
		System.out.printf("%s in %8.3f miliseconds, sum = %8.5f \n", string, t1 / 1e6, res);
	}

	public static double mediaSeq(double[] x){
		double sum = 0;
		for(int i = 0; i < x.length; i++)
			sum += x[i]/x.length;
		return sum;
	}
	
	public static double mediaAsync(double[] x){
		SumArray t = new SumArray(x, 0, x.length);
		ForkJoinPool.commonPool().invoke(t);
		return t.resultado; 
	}
	
	
	public static double[] createLargeArray(){
		double[] data = new double[80000000];
		for (int i = 0; i < data.length; i++)
			//data[i] = i + 1;
			data[i] = Math.floor(Math.random() * 2) + 1;
		return data;
	}
}