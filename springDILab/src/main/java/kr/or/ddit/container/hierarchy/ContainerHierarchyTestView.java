package kr.or.ddit.container.hierarchy;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.container.hierarchy.controller.HierarchyController;
import kr.or.ddit.container.hierarchy.service.HirearchyService;

public class ContainerHierarchyTestView {
	public static void main(String[] args) {
		ConfigurableApplicationContext parent =
				new ClassPathXmlApplicationContext("kr/or/ddit/container/conf/hierarchy/root-context.xml");
		ConfigurableApplicationContext child = 
				new ClassPathXmlApplicationContext(new String[] {
						"kr/or/ddit/container/conf/hierarchy/child-context.xml"
				}, parent);
		child.registerShutdownHook();
		
		HierarchyController controller = child.getBean(HierarchyController.class);
		HirearchyService service1 = parent.getBean(HirearchyService.class);
		HirearchyService service2 = child.getBean(HirearchyService.class);
		System.out.println(service1==service2);
		
	}
}
