package kr.or.ddit.crawlring;

import static org.junit.Assert.*;

import java.util.Map.Entry;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumAndParserTest {
	
	@Test
	public void testcase() {
		Properties props = System.getProperties();
		for(Entry<Object, Object> prop : props.entrySet()) {
			Object key = prop.getKey();
			Object value = prop.getValue();
			System.out.printf("%s : %s\n", key, value);
		}
		System.getenv();
	}

//	@Test
	public void test() {
		
		System.setProperty("webdriver.chrome.driver", "d:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.naver.com");
		try {
			Thread.sleep(2000);
			
			String source = driver.getPageSource();
//			System.out.println(source);
			
			Document dom = Jsoup.parse(source);
			Element themecast = dom.getElementById("themecast");
			Elements elements = themecast.select(".title");
			Element title = elements.get(0);
			System.out.println(title);
			
			driver.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
