package kr.or.ddit.method;

public class MethodInvocation {
	public static void main(String[] args) {
		String text1 = "original";
		StringBuffer text2 = new StringBuffer("original");
		
		callByValue(text1);
		System.out.println(text1);
		
		callByReference(text2);
		System.out.println(text2);
	}
	
	private static void callByValue(String data) {
		data = data+"append data"; // 새로운 값이 생성. 그리고 메서드 종료. 그러면 지역변수도 같이 사라짐.
	}
	private static void callByReference(StringBuffer data) {
		data.append("append data");
	}
}
