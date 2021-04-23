package kr.or.ddit.servlet01;

import javax.servlet.http.*;

import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;
import java.net.URLEncoder;

//@WebServlet("/01/image.do")
@Controller
public class ImageServlet2{
	@RequestMapping("/01/image.do")
	public String doGet(
			@RequestParam("image") String imageFilename,
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String folder = req.getServletContext().getInitParameter("contentFolder");
		File imageFile = new File(folder, imageFilename); // 만약 imageFilename 폴더가 없을 때 404
		if(!imageFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		String mime = req.getServletContext().getMimeType(imageFilename); // tomcat에 명령을 내리는 메서드
		if(mime == null || !mime.startsWith("image/")) {
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return null;
		}
		
		resp.setContentType(mime);
		try(
			FileInputStream fis = new FileInputStream(imageFile);
			OutputStream os =  resp.getOutputStream();
			){
			byte[] buffer = new byte[1024]; //buffer의 크기를 지정해준다. 데이터가 담길 곳.
			
			int pointer = -1;
			while((pointer = fis.read(buffer)) != -1){
				os.write(buffer, 0, pointer); // 새로운 버퍼가 시작될 때 항상 0(첫 시작)부터 pointer까지 읽어라
			}
		}
		return null;
	}
}