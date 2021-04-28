package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberInsertController{
	@Inject
	private IMemberService service;

	@RequestMapping("/member/memberInsert.do")
	public String form(){
		return "member/memberForm";
	}

	@RequestMapping(value="/member/memberInsert.do", method=RequestMethod.POST)
	public String process(
			@Validated(InsertGroup.class) @ModelAttribute("member") MemberVO member
			, BindingResult errors
			, Model model) throws IOException {
		String view = null;
		String message = null;
		if (!errors.hasErrors()) {
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				view = "member/memberForm";
				message = "아이디 중복";
				break;
			case OK:
				view = "redirect:/login/loginForm.jsp";
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
