package kr.or.ddit.servlet01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/01/textViewer_1.tmpl")
public class textFormServlet extends AbstractUseTmplServlet {
	@Override
	protected void setContentType(HttpServletResponse resp) {
		resp.setContentType("text/html;charset=UTF-8");
	}
	@Override
	protected void makeData(HttpServletRequest req) {
		System.out.println("서블릿이 요청 받았음.");
		
		String folder = textFormServlet.class.getClassLoader().getResource("datas").toString();
		File contents = new File(folder.substring(folder.indexOf("D")));
		String[] children = contents.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				String mime = application.getMimeType(name);
				System.out.println(mime);
				return mime != null && mime.startsWith("text/");
			}
		});

		/*
		 * for(String child : children){
		 * 
		 * if(mime.startsWith("image/")) {
		 * 
		 * html.append(String.format("<option>%s</option>", child)); } }
		 */
		
		/*
		 * Date today = new Date(); req.setAttribute("today", today);
		 */
		
		StringBuffer options = new StringBuffer();
		
		for (String child : children) {
			options.append(String.format("<option>%s</option>", child));
		}
		req.setAttribute("options", options);
		
	}
}