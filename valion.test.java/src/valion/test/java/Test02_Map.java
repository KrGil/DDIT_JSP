package valion.test.java;

public class Test02_Map {

	public static void main(String[] args) {
		
		ParamMap param = new ParamMap();
		param.put("key1", "value1");
		param.put("key2", 2);
		System.out.println(param);
		String value = param.getString("key1");
		System.out.println(value);
		int value2 =  param.getInt("key2");
		
		Member m1 = new Member();
		m1.setMemName("홍길동");
		m1.setMemNo("a001");
		param.put("key3",m1);
	
		Member member = param.getObject("key3", Member.class);		
		System.out.println(member);
		
		
	}
	
	
	
}
