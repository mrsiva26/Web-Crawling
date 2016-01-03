import java.awt.Desktop;
import java.io.File;
import java.net.MalformedURLException;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


public class flickr {
	public static void main(String args[]) throws FileNotFoundException, IOException {

		//create url
		String searchTerm = "chennai";
		String url="https://www.flickr.com/search/?q=" + searchTerm.replaceAll(" ", "%20");
		System.out.println(url);

		//create folder
		String folderName = "C:\\Users\\mrsiva268\\Desktop\\"+searchTerm.replaceAll(" ", "");
		File createFolder = new File(folderName);
		createFolder.mkdir();
		System.out.println("Folder created");
		
		//create htmlfile for manual refernce
		String htmlFileName = folderName + "\\" + searchTerm.replaceAll(" ", "") + ".html";
		File htmlFile = new File(htmlFileName);
		FileWriter fw = new FileWriter(htmlFile);
		System.out.println("htmlFile created - data not yet written");

		//fetch url and write it to htmlfile
		Document doc = (Document) Jsoup.connect(url).get();
		System.out.println("URL fetched");
		//System.out.println(doc.body());
		fw.write(doc.toString());
		fw.close();
		System.out.println("html written to file");

		Elements imageNodes = doc.select("#photo-display-container a img[data-defer-src]");
		//System.out.println(imageNodes.toString());
		System.out.println(imageNodes.size());
		for(int i=0;i<imageNodes.size();i++){
			String id = imageNodes.get(i).attr("id");
			String src = imageNodes.get(i).attr("data-defer-src");
			String imageName = folderName + "\\" + id + ".jpg";
			saveImage(src,imageName);
			System.out.println(imageName + "\t" + src);
		}

		System.out.println("Program over");
	}

	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);
		byte[] b = new byte[2048];
		int length;
		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}
		is.close();
		os.close();
	}

	public static void openWebpage(URI uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void openWebpage(String s) throws MalformedURLException {
		try {
			URL url = new URL(s);
			openWebpage(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
};