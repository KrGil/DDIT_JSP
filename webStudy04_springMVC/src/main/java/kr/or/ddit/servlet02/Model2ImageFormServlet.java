package kr.or.ddit.servlet02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.BadRequestException;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;

//@WebServlet("/02/imageForm.do")
@Controller
public class Model2ImageFormServlet {
	private ServletContext application;
	
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		super.init(config);
//		application = config.getServletContext();
//	}
	@RequestMapping(value="/02/imageForm.do",method=RequestMethod.POST)
	public String upload(
			@RequestPart("uploadImage") MultipartFile image,
			HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if(!image.isEmpty()) {
			String folder = application.getInitParameter("contentFolder");
			File contents = new File(folder);
			// 확장자를 통해 mimetype을 확인하다.
			String contentType = application.getMimeType(image.getOriginalFilename());
			// mime이 image가 아닌 경우
			if(contentType==null || !contentType.startsWith("image/")) {
				throw new BadRequestException("이미지만 업로드하랍니다.");
			}
			image.transferTo(new File(contents, image.getOriginalFilename()));
		}
		//post - redirect - get pattern
		return "redirect:/02/imageForm.do";
	}
	
	@RequestMapping("/02/imageForm.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 다 싱글톤이기에 이렇게 if로 한다.
		if(application==null)
				application = req.getServletContext();
		String folder = application.getInitParameter("contentFolder");
		
		File contents = new File(folder);
		String[] children = contents.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				String mime = application.getMimeType(name);
				return mime != null && mime.startsWith("image/");
			}
		});
		
		Cookie[] cookies = req.getCookies();
		Cookie imageCookie = null;
		if(cookies != null) {
			for(Cookie tmp : cookies) {
				if(tmp.getName().equals("imageCookie")) {
					imageCookie = tmp;
					break;
				}
			}
		}
		if(imageCookie!=null) {
			String decodedJson = URLDecoder.decode(imageCookie.getValue(), "utf-8");
			
			req.setAttribute("imageCookie", decodedJson);
		}
		
		req.setAttribute("children", children);
//		String view = "/WEB-INF/views/imageForm2.jsp";
//		req.getRequestDispatcher(view).forward(req, resp);
		String view = "imageForm2";
		return view;
	}

}
