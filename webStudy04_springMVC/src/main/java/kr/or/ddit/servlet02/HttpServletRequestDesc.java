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
 * 
 * HttpServletRequest : 클라이언트와 요청에 대한 모든 정보를 캡슐화한 객체.
 * 
 * http의 요청 패키징 방식
 * 1. Request Line		: URL, request Method, protocol
 *   1) request method : 행위+방식(수단)
 *   	- GET(R) 		: 조회
 *   	- POST(C)		: 신규 등록  -- GET / POST는 서버 공통적으로 지원. 그 외에는 서버에 따라 다름.
 *   	- PUT/PATCH(U)	: 갱신 ** PUT(전체데이터)/PATCH(일부데이터)(U)
 *   	- DELETE(D)		: 삭제 
 *   	- OPTION		: preflight 요청에 사용
 *   	- HEADER		: 응답데이터가 필요없으니 라인과 헤더만 달라는 요청. body가 없는 응답을 요청할 때 사용
 *   	- TRACE			: 서버 디버깅 요청에 사용(특수한 경우에 사용)
 *   	DELETE(D) PUT/PATCH(U)를 쓰때에는 서버에서 지원되는지 확인해 보아야 한다. 그게 preflight()메서드이다.
 *   
 * 2. Request Header	: meta data 영역
 * 		Accept, Content-Type, User-Agent,
 * 		
 * 3. Request Body(ONLY POST) : 서버로 전송할 컨텐츠
 * 		PUT일 경우 POST_FORM 안에 INPUT TYPE 'HIDDEN'으로 보냄. 그래서 결국엔 POST 
 * 		
 */
@WebServlet("/requestDesc.do")
public class HttpServletRequestDesc extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// line
		String uri = req.getRequestURI();
		String method = req.getMethod();
		String protocol = req.getProtocol();
		System.out.printf("Request Line : %s %s %s\n", uri, method, protocol);
		
		// header
		System.out.println("================Request Header================");
		Enumeration<String> names = req.getHeaderNames();
		while (names.hasMoreElements()) {
			String headerName = (String) names.nextElement();
			String headerValue = req.getHeader(headerName);
			System.out.printf("%s : %s\n", headerName, headerValue);
		}
		System.out.println("===========Request Body==============");
		
		// body
//		InputStream is = req.getInputStream();
//		int byteLength = is.available();
//		System.out.printf("body length : %d\n", byteLength);
		
		// parameter
		System.out.println("===========Request parameter==============");
		Map<String, String[]> parameterMap = req.getParameterMap();
		Set<String> parameterNames =  parameterMap.keySet();
		Iterator<String> it = parameterNames.iterator();
		while (it.hasNext()) {
			String parameterName = (String) it.next();
			String[] values= req.getParameterValues(parameterName);
			System.out.printf("%s : %s\n", parameterName, Arrays.toString(values));
		}
		
	}
}









