package kr.or.ddit.servlet02;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * 
 * HttpServletResponse : client로 전송될 응답과 관련된 모든 정보를 가진 객체.
 * Http 응답 패키징 방식
 * 1. Response Line : 상태코드(status code), protocol
 * 	** status code	: 요청 처리 결과를 의미하는 숫자
 * 	1) 1XX(Http 1.1부터) : ing... webSocket에서 사용. (현재 연결통로가 유지되어있다)
 * 	2) 2XX : OK(success)
 * 	3) 3XX : 클라이언트의 추가 액션이 요구되는 상태 코드.
 * 		304(Not Modified)
 * 		302/307(Moved, location 헤더와 함께 사용redirection)
 * 	4) 4XX : client side Fail
 * 		404(NOt Found), 400(Bad Request),
 * 		405(Method Not Allowed), - 상속받은 메서드를 아무것도 오버라이드 하지 않으면 발생
 * 		415(Unsupported Media Type), - 현재 클라이언트가 요구하는 미디어는 지원하지 않는다.
 * 		401(UnAuthorized), 403(Forbidden)외부로 유출을 막을 때 - 보안
 * 	5) 5XX : server side Fail, Internal server Error
 * 
 * 2. Response Header : meta data(데이터 자체에 대한 설정)
 * 	  Content-Type(mime)excel로 내보내는지 결정., Content-Length(length)
 * 	  response.setContentType(mime)
 * 	  response.setHeader(name, value)
 * 	  response.setDateHeader(name, value(long))
 * 	  response.setIntHeader(name, value(int))
 * 		How to use
 * 
 * 3. Resposne Body(message body, content body)
 *	  response.getWriter() - character, response.getOutputStream() - 2진수 으로 기록.
 */
@WebServlet ("/respDesc.do")
public class HttpServletResponseDesc extends HttpServlet{
	
}
