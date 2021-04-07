package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodList.do")
public class ProdListServlet extends HttpServlet{
	private IProdService service = ProdServiceImpl.getInstance();
	private IOthersDAO othersDAO = OthersDAOImpl.getInstance();
	
	private void addAttribute(HttpServletRequest req) {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		List<BuyerVO> buyerList =  othersDAO.selectBuyerList(null);
		
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addAttribute(req);
		
		String prod_lgu =  req.getParameter("prod_lgu");
		String prod_buyer =  req.getParameter("prod_buyer");
		String prod_name =  req.getParameter("prod_name");
		
//		ProdVO detailSearch = new ProdVO();
//		detailSearch.setProd_lgu(prod_lgu);
//		detailSearch.setProd_buyer(prod_buyer);
//		detailSearch.setProd_name(prod_name);
		ProdVO detailSearch = ProdVO.builder().prod_lgu(prod_lgu)
				.prod_buyer(prod_buyer)
				.prod_name(prod_name)
				.build();

		
		
		String pageParam = req.getParameter("page");
		int currentPage = 1;
		if(pageParam!=null && pageParam.matches("\\d+")) {
			currentPage = Integer.parseInt(pageParam);
		}
		PagingVO<ProdVO> pagingVO = new PagingVO<>(10, 5);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		
		int totalRecord = service.retrieveProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		
		List<ProdVO> prodList =  service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList);
		
		
		String accept = req.getHeader("Accept");
		// 비동기처리 - json
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=utf-8");
			// 마샬링과 직렬화
			ObjectMapper mapper = new ObjectMapper();
			try(
				PrintWriter out =resp.getWriter();
			){
				mapper.writeValue(out, pagingVO);
			}
		}else {
			// 동기처리 - html
			req.setAttribute("pagingVO", pagingVO);
			
			String view = "/WEB-INF/views/prod/prodList.jsp";
			req.getRequestDispatcher(view).forward(req, resp);
		}
	}
}
