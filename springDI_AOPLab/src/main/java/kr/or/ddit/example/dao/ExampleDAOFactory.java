package kr.or.ddit.example.dao;

public class ExampleDAOFactory {
	public static IExampleDAO getExampleDAO() {
//		return new ExampleDAO_MySql();
		return new ExampleDAO_Oracle();
	}
}
