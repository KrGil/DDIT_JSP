package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.alba.dao.CodeDAOImpl;
import kr.or.ddit.alba.dao.ICodeDAO;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.vo.LicenseVO;

@Controller
public class CodeRetrieveController {

	ICodeDAO codeDAO = CodeDAOImpl.getInstance();
	
	@RequestMapping("/alba/getLicenseList.do")
	public String getLicenseList(HttpServletResponse resp) throws IOException {
		List<LicenseVO> licenseList = codeDAO.selectLicenseList();
		resp.setContentType("application/json;charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();	
		){
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, licenseList);
		}
		return null;
	}
	
	@RequestMapping("/alba/getGradeList.do")
	public String getGradeList(HttpServletResponse resp) throws IOException {
		List<Map<String, String>> gradeList = codeDAO.selectGradeList();
		resp.setContentType("application/json;charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();	
		){
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, gradeList);
		}
		return null;
	}
}
