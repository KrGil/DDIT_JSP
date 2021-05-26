package kr.or.ddit.member.controller;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberUserDetails;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MypageController{
	@Inject
	IMemberService service;
	@Inject
	IAuthenticateService authService;
	
	@RequestMapping("/mypage.do")
	public String mypageGet(){
		return "member/passwordForm";
	}
	
	@RequestMapping(value="/mypage.do", method=RequestMethod.POST)
	public String mypagePost(
			@RequestParam("mem_pass") String mem_pass
			, Authentication authentication
			, Model model
			, RedirectAttributes redirectAttributes
	){
		
		MemberVO authMember = 
				((MemberUserDetails) authentication.getPrincipal()).getAdaptee();
		String mem_id = authentication.getName();
		
		ServiceResult result = 
				authService.authenticate(new MemberVO(mem_id, mem_pass));
		String view = null;
		if(ServiceResult.OK.equals(result)) {
			MemberVO detailMember = service.retrieveMember(mem_id);
			
			model.addAttribute("member", detailMember);
			view = "member/mypage";
		}else {
			redirectAttributes.addFlashAttribute("message", "비번 오류");
			view = "redirect:/mypage.do";
		}
		return view;
	}
}











