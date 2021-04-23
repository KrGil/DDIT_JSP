package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;

@Controller
public class AlbaRetrieveController {
	
	IAlbaService service = AlbaServiceImpl.getInstance();

	@RequestMapping("/")
	public String index(
			@ModelAttribute("searchDetail") AlbaVO searchDetail, 
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(value="searchType", required=false) String searchType,
			@RequestParam(value="searchWord", required=false) String searchWord,
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return list(searchDetail, currentPage, searchType, searchWord, req, resp);	
	}
	
	@RequestMapping("/alba/albaView.do")
	public String view(@RequestParam("who") String alId, HttpServletRequest req) {
		AlbaVO alba = service.retrieveAlba(alId);
		req.setAttribute("alba", alba);
		req.setAttribute("viewName", "alba/albaView");
		return "template";
	}
	
	@RequestMapping("/alba/albaList.do")
	public String list( @ModelAttribute("searchDetail") AlbaVO searchDetail, 
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(value="searchType", required=false) String searchType,
			@RequestParam(value="searchWord", required=false) String searchWord,
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PagingVO<AlbaVO> pagingVO = new PagingVO<>();
		// 검색 조건
		if(StringUtils.isNotBlank(searchWord) && StringUtils.isNotBlank(searchType)) {
			switch (searchType) {
				case "name":
					searchDetail.setAlName(searchWord.trim());
					break;
				case "address":
					searchDetail.setAlAdd1(searchWord.trim());
					break;
				case "career":
					searchDetail.setAlCareer(searchWord.trim());
					break;
			}
		}
		pagingVO.setSearchDetail(searchDetail);
		
		int totalRecord = service.retrieveAlbaCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord); // totalPage
		pagingVO.setCurrentPage(currentPage); // startRow, endRow, startPage, endPage
		
		List<AlbaVO> AlbaList = service.retrieveAlbaList(pagingVO);
		pagingVO.setDataList(AlbaList);
		
		String accept = req.getHeader("Accept");
		String goPage = null;
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try(
					PrintWriter out = resp.getWriter();	
					){
				mapper.writeValue(out, pagingVO);
			}
		}else {
			req.setAttribute("pagingVO", pagingVO);
			req.setAttribute("viewName", "alba/albaList");
			goPage = "template";
		}
		return goPage;
	}
}
