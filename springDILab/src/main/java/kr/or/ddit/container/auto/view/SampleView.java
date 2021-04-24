package kr.or.ddit.container.auto.view;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import kr.or.ddit.container.auto.service.ISampleService;

@Component
public class SampleView {
	private ISampleService service;
	// 찾을 때의 검색 조건을 내가 설정할 수 있다.
	// 안정성도 높고 의존성도 낮으니 resource를 쓰는게 좋다.
	@Resource(name = "sampleService")
	// 타입으로만 검색하기 때문에 중복된 녀석이 있다면 식별할 수 없다.
	//	@Autowired
	@Required
	public void setService(ISampleService service) {
		this.service = service;
	}
	public void view() {
		System.out.println(service.readData("a001"));
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				new ClassPathXmlApplicationContext("classpath:kr/or/ddit/container/conf/autoDI-context.xml");
		context.registerShutdownHook();
		SampleView sampleView = context.getBean(SampleView.class);
		sampleView.view();
		
	}
}
