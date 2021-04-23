package kr.or.ddit.servlet01;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;
import java.util.Date;

@WebServlet("/01/videoViewer.tmpl")
public class VideoFormServlet extends AbstractTextUseTmplServlet {

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
//		System.out.println(folder.substring(folder.indexOf("D")));
		File contents = new File(folder);
//        <source src="../multi/trailer.mp4" type='video/mp4'>
//        <source src="../multi/trailer.ogv"  type='video/ogg'>
//        <source src="../multi/trailer.webm"  type='video/webM'>
		
		String[] children = contents.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				String mime = application.getMimeType(name);
				return mime != null && (mime.startsWith("video/") || mime.startsWith("image/"));
			}
		});
		StringBuffer options = new StringBuffer();
		
		for (String child : children) {
			options.append(String.format("<option>%s</option>", child));
		}
		req.setAttribute("options", options);
	}
	
}