package kr.or.ddit.member.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class MemberListController{
	@Inject
	private IMemberService service;
	
	@RequestMapping("/member/memberView.do")
	public String view(
		@RequestParam("who") String who
		, Model model
	) {
		MemberVO member = service.retrieveMember(who);
		model.addAttribute("member", member);
		return "member/mypage";
	}
	
	@RequestMapping("/member/memberList.do")
	public String memberList(
			@ModelAttribute("searchVO") SearchVO searchVO
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, Model model){
		
		PagingVO<MemberVO> pagingVO = new PagingVO<>(7, 2);
		pagingVO.setCurrentPage(currentPage);
		// 검색 조건
		pagingVO.setSimpleSearch(searchVO);
		
		int totalRecord = service.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<MemberVO> memberList = 
				service.retrieveMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "member/memberList";
	}
}










