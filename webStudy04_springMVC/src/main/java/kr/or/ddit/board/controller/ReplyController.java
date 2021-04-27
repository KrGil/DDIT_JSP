package kr.or.ddit.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.Reply2VO;

//@Controller
//@ResponseBody
@RestController
@RequestMapping(
		value="/reply/{bo_no}"
		, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
public class ReplyController {
	
	@Inject
	private IReplyService service;
	
	// 목록 조회
//	@RequestMapping(method= RequestMethod.GET)
	@GetMapping
	public List<Reply2VO> list(){
		List<Reply2VO> list = service.retrieveListReply();
		return list;
	}
	// 글쓰기
	@RequestMapping(value="{bo_no}.do", method= RequestMethod.POST)
	@PutMapping
	public List<Reply2VO> insert(
				@ModelAttribute("reply") Reply2VO reply
				, @PathVariable int bo_no ) {
		service.createReply(reply);
		return list();
	}
	// 수정
	public List<Reply2VO> update() {
		return null;
	}
	// 지우기
	public List<Reply2VO> delete() {
		return null;
	}
	
	// 덧글의 페이징처리
//	@RequestMapping(value="/board/boardList.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public PagingVO<BoardVO> listForAjax(
//		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
//		, @RequestParam(value="searchType", required=false) String searchType
//		, @RequestParam(value="searchWord", required=false) String searchWord
//		, @RequestParam(value="minDate", required=false) String minDate
//		, @RequestParam(value="maxDate", required=false) String maxDate
////		, @ModelAttribute("searchVO") SearchVO searchVO
//	) {
//		PagingVO<BoardVO> pagingVO = new PagingVO<>(7, 5);
//		pagingVO.setCurrentPage(currentPage);
//		// 검색 조건
//		Map<String, Object> searchMap = new HashMap<>();
//		searchMap.put("searchType", searchType);
//		searchMap.put("searchWord", searchWord);
//		searchMap.put("minDate", minDate);
//		searchMap.put("maxDate", maxDate);
//		pagingVO.setSearchMap(searchMap);
//		
////		pagingVO.setSimpleSearch(searchVO);
//		
//		int totalRecord = service.retrieveBoardCount(pagingVO);
//		pagingVO.setTotalRecord(totalRecord);
//		
//		List<BoardVO> boardList = 
//				service.retrieveBoardList(pagingVO);
//		for(BoardVO tmp : boardList) {
//			String source = tmp.getBo_content();
//			if(source == null) {
//				continue;
//			}
//			Document dom = Jsoup.parse(source);
//			Elements imgs = dom.getElementsByTag("img");
//			String thumbnail = application.getContextPath() + "/images/1.jfif";
//			if(!imgs.isEmpty()) {
//				Element img = imgs.get(0);
//				thumbnail = img.attr("src");
//			}
//			tmp.setThumbnail(thumbnail);
//		}
//		
//		pagingVO.setDataList(boardList);
//		
//		return pagingVO;
//	}
}
