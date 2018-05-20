package week3;

import java.util.concurrent.RecursiveAction;

class SumArray extends RecursiveAction{
	int vInicio;
	int vFim;
	double resultado = 0;
	double[] array;
	
	public SumArray(double[] x, int l, int h) {
		array = x;
		vFim = h;
		vInicio = l;
	}

	@Override
	protected void compute() {
		if(vFim - vInicio <= 1000)
			for (int i = vInicio; i < vFim; i++)
				resultado += array[i]/array.length;
		else {
			SumArray sumArray1 = new SumArray(array, vInicio, (vInicio + vFim)/2);
			SumArray sumArray2 = new SumArray(array, (vInicio + vFim)/2, vInicio);
			sumArray1.fork();
			sumArray2.compute();
			sumArray1.join();
			resultado = sumArray1.resultado + sumArray2.resultado;
		}	
	}
}