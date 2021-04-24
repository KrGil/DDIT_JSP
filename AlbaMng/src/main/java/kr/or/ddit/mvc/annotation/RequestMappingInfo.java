package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

/**
 * 하나의 커맨드를 처리할 수 있는 핸들러 하나에 대한 정보를 가진 객체
 * 차후에 handler map 의 value 로 사용됨.
 *
 */
public class RequestMappingInfo {
	private RequestMappingCondition mappingCondition;
	private Object commandHandler;
	private Method handlerMethod;
	
	public RequestMappingInfo(RequestMappingCondition mappingCondition, Object commandHandler, Method handlerMethod) {
		super();
		this.mappingCondition = mappingCondition;
		this.commandHandler = commandHandler;
		this.handlerMethod = handlerMethod;
	}

	public RequestMappingCondition getMappingCondition() {
		return mappingCondition;
	}

	public Object getCommandHandler() {
		return commandHandler;
	}

	public Method getHandlerMethod() {
		return handlerMethod;
	}
	
	@Override
	public String toString() {
		return String.format("%s : %s.%s"
				, mappingCondition, commandHandler.getClass().getName(), handlerMethod.getName());
	}
}






