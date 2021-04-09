package kr.or.ddit.servlet05;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("blood/getContent.do")
public class BloodContentServlet_me extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String blood = req.getParameter("blood");
		String view = null;
		boolean redirect = true;
		boolean validate = validate(blood);
		HttpSession session = req.getSession();
		Map<String, String> bloodMap = (Map<String, String>) session.getAttribute("bloodMap");
		boolean validate2 = validate2(bloodMap, blood);
		// 검증
		
		// 1. 값이 넘어왓나?
		if(validate) {
			if(validate2) {
				switch(blood){
				case "a":
					view = "/views/blood/a.jsp";
					return;
				case "b":
					view = "/views/blood/b.jsp";
					return;
				case "ab":
					view = "/views/blood/ab.jsp";
					return;
				case "o":
					view = "/views/blood/o.jsp";
					return;
				default : 
					view = "/05/bloodForm.jsp";
					return;
				}
			}else {
				redirect = true;
			}
		}else {
			redirect = true;
			
		}
		
		if(redirect) {
			
		}else {
			
		}
		
		// 2. 값이 우리가 원한게끔 넘어옴?
		
		
	}
	
	private boolean validate2(Map<String, String> bloodMap, String blood) {
		boolean valid = bloodMap.containsKey(blood);
		return valid;
	}

	// 값이 제대로 넘어왔는가
	private boolean validate(String blood) {
		boolean valid = true;
		valid = !(blood == null || blood.isEmpty());
		return valid;
	}
}
