package kr.or.krgil.mvc.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.krgil.utils.ReflectionUtils;

public class HandlerMapping implements IHandlerMapping {
	private static final Logger logger = LoggerFactory.getLogger(HandlerMapping.class);
	private Map<RequestMappingCondition, RequestMappingInfo> handlerMap;
	
	public HandlerMapping(String...basePackages) {
		handlerMap = new LinkedHashMap<>();
		if(basePackages==null || basePackages.length ==0) 
			return;
		Map<Class<?>, Annotation> controllerClasses 
				= ReflectionUtils.getClassesWithAnnotationAtBasePackages(Controller.class, basePackages);
		for(Entry<Class<?>, Annotation> entry : controllerClasses.entrySet()) {
			Class<?> controllerClass = entry.getKey();
			Object commandHandler = null;
			try {
				commandHandler =  controllerClass.newInstance();
			}catch(Exception e) {
				logger.error("컨트롤러 객체 생성 문제 발생", e);
				continue;
			}
			Map<Method, Annotation> handlerMethods 
				=  ReflectionUtils.getMethodsWithAnnotationAtClass(
						controllerClass, 
						RequestMapping.class,
						String.class);
			// 파라미터에 대한 정보를 체킹하지 ㅇ낳겠다.
						// 트레이싱 과정에서 이 두 녀석을 넣었다. 그래서 반드시 넣어야함.
//						HttpServletRequest.class, HttpServletResponse.class);
			if(handlerMethods.size() == 0) continue;
			Iterator<Method> it = handlerMethods.keySet().iterator();
			while (it.hasNext()) {
				Method handlerMethod = (Method) it.next();
				RequestMapping requestMapping 
						= (RequestMapping) handlerMethods.get(handlerMethod);
				RequestMappingCondition mappingCondition
						= new RequestMappingCondition(requestMapping);
				RequestMappingInfo mappingInfo = 
						new RequestMappingInfo(mappingCondition, commandHandler, handlerMethod);
				handlerMap.put(mappingCondition, mappingInfo);
				logger.info("{}", mappingInfo);
			}
		}
	}
	
	@Override
	public RequestMappingInfo findCommandHandler(HttpServletRequest req) {
		// webstudy02가 붙어잇다.
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length()).split(";")[0];
		RequestMethod method 
				= RequestMethod.valueOf(req.getMethod().toUpperCase());
		RequestMappingCondition key 
				= new RequestMappingCondition(uri, method);
		// ToDO
		return handlerMap.get(key);
	}

}
