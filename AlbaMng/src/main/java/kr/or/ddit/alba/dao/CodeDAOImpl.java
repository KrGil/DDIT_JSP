package kr.or.ddit.alba.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.LicenseVO;

public class CodeDAOImpl implements ICodeDAO {
	
	private static CodeDAOImpl self;
	private CodeDAOImpl() {
		super();
	}
	public static CodeDAOImpl getInstance() {
		if(self==null) self = new CodeDAOImpl();
		return self;
	}
	

	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	@Override
	public List<LicenseVO> selectLicenseList() {
		try(
			SqlSession session = sqlSessionFactory.openSession();
		){
			ICodeDAO mapper = session.getMapper(ICodeDAO.class);
			return mapper.selectLicenseList();
		}
	}

	@Override
	public List<Map<String, String>> selectGradeList() {
		try(
			SqlSession session = sqlSessionFactory.openSession();
		){
			ICodeDAO mapper = session.getMapper(ICodeDAO.class);
			return mapper.selectGradeList();
		}	
	}

}
