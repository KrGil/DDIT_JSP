package kr.or.ddit.fileupload.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.BadRequestException;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;

@Controller
public class FileUploadController {
	private static final Logger logger = 
			LoggerFactory.getLogger(FileUploadController.class);
	@RequestMapping("/fileUpload.do")
	public String form() {
		return "fileupload/uploadForm";
	}
	
	@RequestMapping(value="/fileUpload.do", method=RequestMethod.POST)
	public String upload(
			@RequestParam("uploader") String uploader,
			HttpServletRequest req, HttpSession session) throws IOException, ServletException {
		
		ServletContext application = req.getServletContext();
		String saveFolderUrl = "/prodImages";
		File saveFolder = new File(application.getRealPath(saveFolderUrl));
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		
		Collection<Part> parts = req.getParts();
		int index = 1;
		for(Part tmp : parts) {
			// 일반 파일이면(텍스트) 건너뛰어야한다.
			if(tmp.getContentType()==null) {
				continue;
			}
			String saveFileName = processPart(tmp, saveFolder);
			String saveFileUrl = saveFolderUrl+"/"+saveFileName;
			session.setAttribute("uploadFile"+index++, saveFileUrl);
			logger.info("saveFile : {}", saveFileUrl);
		}
		
		logger.info("uploader : {}", uploader);
		return "redirect:/fileUpload.do";
	}

	private String getOriginalFilename(Part part) {
//		Content-Disposition: form-data; name="uploadFile1"; filename="test.jpg"
		String disposition = part.getHeader("Content-Disposition");
		int index = disposition.indexOf("\"", disposition.indexOf("filename="));
		String originalFilename = null;
		if(index!=-1) {
			originalFilename = disposition.substring(index).replace("\"", "");
		}
		return originalFilename;
	}
	
	private String processPart(Part uploadFile1, File saveFolder) throws IOException {
		String originalFilename1 = getOriginalFilename(uploadFile1);
		// 원본 파일이 있는지 부터 검사.
		if(StringUtils.isNotBlank(originalFilename1)) {
			String mime1 = uploadFile1.getContentType();
			if(!mime1.startsWith("image/")) {
				throw new BadRequestException();
			}
			File saveFile1 = new File(saveFolder, originalFilename1);
			
			byte[] buffer = new byte[1024];
			int  cnt = -1;
			try(
				InputStream is1 = uploadFile1.getInputStream();	
				FileOutputStream fos1 = new FileOutputStream(saveFile1);
			){
				while((cnt = is1.read(buffer))!=-1) {
					fos1.write(buffer,  0, cnt);
				}
			} // try end
		}  // if end
		return originalFilename1;
	}
}
