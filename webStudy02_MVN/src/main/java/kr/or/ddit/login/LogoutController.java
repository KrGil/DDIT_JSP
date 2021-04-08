package kr.or.ddit.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.controller.RequestMapping;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMethod;

//@WebServlet("/login/logout.do")
@Controller
public class LogoutController{
	@RequestMapping(value="/login/logout.do", method=RequestMethod.POST)
	public String logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 검증을 해야한다.
		HttpSession session =  req.getSession();
		// 로그인 없이 이 페이지로 왔다면
		if(session.isNew()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		// 모든 세션 데이터를 지우고 세션을 만료시킨다.
		session.invalidate();
		// invalidate 이후의 session 선언은 아무런 소용이 없다.
//		session.setAttribute("message", "로그아웃 성공");
		
		
		// 세션이 끝났다는 말은 request가 끝났다는 말이나 같다.
		String view = "redirect:/";
		return view;
	}
}
