package kr.or.ddit.prod.controller.advice;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.vo.BuyerVO;

@ControllerAdvice(basePackages = "kr.or.ddit.prod.controller")
public class ProdControllerAdvice {
	@Inject
	private IOthersDAO othersDAO;
	
	@ModelAttribute("lprodList")
	public List<Map<String, Object>> getLprodList(){
		List<Map<String, Object>> lprodList 
			= othersDAO.selectLprodList();
		return lprodList;
	}
	
	@ModelAttribute("buyerList")
	public List<BuyerVO> getBuyerList(){
		List<BuyerVO> buyerList 
			= othersDAO.selectBuyerList(null);
		return buyerList;
	}
	
}









