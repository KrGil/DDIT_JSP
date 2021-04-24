package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.mvc.filter.wrapper.MultipartHttpServletRequest;

public class RequestPartArgumentResolver implements IHandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		RequestPart annotation = parameter.getAnnotation(RequestPart.class);
		Class<?> parameterType = parameter.getType();
		return annotation!=null && 
					(MultipartFile.class.equals(parameterType)
					|| (parameterType.isArray() 
							&& MultipartFile.class.equals(parameterType.getComponentType())));
	}

	@Override
	public Object argumentResolve(Parameter parameter, 
			HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(!(req instanceof MultipartHttpServletRequest)) {
			throw new ServletException("현재 요청은 multipart 요청이 아님.");
		}
		
		RequestPart annotation = 
				parameter.getAnnotation(RequestPart.class);
		
		String partName = annotation.value();
		boolean required = annotation.required();
		
		MultipartHttpServletRequest wrapper = 
				(MultipartHttpServletRequest) req;
		List<MultipartFile> files 
			= wrapper.getFiles(partName);
		if(required && files==null) {
			throw new BadRequestException(partName
							+"에 해당하는 파일이 업로드되지 않았음.");
		}
		
		Class<?> parameterType = parameter.getType();
		
		Object retValue = null;
		if(files!=null && files.size()>0) {
			if(parameterType.isArray()) {
				MultipartFile[] array = new MultipartFile[files.size()];
				retValue = files.toArray(array);
			}else {
				retValue = files.get(0);
			}
		}
		return retValue;
	}

}










