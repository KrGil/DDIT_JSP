package kr.or.ddit.container.auto.dao;

import org.springframework.stereotype.Repository;

@Repository
public class SampleDAOImpl implements ISampleDAO {

	@Override
	public String selectData(String pk) {
		// TODO Auto-generated method stub
		return "조회된 raw data";
	}

}
