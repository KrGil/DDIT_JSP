package kr.or.ddit.servlet02;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.exception.BadRequestException;

@Controller
public class Model2ImageFormServlet implements ApplicationContextAware{
	private ServletContext application;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.application = 
				((WebApplicationContext) applicationContext).getServletContext();
	}
	
	@Value("#{appInfo.contentFolder}")
	private File contents;
	
	@RequestMapping(value="/02/imageForm.do", method=RequestMethod.POST)
	public String upload(
			@RequestPart("uploadImage") MultipartFile image
			) throws IOException {
		if(!image.isEmpty()) {
			String contentType = application.getMimeType(image.getOriginalFilename());
			if(contentType==null || 
					!contentType.startsWith("image/")) {
				throw new BadRequestException("이미지만 업로드하라고!");
			}
			image.transferTo(new File(contents, image.getOriginalFilename()));
		}
//		P-R-G pattern
		return "redirect:/02/imageForm.do";
	}
	
	@RequestMapping("/02/imageForm.do")
	public String doGet(
			@CookieValue(name="imageCookie", required = false) String imageCookieValue
			, Model model
			, HttpServletResponse resp) throws ServletException, IOException {
		
		String[] children = contents.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				String mime = application.getMimeType(name);
				return mime!=null && mime.startsWith("image/");
			}
		});
		
		if(StringUtils.isNotBlank(imageCookieValue)) {
			String decodedJson = URLDecoder.decode(imageCookieValue, "UTF-8");
			model.addAttribute("imageCookie", decodedJson);
		}
		
		
		model.addAttribute("children", children);
		String view = "imageForm";
		return view;
	}
}












