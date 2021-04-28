package kr.or.ddit.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.validator.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberUpdateController{
	@Inject
	private IMemberService service;
	
	private void addCommandAttribute(Model model) {
		model.addAttribute("command", "update");
	}
	
	@RequestMapping("/member/memberUpdate.do")
	public String updateForm(HttpSession session, Model model){
		addCommandAttribute(model);
		MemberVO authMember =  (MemberVO) session.getAttribute("authMember");
		String authId = authMember.getMem_id();
 		MemberVO member = service.retrieveMember(authId);
 		model.addAttribute("member", member);
 		return "member/memberForm";
	}
	
	@RequestMapping(value="/member/memberUpdate.do", method=RequestMethod.POST)
	public String doPost(
			@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member
			, Errors errors
			, HttpSession session
			, Model model
	){
		
		addCommandAttribute(model);
		
//		1. 요청 접수
		MemberVO authMember =  (MemberVO) session.getAttribute("authMember");
		String authId = authMember.getMem_id();
		member.setMem_id(authId);
		
//		if(mem_image!=null && !mem_image.isEmpty()) {
//			String mime = mem_image.getContentType();
//			if(!mime.startsWith("image/")) {
//				throw new BadRequestException("이미지 이외의 프로필은 처리 불가.");
//			}
//			byte[] mem_img = mem_image.getBytes();
//			member.setMem_img(mem_img);
//		}	
		
		String view = null;
		String message = null;
		if (!errors.hasErrors()) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				view = "member/memberForm";
				message = "비번 오류";
				break;
			case OK:
				view = "redirect:/mypage.do";
				break;
			default:
				message = "서버 오류, 잠시 뒤 다시 시도하세요.";
				view = "member/memberForm";
				break;
			}
		} else {
			// 검증 불통
			view = "member/memberForm";
		}

		model.addAttribute("message", message);

		return view;
	}
}










