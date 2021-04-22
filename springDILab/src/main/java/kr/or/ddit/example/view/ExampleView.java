package kr.or.ddit.example.view;

import kr.or.ddit.example.dao.ExampleDAO_Oracle;
import kr.or.ddit.example.dao.IExampleDAO;
import kr.or.ddit.example.service.ExampleServiceImpl;
import kr.or.ddit.example.service.IExampleService;

public class ExampleView {
	public static void main(String[] args) {
		IExampleDAO dao = new ExampleDAO_Oracle();
		IExampleService service = new ExampleServiceImpl(dao);
		String info = service.readData("a001");
		System.out.println(info);
	}
}
