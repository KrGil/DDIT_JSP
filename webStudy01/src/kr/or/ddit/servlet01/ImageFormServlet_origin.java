package kr.or.ddit.servlet01;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;

//@WebServlet("/01/imageForm.do")
public class ImageFormServlet_origin extends HttpServlet {
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = config.getServletContext();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 이미지를 제외한 파일 표기 x
		// - 개발환경 구축 완료 보고서
		String mime = "text/html; charset=utf-8";
		resp.setContentType(mime);
		String folder = "d:/contents";
		File contents = new File(folder);

		String[] children = contents.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				String mime = application.getMimeType(name);
				return mime != null && mime.startsWith("image/");
			}
		});
		StringBuffer html = new StringBuffer("<html><body>");
		html.append("<form action='image.do'><select name = 'image'>");

		for (String child : children) {
//			String image = getServletContext().getMimeType(child); // tomcat에 명령을 내리는 메서드
//			System.out.println(image);
//			if (image == null || !image.startsWith("image/")) {
//
//			} else {
				html.append(String.format("<option value=%s>%s</option>", child, child));
//			}
		}
		html.append("</select><input type='submit' value='Send'/></form></body></html>");
		// resp.getWriter().println(html);

		PrintWriter out = null;
		try {
			out = resp.getWriter();
			out.println(html);
		} finally {
			if (out != null) {
				out.close();
				out.flush();
			}
		}
		// javac ImageFormServlet.java -d ..\classes -encoding UTF-8
	}
}