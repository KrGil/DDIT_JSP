package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import kr.or.ddit.vo.ProdVO;

public class ProdDAOImplTest {
	private IProdDAO dao = ProdDAOImpl.getInstance();

	//TDD 테스트 하고 반영하고
	@Test
	public void testSelectProd() {
		ProdVO prod = dao.selectProd("P101000001");
		assertNotNull(prod);
		assertEquals(2, prod.getUserList().size());
	}

}
