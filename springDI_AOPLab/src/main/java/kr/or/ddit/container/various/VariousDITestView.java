package kr.or.ddit.container.various;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class VariousDITestView {
	public static void main(String[] args) {
		//1. 결합력을 낮추자잉 
		ConfigurableApplicationContext container = 
			new GenericXmlApplicationContext("classpath:kr/or/ddit/container/conf/variousDI-context.xml");
		container.registerShutdownHook();
		VariousDIVO vo1 = container.getBean("vo1", VariousDIVO.class);
		System.out.println(vo1);
		
		VariousDIVO vo2 = container.getBean("vo2", VariousDIVO.class);
		System.out.println(vo2);
		
	}
}
