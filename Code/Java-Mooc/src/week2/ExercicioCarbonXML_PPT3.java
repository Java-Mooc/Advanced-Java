package week2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class ExercicioCarbonXML_PPT3 {
	public static void main(String[] args) {
		//Let's see UK's carbon intensity, more info at: https://api.carbonintensity.org.uk/
		//We are starting by seeing 
		String baseURL = "https://api.carbonintensity.org.uk/xml/intensity";
		String xml;
		try {
			xml = getTexto(baseURL);
			Document doc = loadXMLFromString(xml);
			String resultado = parseXML(doc);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	private static String parseXML(Document doc) throws Exception {
		String ret = "";	
		System.err.println(doc.getFirstChild().getNodeName());
		return ret;		
	}
	
	public static Document loadXMLFromString(String xml) throws Exception {
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(xml));
	    return builder.parse(is);
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