package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Video_servlet
 */
@WebServlet("/Video_servlet.do")
public class Video_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String videoFilename = request.getParameter("videoFile");
		
		if(videoFilename == null || videoFilename.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		String folder = "d:/contents";
		File videoFile = new File(folder, videoFilename);
		if(!videoFile.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		String mime = getServletContext().getMimeType(videoFilename);
		System.out.println(mime);
		if(mime == null || !mime.startsWith("video/") && !mime.startsWith("image/")) {
			response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return;
		}
		
		response.setContentType(mime);
		
		FileInputStream fis = new FileInputStream(videoFile);				
		OutputStream os = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int pointer = -1;
		while((pointer = fis.read(buffer))!=-1){
			os.write(buffer, 0, pointer);
		}
	}
}
