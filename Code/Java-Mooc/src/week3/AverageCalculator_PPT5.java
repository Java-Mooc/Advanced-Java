/**
 * Source code from t	he Java Parallel Programming Coursera course.
 */
package week3;

import java.util.concurrent.ForkJoinPool;

public class AverageCalculator_PPT5 {
	public static void main(String args[]) {
		//Alterar este valor de acordo com o numero de cores do PC, isto indica quantas cores podemos usar em paralelo
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");
		double[] array = createLargeArray();
		//Vamos correr o codigo 5 vezes, para podermos comparar com melhor exatidao o tempo que demoramos a calcular a media
		for(int i = 0; i < 5; i++) {
			System.out.println("Run " + (i+1) + ":");
			long t1 = System.nanoTime();
			double res = mediaSeq(array);
			t1 = System.nanoTime() - t1;
			printResultados("Seq", res, t1);
			
			long t2 = System.nanoTime();
			double res2 = mediaAsync(array);
			t2 = System.nanoTime() - t2;
			printResultados("Async", res2, t2);
			System.out.println();
		}
	}
	   
	private static void printResultados(String string, double res, long t1) {
		System.out.printf("%s in %8.3f miliseconds, sum = %8.5f \n", string, t1 / 1e6, res);
	}

	public static double mediaSeq(double[] x){
		//Forma sequencial de calcular a media
		double sum = 0;
		for(int i = 0; i < x.length; i++)
			sum += x[i]/x.length;
		return sum;
	}
	
	public static double mediaAsync(double[] x){
		//Forma assincrona, usando o ForkJoin API
		AverageArray_RT t = new AverageArray_RT(x, 0, x.length);
		double result = ForkJoinPool.commonPool().invoke(t);
		return result;
	}
	
	
	public static double[] createLargeArray(){
		//Um array bem grande, 80 milhoes de registos
		double[] data = new double[80000000];
		for (int i = 0; i < data.length; i++)
			//data[i] = i + 1;
			data[i] = Math.floor(Math.random() * 2) + 1;
		return data;
	}
}