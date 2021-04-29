package kr.or.ddit.container.collection;

import java.util.Calendar;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalendarContextTestView {
	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = 
				new ClassPathXmlApplicationContext("kr/or/ddit/container/conf/calendar-context.xml");
		context.registerShutdownHook();
		// %tc를 쓰면 캘린더를 출력할 수 있다.
		Calendar now = context.getBean(Calendar.class);
		System.out.printf("%tc\n", now);

		Thread.sleep(2000);
		now = context.getBean(Calendar.class);
		System.out.printf("%tc", now);
	}
}
