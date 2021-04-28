package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BuyerVO;

@Repository
public class OthersDAOImpl implements IOthersDAO {
	@Inject
	private SqlSessionTemplate template;

	@Override
	public List<Map<String, Object>> selectLprodList() {
		IOthersDAO mapper = template.getMapper(IOthersDAO.class);
		return mapper.selectLprodList();
	}

	@Override
	public List<BuyerVO> selectBuyerList(String buyer_lgu) {
		IOthersDAO mapper = template.getMapper(IOthersDAO.class);
		return mapper.selectBuyerList(buyer_lgu);
	}

}
