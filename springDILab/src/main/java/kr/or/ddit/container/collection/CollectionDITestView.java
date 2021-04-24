package kr.or.ddit.container.collection;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionDITestView {
	public static void main(String[] args) {
		ConfigurableApplicationContext container = 
				new GenericXmlApplicationContext("classpath:kr/or/ddit/container/conf/collectionDI-context.xml");
		container.registerShutdownHook();
		CollectionDIVO cvo1 = container.getBean("cvo2",CollectionDIVO.class);
		System.out.println(cvo1);
	}
}
