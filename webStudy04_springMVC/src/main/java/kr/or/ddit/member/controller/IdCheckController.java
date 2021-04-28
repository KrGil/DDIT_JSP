package kr.or.ddit.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;

@Controller
public class IdCheckController{
	@Inject
	private IMemberService service;
	
	@RequestMapping(value="/member/idCheck.do"
			, method=RequestMethod.POST
			, produces = MediaType.APPLICATION_JSON_UTF8_VALUE 
	)
	@ResponseBody
	public Map<String, Object> doPost(@RequestParam("id") String mem_id){
		
		Map<String, Object> resultMap = new HashMap<>();
		try {
			service.retrieveMember(mem_id);
			resultMap.put("result", ServiceResult.FAIL);
		}catch (Exception e) {
			resultMap.put("result", ServiceResult.OK);
		}
//		
//		resp.setContentType(MimeType.JSON.getMime());
//		try(
//			PrintWriter out = resp.getWriter();	
//		){
//			ObjectMapper mapper = new ObjectMapper();
//			mapper.writeValue(out, resultMap);
//		}
		return resultMap;
	}
}










