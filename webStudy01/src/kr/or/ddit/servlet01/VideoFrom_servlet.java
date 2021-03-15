package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VideoFrom_servlet
 */
@WebServlet("/VideoFrom_servlet.do")
public class VideoFrom_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = config.getServletContext();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String folder = "d:/contents";
//		System.out.println(folder.substring(folder.indexOf("D")));
		File contents = new File(folder);
//        <source src="../multi/trailer.mp4" type='video/mp4'>
//        <source src="../multi/trailer.ogv"  type='video/ogg'>
//        <source src="../multi/trailer.webm"  type='video/webM'>
		
		String[] children = contents.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				String mime = application.getMimeType(name);
				return mime != null && (mime.startsWith("video/") || mime.startsWith("image/"));
			}
		});
		StringBuffer options = new StringBuffer();
		String[] options_arr = new String[children.length];
		ArrayList<String> options_list = new ArrayList<String>();
		for (String child : children) {
			int i = 0;
			options.append(String.format("<option>%s</option>", child));
			options_arr[i] = (String.format("<option>%s</option>", child));
			options_list.add(String.format("<option>%s</option>", child));
			System.out.println("options_arr : "+options_arr[i]);
			i++;
		}
		request.setAttribute("options_arr", options_list);
		
//		response.sendRedirect("02/videoView.jsp?options="+options);
		RequestDispatcher rd = request.getRequestDispatcher("02/videoView.jsp");
		rd.forward(request, response);
	}
}
