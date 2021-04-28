package kr.or.ddit.container.javaconfig;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.ddit.container.various.VariousDIVO;

public class JavaConfigTestView {
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				new AnnotationConfigApplicationContext(JavaConfiguration.class);
		context.registerShutdownHook();
	
//		VariousDIVO vo1 = context.getBean(VariousDIVO.class);
//		VariousDIVO vo2 = context.getBean(VariousDIVO.class);
//		System.out.println(vo1==vo2);
	}
}
