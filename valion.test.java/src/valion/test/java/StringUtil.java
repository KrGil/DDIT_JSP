package valion.test.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	// a.contains("b") || a.contains("c") || a.contains("d")
	// StringUtil.containsOr(a, "b", "c", "d");
	// String ... compares ---> String[]
	public static boolean containsOr(String target, String ... compares) {
		if(target==null || compares==null) { return false; }
		for (String s : compares) {
			if(target.contains(s)) {
				return true; 
			}
		}
		return false;
	}
//	public static void main(String[] args) {
//		
//		boolean containsOr = containsOr("홍길동", "흥","홍");
//		System.out.println(containsOr);
//		
//	}
	
	public static String toCarmelCase(String key) {
		// TODO Auto-generated method stub
		if(key==null) { return null; }
		key = key.toLowerCase();
		// 전체 소문자 변환 
		// 언더바를 구분하여 첫글자를 대문자로 처리
		Matcher matcher = Pattern.compile("(_)([a-z])([a-z0-9]+)").matcher(key);
		StringBuffer sb = new StringBuffer();
		while(matcher.find()) {
			System.out.println(matcher.group(2) + matcher.group(3));
			matcher.appendReplacement(sb, matcher.group(2).toUpperCase()+ matcher.group(3));
		}
		matcher.appendTail(sb);
		System.out.println("sb = " + sb);
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
		String carmelCase = toCarmelCase("TEST_VALUE1");
		System.out.println(carmelCase);
		
	}
	
	
}


