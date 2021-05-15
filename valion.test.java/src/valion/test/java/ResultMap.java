package valion.test.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ResultMap extends HashMap<String, Object> {
	
	/** UnderbarCase 키 --> CarmelCase 키로 변환하여 사용 */
	@Override
	public Object put(String key, Object value) {
		// TODO Auto-generated method stub
		key = StringUtil.toCarmelCase(key);
		return super.put(key, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		// TODO Auto-generated method stub
		Set<? extends String> keySet = m.keySet();
		for (String s : keySet) {
			super.put(StringUtil.toCarmelCase(s), m.get(s));
		}
	}

	@Override
	public Object putIfAbsent(String key, Object value) {
		// TODO Auto-generated method stub
		key = StringUtil.toCarmelCase(key);
		return super.putIfAbsent(key, value);
	}

	public static void main(String[] args) {
		ResultMap r = new ResultMap();
		r.put("KEY_VALUE1", "1");
		r.put("key_VALUE2", "2");
		System.out.println(r);
	}
	
	
}
