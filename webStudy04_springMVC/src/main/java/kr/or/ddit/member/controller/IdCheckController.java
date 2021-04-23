package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.MimeType;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;

//@WebServlet("/member/idCheck.do")
@Controller
public class IdCheckController{
private IMemberService service = new MemberServiceImpl();
	
	@RequestMapping(value="/member/idCheck.do", method=RequestMethod.POST)
	public String doPost(
			@RequestParam(value="id") String mem_id,
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Map<String, Object> resultMap = new HashMap<>();
		try {
			service.retrieveMember(mem_id);
			resultMap.put("result", ServiceResult.FAIL);
		}catch (Exception e) {
			resultMap.put("result", ServiceResult.OK);
		}
		
		resp.setContentType(MimeType.JSON.getMime());
		try(
			PrintWriter out = resp.getWriter();	
		){
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, resultMap);
		}
		return null;
	}
}
