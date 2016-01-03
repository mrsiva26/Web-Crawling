package flipkart;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Flipkart {
    public static void main(String[] args) throws MalformedURLException, IOException {
        /*
        Document doc = (Document) Jsoup.connect("http://www.flipkart.com/laptops/pr?sid=6bo,b5g&otracker=ch_vn_laptop_filter_Laptop%20Brands_All%20Brands").get();
        Elements links = doc.select("#products > div > div > div > div.pu-visual-section > a");
        System.out.println(links.size());
        for(Element l : links)
            System.out.println(l.attr("href"));
        */
        
         // Selenium
 System.setProperty("webdriver.chrome.driver", "C:\\Users\\mrsiva268\\Documents\\NetBeansProjects\\Flipkart\\chromedriver.exe");

 WebDriver driver = new ChromeDriver();
  WebDriverWait wait = new WebDriverWait(driver, 3600);
 WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("no-more-results"))); 
 driver.manage().timeouts().implicitlyWait(3600, TimeUnit.SECONDS);
 driver.get("http://www.flipkart.com/laptops/pr?sid=6bo,b5g&otracker=ch_vn_laptop_filter_Laptop%20Brands_All%20Brands");  
 String html_content = driver.getPageSource();
 driver.close();
 Document doc1 = Jsoup.parse(html_content);
 System.out.print(doc1);
        Elements links = doc1.select("#products > div > div > div > div.pu-visual-section > a");
        System.out.println(links.size());
        for(Element l : links)
            System.out.println(l.attr("href")); 
    }    
}