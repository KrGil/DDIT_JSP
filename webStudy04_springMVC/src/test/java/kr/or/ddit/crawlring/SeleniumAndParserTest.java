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
		for (Entry<Object, Object> prop : props.entrySet()) {
			Object key = prop.getKey();
			Object value = prop.getValue();
			System.out.printf("%s : %s\n", key, value);
		}
		System.getenv();
	}

	@Test
	public void test() {
		System.setProperty("webdriver.chrome.driver", "d:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.naver.com");
		try {
			Thread.sleep(2000);
			String source = driver.getPageSource();
//			System.out.println(source);
			// dom구조로 우리가 재구성 할 수 잇다
			// dom 구조에서 beutifulsoup으로 parser를 이용해서 값 가져옴
			// jsoup을 이용
			Document dom = Jsoup.parse(source);
			Element themecast = dom.getElementById("themecast");
//			themecast.getElementsByClass("title");
			Elements elements = themecast.select(".title");
			Element title = elements.get(0);
			System.out.println(title);

			driver.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
