package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.vo.BuyerVO;

//@WebServlet("/member/memberDelete.do")
@Controller
public class MemberDeleteController{
	IBuyerService service = new BuyerServiceImpl();
	private void addCommandAttribute(HttpServletRequest req) {
		req.setAttribute("command", "update");
	}
	@RequestMapping(value="/member/memberDelete.do", method=RequestMethod.POST)
	public String delete(
			@RequestParam(value="password") String password,
			HttpSession session,
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// session에 담긴 값 가져오기
		BuyerVO authMember = (BuyerVO) session.getAttribute("authMember");
		String authId = authMember.getBuyer_id();
		ServiceResult result = null;
		
		String view = null;
		
		switch (result) {
		case INVALIDPASSWORD:
			// 딜리트 완료. request 새로 하자.
			view = "redirect:/mypage.do";
			session.setAttribute("message", "비번오류");
			break;
		case OK:
			// 딜리트 완료. request 새로 하자.
			session.invalidate();
			view = "redirect:/";
			break;
		default:
			view = "redirect:/mypage.do";
			session.setAttribute("message", "서버오류");
			break;
		}
		
		return view;
//		boolean redirect = view.startsWith("redirect:");
//		if (redirect) {
//			view = view.substring("redirect:".length());
//			resp.sendRedirect(req.getContextPath() + view);
//		} else {
//			req.getRequestDispatcher(view).forward(req, resp);
//		}

	}
	private boolean validate(String authPass, String pass, Map<String, String> errors) {
		boolean valid = true;
		if (!pass.equals(authPass)) {
			valid = false;
			errors.put("mem_pass", "비밀번호 오기입");
		}
		return valid;
	}

}
