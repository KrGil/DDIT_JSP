package kr.or.ddit.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/loginCheck.do")
public class LoginCheckServlet2_mvc2 extends HttpServlet {
	private IAuthenticateService service = new AuthenticateServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		// 로그인 한적도 없는 놈이 여기 접근하려고 한다.
		if(session.isNew()) {
			resp.sendError(400);
			return;
		}
		// 요청 분석
		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");
		String view = null;
		boolean redirect = false;
		String message = null;
		boolean valid = validate(mem_id, mem_pass);
		String saveId = req.getParameter("saveId");
		
		// 인증을 실패했다면 무조건 redirect로 보내는게 원칙.
		// 왜냐하면 로그인에 실패했다면 본인이 아닐 가능성이 높기때문
		if(valid) {
			//	인증(id == password)
			MemberVO member = new MemberVO(mem_id, mem_pass);
			ServiceResult result = service.authenticate(member);
			switch (result) {
			case OK :
				// 인증 성공시 index.jsp 로 이동(현재 요청 정보 삭제). //redirect
				redirect = true;
				view = "/";
				session.setAttribute("authMember", member);
				Cookie idCookie = new Cookie("idCookie", mem_id);
				idCookie.setPath(req.getContextPath());
				int maxAge = 0;
				if("saveId".equals(saveId)) {
					maxAge =(60*60*24*7);
				}
				idCookie.setMaxAge(maxAge);
				resp.addCookie(idCookie);
				break;
			case NOTEXIST  :
				redirect = true;
				//	인증 실패시 loginForm.jsp로 이동
				view = "/login/loginForm.jsp";
				//  2) 인증 실패(아이디 상태 유지)
				message = "아이디 오류";
				break;
			case INVALIDPASSWORD:
				redirect = true;
				//	인증 실패시 loginForm.jsp로 이동
				view = "/login/loginForm.jsp";
				//  2) 인증 실패(아이디 상태 유지)
				message = "비번 오류";
				session.setAttribute("failedId", mem_id);
				break;
			}
		}else {
			//	1) 검증
			redirect = true;
			view = "/login/loginForm.jsp";
			message = "아이디나 비번 누락";
		}
		
//		view로 이동
		if(redirect) { // redirect는 request를 날려버림 html의 statless의 특성
			// 그래서 session과 cookies를 사용
			req.getSession().setAttribute("message", message);
			resp.sendRedirect(req.getContextPath() + view);
		}else {
			req.setAttribute("message", message);
			req.getRequestDispatcher(view).forward(req, resp);
		}
	}
	
	// 검증 제대로 값이 넘어왔는가
	private boolean validate(String mem_id, String mem_pass) {
		boolean valid = true;
		valid = !(mem_id ==null || mem_id.isEmpty() || 
				mem_pass== null || mem_pass.isEmpty());
		return valid;
	}
}

	