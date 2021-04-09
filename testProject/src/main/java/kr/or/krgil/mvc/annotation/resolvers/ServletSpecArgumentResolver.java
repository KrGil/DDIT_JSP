package kr.or.krgil.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *	{@link HttpServletRequest}
 *	{@link HttpServletResponse}
 *	{@link HttpSession}
 *	위의 세가지 종류의 헨들러 메소드 아규먼트를 처리할 처리자.
 */
public class ServletSpecArgumentResolver implements IHandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		Class<?> parameterType = parameter.getType();
		// 현재 파라미터가 감당할 수 있는 파라미터인지 
		boolean supported = 
				HttpServletRequest.class.equals(parameterType)
				|| HttpServletResponse.class.equals(parameterType)
				|| HttpSession.class.equals(parameterType);
		return supported;
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		Object parameterValue = null;
		if(HttpServletRequest.class.equals(parameterType)) {
			parameterValue=req;
		}else if(HttpServletResponse.class.equals(parameterType)) {
			parameterValue = resp;
		}else {
			parameterValue = req.getSession();
		}
		return parameterValue;
	}

}
