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
import kr.or.ddit.service.AuthenticateServiceImpl;
import kr.or.ddit.service.IAuthenticateService;
import kr.or.ddit.service.IMemberService;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/mypage.do")
public class MypageServlet_sam extends HttpServlet{
	private IMemberService service = new MemberServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String mem_id = authMember.getMem_id();
		try {
			MemberVO detailMember = service.retrieveMember(mem_id);
			req.setAttribute("member", detailMember);
			String view = "/WEB-INF/views/member/mypage.jsp";
			req.getRequestDispatcher(view).forward(req, resp);
		} catch (UserNotFoundException e) {
//			resp.sendError(400);
			// 500으로 다시 바꿔보기
			throw new IOException(e);
		}
	}
}
