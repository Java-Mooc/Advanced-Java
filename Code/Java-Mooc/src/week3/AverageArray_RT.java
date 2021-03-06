package week3;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class AverageArray_RT extends RecursiveTask<Double>{
	int vInicio;
	int vFim;
	double[] array;
	
	public AverageArray_RT(double[] x, int l, int h) {
		array = x;
		vFim = h;
		vInicio = l;
	}

	@Override
	protected Double compute() {
		double resultado = 0;
		if(vFim - vInicio <= 1000) {
			for (int i = vInicio; i < vFim; i++)
				resultado += array[i]/array.length;
			return resultado;
		}
		else {
			AverageArray_RT sumArray1 = new AverageArray_RT(array, vInicio, (vInicio + vFim)/2);
			AverageArray_RT sumArray2 = new AverageArray_RT(array, (vInicio + vFim)/2, vFim);
			//Este exerc�cio como podemos ver � bastante semelhante ao anterior,
			//simplesmente aqui podemos fazer returns, tornando o c�digo muito mais elegante
			sumArray1.fork();
			double v1 = sumArray2.compute();
			double v2 = sumArray1.join();
			return v1 + v2;
		}	
	}
}