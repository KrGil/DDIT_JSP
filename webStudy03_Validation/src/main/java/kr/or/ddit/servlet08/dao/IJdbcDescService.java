package kr.or.ddit.servlet08.dao;

import java.util.List;
import java.util.Map;

public interface IJdbcDescService {
	public List<Map<String, Object>> retrieveDataBaseProperties(Map<String, Object> pMap);
}
