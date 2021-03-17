package kr.or.ddit.enumpkg;

import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.vo.CalculateVO;

public enum OperatorType {
	PLUS('+', new RealOperator() {
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
	}), DIVIDE('/', new RealOperator() {
		public double operate(double left, double right) {
			return left + right;
		}
	});

	private interface RealOperator{
		double operate(double left, double right);
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
