package kr.or.ddit.servlet01;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

@Controller
public class ImageController{
	@Inject
	private WebApplicationContext context;
	
	private ServletContext application;
	
	@Value("#{appInfo.contentFolder}")
	private File folder;
	
	@PostConstruct
	public void init() {
		this.application = context.getServletContext();
	}

	@RequestMapping("/01/image.do")
	public String doGet(
			@RequestParam("image") String imageFilename
           , HttpServletResponse resp)
              throws ServletException,
                     IOException{
		File imageFile = new File(folder, imageFilename);
		if(!imageFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		String mime = application.getMimeType(imageFilename);
		if(mime==null || !mime.startsWith("image/")) {
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return null;
		}
		
		resp.setContentType(mime);		
		try(
			FileInputStream fis = new FileInputStream(imageFile);
			OutputStream os = resp.getOutputStream();
		){		
			byte[] buffer = new byte[1024];
			int pointer = -1;
			while((pointer = fis.read(buffer))!=-1){
				os.write(buffer, 0, pointer);
			}
		}
		return null;
	}

}



















