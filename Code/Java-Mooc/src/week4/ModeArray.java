package week4;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

import week3.AverageArray_RA;

public class ModeArray extends RecursiveTask<int[]>{
	int vInicio;
	int vFim;
	int[] array;
	
	public ModeArray(int[] x, int l, int h) {
		array = x;
		vFim = h;
		vInicio = l;
	}

	protected int[] compute() {
		int[] valores = new int[601];
		if(vFim - vInicio <= 1000) {
			for (int i = vInicio; i < vFim; i++){
				int valor = array[i];
				valores[valor]++;
			}
			return valores;
		}
		else {
			ModeArray modeArray1 = new ModeArray(array, vInicio, (vInicio + vFim)/2);
			ModeArray modeArray2 = new ModeArray(array, (vInicio + vFim)/2, vFim);
			modeArray1.fork();
			int[] v1 = modeArray2.compute();
			int[] v2 = modeArray1.join();
			for(int i = 0; i < v1.length; i++)
				v1[i] += v2[i];
			return v1;
		}
	}

	public int getModa() {
		int[] valores = ForkJoinPool.commonPool().invoke(this);
		//int[] valores = compute();
		int max = 0;
		int numero = 0;
		for(int i = 0; i < valores.length; i++)
			if(valores[i] > max){
				numero = i;
				max = valores[i];
			}
		return numero;
	}
}