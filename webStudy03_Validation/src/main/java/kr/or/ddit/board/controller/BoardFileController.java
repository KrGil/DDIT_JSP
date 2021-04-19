package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;

@Controller
public class BoardFileController {
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
