package week1;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

public class DownloadImages_PPT1 {
	//URL base da página
	static String mainWebsite = "https://java-mooc.github.io/Advanced-Java/";
	
	public static void main(String[] args) {
		ArrayList<String> links = new ArrayList<String>();
		try {
			String textoRecebido = getTexto("https://java-mooc.github.io/Advanced-Java/ex3.html");
			
			String[] partesDotextoRecebido = textoRecebido.split("<img src=\"");
			for(int i = 1; i < partesDotextoRecebido.length; i++){
				String link = partesDotextoRecebido[i].split("\"")[0];
				link = getUrl(link);
				links.add(link);
			} //for
	
			downloadFiles(links);
		
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	private static void downloadFiles(ArrayList<String> links) {
		try{
			String caminhoParaFicheiro = ".\\src\\week1\\";
			for(int i = 0; i < links.size(); i++){
				//nome do ficheiro
				String nome = getNome(links.get(i));
				//url para ir buscar o ficheiro
				URL website = new URL(links.get(i));
				//transferência e guardar o ficheiro
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());
				FileOutputStream fos = new FileOutputStream(caminhoParaFicheiro + nome);
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				System.out.println(nome);
			}
		} catch(Exception e){
			System.err.println(e.getMessage());
		}
	}

	private static String getNome(String string) {
		String[] lista = string.split("/");
		String ultimaPalavra = lista[lista.length - 1];
		return ultimaPalavra;
	}

	private static String getUrl(String string) {
		if (string.startsWith(".//"))
			string = string.replace(".//", mainWebsite);
		return string;
	}

	private static String getTexto(String link) throws Exception {
		URL url = new URL(link);
		
		//Aqui pedimos a pagina web em questao
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