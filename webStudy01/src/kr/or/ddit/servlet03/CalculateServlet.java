package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.MimeType;
import kr.or.ddit.enumpkg.OperatorType;
import kr.or.ddit.servlet03.view.JsonView;
import kr.or.ddit.servlet03.view.XmlView;
import kr.or.ddit.vo.CalculateVO;
import sun.net.www.MimeTable;

@WebServlet("/03/calculator")
public class CalculateServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
//		요청 분석(검증)
		int status = bindAndValidate(req);
		if(status!=HttpServletResponse.SC_OK) {
			resp.sendError(status);
			return;
		}
//		연산 callByRefernce를 씀.
		// scope를 쓰는 이유 : 전역변수가 불가능하다. 접속저가 한두명이 아니고 servlet은 singleton으로 운영되기 때문에 동시에 작업이 이루어졌을 때
		// 어떤 값이 담겨있는지 모르기 때문.
		CalculateVO vo = (CalculateVO) req.getAttribute("vo");
		
		OperatorType operator = vo.getOperator();
		double result = operator.operate(vo.getLeft(), vo.getRight());
		String expr = operator.expression(vo);
		
		//vo에 결과 담아주기
		vo.setResult(result);
		vo.setExpression(expr); //callByReference이기 때문에 다시requset에 담아줄 필요가 없다.
		
		
//		응답 전송
		String accept = req.getHeader("accept");
		MimeType mimeType = MimeType.searchMimeType(accept);
		resp.setContentType(mimeType.getMime());
		
		StringBuffer respData = new StringBuffer(); // 어차피 모두 text이기때문
		String view = null;
		// model1이다. 혼자서 request받고 response내보내줬기 때문이다.
		switch (mimeType) {
			case JSON:
				new JsonView().mergeModelAndView(vo, resp);
				break;
			case XML:
				new XmlView().mergeModelAndView(vo, resp);
				break;
			case PLAIN:
				respData.append(expr);
				break;
			default:
				view = "/WEB-INF/views/calculatorView.jsp";
				break;
		}
		
		if(view!= null) {
			
			req.getRequestDispatcher(view).forward(req, resp);
		}else {
			// trycatch 자동으로 닫아주기
			try(
					PrintWriter out = resp.getWriter();
					){
				out.print(respData); //직렬화 - serialization
			}
		}
	}

	private int bindAndValidate(HttpServletRequest req) {
		int status = HttpServletResponse.SC_OK; // return 먼저 선언
		
		String leftParam = req.getParameter("left");
		String rightParam = req.getParameter("right");
		String operatorParam = req.getParameter("operator");
		
		double left = -1;
		double right = -1;
		OperatorType operator = null;
		
		if(leftParam == null || rightParam == null || operatorParam == null) {
			status = HttpServletResponse.SC_BAD_REQUEST;
		}else {
			try { // 정규표현식을 써주지 않고도 검증이 가능하다! 두가지 방법!!
				left = Double.parseDouble(leftParam);
				right = Double.parseDouble(rightParam);
				operator = OperatorType.valueOf(operatorParam);
			} catch (IllegalArgumentException e) { // NumberFormatException | 얘 상위에 illegal이 있기 때문에 빼도 된다.
				status = HttpServletResponse.SC_BAD_REQUEST;
			}
		}
		
		// 상태코드 결정.
		if(status != HttpServletResponse.SC_BAD_REQUEST) {
			// vo에 담아서 request에 저장. request객체는 callByReference이기 때문에 여기서 해도 된다.
			CalculateVO vo = new CalculateVO(left, right, operator);
			req.setAttribute("vo", vo);
		}
		return status;
	}
}
