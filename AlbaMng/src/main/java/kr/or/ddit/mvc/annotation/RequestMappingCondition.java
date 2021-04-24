package kr.or.ddit.mvc.annotation;

/**
 * HandlerMappging 에 의해 수집된 핸들러들에 대한 정보가 Map으로 구성될때,
 * key 의 역할을 부여할 클래스.
 *
 */
public class RequestMappingCondition {
	private String url;
	private RequestMethod method;
	
	public RequestMappingCondition(RequestMapping requestMapping){
		this.url = requestMapping.value();
		this.method = requestMapping.method();
	}

	public RequestMappingCondition(String url, RequestMethod method) {
		super();
		this.url = url;
		this.method = method;
	}
	
	public String getUrl() {
		return url;
	}

	public RequestMethod getMethod() {
		return method;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestMappingCondition other = (RequestMappingCondition) obj;
		if (method != other.method)
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("[%s-%s]", url, method);
	}
}










