package kr.or.ddit.servlet07;

import java.io.IOException;
import java.io.Reader;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/07/cookieGenerate.do")
public class CookieGeneratorServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(
			Reader reader = req.getReader();
		){
			ObjectMapper mapper = new ObjectMapper();
			// 언마샬링하기. jsp->servlet
			String[] values = mapper.readValue(reader, String[].class);
			// 마샬링하기 servlet-> cookie
			String json = mapper.writeValueAsString(values);
			// ecoding하기 
			String encodedJson = URLEncoder.encode(json, "utf-8");
			Cookie imageCookie = new Cookie("imageCookie", encodedJson);
			
			imageCookie.setPath(req.getContextPath());
			imageCookie.setMaxAge(60*60*24*7);
			
			resp.addCookie(imageCookie);
		}
	}
}
