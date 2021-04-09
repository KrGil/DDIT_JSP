package kr.or.krgil.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *	@RequestParam 어노테이션으로 설정된 핸들러 메소드 아규먼트를 처리할 처리자
 *
 */
public class RequestParamArgumentResolver implements IHandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		// request, modelattribute의 타입이 다르다면 사용 목적이 다르다.
		// 1. 어노테이션이 존재하는가
		Class<?> parameterType = parameter.getType();
		RequestParam annotation = 
				parameter.getAnnotation(RequestParam.class);
		boolean supported = annotation!=null
					&&(	// 값 하나를 받을 수 있는 녀석인가?
						String.class.equals(parameterType)
						|| ClassUtils.isPrimitiveOrWrapper(parameterType)
						);
		return supported;
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		RequestParam annotation = 
				parameter.getAnnotation(RequestParam.class);
		String reqParamName = annotation.value();
		String reqParamValue = req.getParameter(reqParamName);
		// value가 없는 경우 체크 -> 필수 파라미터 여부를 판단해야함.
		boolean required = annotation.required();
		if(required && StringUtils.isBlank(reqParamValue)) {
			throw new BadRequestException("필수파라미터 누락");
		}else if(!required && StringUtils.isBlank(reqParamValue)){
			reqParamValue = annotation.defaultValue();
		}
		Object parameterValue = null;
		try {
			// 타입 parsing해주기
			// 1.8 이후에 오토 언박싱이 가능함? 
			if(byte.class.equals(parameterType) || Byte.class.equals(parameterType)) {
				parameterValue = Byte.parseByte(reqParamValue);
			}else if(short.class.equals(parameterType) || Short.class.equals(parameterType)) {
				parameterValue = Short.parseShort(reqParamValue);
			}else if(int.class.equals(parameterType) || Integer.class.equals(parameterType)) {
				parameterValue = Integer.parseInt(reqParamValue);
			}else if(long.class.equals(parameterType) || Long.class.equals(parameterType)) {
				parameterValue = Long.parseLong(reqParamValue);
			}else if(float.class.equals(parameterType) || Float.class.equals(parameterType)) {
				parameterValue = Float.parseFloat(reqParamValue);
			}else if(double.class.equals(parameterType) || Double.class.equals(parameterType)) {
				parameterValue = Double.parseDouble(reqParamValue);
			}else if(boolean.class.equals(parameterType) || Boolean.class.equals(parameterType)) {
				parameterValue = Boolean.parseBoolean(reqParamValue);
			}else if(char.class.equals(parameterType) || Character.class.equals(parameterType)) {
				parameterValue = reqParamValue.charAt(0);
			}else {
				parameterValue = reqParamValue;
			}
			return parameterValue;
		}catch (Exception e) {
			throw new BadRequestException(e);
		}
	}
}
