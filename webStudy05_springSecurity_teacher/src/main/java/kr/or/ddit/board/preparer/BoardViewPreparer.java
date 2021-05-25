package kr.or.ddit.board.preparer;

import java.util.Map;

import javax.inject.Inject;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.vo.MenuVO;

@kr.or.ddit.annotation.ViewPreparer
public class BoardViewPreparer implements ViewPreparer {
	private static final Logger logger =
			LoggerFactory.getLogger(BoardViewPreparer.class);
	
	IMemberDAO dao;
	@Inject
	public void setDao(IMemberDAO dao) {
		this.dao = dao;
		logger.info("{} 주입되었음. ", dao.getClass().getName());
	}

	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		MenuVO menu1 = new MenuVO();
		menu1.setMenuURL("/board/boardInsert.do");
		menu1.setMenuText("새글쓰기");
		Map<String, Object> requestScope = 
				tilesContext.getContext(Request.REQUEST_SCOPE);
		requestScope.put("menu", menu1);

	}

}
