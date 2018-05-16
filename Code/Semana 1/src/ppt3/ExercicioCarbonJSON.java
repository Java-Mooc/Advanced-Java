package ppt3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExercicioCarbonJSON {
	public static void main(String[] args) {
		//Let's see UK's carbon intensity, more info at: https://api.carbonintensity.org.uk/
		//We are starting by seeing 
		String baseURL = "https://api.carbonintensity.org.uk/intensity";
		String json;
		try {
			json = getTexto(baseURL);
			String resultado = parseJSON(json);
			System.out.println(resultado);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	private static String parseJSON(String json) throws Exception {
		String ret = "";	
		
		ret += "Forecast: " + find("forecast", json) + "\n";
		ret += "Actual: " + find("actual", json) + "\n";
		ret += "Index: " + find("index", json) + "\n";
		
		return ret;		
	}
	
	
	
	private static String find(String string, String json) {
		String ret = "";
		Pattern regexPattern = Pattern.compile("\"" + string + "\": \"*(\\w+)\"*");
		Matcher res = regexPattern.matcher(json);
		if(res.find())
			ret += res.group(1);
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
