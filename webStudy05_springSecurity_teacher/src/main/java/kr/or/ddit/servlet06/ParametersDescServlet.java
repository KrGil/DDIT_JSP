package kr.or.ddit.servlet06;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.ParameterVO;

@WebServlet("/06/parameters")
public class ParametersDescServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String contentType = req.getContentType();
		ParameterVO bean = null;
		if(contentType!=null && contentType.toLowerCase().contains("json")) {
			ObjectMapper mapper = new ObjectMapper();
			bean = mapper.readValue(req.getReader(), ParameterVO.class);
//			Map<String, Object>[] bean = mapper.readValue(req.getReader(), Map[].class);
		}else {
			Map<String, String[]> parameterMap = req.getParameterMap();
			bean = new ParameterVO();
			try {
				BeanUtils.populate(bean, parameterMap);
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(bean);
//		for( String paramName : parameterMap.keySet()) {
//			String[] values = req.getParameterValues(paramName);
//			System.out.printf("%s : %s \n", 
//						paramName, Arrays.toString(values));
//		}
	}
}










