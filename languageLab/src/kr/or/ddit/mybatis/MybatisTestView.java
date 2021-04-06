package kr.or.ddit.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.DBPropertyVO;

public class MybatisTestView {
	private static SqlSessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(MybatisTestView.class);
	static {
		String xmlRes = "kr/or/ddit/mybatis/Configuration.xml";
		try(
			Reader reader =  Resources.getResourceAsReader(xmlRes);
		){
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public static void main(String[] args) {
		try(
			SqlSession session = sessionFactory.openSession();
		){
			 IDBPropertyDAO mapper = session.getMapper(IDBPropertyDAO.class);
			 List<DBPropertyVO> propList = mapper.selectDBPropertyList();
			 for(DBPropertyVO tmp : propList) {
				 logger.info("{}", tmp);
			 }
		}
	}
}
