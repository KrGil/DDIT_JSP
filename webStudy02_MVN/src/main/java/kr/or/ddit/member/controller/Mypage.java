package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.UserNotFoundException;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/mypage.do")
@Controller
public class Mypage {
	kr.or.ddit.member.service.IAuthenticateService authService = new AuthenticateServiceImpl();
	private IMemberService service = new MemberServiceImpl();
	
	@RequestMapping("/mypage.do")
	public String form(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "member/passwordForm";
		return view;
	}
	@RequestMapping(value="/mypage.do",method=RequestMethod.POST)
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mem_pass = req.getParameter("mem_pass");
		if (mem_pass == null || mem_pass.isEmpty()) {
			resp.sendError(400);
			return null;
		}
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String mem_id = authMember.getMem_id();
		ServiceResult result = authService.authenticate(new MemberVO(mem_id, mem_pass));

		String view = null;
		if (ServiceResult.OK.equals(result)) {
			MemberVO detailMember = service.retrieveMember(mem_id);

			req.setAttribute("member", detailMember);
			view = "member/mypage";
		} else {
			session.setAttribute("message", "비번 오류");
			// get방식으로 다시 여기 거쳐서 passwordform으로.
			view = "redirect:/mypage.do";
		}
		return view;

	}
}
