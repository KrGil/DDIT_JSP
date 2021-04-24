package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/bts", loadOnStartup=1)
public class BTSServlet extends HttpServlet {
	Map<String, String>bts;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Map<String, List<String>> btsMap = new LinkedHashMap<>();
		btsMap.put("B001", Arrays.asList("뷔", "/WEB-INF/views/bts/bui.jsp"));
		btsMap.put("B002", Arrays.asList("제이홉", "/WEB-INF/views/bts/jhop.jsp"));
		btsMap.put("B003", Arrays.asList("지민", "/WEB-INF/views/bts/jimin.jsp"));
		btsMap.put("B004", Arrays.asList("진", "/WEB-INF/views/bts/jin.jsp"));
		btsMap.put("B005", Arrays.asList("정국", "/WEB-INF/views/bts/jungkuk.jsp"));
		btsMap.put("B006", Arrays.asList("RM", "/WEB-INF/views/bts/rm.jsp"));
		btsMap.put("B007", Arrays.asList("수거", "/WEB-INF/views/bts/suga.jsp"));
		
		// 이녀석은 어디에서나 쓸 수 있다. 모든 클라이언트가 접속할 수 있다. 싱글톤패턴이다.
		getServletContext().setAttribute("btsMap", btsMap);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view ="/WEB-INF/views/btsForm.jsp"; // 2번.
		req.getRequestDispatcher(view).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String member = req.getParameter("member");
		if(member==null || member.isEmpty()) {
			resp.sendError(400);
			return;
		}
		
		Map<String, List<String>> btsMap = (Map) getServletContext().getAttribute("btsMap");
		List<String> data = btsMap.get(member);
		if(data==null) {
			resp.sendError(404); // notFound error
			return;
		}
		
		String view = data.get(1);
		req.getRequestDispatcher(view).forward(req, resp);
		
	}

	private int validate(String member) {
		int status = 200;
		if(member == null || member.isEmpty()) {
			status =400;
		}else {
			if(!bts.containsKey(member)) {
				status =400;
			}
		}
		return status;
	}
}
