package kr.or.ddit.servlet01;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;
import java.util.Date;

@WebServlet("/01/imageForm.tmpl")
public class ImageFormServlet extends AbstractUseTmplServlet{
	@Override
	protected void setContentType(HttpServletResponse resp) {
		resp.setContentType("text/html;charset=UTF-8");
	}

	@Override
	protected void makeData(HttpServletRequest req) {
		System.out.println("서블릿이 요청 받았음." + application.hashCode());
		
		String folder = application.getInitParameter("contentFolder");			 
		File contents = new File(folder);
		String[] children = contents.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				String mime = application.getMimeType(name);
				return mime!=null && mime.startsWith("image/");
			}
		});
		
		Date today = new Date();
		req.setAttribute("today", today);
		
		StringBuffer options = new StringBuffer();
		
		for(String child : children){
			options.append(String.format("<option>%s</option>", child));
		}
		req.setAttribute("options", options);
	}

}



















