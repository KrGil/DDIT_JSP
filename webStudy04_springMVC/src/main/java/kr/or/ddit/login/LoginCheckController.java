package kr.or.ddit.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/login/loginCheck.do")
@Controller
public class LoginCheckController {
	private static final Logger logger = LoggerFactory.getLogger(LoginCheckController.class);
		//	LoggerFactory.getLogger("kr.or.ddit.member");
	private IAuthenticateService service = new AuthenticateServiceImpl();
	
	@RequestMapping(value="/login/loginCheck.do", method=RequestMethod.POST)
	public String login(
			@RequestParam("mem_pass") String mem_pass,
			@RequestParam("mem_id") String mem_id,
			HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
		// 로그인 한적도 없는 놈이 여기 접근하려고 한다.
		if(session.isNew()) {
			resp.sendError(400);
			return null;
		}
		// 요청 분석
		String view = null;
		String message = null;
		boolean valid = validate(mem_id, mem_pass);
		String saveId = req.getParameter("saveId");
		
		// 인증을 실패했다면 무조건 redirect로 보내는게 원칙.
		// 왜냐하면 로그인에 실패했다면 본인이 아닐 가능성이 높기때문
		if(valid) {
			//	인증(id == password)
			MemberVO member = new MemberVO(mem_id, mem_pass);
			if(logger.isDebugEnabled()) 
					logger.debug("인증전 MemberVO : {} ", member);
			ServiceResult result = service.authenticate(member);
			switch (result) {
			case OK :
				if(logger.isInfoEnabled()) 
						logger.info("인증후 MemberVO : {} ", member);
				// 인증 성공시 index.jsp 로 이동(현재 요청 정보 삭제). //redirect
				view = "redirect:/";
				session.setAttribute("authMember", member);
				Cookie idCookie = new Cookie("idCookie", mem_id);
				idCookie.setPath(req.getContextPath());
				int maxAge = 0;
				if("saveId".equals(saveId)) {
					maxAge =(60*60*24*7);
				}
				idCookie.setMaxAge(maxAge);
				resp.addCookie(idCookie);
				break;
			case NOTEXIST  :
//				redirect = true;
				//	인증 실패시 loginForm.jsp로 이동  
				view = "redirect:/login/loginForm.jsp";
				//  2) 인증 실패(아이디 상태 유지)
				message = "아이디 오류";
				break;
			case INVALIDPASSWORD:
//				redirect = true;
				//	인증 실패시 loginForm.jsp로 이동
				view = "redirect:/login/loginForm.jsp";
				//  2) 인증 실패(아이디 상태 유지)
				message = "비번 오류";
				session.setAttribute("failedId", mem_id);
				break;
			}
		}else {
			//	1) 검증
//			redirect = true;
			view = "redirect:/login/loginForm.jsp";
			message = "아이디나 비번 누락";
		}
			req.getSession().setAttribute("message", message);
		
////		view로 이동
//		if(redirect) { // redirect는 request를 날려버림 html의 statless의 특성
//			// 그래서 session과 cookies를 사용
//			resp.sendRedirect(req.getContextPath() + view);
//		}else {
//			req.setAttribute("message", message);
//			req.getRequestDispatcher(view).forward(req, resp);
//		}
		
		return view;
	}
	
	// 검증 제대로 값이 넘어왔는가
	private boolean validate(String mem_id, String mem_pass) {
		boolean valid = true;
		valid = !(mem_id ==null || mem_id.isEmpty() || 
				mem_pass== null || mem_pass.isEmpty());
		return valid;
	}
}

	