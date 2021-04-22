package kr.or.ddit.example.dao;

public class ExampleDAO_MySql implements IExampleDAO{
	
	public ExampleDAO_MySql() {
		super();
		System.out.println(getClass().getSimpleName()+"객체 생성");
	}

	@Override
	public String selectData(String pk) {
		return String.format("%s 로 MySql 에서 조회된 raw data", pk);
	}
}
