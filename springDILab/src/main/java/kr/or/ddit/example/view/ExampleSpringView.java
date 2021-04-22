package kr.or.ddit.example.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.example.service.IExampleService;

public class ExampleSpringView {
	public static void main(String[] args) {
		// 컨테이너의 구현체
		ApplicationContext container = 
				new ClassPathXmlApplicationContext("kr/or/ddit/example/conf/example-context.xml");
//		IExampleService service = 
//				(IExampleService) container.getBean("examplesServiceImpl");
		// 식별자 이름과 class 를 가져오기
		IExampleService service = 
				container.getBean("exampleServiceImpl", IExampleService.class);
		String info = service.readData("a001");
		System.out.println(info);
	}
}
