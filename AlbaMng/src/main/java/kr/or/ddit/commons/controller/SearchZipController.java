package kr.or.ddit.commons.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.commons.service.ISearchZipService;
import kr.or.ddit.commons.service.SearchZipServiceImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.utils.JsonResponseUtils;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.ZipVO;

@Controller
public class SearchZipController{
	private ISearchZipService service = SearchZipServiceImpl.getInstance();
	
	@RequestMapping("/commons/searchZip.do")
	public String doGet(
			@RequestParam(value="draw", required=false, defaultValue="1") String draw
			, @RequestParam(value="length", required=false, defaultValue="7") int screenSize
			, @RequestParam(value="start", required=false, defaultValue="0") String start
			, @RequestParam(value="search[value]", required=false) String searchWord
			, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int currentPage = StringUtils.isNumeric(start)?Integer.parseInt(start)/screenSize + 1 : 1;
			
		PagingVO<ZipVO> pagingVO = new PagingVO<>(screenSize, 5);
		// 검색 전 레코드 수
		int recordsTotal = service.retrieveZipCount(pagingVO);
		
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(new SearchVO(null, searchWord));
		// 검색 후 레코드 수
		int recordsFiltered = service.retrieveZipCount(pagingVO);
		
		List<ZipVO> zipList = service.retrieveZipList(pagingVO);
		
		req.setAttribute("draw", draw);
		req.setAttribute("recordsTotal", recordsTotal);
		req.setAttribute("recordsFiltered", recordsFiltered);
		req.setAttribute("data", zipList);
		
		
		JsonResponseUtils.toJsonResponse(req, resp);
		
		return null;	
	}
}













