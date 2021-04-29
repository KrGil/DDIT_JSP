package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.or.ddit.TestWebAppConfiguration;
import kr.or.ddit.vo.ProdVO;

@RunWith(SpringJUnit4ClassRunner.class)
@TestWebAppConfiguration
public class ProdDAOImplTest {
	@Inject
	private IProdDAO dao;
	
	//TDD 테스트 하고 반영하고
	@Test
	public void testSelectProd() {
		ProdVO prod = dao.selectProd("P101000001");
		assertNotNull(prod);
		assertEquals(2, prod.getUserList().size());
	}

}
