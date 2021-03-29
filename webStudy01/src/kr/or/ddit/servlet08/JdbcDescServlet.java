package kr.or.ddit.servlet08;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.servlet08.dao.IJdbcDescService;

@WebServlet("/10/jdbcDesc.do")
public class JdbcDescServlet extends HttpServlet{
	IJdbcDescService service = new JdbcDescServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청 접수
		// 요청 분석
		// 커맨드처리(DB 접속) dao로 분리
		// 컨텐츠 전달
		// 뷰 선택
		// 뷰 이동
		String property_name = req.getParameter("property_name");
		
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("property_name", property_name);
		
		List<Map<String, Object>> dataList = service.retrieveDataBaseProperties(pMap);
		String[] headers = (String[]) pMap.get("headers");
		
		req.setAttribute("headers", headers);
		req.setAttribute("dataList", dataList);
		
		String view  = "/WEB-INF/views/10/jdbcDesc.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
}
