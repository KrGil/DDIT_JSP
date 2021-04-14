package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.util.BeanUtil;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validator.CommonValidator;
import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;

//@WebServlet("/prod/prodInsert.do")
@Controller
public class ProdInsertController{
	IProdService service = ProdServiceImpl.getInstance();
	private IOthersDAO othersDAO = OthersDAOImpl.getInstance();
	
	private void addAttribute(HttpServletRequest req) {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		List<BuyerVO> buyerList =  othersDAO.selectBuyerList(null);
		
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
	}

	@RequestMapping("/prod/prodInsert.do")
	public String doGet(HttpServletRequest req){
		addAttribute(req);
		return "prod/prodForm";
	}
	@RequestMapping(value="/prod/prodInsert.do", method=RequestMethod.POST)
	public String process(
			@ModelAttribute("prod") ProdVO prod,
			@RequestPart("prod_image") MultipartFile prod_image,
			HttpServletRequest req) throws IOException {
		addAttribute(req);
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);

//		EDD/TDD
		String saveFolderUrl = "/prodImages";
		File saveFolder = new File(
				req.getServletContext().getRealPath(saveFolderUrl));
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		if(!prod_image.isEmpty()) {
			prod_image.saveTo(saveFolder);
			prod.setProd_img(prod_image.getUniqueSaveName());
		}
		// 값 가져오기(vo에 담기 !단, name과 vo변수명이 같을 시)
		
		// 2. 검증( 데이터의 목적, 경로에 따라 방법이 달라져야 한다.)
		// 누가 통과 못했는지, 검증결과 메시지
		
		// 검증
		boolean valid = new CommonValidator<ProdVO>()
				.validate(prod, errors, InsertGroup.class);
		
		String view = null;
		String message = null;
		// 검증 통과 후
		if (valid) {
			// sql문 실행
			ServiceResult result = service.createProd(prod);
			switch (result) {
				case OK:
					view = "redirect:/prod/prodView.do?what="+prod.getProd_id();
					break;
				default:
					message = "서버 오류, 잠시 후 다시 시도해주세요.";
					view = "prod/prodForm";
					break;
			}
		} else {
			// 검증 불통
			view = "prod/prodForm";
		}
		
		req.setAttribute("message", message);
		return view;
	}
}
