package flipkart;
import java.awt.Desktop;
import java.net.MalformedURLException;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class readFromFile {
    public static void main(String args[]) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("laptops.txt"));
        PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
        String line;
        while((line = br.readLine()) != null) {
              double star = 4, inch = 14, price = 50000;
              String url = /*"http://www.flipkart.com" +*/ line;
              System.out.println(line);
              //openWebpage(url);
              Document doc = (Document) Jsoup.connect(url).get();
              System.out.println(doc);
              Elements cpu = doc.select("#specifications td:contains(i3 (4th Gen)),#specifications td:contains(i5 (3rd Gen))");
              Elements ram = doc.select("#specifications td:contains(4 GB DDR3)");
              Elements stars = doc.select("#fk-mainbody-id div.pp-big-star");
              Elements disp = doc.select("#specifications td:contains(inch)");
              Elements rate = doc.select("#topsection span.fk-font-verybig.pprice.vmiddle.fk-bold");
              System.out.println(stars.text());
              System.out.println(cpu.text());
              System.out.println(ram.text());
              System.out.println(disp.text());
              System.out.println(rate.text());
              if(stars.text()!= null && stars.text().length() > 0)
                star = Double.parseDouble(stars.text());
              if(disp.text()!= null && disp.text().length() > 0)
                inch = Double.parseDouble(disp.text().split(" ")[0]);
              if(rate.text()!= null && rate.text().length() > 0)
                price = Double.parseDouble(rate.text().split(" ")[1]);
              if(inch<15 && price < 35000 && star > 3.5 && cpu.size() > 0 && ram.size() >= 0 )    { //
                    writer.println(url + "\n");
                    System.out.println("Accepted");
              }
        }
    writer.close();
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
}
