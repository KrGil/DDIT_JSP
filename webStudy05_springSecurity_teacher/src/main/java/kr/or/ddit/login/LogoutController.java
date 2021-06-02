package kr.or.ddit.login;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.exception.BadRequestException;

@Controller
public class LogoutController{
	@RequestMapping(value="/login/logout.do", method=RequestMethod.POST)
	public String logout(HttpSession session){
		if(session.isNew()) {
			throw new BadRequestException("비정상 세션에서 로그아웃하려함.");
		}
		
		session.invalidate();
		
		return "redirect:/";
	}
}












