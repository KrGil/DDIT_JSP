package kr.or.ddit.servlet01;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;

@WebServlet("/01/videoView.do")
public class VideoServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req,
                     HttpServletResponse resp)
              throws ServletException,
                     IOException{
	
		String videoFilename = req.getParameter("videoFile");
		
		if(videoFilename == null || videoFilename.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		String folder = "d:/contents";
		File videoFile = new File(folder, videoFilename);
		if(!videoFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		String mime = getServletContext().getMimeType(videoFilename);
		System.out.println(mime);
		if(mime == null || !mime.startsWith("video/") && !mime.startsWith("image/")) {
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return;
		}
		
		resp.setContentType(mime);
		
		FileInputStream fis = new FileInputStream(videoFile);				
		OutputStream os = resp.getOutputStream();
		byte[] buffer = new byte[1024];
		int pointer = -1;
		while((pointer = fis.read(buffer))!=-1){
			os.write(buffer, 0, pointer);
		}
	}
}