package kr.or.ddit.container.auto;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.example.service.IExampleService;

public class ExampleAutoDITextView {
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				new ClassPathXmlApplicationContext("kr/or/ddit/container/conf/exampleAuto-context.xml");
	context.registerShutdownHook();
	IExampleService service1 = context.getBean(IExampleService.class);
	IExampleService service2 = context.getBean(IExampleService.class);
	System.out.println(service1==service2);
	//	System.out.println(service.readData("a001"));
	}
}
