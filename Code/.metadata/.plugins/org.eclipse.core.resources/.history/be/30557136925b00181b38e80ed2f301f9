package ppt2;

public class Exercicio {

	public static void main(String[] args) {
		String textoExemplo =
				"King Arthur, also called Arthur or Arthur Pendragon, legendary British king who appears in a cycle of medieval "
				+ "romances (known as the Matter of Britain) as the sovereign of a knightly fellowship of the Round Table.";
		String regex1 = "^K.+[^a-h]+$";
		String regex2 = "(?i)\\s(Arthur)";
		String regex3 = "Arthur";
		String regex4 = "[a-z]+?";
		
		boolean regex1Result = textoExemplo.matches(regex1);
		String[] regex2Result = textoExemplo.split(regex2);
		String regex3Result = textoExemplo.replaceFirst(regex3, "XXX");
		String regex4Result = textoExemplo.replaceAll(regex4, "A");
		
		//Printing
		System.out.println("Regex 1: " + regex1Result);
		
		System.out.print("Regex 2: ");
		for(int i = 0; i < regex2Result.length; i++)
			System.out.print(" [" + regex2Result[i] + "] ");
		System.out.println();
		
		System.out.println("Regex 3: " + regex3Result);
		
		System.out.println("Regex 4: " + regex4Result);

	}

}
