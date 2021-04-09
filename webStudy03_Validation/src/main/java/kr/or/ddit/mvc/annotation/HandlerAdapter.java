package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import kr.or.ddit.mvc.annotation.resolvers.BadRequestException;
import kr.or.ddit.mvc.annotation.resolvers.IHandlerMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttributeArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.RequestParamArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ServletSpecArgumentResolver;

public class HandlerAdapter implements IHandlerAdapter{
	private List<IHandlerMethodArgumentResolver> argumentResolvers;
	public HandlerAdapter() {
		argumentResolvers = new ArrayList<>();
		argumentResolvers.add(new ServletSpecArgumentResolver());
		argumentResolvers.add(new ModelAttributeArgumentResolver());
		argumentResolvers.add(new RequestParamArgumentResolver());
		
	}
	private IHandlerMethodArgumentResolver findArgumentResolver(Parameter parameter) {
		IHandlerMethodArgumentResolver finded = null;
		for(IHandlerMethodArgumentResolver resolver : argumentResolvers) {
			if(resolver.isSupported(parameter)) {
				finded = resolver;
				break;
			}
		}
		return finded;
	}
	
	
	// return값이 논리적인 viewname
	@Override
	public String invokHandler(RequestMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 어떤 것에서 어떻게 하겠다는 정보
		Object controllerObj = mappingInfo.getCommandHandler();
		Method handlerMethod = mappingInfo.getHandlerMethod();
		int parameterCount = handlerMethod.getParameterCount();
		Parameter[] parameters = handlerMethod.getParameters();
		try {
			if(parameterCount==0) {
				return (String) handlerMethod.invoke(controllerObj);
			}
			Object[] parameterValues = new Object[parameterCount];
			for(int idx=0; idx<parameterCount;idx++) {
				Parameter parameter = parameters[idx];
				Class<?> parameterType = parameter.getType();
				IHandlerMethodArgumentResolver resolver 
									= findArgumentResolver(parameter);
				if(resolver==null)
					throw new ServletException(
						String.format("%s 타입의 핸들러 메소드 아규먼트는 처리할 수 없음", parameterType.getName())
					);
				parameterValues[idx] = resolver.argumentResolve(parameter, req, resp);
			}
			return (String) handlerMethod.invoke(controllerObj, parameterValues);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// public으로 안하고 private으로 선언해서 접근오류일때
			// 깜빡하고 파라미터를 하나 덜 넣었을 경우
			throw new ServletException(e);
		} catch (BadRequestException e) {
			resp.sendError(400, e.getMessage());
			return null;
		}
	}
}
