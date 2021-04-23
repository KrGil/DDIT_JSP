package kr.or.ddit.alba.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.LicenseVO;

public interface ICodeDAO {
	public List<LicenseVO> selectLicenseList();
	public List<Map<String, String>> selectGradeList();
}
