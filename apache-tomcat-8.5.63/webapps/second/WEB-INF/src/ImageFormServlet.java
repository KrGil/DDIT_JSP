package kr.or.ddit;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;


public class ImageFormServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String mime = "text/html; charset=utf-8";
		resp.setContentType(mime);
		String folder = "d:/contents";
		File contents = new File(folder);
		
		String[] children = contents.list();
		String html = "<html><body>";
		html += "<form action='image.do'><select name = 'image'>";
		for(String child : children){
			html +="<option value="+child+">"+child+"</option>";
		}
		html +="</select><input type='submit' value='전송'/></form></body></html>";
		// resp.getWriter().println(html);
		PrintWriter out =resp.getWriter();
		out.println(html);

		//javac ImageFormServlet.java -d ..\classes -encoding UTF-8
	}
}