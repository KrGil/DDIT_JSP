package kr.or.ddit.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberDeleteController{
	@Inject
	private IMemberService service;
	@RequestMapping(value="/member/memberDelete.do", method=RequestMethod.POST)
	public String memberDelete(
			@RequestParam("password") String password
			, HttpSession session
			, RedirectAttributes redirectAttributes
	){
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String authId = authMember.getMem_id();
		ServiceResult result =
				service.removeMember(new MemberVO(authId, password));
		String view = null;
		switch (result) {
		case INVALIDPASSWORD:
			view = "redirect:/mypage.do";
			redirectAttributes.addFlashAttribute("message", "비번 오류");
			break;
		case OK:
			session.invalidate();
			view = "redirect:/";
			break;
		default:
			view = "redirect:/mypage.do";
			redirectAttributes.addFlashAttribute("message", "서버 오류");
			break;
		}
		
		return view;
	}
}










