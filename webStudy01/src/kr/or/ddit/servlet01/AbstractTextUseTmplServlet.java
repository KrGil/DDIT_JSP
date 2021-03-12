package kr.or.ddit.servlet01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.startup.SetContextPropertiesRule;
// 템플릿 클래스
public abstract class AbstractTextUseTmplServlet extends HttpServlet{
	protected ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = config.getServletContext();
	}
	
	@Override //service -> 먼저 받고 doXXX에게 분배하는 역할. super를 지운다는건 그걸 안하겠다.
	// throws 미루고 미루다 service를 호출한 녀석(이 녀석은 callbackMethod)결국 톰캣이 오류를 처리해서 500error를 반환한다.
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 틀(순서에 따라 메소드를 부르는 녀석)을 가지고 있다. 이게 템플릿 메서드 패턴다.
		// 0. mime 결정
		setContentType(resp);
		// 1. tmpl 읽기
		StringBuffer tmplSrc = readTmpl(req);
		// 2. 데이터 만들기
		makeData(req);
		// 3. tmpl을 데이터로 치환 req.setAttribute / req.getAttribute와 비슷
		StringBuffer html = replaceData(tmplSrc, req);
		// 4. 응답을 전송 try ~ with resource
		try(
			PrintWriter out = resp.getWriter();
		){
			out.println(html);
		}
	}
	// 5. mime을 다르게 넣어줘도 된다
	protected abstract void setContentType(HttpServletResponse resp);

	// 3. tmpl을 데이터로 치환
	private StringBuffer replaceData(StringBuffer tmplSrc, HttpServletRequest req) {
		Pattern regex = Pattern.compile("%([a-zA-Z0-9_]+)%");
		Matcher matcher  = regex.matcher(tmplSrc);
		
		StringBuffer html = new StringBuffer(); // 최종 결과를 담는 녀석
		while(matcher.find()) {
			String name = matcher.group(1);
			Object value = req.getAttribute(name);
			if(value != null) {
				matcher.appendReplacement(html, value.toString()); // 알아서 식별자를 구분해서 해줌.
			}
		}
		matcher.appendTail(html); // while문이 끝나고 남은 녀석들을 처리해주어야한다.
		
		return html;
	}
	// 2. 데이터 만들기
	// 데이터는 자식이 결정해서 만들어진다(select나 option 등등) 그래서 abstact로. method 중 하나라도 abstract면 클래스도 abstract
	protected abstract void makeData(HttpServletRequest req);
	// 1. tmpl 읽기
	
		private StringBuffer readTmpl(HttpServletRequest req) throws IOException {
			String tmplPath = req.getServletPath();
			InputStream is = application.getResourceAsStream(tmplPath); // 파일 읽기
			InputStreamReader isr = new InputStreamReader(is); // input(1byte) -> reader(2byte)로 
			BufferedReader reader = new BufferedReader(isr); // 한줄씩 팍팍 읽어서 쏴주자
			String temp = null;
			StringBuffer tmplSrc = new StringBuffer();
			while((temp = reader.readLine()) != null) {
				tmplSrc.append(String.format("%s\n",temp));
			}
			return tmplSrc;
		}
}
