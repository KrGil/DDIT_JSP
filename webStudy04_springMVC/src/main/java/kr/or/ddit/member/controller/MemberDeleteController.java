package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/memberDelete.do")
//@Controller
public class MemberDeleteController{
	@Inject
	IMemberService service;
	
	private void addCommandAttribute(HttpServletRequest req) {
		req.setAttribute("command", "update");
	}
	
	@RequestMapping(value="/member/memberDelete.do", method=RequestMethod.POST)
	public String delete(
			@RequestParam(value="password") String password,
			HttpSession session,
			HttpServletResponse resp) throws ServletException, IOException {

		// session에 담긴 값 가져오기
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String authId = authMember.getMem_id();
		ServiceResult result = service.removeMember(new MemberVO(authId, password));
		
		String view = null;
		
		switch (result) {
		case INVALIDPASSWORD:
			// 딜리트 완료. request 새로 하자.
			view = "redirect:/mypage.do";
			session.setAttribute("message", "비번오류");
			break;
		case OK:
			// 딜리트 완료. request 새로 하자.
			session.invalidate();
			view = "redirect:/";
			break;
		default:
			view = "redirect:/mypage.do";
			session.setAttribute("message", "서버오류");
			break;
		}
		
		return view;
//		boolean redirect = view.startsWith("redirect:");
//		if (redirect) {
//			view = view.substring("redirect:".length());
//			resp.sendRedirect(req.getContextPath() + view);
//		} else {
//			req.getRequestDispatcher(view).forward(req, resp);
//		}
	}
	private boolean validate(String authPass, String pass, Map<String, String> errors) {
		boolean valid = true;
		if (!pass.equals(authPass)) {
			valid = false;
			errors.put("mem_pass", "비밀번호 오기입");
		}
		return valid;
	}

}
