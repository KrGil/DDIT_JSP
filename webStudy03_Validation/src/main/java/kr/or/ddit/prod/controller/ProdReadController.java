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

import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdReadController{
	private IProdService service = ProdServiceImpl.getInstance();
	private IOthersDAO othersDAO = OthersDAOImpl.getInstance();
	
	private void addAttribute(HttpServletRequest req) {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		List<BuyerVO> buyerList =  othersDAO.selectBuyerList(null);
		
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
	}
	
	@RequestMapping("/prod/prodList.do")
	public String list(
			@ModelAttribute("detailSearch") ProdVO detailSearch,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addAttribute(req);
//		
//		String prod_buyer =  req.getParameter("prod_buyer");
//		String prod_name =  req.getParameter("prod_name");
		
//		ProdVO detailSearch = new ProdVO();
//		detailSearch.setProd_lgu(prod_lgu);
//		detailSearch.setProd_buyer(prod_buyer);
//		detailSearch.setProd_name(prod_name);

//		String pageParam = req.getParameter("page");
//		int currentPage = 1;
//		if(pageParam!=null && pageParam.matches("\\d+")) {
//			currentPage = Integer.parseInt(pageParam);
//		}
		PagingVO<ProdVO> pagingVO = new PagingVO<>(10, 5);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		
		int totalRecord = service.retrieveProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		
		List<ProdVO> prodList =  service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList);
		
		
		String accept = req.getHeader("Accept");
		// 비동기처리 - json
		String view = null;
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
			
			view = "prod/prodList";
		}
		return view;
	}
	// 하나의 객체 안에 유사한 일을 하는 녀석에가 모아둘 수 있다.
	@RequestMapping("/prod/prodView.do")
	public String view(
				@RequestParam(value="what", required=true, defaultValue="1") String prod_id, 
				HttpServletRequest req){
		ProdVO prod =  service.retrieveProd(prod_id);
		req.setAttribute("prod", prod);
		String view = "prod/prodView";
		return view;
	}
}
