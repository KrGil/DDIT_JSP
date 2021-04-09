package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.validator.CommonValidator;
import kr.or.ddit.validator.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/memberUpdate.do")
@Controller
public class MemberUpdateController {
	IMemberService service = new MemberServiceImpl();
	private void addCommandAttribute(HttpServletRequest req) {
		req.setAttribute("command", "update");
	}
	@RequestMapping("/member/memberUpdate.do")
	public String doGet(
				@ModelAttribute("member") MemberVO member,
				HttpSession session,
				HttpServletRequest req, HttpServletResponse resp){
		addCommandAttribute(req);
		
		// logincheckServlet  에서....
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		
		String authId = authMember.getMem_id();
		member = service.retrieveMember(authId);
		
		String view = "member/memberForm02_ajax";
		// 자기자신의 정보가 필요하다
		req.setAttribute("member", member);
		
		// memberForm.jsp 재활용 수정으로 사용
//		req.getRequestDispatcher(view).forward(req, resp);
		return view;
	}
	
	@RequestMapping(value="/member/memberUpdate.do", method=RequestMethod.POST)
	public String doPost(
				@ModelAttribute("member") MemberVO member,
				HttpSession session,
				HttpServletRequest req, HttpServletResponse resp) {
		addCommandAttribute(req);
		
//		req.setCharacterEncoding("utf-8");
//		1. 요청 접수
		req.setAttribute("member", member); //문제 생길까바 미리 집어넣음.
		
		// logincheckServlet  에서....
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		// setid ?????
		String authId = authMember.getMem_id();
		member.setMem_id(authId);
		
		// 규칙성 Mem_id와 변수명 mem_id가 같다. 그럼 reflection을 쓸 수 있다.
//		member.setMem_id(req.getParameter("mem_id"));
		
//		2. 검증( 데이터의 목적, 경로에 따라 방법이 달라져야 한다.)
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		boolean valid = new CommonValidator<MemberVO>().validate(member, errors, UpdateGroup.class);
		
		String view = null;
		String message = null;

		if (valid) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				view = "member/memberForm02_ajax";
				message = "비번 오류";
				break;
			case OK:
				// 업데이트 완료. request 새로 하자.
				view = "redirect:/mypage.do";
				break;
			default:
				message = "서버 오류, 잠시 후 다시 시도해주세요.";
				view = "member/memberForm02_ajax";
				break;
			}
		} else {
			// 검증 불통
			view = "member/memberForm02_ajax";
		}

		req.setAttribute("message", message);
		return view;
	}
}
