package kr.or.ddit.fileupload.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	private static final Logger logger =
			LoggerFactory.getLogger(FileUploadController.class);
	
	@Inject
	private WebApplicationContext container;
	private ServletContext application;
	
	@PostConstruct
	public void init() {
		application = container.getServletContext();
	}
	
	@RequestMapping("/fileUpload.do")
	public String form() {
		return "fileupload/uploadForm";
	}
	
	@RequestMapping(value="/fileUpload.do", method=RequestMethod.POST)
	public String upload(
			@RequestParam("uploader") String uploader,
			@RequestPart(value="uploadFile1") MultipartFile[] file1,  
			@RequestPart(value="uploadFile2", required=false) MultipartFile[] file2,
			HttpSession session) throws IOException, ServletException {
		String saveFolderUrl = "/prodImages";
		File saveFolder = new File(application.getRealPath(saveFolderUrl));
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		
		if(file1.length>0) {
			String savename = UUID.randomUUID().toString();
			file1[0].transferTo(new File(saveFolder, savename));
			String saveFileUrl = saveFolderUrl +"/"+savename;
			session.setAttribute("uploadFile1", saveFileUrl);
			logger.info("saveFile : {}", saveFileUrl);
		}
		if(file2!=null && file2.length>0) {
			String savename = UUID.randomUUID().toString();
			file2[0].transferTo(new File(saveFolder, savename));
			String saveFileUrl = saveFolderUrl +"/"+savename;
			session.setAttribute("uploadFile2", saveFileUrl);
			logger.info("saveFile : {}", saveFileUrl);
		}
//		if(req instanceof MultipartHttpServletRequest) {
//			MultipartHttpServletRequest wrapper = 
//					(MultipartHttpServletRequest) req;
//			Map<String, List<MultipartFile>> fileMap 
//				= wrapper.getFileMap();
//			for(Entry<String, List<MultipartFile>> entry 
//					: fileMap.entrySet()) {
//				String partName = entry.getKey();
//				List<MultipartFile> files = entry.getValue();
//				for(MultipartFile file : files) {
//					if(file.isEmpty()) continue;
//					file.saveTo(saveFolder);
//					String saveFileUrl = saveFolderUrl +"/"+file.getUniqueSaveName();
//					session.setAttribute(partName, saveFileUrl);
//					logger.info("saveFile : {}", saveFileUrl);
//				}
//			}
//		}
		
//		Collection<Part> parts = req.getParts();
//		int index = 1;
//		for(Part tmp : parts) {
//			if(tmp.getContentType()==null) continue;
//			String saveFileName = processPart(tmp, saveFolder);
//			String saveFileUrl = saveFolderUrl+"/"+saveFileName;
//			session.setAttribute("uploadFile"+ index++, saveFileUrl);
//			logger.info("saveFile : {}", saveFileUrl);
//		}
		logger.info("uploader : {}", uploader);
		return "redirect:/fileUpload.do";
	}


}



















