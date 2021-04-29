package kr.or.ddit.example.dao;

import org.springframework.stereotype.Repository;

@Repository
public class ExampleDAO_Oracle implements IExampleDAO{
	public ExampleDAO_Oracle() {
		super();
		System.out.println(getClass().getSimpleName()+"객체 생성");
	}

	public void init(){
		System.out.println(getClass().getSimpleName()+"객체 초기화");
	}
	public void destroy() {
		System.out.println(getClass().getSimpleName()+"객체 소멸");
	}
	
	@Override
	public String selectData(String pk) {
		
		return String.format("%s 로 Oracle 에서 조회된 raw data", pk);
	}
}
