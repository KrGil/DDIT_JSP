package kr.or.ddit.servlet02;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * HttpServletResponse : client 로 전송될 응답과 관련된 모든 정보를 가진 객체.
 * 
 * Http 응답 패키징 방식
 * 1. Response Line : 상태코드(status code), protocol
 * 	 ** status code : 요청 처리 결과를 의미하는 숫자
 *   1) 1XX(Http 1.1 부터 ) : ing...
 *   2) 2XX : OK(success)
 *   3) 3XX : 클라이언트의 추가 액션이 요구되는 상태 코드.
 *   	304(Not Modified)
 *      302/307(Moved, location 헤더와 함께 사용)
 *   4) 4XX : client side Fail
 *   	404(Not Found), 400(Bad Request),
 *      405(Method Not Allowed), 415(Unsupported Media Type),
 *      401(UnAuthorized), 403(Forbidden) 
 *   5) 5XX : server side Fail, Internal server Error
 * 2. Response Header : meta data
 * 	  Content-Type(mime), Content-Length(length)
 * 		response.setContentType(mime)
 * 		response.setHeader(name, value)
 * 		response.setDateHeader(name, value(long))
 * 		response.setIntHeader(name, value(int))
 *    
 *    1) 캐시 제어
 *    2) auto request(polling)
 *    3) 흐름 제어(redirect/forward[include])
 * 
 * 3. Response Body(message body, content body)
 * 	  response.getWriter(), repsonse.getOutputStream() 으로 기록.
 *
 */
@WebServlet("/respDesc.do")
public class HttpServletResponseDesc extends HttpServlet{

}











