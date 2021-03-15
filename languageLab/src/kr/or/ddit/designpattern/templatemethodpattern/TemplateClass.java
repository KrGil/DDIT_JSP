package kr.or.ddit.designpattern.templatemethodpattern;

public abstract class TemplateClass {
	public final void template() { // 외부에서 이녀석만 호출할 수 있다. 그러나 final로 고정되어있다.
		stepOne(); // 단순히 순서만 정의되어 있다.
		stepTwo();
		stepThree();
		
	}
	private void stepOne() {
		System.out.println("1단계");
	}
	protected abstract void stepTwo(); // 추상은 다른곳에서 상속받아서 써야하기때문에 적어도 프로텍티드는 되어야 한다.
	
	private void stepThree() {
		System.out.println("3단계");
	}
}
