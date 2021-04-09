package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

//@WebServlet("/member/memberList.do")
@Controller
public class MemberListController {
	IMemberService service = new MemberServiceImpl();
	
	@RequestMapping("/member/memberList.do")
	public String MemberList(
			@ModelAttribute(value="search") SearchVO searchVO,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			HttpServletRequest req, HttpServletResponse resp){
		
		PagingVO<MemberVO> pagingVO = new PagingVO(7, 2);
		pagingVO.setCurrentPage(currentPage);
		// 검색 조건 먼저 담아주어야함.
		pagingVO.setSimpleSearch(searchVO);
		
		int totalRecord = service.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<MemberVO> memberList =  service.retrieveMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		
		req.setAttribute("pagingVO", pagingVO);
		
		String view = "member/memberList";
		return view;
	}
}
