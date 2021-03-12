package kr.or.ddit.servlet01;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;
import java.util.Date;

@WebServlet("/01/imageForm.tmpl")
public class ImageFormServlet extends AbstractUseTmplServlet {

	@Override
	protected void setContentType(HttpServletResponse resp) {
		// TODO Auto-generated method stub
		resp.setContentType("text/html; charset=utf-8");
	}

	@Override
	protected void makeData(HttpServletRequest req) {
		// 이미지를 제외한 파일 표기 x
		// - 개발환경 구축 완료 보고서
		System.out.println("서블릿이요청 받았음");
		
		String folder = "d:/contents";
		File contents = new File(folder);
	
		String[] children = contents.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				String mime = application.getMimeType(name);
				return mime != null && mime.startsWith("image/");
			}
		});
		// 1. tmlp 읽는다
		// 2. 만든다
		// 3. 구멍채운다
		// 4. 출력스트림으로 내보낸다.
		
		Date today = new Date();
		req.setAttribute("today", today);
		
		StringBuffer options = new StringBuffer();
		
		for (String child : children) {
			options.append(String.format("<option value=%s>%s</option>", child, child));
		}
		req.setAttribute("options", options);
	}
	
	
}