package week3;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.RecursiveAction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageDownloader extends RecursiveAction {
	private static final long serialVersionUID = 1L;
	String link;
	
	public ImageDownloader(String link) {
		this.link = link;
	}
	@Override
	protected void compute() {
		String regexToGetFilename = "[^\\/]+$";
		Pattern regexPattern = Pattern.compile(regexToGetFilename);
		String name = null;
		String caminhoParaFicheiro = ".\\src\\week3\\";
		try{
			Matcher imagensEncontradas = regexPattern.matcher(link);
			if(imagensEncontradas.find())
				name = imagensEncontradas.group();
			link = link.replaceFirst("^\\.\\/\\/", "https://java-mooc.github.io/Advanced-Java/");
			link = link.replaceFirst("^\\.\\/", "https://java-mooc.github.io/Advanced-Java/");
			//this.fork();
			URL website = new URL(link);
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream(caminhoParaFicheiro + name);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			System.out.println("Saving picture: " + name);
		} catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
}
