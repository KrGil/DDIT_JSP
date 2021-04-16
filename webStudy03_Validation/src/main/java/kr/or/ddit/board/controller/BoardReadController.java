package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class BoardReadController {
	private static final Logger logger = LoggerFactory.getLogger(BoardReadController.class);
	IBoardService service = BoardServiceImpl.getInstance();
	
	@RequestMapping("/board/boardView.do")
	public String viewForAjax(
			@RequestParam("what") int what,
			HttpServletRequest req,
			HttpServletResponse resp
			) throws IOException {
		BoardVO search = new BoardVO();
		search.setBo_no(what);
		BoardVO board = service.retrieveBoard(search);
		
		String accept = req.getHeader("Accept");
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			// text로 보내기
//			resp.setContentType("text/plain;charset=utf-8");
//			try(
//				PrintWriter out = resp.getWriter();
//				){
//				out.println(board.getBo_content());
//				return null;
//			}
			// json으로 받아오기
			resp.setContentType("application/json;charset=utf-8");
			// 마샬링과 직렬화
			ObjectMapper mapper = new ObjectMapper();
			try(
				PrintWriter out =resp.getWriter();
			){
				mapper.writeValue(out, board);
				return null;
			}
		}else {
			resp.setContentType("text/plain;charset=utf-8");
			req.setAttribute("board", board);
			return "board/boardView";
		}
	}
	
	
	@RequestMapping("/board/boardList.do")
	public String list(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(value="searchType", required=false) String searchType,
			@RequestParam(value="searchWord", required=false) String searchWord,
			@RequestParam(value="minDate", required=false) String minDate,
			@RequestParam(value="maxDate", required=false) String maxDate,
			HttpServletRequest req	) {
		
		PagingVO<BoardVO> pagingVO = new PagingVO(10, 5);
		pagingVO.setCurrentPage(currentPage);
		
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);
		searchMap.put("minDate", minDate);
		searchMap.put("maxDate", maxDate);
		
		pagingVO.setSearchMap(searchMap);
		
		int totalRecord = service.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<BoardVO> boardList = service.retrieveBoardList(pagingVO);
		pagingVO.setDataList(boardList);
		
		req.setAttribute("pagingVO", pagingVO);
		return "/board/boardList";
	}
}
