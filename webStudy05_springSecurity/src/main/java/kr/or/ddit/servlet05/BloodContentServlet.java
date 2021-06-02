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

@WebServlet(value="/blood/getContent.do", loadOnStartup=1)
public class BloodContentServlet extends HttpServlet{
	Map<String, String> bloodMap;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		bloodMap = new LinkedHashMap<>();
		bloodMap.put("a", "A형");
		bloodMap.put("b", "B형");
		bloodMap.put("ab", "AB형");
		bloodMap.put("o", "O형");
		config.getServletContext().setAttribute("bloodMap", bloodMap);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String blood = req.getParameter("blood");
		int status = validate(blood);
		if(status!=200) {
			resp.sendError(status);
			return;
		}
		
		String view = "/WEB-INF/views/blood/"+blood+".jsp";
		req.getRequestDispatcher(view).forward(req, resp);
//		resp.sendRedirect(req.getContextPath() + view);
	}

	private int validate(String blood) {
		int status = 200;
		if(blood==null || blood.isEmpty()) {
			status = 400;
		}else {
			if(!bloodMap.containsKey(blood)) {
				status = 400;
			}
		}
		return status;
	}
}











