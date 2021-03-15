package kr.or.ddit.designpattern.templatemethodpattern;

public class DerivedClass1 extends TemplateClass{
	@Override
	protected void stepTwo() { // ctrl+space 시 이녀석만 보인다. 
		System.out.println("2단계_A");
	}
}
