package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.TestWebAppConfiguration;
import kr.or.ddit.vo.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@TestWebAppConfiguration
public class IBoardDAOTest {

	@Inject
	private IBoardDAO dao;
	@Inject
	private WebApplicationContext container;
	
	@Test
	public void testSelectBoard() {
		BoardVO search = new BoardVO();
		search.setBo_no(1522);
		BoardVO board = dao.selectBoard(search);
		assertEquals(2, board.getAttatchList().size());
		
		search.setBo_no(499);
		board = dao.selectBoard(search);
		assertEquals(0, board.getAttatchList().size());
	}

}
