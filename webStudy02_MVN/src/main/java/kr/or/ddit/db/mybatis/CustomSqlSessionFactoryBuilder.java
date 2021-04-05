package kr.or.ddit.db.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomSqlSessionFactoryBuilder {
	private static SqlSessionFactory sessionFactory;
	
	static {
		String xmlRes = "kr/or/ddit/db/mybatis/Config.xml";
		try(
			Reader reader =	Resources.getResourceAsReader(xmlRes);
		){
			sessionFactory =  new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			throw new RuntimeException(e); 
			// 에러 발생되면 종료되니까. 톰캣에게 넘긴다. 
			// 
		}
	}
	
	public static SqlSessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
