package kr.or.ddit.designpattern.pooling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.pool2.ObjectPool;

public class ReaderUtil {
	private ObjectPool<StringBuffer> pool;
	
	// 필수 데이터이기 때문에 생성자로 받자
	public ReaderUtil(ObjectPool<StringBuffer> pool) {
		super();
		this.pool = pool;
	}

	
	public String readFromInputStreamToStream(InputStream is) throws IOException {
		InputStreamReader reader = new InputStreamReader(is);
		return readFromReaderToString(reader);
	}
	// 캐릭터셋팅으로 한글이 깨지지 않게 들고올 수 있다.
	public String readFromInputStreamToStream(InputStream is, String charset) throws IOException {
		InputStreamReader reader = new InputStreamReader(is, charset);
		return readFromReaderToString(reader);
	}
	
	public String readFromReaderToString(Reader reader) throws IOException{
//		StringBuffer result = new StringBuffer();
		StringBuffer result = null;
		try(
			BufferedReader br = new BufferedReader(reader);
		){
			result = pool.borrowObject();
			String tmp = null;
			while((tmp=br.readLine())!=null) {
				result.append(String.format("%s\n", tmp));
			}
			String str = result.toString();
			return str;
		}catch (Exception e) {
			throw new IOException(e);
		}finally {
			// 모두 상요한 녀석 pool에 반납하기
			if(result != null) {
				try {
					pool.returnObject(result);
				} catch (Exception e) {
					throw new IOException(e);
				}
			}
		}
		
	}
}
