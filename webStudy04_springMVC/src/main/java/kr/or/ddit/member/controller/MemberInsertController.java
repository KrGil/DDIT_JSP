package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.BadRequestException;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.vo.MemberVO;

//@Controller
public class MemberInsertController {
	@Inject
	private IMemberService service;
	
	@RequestMapping("/member/memberInsert.do")
	public String form()  {
		String view = "member/memberForm";
		return view;
	}

	@RequestMapping(value = "/member/memberInsert.do", method =RequestMethod.POST)
	public String process(
			@Validated(InsertGroup.class) @ModelAttribute("member") MemberVO member
			, Errors errors
			,@RequestPart(value="mem_image", required=false) MultipartFile mem_image,
			Model model, 
			HttpServletResponse resp) throws ServletException, IOException {
//		Locale.setDefault(Locale.ENGLISH);
//		1. 요청 접수
		//ModelAttributeArgumentResolver에서 처리하게끔
		
		// member에 image를 바이트배열로 바꾼걸 세팅하기
		if(mem_image!=null && !mem_image.isEmpty()) {
			String mime = mem_image.getContentType();
			if(!mime.startsWith("image/")) {
				throw new BadRequestException("이미지 이외의 프로필은 처리 불가.");
			}
			byte[] mem_img = mem_image.getBytes();
			member.setMem_img(mem_img);
		}
		
//		2. 검증( 데이터의 목적, 경로에 따라 방법이 달라져야 한다.)
//		Map<String, List<String>> errors = new LinkedHashMap<>();
//		req.setAttribute("errors", errors);
		
		
		boolean valid =!errors.hasErrors();
		
		String view = null;
		String message = null;

		if (valid) {
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
				message = "서버 오류, 잠시 후 다시 시도해주세요.";
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
