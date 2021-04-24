package kr.or.ddit.container.collection;

import java.util.Calendar;

import org.springframework.beans.factory.config.AbstractFactoryBean;

public class CalendarFactoryBean extends AbstractFactoryBean<Calendar> {
	// scope를 프로토타입으로 바꾸기.
	public CalendarFactoryBean() {
		setSingleton(false);
	}
	@Override
	public Class<?> getObjectType() {
		return Calendar.class;
	}
	
	@Override
	protected Calendar createInstance() throws Exception {
		return Calendar.getInstance();
	}
	
}
