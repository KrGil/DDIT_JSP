package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

//@WebServlet("/prod/prodView.do")
public class ProdViewServlet_1 extends HttpServlet{
	private IProdService service = 
			ProdServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String prod_id = req.getParameter("what");
		if(prod_id==null || prod_id.isEmpty()) {
			resp.sendError(400);
			return;
		}
		
		ProdVO prod =  service.retrieveProd(prod_id);
		req.setAttribute("prod", prod);
		String view = "/WEB-INF/views/prod/prodView.jsp";
		
		boolean redirect = view.startsWith("redirect:");
		if(redirect) {
			view = view.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + view);
		}else {
			req.getRequestDispatcher(view).forward(req, resp);
		}
	}
}





