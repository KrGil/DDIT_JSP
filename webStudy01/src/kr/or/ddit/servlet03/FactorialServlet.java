package kr.or.ddit.servlet03;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.MimeType;
import kr.or.ddit.servlet03.view.JsonView;
import kr.or.ddit.servlet03.view.XmlView;

@WebServlet("/03/factorial")
public class FactorialServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// jsp 연결
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		// 받고 검증 연산 응답  enum으로 단항연산식이 가능한가?
		// 검증 status
		
		// 연산
		
		// 응답
		String accept = req.getHeader("accept");
		MimeType mimeType = MimeType.searchMimeType(accept);
		resp.setContentType(mimeType.getMime());
		
		StringBuffer respData = new StringBuffer(); // 어차피 모두 text이기때문
		String view = null;
		// model1이다. 혼자서 request받고 response내보내줬기 때문이다.
		switch (mimeType) {
			case JSON:
				new JsonView().mergeModelAndView("", resp);
				break;
			case XML:
				new XmlView().mergeModelAndView("", resp);
				break;
			default:
				view = "/WEB-INF/views/factorialForm.jsp";
				break;
		}
	}
}
