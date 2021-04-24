package kr.or.ddit.container.resource;

import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public class ResourceLoadingDesc {
	public static void main(String[] args) throws IOException {
		Resource cpr = new ClassPathResource("log4j2.xml");
		System.out.println(cpr.getFile().exists());
		Resource fsr = new FileSystemResource("D:/contents/오래된 노래_utf8.txt");
		System.out.println(fsr.exists());
		UrlResource urlr = new UrlResource("https://www.google.com/logos/doodles/2021/earth-day-2021-6753651837108909-vacta.gif");
		System.out.println(urlr.contentLength());
		
		ConfigurableApplicationContext container = 
				new ClassPathXmlApplicationContext();
		
		//container 자체가 resourceloader이기 때문에 prefix를 쓸 수 있다.
		cpr = container.getResource("classpath:log4j2.xml");
		System.out.println(cpr);
		fsr = container.getResource("file://D:/contents/오래된 노래_utf8.txt");
		System.out.println(fsr);
		urlr = (UrlResource) 
				container.getResource("https://www.google.com/logos/doodles/2021/earth-day-2021-6753651837108909-vacta.gif");
		System.out.println(urlr);
	}
}
