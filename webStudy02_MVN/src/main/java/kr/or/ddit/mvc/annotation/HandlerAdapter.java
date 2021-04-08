package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.controller.IHandlerAdapter;

public class HandlerAdapter implements IHandlerAdapter{

	// return값이 논리적인 viewname
	@Override
	public String invokHandler(RequestMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 어떤 것에서 어떻게 하겠다는 정보
		Object controllerObj = mappingInfo.getCommandHandler();
		Method handlerMethod = mappingInfo.getHandlerMethod();
		try {
			return (String) handlerMethod.invoke(controllerObj, req, resp);
			// public으로 안하고 private으로 선언해서 접근오류일때
			// 깜빡하고 파라미터를 하나 덜 넣었을 경우
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ServletException(e);
		}
	}

}
