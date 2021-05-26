package kr.or.ddit.login;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.BadRequestException;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.vo.MemberVO;

@Controller
public class LoginCheckController{
	private static final Logger logger =
			LoggerFactory.getLogger(LoginCheckController.class);
	
	@Inject
	private IAuthenticateService service;
	
	@Inject
	private WebApplicationContext container;
	private ServletContext application;
	
	@PostConstruct
	public void init() {
		application = container.getServletContext();
	}
	
	@RequestMapping(value="/login/loginCheck.do", method=RequestMethod.POST)
	public String doPost(
			@RequestParam(required=true) String mem_id
			, @RequestParam(required=true) String mem_pass
			, @RequestParam(required=false) String saveId
			, HttpServletResponse resp
			, HttpSession session
			, RedirectAttributes redirectAttributes
			){
		if(session.isNew()) {
			throw new BadRequestException("비정상 세션");
		}
		//		요청 분석
		String view = null;
		
		String message = null;
//		인증(id==password)
		MemberVO member = new MemberVO(mem_id, mem_pass);
		if(logger.isDebugEnabled())
			logger.debug("인증전 MemberVO : {}", member);
		ServiceResult result = service.authenticate(member);
		switch (result) {
		case OK:
			if(logger.isInfoEnabled())
				logger.info("인증후 MemberVO : {}", member);
			view = "redirect:/";
			session.setAttribute("authMember", member);
			Cookie idCookie = new Cookie("idCookie", mem_id);
			idCookie.setPath(application.getContextPath());
			int maxAge = 0;
			if("saveId".equals(saveId)) {
				maxAge = 60*60*24*7;
			}
			idCookie.setMaxAge(maxAge);
			resp.addCookie(idCookie);
			break;
		case NOTEXIST:
			view = "redirect:/login/loginForm.jsp";
			message = "아이디 오류";
			break;
		case INVALIDPASSWORD:
			view = "redirect:/login/loginForm.jsp";
//			2) 인증 실패(아이디 상태 유지)
			message = "비번 오류";
			redirectAttributes.addFlashAttribute("failedId", mem_id);
			break;
		}
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return view;
	}
}











