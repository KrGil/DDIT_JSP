package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.login.LoginCheckServlet;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodView.do")
public class ProdViewServlet extends HttpServlet{
	private static final Logger logger = LoggerFactory.getLogger(LoginCheckServlet.class);
	IProdService service = ProdServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String prod_id = req.getParameter("what");
		ProdVO prod =  service.retrieveProd(prod_id);
		
		if(logger.isDebugEnabled()) 
			logger.debug("인증전 prod : {} ", prod);
		
		req.setAttribute("prod", prod);
		String view = "/WEB-INF/views/prod/prodView.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
}
