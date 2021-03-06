package week1;

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

public class DownloadImages_PPT2 {
	//URL base da p�gina
	static String mainWebsite = "https://java-mooc.github.io/Advanced-Java/";
	
	public static void main(String[] args) {
		try {
			String textoRecebido = getTexto("https://java-mooc.github.io/Advanced-Java/ex4.html");
			String regexToGetLink = "(<img[^>]+src=\")(.+?)(\")"; 
			//(<img[^>]+src=\") -> group(1)
			//(.+?) -> group(2)
			//(\") -> group(3)
			
			Pattern regexPattern = Pattern.compile(regexToGetLink);
			Matcher imagensEncontradas = regexPattern.matcher(textoRecebido);
			while(imagensEncontradas.find()){
	            String urlDaImagem = imagensEncontradas.group(2);
	            downloadFile(urlDaImagem);
	        } //while
		
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	private static void downloadFile(String link) {
		String regexToGetFilename = "[^\\/]+$";
		Pattern regexPattern = Pattern.compile(regexToGetFilename);
		String name = null;
		String caminhoParaFicheiro = ".\\src\\week2\\";
		try{
			Matcher imagensEncontradas = regexPattern.matcher(link);
			
			//caso exista um filename
			if(imagensEncontradas.find())
				name = imagensEncontradas.group();

			//caso seja um caminho relativo, alteramos o ".\\", de modo a ficarmos com o caminho absoluto
			link = link.replaceFirst("^\\.\\+/", "https://java-mooc.github.io/Advanced-Java/");
			
			//acedemos ao url
			URL website = new URL(link);
			
			//fazer o download e save da imagem
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream(caminhoParaFicheiro + name);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			System.out.println("Saving picture: " + name);
		} catch(Exception e){
			System.err.println(e.getMessage());
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
