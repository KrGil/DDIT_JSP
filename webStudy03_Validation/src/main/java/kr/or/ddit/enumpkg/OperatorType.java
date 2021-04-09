package kr.or.ddit.enumpkg;

import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.vo.CalculateVO;

public enum OperatorType {
	PLUS('+', new RealOperator() { // 인터페이스 구현체
		public double operate(double left, double right) { 
			return left + right;
		}
	}), MINUS('-', new RealOperator() {
		public double operate(double left, double right) {
			return left - right;
		}
	}), MULIPLY('*', new RealOperator() {
		public double operate(double left, double right) {
			return left + right;
		}
	}), DIVIDE('/', (left, right) ->{// lambda로 하기 / 해당 인터페이스 안에 하나의 메서드만 존재할 때
		return left / right;
		
	}), MODULAR('%', (left, right) ->{
		return left % right;
	});
	
	@FunctionalInterface
	private interface RealOperator{ //다형성 functional interface. 한개의 매서드만 있을 경우
		double operate(double left, double right); // 안에서 무엇을 할 것이다는 나중에 지정해줄 수 있다.
	}
	
	private RealOperator realOperator;
	OperatorType(char sign) {
		this.sign = sign;
	}
	private OperatorType(char sign, RealOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}
	
	private final char sign;
	public char getSign(){
		return sign;
	}
	
	public double operate(double left, double right) {
		return realOperator.operate(left, right);
	}
	
	private static final String EXPRPTRN = "%f %s %f = %f";
	public String expression(CalculateVO vo) {
		return String.format(EXPRPTRN, vo.getLeft(), sign, vo.getRight(), operate(vo.getLeft(), vo.getRight()));
	}
}
