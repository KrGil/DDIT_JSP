package kr.or.ddit.designpattern.adapterpattern;

public class OtherConcrete implements Target {

	@Override
	public void request() {
		System.out.printf("%s 에 의해 직접 처리된 명령\n", getClass().getName());
	}
}
