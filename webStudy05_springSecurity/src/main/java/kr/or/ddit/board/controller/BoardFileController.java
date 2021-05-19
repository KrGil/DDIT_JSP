package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.AttatchVO;

@Controller
public class BoardFileController {
	@Inject
	private WebApplicationContext container;
	private ServletContext application;
	@Inject
	private IBoardService service;
	@Value("#{appInfo['boardImages']}")
	private String saveFolderURL;
	private File saveFolder;
	
	@PostConstruct
	public void init() {
		application = container.getServletContext();
		String saveFolderPath = application.getRealPath(saveFolderURL);
		saveFolder = new File(saveFolderPath);
	}
	
	
	@RequestMapping("/board/download.do")
	public String download(
		@RequestParam("what") int att_no
		, Model model
	){
		AttatchVO attatch = service.download(att_no);
		model.addAttribute("attatch", attatch);
		return "downloadView";
	}
	
	@RequestMapping(value="/board/boardImage.do"
				, method=RequestMethod.POST
				, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> imageUpload(
		@RequestPart("upload") MultipartFile upload
	) throws IllegalStateException, IOException{
		Map<String, Object> resultMap = new HashMap<>();
		if(!upload.isEmpty()) {
			String saveName = UUID.randomUUID().toString();
			upload.transferTo(new File(saveFolder, saveName));
			int uploaded = 1;
			String fileName = upload.getOriginalFilename();
			String url = application.getContextPath() + saveFolderURL + "/" + saveName;
			resultMap.put("uploaded", uploaded);
			resultMap.put("fileName", fileName);
			resultMap.put("url", url);
		}
		return resultMap;
	}
}



















