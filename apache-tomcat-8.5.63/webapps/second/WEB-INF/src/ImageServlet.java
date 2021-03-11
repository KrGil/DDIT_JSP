package kr.or.ddit;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;


public class ImageServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String mime = "image/jpeg; characterSet=UTF-8";
		resp.setContentType(mime);
		String folder = "d:/contents";
		String imageFilename = req.getParameter("image");
		
		File imageFile = new File(folder, imageFilename);
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