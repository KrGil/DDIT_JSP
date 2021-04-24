package kr.or.ddit.alba.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;

public class IAlbaDAOTest {
	
	IAlbaDAO dao = AlbaDAOImpl.getInstance();
	PagingVO<AlbaVO> pagingVO = new PagingVO<>();
	@Before
	public void setup() {
		pagingVO.setCurrentPage(1);
	}

	@Test
	public void testInsertAlba() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectAlbaCount() {
		int totalRecord = dao.selectAlbaCount(pagingVO);
		System.out.println(totalRecord);
	}

	@Test
	public void testSelectAlbaList() {
		List<AlbaVO> list = dao.selectAlbaList(pagingVO);
		System.out.println(list.size());
	}

	@Test
	public void testSelectAlba() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAlba() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAlba() {
		fail("Not yet implemented");
	}

}
