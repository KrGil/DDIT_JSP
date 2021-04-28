package kr.or.ddit.container.collection;

import org.springframework.beans.factory.FactoryBean;

public class StringArrayFactoryBean implements FactoryBean<String[]> {

	@Override
	public String[] getObject() throws Exception {
		return new String[] {"value1", "value2"};
	}

	@Override
	public Class<?> getObjectType() {
		return String[].class;
	}
	
	// 하나만 만들어서 계속해서 써먹기.
	@Override
	public boolean isSingleton() {
		return false;
	}

}
