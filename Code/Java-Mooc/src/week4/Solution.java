package week4;

public class Solution {
	public static void main(String args[]) {
		int[] arr = createLargeArray();
		ModeArray t = new ModeArray(arr, 0, arr.length);
		long t1 = System.nanoTime();
		int result = t.getModa();
		t1 = System.nanoTime() - t1;
		System.out.println("Moda: " + result + "\nTempo de pesquisa: " + t1/1e6 + " milissegundos");
	}
	
	public static int[] createLargeArray(){
		int[] data = new int[80000000];
		for (int i = 0; i < data.length; i++)
			data[i] = (int) (Math.random() * 10) + 1;
		return data;
	}

	private static void printArray(int[] data, String string) {
		System.out.println(string);
		for(int i = 0; i < data.length; i++)
			System.out.print(data[i] + " ");
		System.out.println();
	}
}