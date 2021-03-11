package kr.or.ddit;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

// 한글로 주석을 처리해 보자.
public class HelloServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException, ServletException{
		// M InternetMailExtends TEXT : mainType/subType
		// text/html, text/css, text/javascript, text/pain
		application/json, application/xml, image/jpeg, video/mp4
		
		resp.setContentType("text/html; charset=UTF-8");
		String data = "<html><body><h4>Hello Servlet</h4>";		
		data += "<h4>서버 타임 : "+ new Date()+ "</h4>";
		data += "<h4 id = 'timerArea'></h4>";
		data += "<script type = 'text/javascript'>var today = new Date();";
		data += "document.getElementById('timerArea').innerHTML=today;</script>";
		
		PrintWriter out = resp.getWriter();
		out.println(data);
	}
}