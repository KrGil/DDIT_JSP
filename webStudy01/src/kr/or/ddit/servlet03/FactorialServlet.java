package kr.or.ddit.servlet03;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.MimeType;
import kr.or.ddit.servlet03.view.JsonView;

@WebServlet("/03/factorial")
public class FactorialServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// jsp 연결
		String view = "/WEB-INF/views/factorialForm.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
	// RESTful URI[POST, PUT, DELETE]
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 받고 검증 연산 응답  enum으로 단항연산식이 가능한가?
		String single = req.getParameter("single");
		String accept = req.getHeader("accept");
		// 3가지 return 
		// 1. 동기  - html 
		// 2. 비동기 / json
		// 3. 비동기 / html
		int status = 200;
		String message = null; // 오류메세지
		String view = null;
		
		// 검증 status
		if(single==null || !single.matches("[0-9]{1,2}")) {
			status = 400;
			message = "필수 파리미터 누락";
		}else {
			// 연산
			long op = Long.parseLong(single);
			try {
				long result = factorial(op);
				MimeType mime = MimeType.searchMimeType(accept); // return enum
				Map<String, Object> target = new HashMap<>();
				target.put("op", op);
				target.put("result", result);
				target.put("expression", String.format("%d!=%d", op, result));
				
				if(MimeType.JSON.equals(mime)) {
					resp.setHeader("Content-Type", "application/json;charset=utf-8");
					new JsonView().mergeModelAndView(target, resp);
				}else {
					req.setAttribute("target", target);
					view = "/WEB-INF/views/factorialForm.jsp";
				}
				
			}catch (IllegalArgumentException e) { // 500 -> 400으로 에러 변경
				status = 400;
				message = "음수는 연산 불가";
			}
		}
		if(status == HttpServletResponse.SC_OK) {
			if(view != null) {
				req.getRequestDispatcher(view).forward(req, resp);
			}
		}else {
			resp.sendError(status, message);
		}
			
	}
	// 재귀호출 (recursive calling)
	private long factorial(long op) {
		if(op <=0) {
			throw new IllegalArgumentException("양수만 대상으로 연산 수행 가능");
		}
		if(op == 1) {
			return 1;
		}else {
			return op * factorial(op -1);
		}
	}
}
