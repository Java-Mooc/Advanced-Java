package ppt1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Exemplo {

	public static void main(String[] args) {
		try {
			//A tentar conectarmos com o url
			URL url = new URL("https://java-mooc.github.io/Advanced-Java/ex1.html");
			String textoRecebido = "";
			
			//Aqui pedimos a página web em questão
			url.openConnection();
			
			//Stream onde recebemos os dados
			InputStream streamDeLeitura = url.openStream();
			//Buffer onde armazenamos os dados
	        BufferedReader buffer = new BufferedReader(new InputStreamReader(streamDeLeitura));
	        
	        String textoDoBuffer;
	        while ((textoDoBuffer = buffer.readLine()) != null) {
	            textoRecebido += textoDoBuffer;
	        }
	        
			System.out.println(textoRecebido);
		} catch (Exception e){
			System.err.println("Ocorreu uma excepção!\n" + e.getMessage());
		} 
	}
}
