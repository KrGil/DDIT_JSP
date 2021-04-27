package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import kr.or.ddit.validator.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/memberUpdate.do")
//@Controller
public class MemberUpdateController {
	@Inject
	IMemberService service;
	private void addCommandAttribute(Model model) {
		model.addAttribute("command", "update");
	}
	@RequestMapping("/member/memberUpdate.do")
	public String doGet(
				@ModelAttribute("member") MemberVO member,
				HttpSession session,
				Model model){
		addCommandAttribute(model);
		
		// logincheckServlet  에서....
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		
		String authId = authMember.getMem_id();
		member = service.retrieveMember(authId);
		
		String view = "member/memberForm";
		// 자기자신의 정보가 필요하다
		model.addAttribute("member", member);
		
		// memberForm.jsp 재활용 수정으로 사용
//		req.getRequestDispatcher(view).forward(req, resp);
		return view;
	}
	
	@RequestMapping(value="/member/memberUpdate.do", method=RequestMethod.POST)
	public String doPost(
				@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member,
				Errors errors,
				@RequestPart(value="mem_image", required=false) MultipartFile mem_image,
				HttpSession session,
				Model model, 
				HttpServletResponse resp) throws IOException {
		addCommandAttribute(model);
		
//		req.setCharacterEncoding("utf-8");
//		1. 요청 접수
		model.addAttribute("member", member); //문제 생길까바 미리 집어넣음.
		
		// logincheckServlet  에서....
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		// setid ?????
		String authId = authMember.getMem_id();
		member.setMem_id(authId);

		if(mem_image!=null && !mem_image.isEmpty()) {
			String mime = mem_image.getContentType();
			if(!mime.startsWith("image/")) {
				throw new BadRequestException("이미지 이외의 프로필은 처리 불가.");
			}
			byte[] mem_img = mem_image.getBytes();
			member.setMem_img(mem_img);
		}
		// 규칙성 Mem_id와 변수명 mem_id가 같다. 그럼 reflection을 쓸 수 있다.
//		member.setMem_id(req.getParameter("mem_id"));
		
//		2. 검증( 데이터의 목적, 경로에 따라 방법이 달라져야 한다.)
		boolean valid = !errors.hasErrors();
		
		String view = null;
		String message = null;

		if (valid) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				view = "member/memberForm";
				message = "비번 오류";
				break;
			case OK:
				// 업데이트 완료. request 새로 하자.
				view = "redirect:/mypage.do";
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
