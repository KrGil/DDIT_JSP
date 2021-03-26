package kr.or.ddit.servlet02;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/02/imageForm.do")
public class Model2ImageFormServlet extends HttpServlet{
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = config.getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String folder = "d:/contents";
		File contents = new File(folder);
		String[] children = contents.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				String mime = application.getMimeType(name);
				return mime != null && mime.startsWith("image/");
			}
		});
		
		Cookie[] cookies = req.getCookies();
		Cookie imageCookie = null;
		if(cookies != null) {
			for(Cookie tmp : cookies) {
				if(tmp.getName().equals("imageCookie")) {
					imageCookie = tmp;
					break;
				}
			}
		}
		if(imageCookie!=null) {
			String decodedJson = URLDecoder.decode(imageCookie.getValue(), "utf-8");
			
			req.setAttribute("imageCookie", decodedJson);
		}
		
		
		
		req.setAttribute("children", children);
		String view = "/WEB-INF/views/imageForm2.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
}
