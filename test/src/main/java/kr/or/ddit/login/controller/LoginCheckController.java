package kr.or.ddit.login.controller;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.CookieGenerator;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.login.service.IAuthenticateService;
import kr.or.ddit.vo.EmployeeVO;

@Controller
@RequestMapping("/login")
public class LoginCheckController {
	private static final Logger logger = LoggerFactory.getLogger(LoginCheckController.class);
	
	@Inject
	private IAuthenticateService service;
	
	@RequestMapping(value = "/loginCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResult idCheck(
				@ModelAttribute("empVO") EmployeeVO empVO 
				, Errors errors
				, RedirectAttributes redirectAttributes
				, @RequestParam("remember_userId") String remember
				, CookieGenerator cookie
				, HttpServletResponse resp
				
			) {
		// todo 비번 암호화
		ServiceResult result = service.authenticate(empVO);
		
		boolean valid = !errors.hasErrors();
		String view = null;
		if(valid) {
			if(ServiceResult.OK.equals(result)) {
				cookie.addCookie(resp, empVO.getEmployee_id());
				cookie.setCookieMaxAge(60*60*24);
			}else {
				redirectAttributes.addFlashAttribute("message", "비밀번호 오류");
			}
		}else {
			redirectAttributes.addFlashAttribute("message", "필수 데이터 누락");
		}
		return result;
	}
}
