package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdReadController{
	@Inject
	private IProdService service;
	
	
	@RequestMapping("/prod/prodList.do")
	public String list(
		@ModelAttribute("detailSearch") ProdVO detailSearch
		, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, Model model) {
		
		PagingVO<ProdVO> pagingVO = list(detailSearch, currentPage);
		
		model.addAttribute("pagingVO", pagingVO); 
		
		return "prod/prodList";
	}
	
	@RequestMapping(value="/prod/prodList.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<ProdVO> list(
		@ModelAttribute("detailSearch") ProdVO detailSearch
		, @RequestParam(value="page"
					, required=false
					, defaultValue="1") int currentPage
	){
		PagingVO<ProdVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		
		int totalRecord = service.retrieveProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<ProdVO> prodList = 
				service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList);
		
		return pagingVO;		
	}
	
	@RequestMapping("/prod/prodView.do")
	public String view(
			@RequestParam(value="what", required=true) String prod_id
			, Model model){
		ProdVO prod =  service.retrieveProd(prod_id);
		model.addAttribute("prod", prod);
		return "prod/prodView";		
	}
}











