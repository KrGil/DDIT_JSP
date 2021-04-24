package kr.or.ddit.alba.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.LicenseVO;

public interface ILicenseDAO {
	public int insertLicenses(AlbaVO alba, SqlSession session);
	public int deleteLicenses(AlbaVO alba, SqlSession session);
	public LicenseVO selectLicense(LicenseVO licAlba);
}
