package kr.or.ddit.alba.controller;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.vo.LicenseVO;

@Controller
public class LicenseImageViewController {
	IAlbaService service = AlbaServiceImpl.getInstance();
	
	@RequestMapping("/alba/licenseImage.do")
	public String licenseImage(@RequestParam("alId") String alId, @RequestParam("licCode") String licCode
			, HttpServletRequest req,  HttpServletResponse resp) throws IOException {
		LicenseVO licAlba = LicenseVO.builder()
									.alId(alId)
									.licCode(licCode)
									.build();
		LicenseVO license = service.retrieveLicense(licAlba);
		resp.setContentType("application/octet-stream");
		byte[] imageData = license.getLicImg();
		try(
			InputStream is = imageData==null ? new FileInputStream(req.getServletContext().getRealPath("/images/noImage.png"))
											 : new ByteArrayInputStream(imageData);
			OutputStream os = resp.getOutputStream();	
		){
			IOUtils.copy(is, os);
		}
		return null;
	}
	
}
