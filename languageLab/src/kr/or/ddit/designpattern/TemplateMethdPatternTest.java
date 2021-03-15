package kr.or.ddit.designpattern;

import kr.or.ddit.designpattern.templatemethodpattern.DerivedClass1;
import kr.or.ddit.designpattern.templatemethodpattern.DerivedClass2;
import kr.or.ddit.designpattern.templatemethodpattern.TemplateClass;

public class TemplateMethdPatternTest {
	public static void main(String[] args) {
		TemplateClass instance1 = new DerivedClass1();
		TemplateClass instance2 = new DerivedClass2();
		TemplateClass[] array = new TemplateClass[] {instance1, instance2};
		for(TemplateClass tmp : array) {
			tmp.template();
		}
		
	}
}
