package kr.or.ddit.enumpkg;

import kr.or.ddit.vo.CalculateVO;

public enum OperatorType {
	PLUS('+', new RealOperator() {
		public double operate(double left, double right) {
			return left + right;
		}
	}), 
	MINUS('-', new RealOperator() {
		public double operate(double left, double right) {
			return left - right;
		}
	}), 
	MULTIPLY('*', new RealOperator() {
		public double operate(double left, double right) {
			return left * right;
		}
	}), 
	DIVIDE('/', (left, right)->{
		return left / right;
	}), 
	MODULAR('%', (left, right)->{
		return left % right;
	});
	
	@FunctionalInterface
	private interface RealOperator{
		public double operate(double left, double right);
	}
	
	private OperatorType(char sign, RealOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}

	private RealOperator realOperator;
	private char sign;
	public char getSign() {
		return sign;
	}
	
	public double operate(double left, double right) {
		return realOperator.operate(left, right);
	}
	
	private static final String EXPRPTRN = "%f %s %f = %f";
	
	public String expression(CalculateVO vo) {
		return String.format(EXPRPTRN, vo.getLeft(), sign, vo.getRight(), 
									operate(vo.getLeft(), vo.getRight()));
	}
}













