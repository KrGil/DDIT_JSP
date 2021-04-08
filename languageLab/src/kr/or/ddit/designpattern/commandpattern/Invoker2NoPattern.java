package kr.or.ddit.designpattern.commandpattern;

import kr.or.ddit.annotation.FirstAnnotation;

@FirstAnnotation(number=45)
public class Invoker2NoPattern {
	
	public Invoker2NoPattern() {
		super();
	}
	
	private Receiver2 recv2;

	public Invoker2NoPattern(Receiver2 recv2) {
		super();
		this.recv2 = recv2;
	}

	public void order() {
		recv2.specific20perate2();
	}
}
