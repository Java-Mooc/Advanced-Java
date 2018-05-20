package week3;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class ExercicioMelhorado2 {
	static String mainWebsite = "https://java-mooc.github.io/Advanced-Java/";
	
	public static void main(String[] args) {
		long t1 = System.nanoTime();
		try {
			String textoRecebido = getTexto("https://java-mooc.github.io/Advanced-Java/ex4.html");
			String regexToGetLink = "(<img[^>]+src=\")(.+?)(\")"; 
			//(<img[^>]+src=\") -> group(1)
			//(.+?) -> group(2)
			//(\") -> group(3)
			
			Pattern regexPattern = Pattern.compile(regexToGetLink);
			Matcher imagensEncontradas = regexPattern.matcher(textoRecebido);
			System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");
			while(imagensEncontradas.find()){
	            String urlDaImagem = imagensEncontradas.group(2);
	            new ImageDownloader(urlDaImagem).fork();
	        } //while
			
			t1 = System.nanoTime() - t1;
			System.out.println(t1 / 1e9 + " seconds");
		
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}


	
	private static String getTexto(String link) throws Exception {
		URL url = new URL(link);
		
		//Aqui pedimos a p�gina web em quest�o
		url.openConnection();
		
		//Stream onde recebemos os dados
		InputStream streamDeLeitura = url.openStream();
		//Buffer onde armazenamos os dados
        BufferedReader buffer = new BufferedReader(new InputStreamReader(streamDeLeitura));
        
        String textoDoBuffer;
        String textoRecebido = ""; 
        while ((textoDoBuffer = buffer.readLine()) != null) {
            textoRecebido += textoDoBuffer;
        }
		return textoRecebido;
	}
}