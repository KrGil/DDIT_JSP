package kr.or.ddit.designpattern.builder;

/**
 * 
 * 객체 생성 방법
 * 1. 점층적 생성자 패턴(하나씩 하나씩 새로운 생성자 생성하는방법)
 * 2. 자바빈 패턴 
 * 3. Builder 패턴
 *
 */
public class BuilderPatternDesc {
	public static void main(String[] args) {
		TestVO vo = TestVO.builder().build();
		TestVO vo1 = TestVO.builder().prop1("prop1").build();
		
		TestVO vo2 = TestVO.builder().prop2("prop2").prop3("prop3").build();
		
		TestVO vo3 = TestVO.builder().prop1("prop1").prop3("prop").build();
		
		TestVO vo4 = TestVO.builder().prop1("prop1").prop2("prop2").prop3("prop3").build();
		
		
//		TestVO vo = new TestVO();
////		1. prop1만 결정
//		TestVO vo1 = new TestVO("prop1");
//		vo.setProp1("prop1");
////		2. prop2, prop3 결정
//		TestVO vo2 = new TestVO("prop2", "prop3");
//		vo2.setProp2("prop2");
//		vo2.setProp3("prop3");
////		3. prop1, prop3 결정
////		TestVO vo3 = new TestVO("prop1", null, "prop3");
//		TestVO vo3 = new TestVO(null, "prop1", "prop3");
////		4. prop1, prop2, prop3 결정
//		TestVO vo4 = new TestVO();
//		vo4.setProp1("prop1");
//		vo4.setProp2("prop2");
//		vo4.setProp3("prop3");
	}
}
