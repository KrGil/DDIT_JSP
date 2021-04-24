package kr.or.ddit.servlet01;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;

@WebServlet("/01/textView_1.do")
public class textServlet_1 extends HttpServlet{
	protected void doGet(HttpServletRequest req,
                     HttpServletResponse resp)
              throws ServletException,
                     IOException{
	
		String textFilename = req.getParameter("textFile");
		if(textFilename == null || textFilename.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		String folder = textFormServlet_1.class.getClassLoader().getResource("datas").toString();
		folder = folder.substring(folder.indexOf("D"));
		File textFile = new File(folder, textFilename);
		if(!textFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		String mime = getServletContext().getMimeType(textFilename);
		if(mime == null || !mime.startsWith("text/")) {
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return;
		}
		
		resp.setContentType(mime);
		
		FileInputStream fis = new FileInputStream(textFile);				
		OutputStream os = resp.getOutputStream();
		byte[] buffer = new byte[1024];
		int pointer = -1;
		while((pointer = fis.read(buffer))!=-1){
			os.write(buffer, 0, pointer);
		}
	}
}