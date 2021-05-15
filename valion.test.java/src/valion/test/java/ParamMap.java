package valion.test.java;

import java.nio.channels.FileChannel.MapMode;
import java.util.HashMap;

/** Map 타입으로서 키는 반드시 String으로 처리 */
/** Map이 갖는 기본 함수 그대로 사용 -> 상속 */
public class ParamMap extends HashMap<String, Object> {

	/** getString(키) String */
	public String getString(String key) {
		// TODO Auto-generated method stub
		Object object = this.get(key);
		if(object==null) {
			return null;
		}else {
			return String.valueOf(object);
		}
	}

	/** getInt(키) int */
	public Integer getInt(String key) {
		// TODO Auto-generated method stub
		Object object = this.get(key);
		if(object==null) {
			return null;
		}else {
			String valueOf = String.valueOf(object);
			valueOf = valueOf.replace(",","");
			if(valueOf.matches("^[[-][+]]?[0-9]+$")) {
				return Integer.parseInt(valueOf);
			}else {
				return null;
			}
		}
	}

	public <T> T getObject(String key, Class<T> class1) {
		// TODO Auto-generated method stub
		Object object = this.get(key);
		return (T) object;
	}
	
	
	
	
	/** getObject(키) 사용자가 원하는 타입을 동적제공 */
	
	

}
