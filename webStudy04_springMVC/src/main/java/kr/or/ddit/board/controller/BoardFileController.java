package kr.or.ddit.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.AttatchVO;

@Controller
public class BoardFileController {
	private static final Logger logger = LoggerFactory.getLogger(BoardFileController.class);
	// 자기자신에게 등록된 bean에 자기자신의 reference를 집어넣을 수 있다.
	@Inject
	private WebApplicationContext container;
	private ServletContext application;
	@Inject
	private IBoardService service;
	// setter 만드는 이유 PRIVATE에 inject가 붙으면 public으로 강제로 바꾸기 때문
//	@Inject
//	public void setContainer(WebApplicationContext container) {
//		this.container = container;
//		application = container.getServletContext();
//	}
	// injection이 끝나고 나면 lifecycle을 호출한다.
	@Value("#{appInfo.boardImages}")
	private String saveFolderURL;
	private File saveFolder ;
	
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
			) {
		AttatchVO attatch = service.download(att_no);
		model.addAttribute("attatch", attatch);
		return "downloadView";
	}
	
	@RequestMapping(
			value="/board/boardImage.do"
			, method=RequestMethod.POST
			, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	// 마샬링과 직렬화를 어뎁터가 자동으로 해 주게끔 하자.
	@ResponseBody
	public Map<String, Object> imageUpload(
			@RequestPart("upload")	MultipartFile upload
	) throws IllegalStateException, IOException {
		
		Map<String, Object> resultMap = new HashMap<>();
		// 실제 파일이 업로드 되었을 때
		if(!upload.isEmpty()) {
			String saveName = UUID.randomUUID().toString();
			upload.transferTo(new File(saveFolder, saveName));
			int uploaded = 1;
			String fileName = upload.getOriginalFilename();
			//https://ckeditor.com/apps/ckfinder/userfiles/images/4.jpgs
			String url = application.getContextPath()+saveFolderURL + "/" + saveName;
			resultMap.put("uploaded", uploaded);
			resultMap.put("fileName", fileName);
			resultMap.put("url", url);
		}
		return resultMap;
	}
}
