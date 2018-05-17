package ppt3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ExercicioCarbonXML {
	public static void main(String[] args) {
		//Let's see UK's carbon intensity, more info at: https://api.carbonintensity.org.uk/
		//We are starting by seeing 
		String baseURL = "https://api.carbonintensity.org.uk/xml/intensity";
		String xml;
		try {
			xml = getTexto(baseURL);
			Document doc = loadXMLFromString(xml);
			String resultado = parseXML(doc);
			System.out.println(resultado);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	private static String parseXML(Document doc) throws Exception {
		String ret = "";	
		boolean bEverythingOkay = checkIfHttpCode200(doc);
		if(bEverythingOkay){
			Node tagWithValues = doc.getElementsByTagName("intensity").item(0);
			NodeList values = tagWithValues.getChildNodes();
			for(int i = 0; i < values.getLength(); i++){
				Node nodeAtual = values.item(i);
				ret += nodeAtual.getNodeName() + ": " + nodeAtual.getFirstChild().getTextContent() + "\n";
			}
		}
		return ret;		
	}
	
	private static boolean checkIfHttpCode200(Document doc) {
		Node tag = doc.getElementsByTagName("httpCode").item(0);
		return "200".equals(tag.getTextContent());
	}

	public static Document loadXMLFromString(String xml) throws Exception
	{
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