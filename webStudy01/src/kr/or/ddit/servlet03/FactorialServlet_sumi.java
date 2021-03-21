package kr.or.ddit.servlet03;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.MimeType;
import kr.or.ddit.servlet03.view.JsonView;
import kr.or.ddit.servlet03.view.XmlView;

//@WebServlet("/03/factorial")
public class FactorialServlet_sumi extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// jsp까지 연결을 해준다 -> 브라우저에 factorialForm뜬다 > 이 때 action : 현재 브라우저가 가지고 있는 주소

		RequestDispatcher disp =  req.getRequestDispatcher("/WEB-INF/views/factorialForm.jsp");
		disp.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// jsp에서 값을 받아와서 값을 검증, 연산, 값 보내기한다
		int single = Integer.parseInt(req.getParameter("single"));
//		System.out.println(single);
		
		int res = 1;
		for(int i = 1; i <= single; i++) {
			res = res * i;
			System.out.println(res);
		}
		
		String accept = req.getHeader("accept");
		MimeType mimeType = MimeType.searchMimeType(accept);
		resp.setContentType(mimeType.getMime());
		
		StringBuffer respData = new StringBuffer(); // 어차피 모두 text이기때문
		String view = null;
		
		// model1이다. 혼자서 request받고 response내보내줬기 때문이다.
		switch (mimeType) {
			case JSON:
				new JsonView().mergeModelAndView(res, resp);
				break;
			case XML:
				new XmlView().mergeModelAndView(res, resp);
				break;
			default:
				view = "/WEB-INF/views/factorialForm.jsp";
				break;
		}

		req.setAttribute("res", res);
		
		RequestDispatcher disp =  req.getRequestDispatcher("/WEB-INF/views/factorialForm.jsp");
		disp.forward(req, resp);
		
	}
	
}
