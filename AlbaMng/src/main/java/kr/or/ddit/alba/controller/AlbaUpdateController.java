package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.alba.dao.CodeDAOImpl;
import kr.or.ddit.alba.dao.ICodeDAO;
import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.AlbaVO;

@Controller
public class AlbaUpdateController{
	ICodeDAO codeDAO = CodeDAOImpl.getInstance();
	IAlbaService service = AlbaServiceImpl.getInstance();
	
	private void setCodeInScope(HttpServletRequest req){
		req.setAttribute("licenses", codeDAO.selectLicenseList());
		req.setAttribute("grades", codeDAO.selectGradeList());
	}
	
	@RequestMapping("/alba/albaUpdate.do")
	public String doGet(@RequestParam("who") String who, HttpServletRequest req){
		setCodeInScope(req);
		AlbaVO alba = service.retrieveAlba(who);
		req.setAttribute("alba", alba);
		req.setAttribute("viewName", "alba/albaForm");
		return "template";
	}

	@RequestMapping(value="/alba/albaUpdate.do", method=RequestMethod.POST)
	public String update( 
			@ModelAttribute("alba")AlbaVO alba
			, @RequestPart(value="alImage", required=false) MultipartFile alImage
			, @RequestPart(value="licImages", required=false) MultipartFile[] licImages
			, HttpServletRequest req ) throws IOException {
		setCodeInScope(req);
		alba.setAlImage(alImage);
		if(licImages!=null)
			alba.setLicImages(Arrays.asList(licImages));
		Map<String, List<String>> errors = new LinkedHashMap<>();
		CommonValidator<AlbaVO> validator = new CommonValidator<>();
		boolean valid = validator.validate(alba, errors, UpdateGroup.class);
		
		String viewName = null;
		String goPage = "template";
		if(valid) {
			ServiceResult result = service.modifyAlba(alba);
			if(ServiceResult.OK.equals(result)) {
				goPage = "redirect:/alba/albaView.do?who="+alba.getAlId();
			}else {
				req.setAttribute("message", "서버 오류로 등록 실패.");
				viewName = "alba/albaForm";
			}
		}else {
			viewName = "alba/albaForm";
		}
		req.setAttribute("viewName", viewName);
		return goPage;
	}
}
