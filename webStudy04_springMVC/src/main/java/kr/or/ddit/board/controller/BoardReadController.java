package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.exception.BadRequestException;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Controller
public class BoardReadController {
	public static final String BOARDAUTH = "board.authenticated";
	@Inject
	private IBoardService service;
	@Inject
	private WebApplicationContext container;
	private ServletContext application;
	@PostConstruct
	public void init() {
		application = container.getServletContext();
	}
	
	@RequestMapping(value="/board/authenticate.do", method=RequestMethod.POST)
	public String boardAuth(
		@RequestParam("bo_no") int bo_no
		, @RequestParam("bo_pass") String bo_pass
		, HttpSession session
	) {
		BoardVO search = new BoardVO();
		search.setBo_no(bo_no);
		search.setBo_pass(bo_pass);
		String view = null;
		if(service.boardAuthenticate(search)) {
			session.setAttribute(BOARDAUTH, search);
			view = "redirect:/board/boardView.do?what="+bo_no;
		}else {
			session.setAttribute("message", "비밀번호 오류");
			view = "redirect:/board/boardList.do";
		}
		return view;
	}
	
	@RequestMapping("/board/boardView.do")
	public String view(
		@RequestParam("what") int bo_no	
		, @ModelAttribute("search") BoardVO search
		, Model model
		, HttpSession session
	) {
		search.setBo_no(bo_no);
		BoardVO board = service.retrieveBoard(search);
		
		boolean valid = true;
		if("Y".equals(board.getBo_sec())) {
			BoardVO authenticated = 
					(BoardVO) session.getAttribute(BOARDAUTH);
			if(authenticated==null || authenticated.getBo_no() != bo_no) {
				valid = false;
			}
		}
		
		String view = null;
		if(valid) {
			model.addAttribute("board", board);
			view = "board/boardView";
		}else {
			view = "board/passwordForm";
		}
		return view;
	}
	
	@RequestMapping(value="/board/boardView.do"
			, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String preview(
			@RequestParam("what") int bo_no
			, @ModelAttribute("search") BoardVO search
			, HttpSession session
		) throws IOException{
		search.setBo_no(bo_no);
		BoardVO board = service.retrieveBoard(search);
		
		boolean valid = true;
		if("Y".equals(board.getBo_sec())) {
			BoardVO authenticated = 
					(BoardVO) session.getAttribute(BOARDAUTH);
			if(authenticated==null || authenticated.getBo_no() != bo_no) {
				valid = false;
			}
		}
		
		if(valid) {
			session.removeAttribute(BOARDAUTH);
			return board.getBo_content();
		}else {
			throw new BadRequestException("비밀글은 미리보기 불가.");
		}
	}
	
	@RequestMapping(value="/board/noticeList.do"
			, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<BoardVO> noticeListForAjax(
			@RequestParam(value="searchType", required=false) String searchType
			, @RequestParam(value="searchWord", required=false) String searchWord
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(value="minDate", required=false) String minDate
			, @RequestParam(value="maxDate", required=false) String maxDate
			, HttpServletRequest req
			) {
		searchType = "type";
		searchWord = "NOTICE";
		return listForAjax(currentPage, searchType, 
				searchWord, minDate, maxDate);
	}
	@RequestMapping("/board/noticeList.do")
	public String noticeList(
			@RequestParam(value="searchType", required=false) String searchType
			, @RequestParam(value="searchWord", required=false) String searchWord
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(value="minDate", required=false) String minDate
			, @RequestParam(value="maxDate", required=false) String maxDate
			, Model model
			) {
		searchType = "type";
		searchWord = "NOTICE";
		return listForHTML(currentPage, searchType, 
				searchWord, minDate, maxDate, model);
	}
	
	@RequestMapping(value={"/board/boardList.do"})
	public String listForHTML(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(value="searchType", required=false) String searchType
			, @RequestParam(value="searchWord", required=false) String searchWord
			, @RequestParam(value="minDate", required=false) String minDate
			, @RequestParam(value="maxDate", required=false) String maxDate
//			, @ModelAttribute("searchVO") SearchVO searchVO
			, Model model
		) {
		PagingVO<BoardVO> pagingVO = listForAjax(currentPage, searchType, searchWord, minDate, maxDate);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "board/boardList";
		
	}
	
	@RequestMapping(value="/board/boardList.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<BoardVO> listForAjax(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @RequestParam(value="searchType", required=false) String searchType
		, @RequestParam(value="searchWord", required=false) String searchWord
		, @RequestParam(value="minDate", required=false) String minDate
		, @RequestParam(value="maxDate", required=false) String maxDate
//		, @ModelAttribute("searchVO") SearchVO searchVO
	) {
		PagingVO<BoardVO> pagingVO = new PagingVO<>(7, 5);
		pagingVO.setCurrentPage(currentPage);
		// 검색 조건
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);
		searchMap.put("minDate", minDate);
		searchMap.put("maxDate", maxDate);
		pagingVO.setSearchMap(searchMap);
		
//		pagingVO.setSimpleSearch(searchVO);
		
		int totalRecord = service.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<BoardVO> boardList = 
				service.retrieveBoardList(pagingVO);
		for(BoardVO tmp : boardList) {
			String source = tmp.getBo_content();
			if(source == null) {
				continue;
			}
			Document dom = Jsoup.parse(source);
			Elements imgs = dom.getElementsByTag("img");
			String thumbnail = application.getContextPath() + "/images/cat1.jpg";
			if(!imgs.isEmpty()) {
				Element img = imgs.get(0);
				thumbnail = img.attr("src");
			}
			tmp.setThumbnail(thumbnail);
		}
		
		pagingVO.setDataList(boardList);
		
		return pagingVO;
	}
}
