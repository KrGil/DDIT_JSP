package kr.or.ddit.servlet01;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;

@WebServlet("/01/image.do")
public class ImageServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String imageFilename = req.getParameter("image");
		if(imageFilename == null || imageFilename.isEmpty()) { // 만약 null일때 400
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		String folder = "d:/contents";
		File imageFile = new File(folder, imageFilename); // 만약 imageFilename 폴더가 없을 때 404
		if(!imageFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		String mime = getServletContext().getMimeType(imageFilename); // tomcat에 명령을 내리는 메서드
		if(mime == null || !mime.startsWith("image/")) {
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return;
		}
		
		resp.setContentType(mime);
		
		FileInputStream fis = new FileInputStream(imageFile);
		
		OutputStream os =  resp.getOutputStream();
		byte[] buffer = new byte[1024]; //buffer의 크기를 지정해준다. 데이터가 담길 곳.
		
		int pointer = -1;
		while((pointer = fis.read(buffer)) != -1){
			os.write(buffer, 0, pointer); // 새로운 버퍼가 시작될 때 항상 0(첫 시작)부터 pointer까지 읽어라
		}
		//javac ImageServlet.java -d ..\classes -encoding UTF-8
		
	}
}