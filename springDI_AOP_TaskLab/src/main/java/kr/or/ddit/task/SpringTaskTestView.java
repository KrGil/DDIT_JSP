package kr.or.ddit.task;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTaskTestView {
	public static void main(String[] args) {
		ConfigurableApplicationContext context
				= new ClassPathXmlApplicationContext("kr/or/ddit/task/*-context.xml");
	
	}
}
