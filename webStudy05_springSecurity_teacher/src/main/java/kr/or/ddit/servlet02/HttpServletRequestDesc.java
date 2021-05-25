package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HttpServletRequest : 클라이언트와 요청에 대한 모든 정보를 캡슐화한 객체.
 * 
 * Http 의 요청 패키징 방식
 * 1. Request Line : URL, request method, protocol
 * 	1) request method : 행위+방식(수단)
 * 		- GET(R) : 조회
 *  	- POST(C) : 신규 등록
 *  	- PUT/PATCH(U) : 갱신
 *  	- DELETE(D) : 삭제
 *  	- OPTION : preflight 요청에 사용
 *  	- HEADER : body 가 없는 응답을 요청할때 사용
 *  	- TRACE : 서버 디버깅 요청에 사용
 * 2. Request Header : meta data 영역
 * 		Accept, Content-Type, User-Agent
 * 3. Request Body(only POST) : 서버로 전송할 컨텐츠
 *
 */
@WebServlet("/requestDesc.do")
public class HttpServletRequestDesc extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String method = req.getMethod();
		String protocol = req.getProtocol();
		System.out.printf("Request Line : %s %s %s\n", uri, method, protocol);
		System.out.println("========= Request Header =========");
		Enumeration<String> names = req.getHeaderNames();
		while (names.hasMoreElements()) {
			String headerName = (String) names.nextElement();
			String headerValue = req.getHeader(headerName);
			System.out.printf("%s : %s\n", headerName, headerValue);
		}
		System.out.println("========= Request Body =========");
//		InputStream is = req.getInputStream();
//		int byteLength = is.available();
//		System.out.printf("body length : %d", byteLength);
		
		System.out.println("========= Request Parameter =========");
		Map<String, String[]> parameterMap = req.getParameterMap();
		Set<String> parameterNames = parameterMap.keySet();
		Iterator<String> it = parameterNames.iterator();
		while (it.hasNext()) {
			String parameterName = (String) it.next();
			String[] values = req.getParameterValues(parameterName);
			System.out.printf("%s : %s\n", parameterName, Arrays.toString(values));
		}
	}
}












