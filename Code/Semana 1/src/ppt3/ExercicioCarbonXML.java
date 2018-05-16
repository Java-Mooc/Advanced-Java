package ppt3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExercicioCarbonXML {
	public static void main(String[] args) {
		//Let's see UK's carbon intensity, more info at: https://api.carbonintensity.org.uk/
		//We are starting by seeing 
		String cidade = "London";
		String baseURL = "https://api.carbonintensity.org.uk/xml/intensity";
		String xml;
		try {
			xml = getTexto(baseURL);
			String resultado = parseXML(xml);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	private static String parseXML(String xml) {
		String ret = "";
		boolean httpCodeOk = xml.contains("<httpCode>200</httpCode>");
		
		if(httpCodeOk){
			Pattern regexPattern = Pattern.compile("(?<=<forecast>)\\d+(?=<\\/forecast>)");
			Matcher m = regexPattern.matcher("FOO[BAR]");
			while (m.find()) {
			    String s = m.group(1);
			    // s now contains "BAR"
			}
		}
		
		return ret;
		
	}

	private static String getTexto(String link) throws Exception {
		URL url = new URL(link);
		
		//Aqui pedimos a página web em questão
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
