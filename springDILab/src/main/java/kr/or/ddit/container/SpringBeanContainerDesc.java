package kr.or.ddit.container;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.example.service.IExampleService;

/**
 * Spring Bean Container 사용 단계
 * : bean 의  lifecycle 관리자
 * 1. spring container module 을
 *  빌드패스에 추가
 * 		(beans, core, context, spEL)
 * 2. bean metadata(bean definition metadata) 등록 파일
 * 		1) bean 등록(bean 엘리먼트)
 * 		2) 등록된 bean들간의 의존관계 형성(의존성 주입, dependency injection)
 * 			- constructor injection (필수 전략 주입)
 * 			  constructor-arg, c namespace(3.1)
 * 			- setter injection (optional 전략 주입)
 * 			  property, p namespace(3.0)
 * 
 * 3. entry point에서 Container 객체 생성
 * 		ApplicationContext 의 구현체
 * 4. .getBean으로 의존객체 주입
 *		- type 을 기중으로 한 주입 (두개 이상의 빈이 존재 시 exception 발생) 
 *		- id를 기준으로 한 주입(캐스팅을 매번 해 주어야 한다)
 * 5. 컨테이너 종료(shutdownHook 등록)
 * 		
 * *** 컨테이너의 빈 관리 정책
 * 1. 특별한 설정(scope)이 없는 한 빈은 singleton 으로 관리됨.
 * 		** 싱글톤의 대상은 빈!!!
 * 		scope - singleton (기본 정책) - 하나의 빈은 하나의 객체
 * 				prototype - 주입될 때마다 새로운 객체가 생성됨.
 * 				request / session 생명 범위가 동일하다.
 * 2. 특별한 설정(lazy-init)이 없는 한 컨테이너가 초기화될 때 등록된 빈의 모든 객체 생성.
 * 		: 객체의 생성 시점을 지연시키거나 생성 순서를 어느정도 제어할 수 있음.
 * 
 * 3. depends-on을 이용하여 빈들간의 순서를 직접 제어도 가능은 함.(잘 모르겠으면 쓰지마)
 * 4. 생명주기 콜백 정의 가능
 * 		*** init-method 는 필요한 주입이 모두 끝난 후에 호출됨.
 * 
 */		


public class SpringBeanContainerDesc {
	public static void main(String[] args) {
		ConfigurableApplicationContext container
			= new GenericXmlApplicationContext("classpath:kr/or/ddit/container/conf/spring-container.xml");
		// 더이상 활성화되어있는 쓰레드가 없으면 닫아줌
		// 데몬쓰레드가 자신만 남아있으면 알아서 종료해준다.
		container.registerShutdownHook(); 
		
//		IExampleService service1 = container.getBean("service1", IExampleService.class);
//		IExampleService service1_1 = container.getBean("service1", IExampleService.class);
//		IExampleService service2 = container.getBean("service2", IExampleService.class);
//		IExampleService service2_2 = container.getBean("service2", IExampleService.class);
//		System.out.println(service1.readData("a001"));
//		System.out.println(service1 == service2);
//		System.out.println(service1 == service1_1);
//		System.out.println(service2 == service2_2);
	}
}
