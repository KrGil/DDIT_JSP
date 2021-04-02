package kr.or.ddit.servlet02;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.BrowserType;

//@WebServlet("/")
public class CalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String num1 = request.getParameter("left");
		String num2 = request.getParameter("right");
		
		int int_num1 = Integer.parseInt(num1);
		int int_num2 = Integer.parseInt(num2);

		String op_upper = op.toUpperCase();
		String op_last = "";
		int result = 0;
		
		RequestDispatcher rd = request.getRequestDispatcher("calculator.jsp");
		rd.forward(request, response);
	}
}
