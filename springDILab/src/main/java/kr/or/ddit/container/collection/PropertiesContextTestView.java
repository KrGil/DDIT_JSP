package kr.or.ddit.container.collection;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class PropertiesContextTestView {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				new GenericXmlApplicationContext("classpath:kr/or/ddit/container/conf/properties-context.xml");
		context.registerShutdownHook();
		DBInfoVO info1 = context.getBean("infoVO",DBInfoVO.class);
		DBInfoVO info2 = context.getBean("infoVO2",DBInfoVO.class);
		DBInfoVO info3 = context.getBean("infoVO3",DBInfoVO.class);
		System.out.println(info1);
		System.out.println(info2);
		System.out.println(info3);
	}
}
