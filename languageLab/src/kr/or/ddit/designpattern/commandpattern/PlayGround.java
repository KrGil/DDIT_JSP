package kr.or.ddit.designpattern.commandpattern;

public class PlayGround {
	public static void main(String[] args) {
		Receiver1 recv1 = new Receiver1();
		Receiver2 recv2 = new Receiver2();
		
		// pattern 적용 전
		Invoker1NoPattern iv1 = new Invoker1NoPattern(recv1);
		iv1.order();
		
		Invoker2NoPattern iv2 = new Invoker2NoPattern(recv2);
		iv2.order();
		
		// command pattern 적용 - 결합력을 끊기 위해서
		Command command1 = new ConcreteCommand(recv1);
		Command command2 = new ConcreteCommand2(recv2);
		Invoker front =  new Invoker(command1, command2);
		front.order(1);
		front.order(0);
		front.order(7);
		
//		front.order();
//		front.setCommand(command2);
//		front.order();
	}
}
