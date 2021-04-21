package kr.or.ddit.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.vo.AttatchVO;

@Controller
public class BoardFileController {
	IBoardService service = new BoardServiceImpl();
	private File saveFolder = new File("D:/attatches");
	private static final Logger logger = LoggerFactory.getLogger(BoardFileController.class);
	
	@RequestMapping("/board/download.do")
	public String download(
			@RequestParam("what") int att_no
			, HttpServletResponse resp
			, HttpServletRequest req) throws FileNotFoundException, IOException {
		// pk 받기
		// attatch 불러오기
		// inputstream으로 읽어오기
		// outputstream으로 내보내기
		AttatchVO attatch = new AttatchVO();
		attatch = service.download(att_no);
		String filename = attatch.getAtt_filename();
		
		// 인코딩을 모두 안깨지게 하려면 클라이언트의 정보를 받아서 각각 바꾸면 된다.
		String agent = req.getHeader("User-Agent");
		if(StringUtils.containsIgnoreCase(agent, "trident")) {
			filename = URLEncoder.encode(filename, "utf-8").replaceAll("\\+", "");
		}else { // non_explore계열
			// 글을 모두 쪼개서 보내겠다.
			byte[] bytes = filename.getBytes();
			filename = new String(bytes, "ISO-8859-1");
		}
		// 한글이름 파일을 다운받을때 깨짐 방지
//		originalFileName = new String(
//				originalFileName.getBytes("euc-kr"),"ISO-8859-1"); // 이렇게 써줘도 됨 8859_1
		
		// inline - 화면에 바로 띄우기
		// attatchment - 다운로드하기
		resp.setHeader("Content-Disposition", "attatchment;filename="+filename);
		resp.setHeader("Content-Length", attatch.getAtt_size()+""); // header의 값은 문자열로 나가니까.
		// stream이 바이트(octet-8을 의미)단위로 나가고 있다.
		resp.setContentType("application/octet-stream");
		
		// 경로에 맞는 파일 클래스 구체화.
		File saveFile = new File(saveFolder, attatch.getAtt_savename());	
		if(!saveFile.exists())
			return null;
		//파일은 서버에 저장돼있음
		//서버에있는 파일을 내보내려면 일단 "먼저 파일을 읽어내서" 내보내야함
		try(
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(saveFile));
			// 읽어들인 파일을 "출력"한다
			OutputStream os = resp.getOutputStream();
		){
			FileUtils.copyFile(saveFile, os);
		}
		return null;
	}
	
	@RequestMapping(value="/board/boardImage.do",method=RequestMethod.POST)
	public String imageUpload(
			@RequestPart("upload")	MultipartFile upload
			, HttpServletRequest req
			, HttpServletResponse resp
			) throws IOException {
		// 저장할 위치
		String saveFolderURL = "/boardImages";
		String saveFolderPath = req.getServletContext().getRealPath(saveFolderURL);
		File saveFolder = new File(saveFolderPath);
		Map<String, Object> resultMap = new HashMap<>();
		// 실제 파일이 업로드 되었을 때
		if(!upload.isEmpty()) {
			upload.saveTo(saveFolder);
			int uploaded = 1;
			String fileName = upload.getOriginalFilename();
			String saveName = upload.getUniqueSaveName();
			//https://ckeditor.com/apps/ckfinder/userfiles/images/4.jpgs
			String url = req.getContextPath()+saveFolderURL + "/" + saveName;
			resultMap.put("uploaded", uploaded);
			resultMap.put("fileName", fileName);
			resultMap.put("url", url);
		}
		// mime타입을 설정하기 위해 response
		resp.setCharacterEncoding("application/json;charset=utf-8");
		// marshalling
		try(
			// serialize
			PrintWriter out = resp.getWriter();
			){
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, resultMap);
		}
		return null;
	}
}
