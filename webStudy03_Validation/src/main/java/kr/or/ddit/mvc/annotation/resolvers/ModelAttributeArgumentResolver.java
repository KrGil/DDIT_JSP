package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ClassUtils;

/**
 *	@ModelAttribute 어노테이션으로 설정된 핸들러 메소드 아규먼트를 처리할 처리자. 
 *	
 */
public class ModelAttributeArgumentResolver implements IHandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		// 1. 아규먼트가 잇느냐 없느냐 
		// 2. 스트링이냐
		Class<?> parameterType = parameter.getType();
		ModelAttribute annotation = parameter.getAnnotation(ModelAttribute.class);
		boolean supported = annotation!=null
						&& !(
								String.class.equals(parameterType)
								// 기본형, Integer 등인지 확인 lang3을 사용
								|| ClassUtils.isPrimitiveOrWrapper(parameterType)
							);
		return supported;
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		parameter.getAnnotation(ModelAttribute.class);
		// 이 한줄이 MemberVO member = new MemberVO와 같다
		ModelAttribute annotation = parameter.getAnnotation(ModelAttribute.class);
		try {
			Object parameterValue= parameterType.newInstance();
			String attributeName = annotation.value();
			req.setAttribute(attributeName, parameterValue); //문제 생길까바 미리 집어넣음.
		
			//규칙성 Mem_id와 변수명 mem_id가 같다. 그럼 reflection을 쓸 수 있다.
			BeanUtils.populate(parameterValue, req.getParameterMap());
			return parameterValue;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
