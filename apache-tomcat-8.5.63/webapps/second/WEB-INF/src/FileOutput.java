package kr.or.ddit;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.imageio.ImageIO;

import java.awt.image.*;
import java.io.*;
import java.util.*;

// 한글로 주석을 처리해 보자.
public class FileOutput extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException, ServletException{
		// M InternetMailExtends TEXT : mainType/subType
		// text/html, text/css, text/javascript, text/pain
		// application/json, application/xml, image/jpeg, video/mp4
		
		resp.setContentType("image/jpg; charset=UTF-8");
		// uploadfile
		String uploadPath = "D:/contents/ccc.jpg";
		File file = new File(uploadPath);
		BufferedImage bi = ImageIO.read(file);
		
		OutputStream os = resp.getOutputStream();
		ImageIO.write(bi, "jpg", os);
		//javac FileOutput.java -d ..\classes -cp %catalina_home%\lib\servlet-api.jar -encoding UTF-8
	}
}
