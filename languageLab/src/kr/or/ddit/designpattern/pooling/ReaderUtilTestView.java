package kr.or.ddit.designpattern.pooling;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;

public class ReaderUtilTestView {
	public static void main(String[] args) throws IOException {
		PooledObjectFactory<StringBuffer> factory = new StringBufferFactory();
		GenericObjectPool<StringBuffer> pool = new GenericObjectPool<StringBuffer>(factory) ;
		
		pool.setMaxTotal(3);
		pool.setMaxWaitMillis(2000);
		
		InputStream is = ReaderUtilTestView.class.getResourceAsStream("/kr/or/ddit/io/another day.txt");
//		InputStreamReader reader = new InputStreamReader(is);
		String result =  new ReaderUtil(pool).readFromInputStreamToStream(is);
//		new ReaderUtil(pool).readFromInputStreamToStream(is);
//		new ReaderUtil(pool).readFromInputStreamToStream(is);
//		new ReaderUtil(pool).readFromInputStreamToStream(is);
		
		System.out.printf("active count : %d\n", pool.getNumActive());
		System.out.println(result);
	}
}
