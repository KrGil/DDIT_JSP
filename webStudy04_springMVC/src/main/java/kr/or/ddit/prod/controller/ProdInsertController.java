package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod/prodInsert.do")
public class ProdInsertController{
	@Inject
	private IProdService service;
	@Inject
	private IOthersDAO othersDAO;
	
	private void addAttribute(Model model) {
		List<Map<String, Object>> lprodList 
			= othersDAO.selectLprodList();
		List<BuyerVO> buyerList 
			= othersDAO.selectBuyerList(null);
		model.addAttribute("lprodList", lprodList);
		model.addAttribute("buyerList", buyerList);
	}
	
	@RequestMapping
	public String form(Model model){
		addAttribute(model);
		return "prod/prodForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String process(
		@Validated(InsertGroup.class) @ModelAttribute("prod") ProdVO prod
		, Errors errors
		, Model model
	) throws IOException{
		addAttribute(model);
		
//		if(!prod_image.isEmpty()) {
//			prod_image.saveTo(saveFolder);
//			prod.setProd_img(prod_image.getUniqueSaveName());
//		}
		
		String view = null;
		String message = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.createProd(prod);
			if(ServiceResult.OK.equals(result)) {
				view = "redirect:/prod/prodView.do?what="+prod.getProd_id();
			}else {
				message = "서버 오류";
				view = "prod/prodForm";
			}
		}else {
			view = "prod/prodForm";
		}
		
		model.addAttribute("message", message);
		
		return view;
	}

}












