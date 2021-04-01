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
import kr.or.ddit.service.IMemberService;
import kr.or.ddit.service.MemberServiceImpl;

@WebServlet("/member/idCheck.do")
public class IdCheckServlet extends HttpServlet{
	private IMemberService service = new MemberServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String mem_id = (String) req.getParameter("id");
		if(mem_id==null || mem_id.isEmpty()) {
			resp.sendError(400);
			return;
		}
		
		Map<String, Object> resultMap = new HashMap<>();
		System.out.println(mem_id);
		try {
			// null일 때 return이 exception
			service.retrieveMember(mem_id);
			resultMap.put("result", ServiceResult.OK);
		}catch (Exception e) {
			// 일로 넘어오면 성공
			resultMap.put("result", ServiceResult.FAIL);
		}
		
		// mimeType을 세팅해 주어야 한다.
		// request accept 설정.
		resp.setContentType(MimeType.JSON.getMime());
		try( // 직열화.
			PrintWriter out = resp.getWriter();
		){
			// 1. 마샬링, 직렬화
			ObjectMapper mapper = new ObjectMapper();
//			String json = mapper.writeValueAsString(resultMap);
			mapper.writeValue(out, resultMap); //marshalling과 serialliziton을 함께 해주기
		}
	}
}
