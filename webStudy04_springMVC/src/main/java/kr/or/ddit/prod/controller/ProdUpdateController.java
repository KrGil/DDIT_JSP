package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.HandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validator.CommonValidator;
import kr.or.ddit.validator.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

// /prod/prodUpdate.do
@Controller
public class ProdUpdateController {
	private static final Logger logger = LoggerFactory.getLogger(HandlerMapping.class);
	IProdService service = ProdServiceImpl.getInstance();
	private IOthersDAO othersDAO = OthersDAOImpl.getInstance();
	
	private void addAttribute(HttpServletRequest req) {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		List<BuyerVO> buyerList =  othersDAO.selectBuyerList(null);
		
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
	}
	private void addCommandAttribute(HttpServletRequest req) {
		req.setAttribute("command", "update");
	}
	@RequestMapping("/prod/prodUpdate.do")
	public String updateForm(
			@RequestParam("what") String prod_id,
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addAttribute(req);
		addCommandAttribute(req);
		
		ProdVO prod =  service.retrieveProd(prod_id);
		req.setAttribute("prod", prod);
		String view = "prod/prodForm";
		return view;
	}
	@RequestMapping(value="/prod/prodUpdate.do", method=RequestMethod.POST)
	public String updateProcess(
			// commandObject라고 부른다. 요 녀석을
			// @ModelAttribute는 commandobject를 만들어 주기 위한 녀석.
			@ModelAttribute("prod") ProdVO prod,
			@RequestPart(value="prod_image", required=false) MultipartFile prod_image,
			HttpServletRequest req) throws IOException{

		req.setAttribute("prod", prod);
		
		// 2. 검증( 데이터의 목적, 경로에 따라 방법이 달라져야 한다.)
		// 누가 통과 못했는지, 검증결과 메시지
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		String saveFolderUrl = "/prodImages";
		File saveFolder = new File(
				req.getServletContext().getRealPath(saveFolderUrl));
		if(prod_image!=null && !prod_image.isEmpty()) {
			prod_image.saveTo(saveFolder);
			prod.setProd_img(prod_image.getUniqueSaveName());
		}
		
		boolean valid = new CommonValidator<ProdVO>()
							.validate(prod, errors, UpdateGroup.class);
		
		String view = null;
		String message = null;
		// 검증 통과 후
		if (valid) {
			// sql문 실행
			ServiceResult result = service.modifyProd(prod);
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
