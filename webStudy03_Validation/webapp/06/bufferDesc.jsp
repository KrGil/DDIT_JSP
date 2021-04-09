<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="8kb" autoFlush="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/bufferDesc.jsp</title>
</head>
<body>
<h4> JspWriter (out) : 응답 기록, 버퍼 제어 </h4>
<pre>
	: 응답 데이터 전송 시 임시 버퍼(buffer 속성으로 크기 설정)가 활용됨.
	** 버퍼가 한번이라도 flush 된 이후에는 에러 처리가 불가능함.
	** 버퍼가 한번이라도 flush 된 이후에는 forward가 불가능함.
	getBufferSize : <%=out.getBufferSize() %>
	getRemaining : <%=out.getRemaining() %>
	isAutoFlush : <%=out.isAutoFlush() %>
	out.flush : 명시적 방출
	out.clearBuffer : 버퍼 비우기
	out.clear : : 버퍼 비우기지만, 한번이라도 flush 된 이후라면 에러.
	
	<%-- 
		out.flush(); // 비우고 보내기
		out.clearBuffer(); // 비우고 삭제 flush 이후에도 작동
		out.clear(); 
	--%>
	
	<%--
		for(int count=1; count<=100; count++){
			out.println(count+"번째 반복, 버퍼의 잔여 용량 : "+out.getRemaining());
			if(count==95){
// 				throw new IllegalArgumentException("강제 발생 예외");
				request.getRequestDispatcher("/06/implicitObject.jsp").include(request, response);
			}
		}
	--%>
</pre>
</body>
</html>