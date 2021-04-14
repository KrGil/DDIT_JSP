package kr.or.ddit.mvc.annotation.resolvers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ClassUtils;

import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.mvc.filter.wrapper.MultipartHttpServletRequest;
/**
 *	@RequestPart  어노테이션으로 설정된 핸들러 메소드 아규먼트를 처리할 처리자
 *
 */
public class RequestPartArgumentResolver implements IHandlerMethodArgumentResolver{

	@Override
	public boolean isSupported(Parameter parameter) {
//		1. 어노테이션이 존재하는지
//		2. parameter 타입이 multipart인지
		RequestPart annotation = parameter.getAnnotation(RequestPart.class);
		Class<?> parameterType = parameter.getType();
		
		return annotation!=null
				&& (MultipartFile.class.equals(parameterType)
//						파라미터타입이 배일이면서 배열타입이 멀티파티타입이면
					|| (parameterType.isArray() && MultipartFile.class.equals(
											parameterType.getComponentType()))
					);
	}

	
	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 원본인지 랩핑된 녀석인지 확인하기
		if(!(req instanceof MultipartHttpServletRequest)) {
			throw new ServletException("현재 요청은 multipart 요청이 아님");
		}
		// 
		RequestPart annotation = parameter.getAnnotation(RequestPart.class);
		
		String partName = annotation.value();
		boolean required = annotation.required();
		
		// multipart타입 
		MultipartHttpServletRequest wrapper = (MultipartHttpServletRequest) req;
		List<MultipartFile> files = wrapper.getFiles(partName);
		if(required && files ==null) {
			throw new BadRequestException(partName + "에 해당하는 파일이 업로드 되지 않았음");
		}
		Class<?> parameterType = parameter.getType();
		
		Object retValue = null;
		if(files!=null && files.size()>0) {
			if(parameterType.isArray()) {
				MultipartFile[] array = new MultipartFile[files.size()];
				// list를 array로 변경
				retValue = files.toArray(array);
			}else {
				retValue = files.get(0);
			}
		}
		return retValue;
	}
}





