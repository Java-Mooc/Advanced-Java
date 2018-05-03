package pp2;

public class Exercicio {

	public static void main(String[] args) {
		String textoExemplo =
				"King Arthur, also called Arthur or Arthur Pendragon, legendary British king who appears in a cycle of medieval "
				+ "romances (known as the Matter of Britain) as the sovereign of a knightly fellowship of the Round Table. It is"
				+ " not certain how these legends originated or whether the figure of Arthur was based on a historical person. ";
		String regex1 = "";
		String regex2 = "";
		String regex3 = "";
		String regex4 = "";
		String regex5 = "";
		String regex6 = "";
		
		
		boolean regex1Result = textoExemplo.matches(regex1);
		String[] regex2Result = textoExemplo.split(regex2);
		String regex3Result = textoExemplo.replaceFirst(regex3, "replace");
		String regex4Result = textoExemplo.replaceAll(regex4, replace);
		
		

	}

}
