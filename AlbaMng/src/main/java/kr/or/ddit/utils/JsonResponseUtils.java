package kr.or.ddit.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.MimeType;

public class JsonResponseUtils {
	public static void toJsonResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Enumeration<String> names = req.getAttributeNames();
		Map<String, Object> dataMap = new HashMap<>();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			Object value= req.getAttribute(name);
			dataMap.put(name, value);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String contentType = resp.getContentType();
		if(contentType==null) {
			resp.setContentType(MimeType.JSON.toString());
		}
		
		try(
			PrintWriter out = resp.getWriter();	
		){
			mapper.writeValue(out, dataMap);
		}
		
	}
}










