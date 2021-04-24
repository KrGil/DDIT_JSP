package kr.or.ddit.buyer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

//@WebServlet("/member/memberList.do")
@Controller
public class MemberListController {
	IBuyerService service = new BuyerServiceImpl();
	
	@RequestMapping("/member/memberList.do")
	public String MemberList(
			@ModelAttribute(value="search") SearchVO searchVO,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			HttpServletRequest req, HttpServletResponse resp){
		
		PagingVO<BuyerVO> pagingVO = new PagingVO(7, 2);
		pagingVO.setCurrentPage(currentPage);
		// 검색 조건 먼저 담아주어야함.
		pagingVO.setSimpleSearch(searchVO);
		
		int totalRecord = service.retrieveBuyerCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<BuyerVO> memberList =  service.retrieveBuyerList(pagingVO);
		pagingVO.setDataList(memberList);
		
		req.setAttribute("pagingVO", pagingVO);
		
		String view = "member/memberList";
		return view;
	}
}
