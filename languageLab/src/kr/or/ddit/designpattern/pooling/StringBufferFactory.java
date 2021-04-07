package kr.or.ddit.designpattern.pooling;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class StringBufferFactory extends BasePooledObjectFactory<StringBuffer>{

	@Override
	public StringBuffer create() throws Exception {
		return new StringBuffer();
	}

	@Override
	public PooledObject<StringBuffer> wrap(StringBuffer obj) {
		return new DefaultPooledObject<StringBuffer>(obj);
	}
	
	@Override
	public void passivateObject(PooledObject<StringBuffer> p) throws Exception {
		// 반납받은 객체 
		StringBuffer returnObj =  p.getObject();
		if(returnObj.length()>0)
			returnObj.delete(0, returnObj.length());
		returnObj.setLength(0);
	}
}
