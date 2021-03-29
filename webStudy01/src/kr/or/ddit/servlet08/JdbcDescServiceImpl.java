package kr.or.ddit.servlet08;

import java.util.List;
import java.util.Map;

import kr.or.ddit.servlet08.dao.IJdbcDescService;
import kr.or.ddit.servlet08.dao.JdbcDescDAO;

public class JdbcDescServiceImpl implements IJdbcDescService{
	JdbcDescDAO dao = new JdbcDescDAO();
	
	@Override
	public List<Map<String, Object>> retrieveDataBaseProperties(Map<String, Object> pMap) {
		List<Map<String, Object>> dataList = dao.selectDataBaseProperties(pMap);
		for(Map<String, Object> record : dataList) {
			String pName = (String) record.get("PROPERTY_NAME");
			record.put("PROPERTY_NAME",  pName.toLowerCase());
		}
		return dataList;
	}
}
