package kr.or.ddit.servlet05;

import java.io.IOException;
import java.util.LinkedHashMap;
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
		bts = new LinkedHashMap<>();
		bts.put("bui", "부이");
		bts.put("jhop", "제이홉");
		bts.put("jimin", "지민");
		bts.put("jin", "진");
		bts.put("jungkuk", "중쿸");
		bts.put("rm", "알엠");
		bts.put("suga", "수가");
		// 이녀석은 어디에서나 쓸 수 있다. 모든 클라이언트가 접속할 수 있다. 싱글톤패턴이다.
		config.getServletContext().setAttribute("bts", bts);
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view ="/WEB-INF/views/btsForm.jsp"; // 2번.
		req.getRequestDispatcher(view).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String member = req.getParameter("member");
		System.out.println(member);
		
		
		int status = validate(member);
		if(status !=200) {
			resp.sendError(status);
			return;
		}
		System.out.println(member);
		
		String view ="/WEB-INF/views/bts/"+member+".jsp";
		req.getRequestDispatcher(view).forward(req, resp);
//		resp.sendRedirect(req.getContextPath()+view);
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
