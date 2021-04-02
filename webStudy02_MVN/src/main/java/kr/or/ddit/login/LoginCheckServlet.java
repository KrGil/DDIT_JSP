package kr.or.ddit.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/login/loginCheck.do")
public class LoginCheckServlet extends HttpServlet {
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
		String message = "null";
		boolean valid = validate(mem_id, mem_pass);
		
		// 인증을 실패했다면 무조건 redirect로 보내는게 원칙.
		// 왜냐하면 로그인에 실패했다면 본인이 아닐 가능성이 높기때문
		
		if(valid) {
			//	인증(id == password)
			boolean auth = authenticate(mem_id, mem_pass);
			if(auth) {
			// 인증 성공시 index.jsp 로 이동(현재 요청 정보 삭제). //redirect
				redirect = true;
				view = "/";
				session.setAttribute("authId", mem_id);
			}else {
				redirect = true;
				//	인증 실패시 loginForm.jsp로 이동
				view = "/login/loginForm.jsp";
				//  2) 인증 실패(아이디 상태 유지)
				message = "비번 오류";
				session.setAttribute("failedId", mem_id);
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
	// 아이디와 비밀번호가 같다면.
	private boolean authenticate(String mem_id, String mem_pass) {
		return mem_id.equals(mem_pass);
	}
	// 검증 제대로 값이 넘어왔는가
	private boolean validate(String mem_id, String mem_pass) {
		boolean valid = true;
		valid = !(mem_id ==null || mem_id.isEmpty() || mem_pass== null || mem_pass.isEmpty());
		return valid;
	}
}

//		boolean check = true;
//		
//		if(mem_id != null || !mem_id.isEmpty()) {
//			check = true;
//		}else { // id를 공백 혹은 잘못 입력 했을 시.
//			check = false;
//		}
//		if(mem_pass != null || !mem_pass.isEmpty()) {
//			check = true;
//		}else { // pass를 공백 혹은 잘못 입력 했을 시
//			check = false;
//		}
////		인증(id == password)
//		if(mem_id.equals(mem_pass) ){
//			check = true;
//		}else {
//			check = false;
//		}
//		if(check) {
////		view로 이동
////		인증 성공시 index.jsp 로 이동(현재 요청 정보 삭제). //redirect
//			String location = req.getContextPath()+"/";
//			resp.sendRedirect(location);
//			
//		}else {
////		인증 실패시 loginForm.jsp로 이동
////			1) 검증
////			2) 인증 실패(아이디 상태 유지)
//			req.setAttribute("mem_id", mem_id);
//			RequestDispatcher rd = req.getRequestDispatcher("/login/loginForm.jsp");
//			rd.forward(req, resp);
//		}
	