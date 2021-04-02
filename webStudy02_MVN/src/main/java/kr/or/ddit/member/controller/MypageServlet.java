package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/mypage.do")
public class MypageServlet extends HttpServlet{
	private IMemberService service = new MemberServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		MemberVO member = new MemberVO();
		member = (MemberVO) session.getAttribute("authMember");
		member = service.retrieveMember(member.getMem_id());
		
		System.out.println(member.getMem_id());
		
		req.setAttribute("memberInfo", member);
		req.getRequestDispatcher("WEB-INF/views/member/mypage.jsp").forward(req, resp);
	}
}
