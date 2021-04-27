package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.MimeType;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

//@WebServlet("/member/idCheck.do")
//@Controller
public class IdCheckController{

	@Inject
	private IMemberService service;
	
	@RequestMapping(value="/member/idCheck.do", method=RequestMethod.POST)
	public String doPost(
			@RequestParam(value="id") String mem_id
			, HttpServletResponse resp) throws ServletException, IOException {
		
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
