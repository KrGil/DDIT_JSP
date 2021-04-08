package kr.or.ddit.designpattern.commandpattern;

public class ConcreteCommand implements Command {
	private Receiver1 recv1;
	
	public ConcreteCommand(Receiver1 recv1){
		super();
		this.recv1 = recv1;
	}
	
	@Override
	public void execute() {
		recv1.specificOperate();
	}

}
