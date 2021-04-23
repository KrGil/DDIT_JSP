package kr.or.ddit.alba.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.LicenseVO;

public class LicenseDAOImpl implements ILicenseDAO {
	private static LicenseDAOImpl self;
	private LicenseDAOImpl() {
		super();
	}
	public static LicenseDAOImpl getInstance() {
		if(self==null) self = new LicenseDAOImpl();
		return self;
	}
	
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertLicenses(AlbaVO alba, SqlSession session) {
		return session.insert("kr.or.ddit.alba.dao.ILicenseDAO.insertLicenses", alba);
	}

	@Override
	public int deleteLicenses(AlbaVO alba, SqlSession session) {
		return session.insert("kr.or.ddit.alba.dao.ILicenseDAO.deleteLicenses", alba);
	}

	@Override
	public LicenseVO selectLicense(LicenseVO licAlba) {
		try(
			SqlSession session = sqlSessionFactory.openSession();
		){
			ILicenseDAO mapper = session.getMapper(ILicenseDAO.class);
			return mapper.selectLicense(licAlba);
		}
	}

}
