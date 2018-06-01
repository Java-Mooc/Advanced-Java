package week3;

import java.util.concurrent.RecursiveAction;

public class AverageArray_RA extends RecursiveAction{
	int vInicio;
	int vFim;
	double resultado = 0;
	double[] array;
	
	public AverageArray_RA(double[] x, int l, int h) {
		array = x;
		vFim = h;
		vInicio = l;
	}

	@Override
	protected void compute() {
		//este método é obrigatório ser implementado para podermos utilizar a classe RecursiveAction

		//Sempre que tenhamos mais de 1000 elementos para processar,
		//fazemos um fork, para calcular a média
		//Sendo que no final esperamos por todas as threads terminarem (join) e somamos os resultados
		if(vFim - vInicio <= 1000)
			for (int i = vInicio; i < vFim; i++)
				resultado += array[i]/array.length;
		else {
			AverageArray_RA sumArray1 = new AverageArray_RA(array, vInicio, (vInicio + vFim)/2);
			AverageArray_RA sumArray2 = new AverageArray_RA(array, (vInicio + vFim)/2, vFim);
			sumArray1.fork();
			sumArray2.compute();
			sumArray1.join();
			resultado = sumArray1.resultado + sumArray2.resultado;
		}	
	}
}