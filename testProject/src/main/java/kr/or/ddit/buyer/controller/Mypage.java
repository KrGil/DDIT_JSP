package kr.or.ddit.buyer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.buyer.service.AuthenticateServiceImpl;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.vo.BuyerVO;

//@WebServlet("/mypage.do")
@Controller
public class Mypage {
	kr.or.ddit.buyer.service.IAuthenticateService authService = new AuthenticateServiceImpl();
	private IBuyerService service = new BuyerServiceImpl();
	
	@RequestMapping("/mypage.do")
	public String form() {
		String view = "member/passwordForm";
		return view;
	}
	@RequestMapping(value="/mypage.do",method=RequestMethod.POST)
	public String process(
			@RequestParam("mem_pass") String mem_pass,
			HttpSession session,
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		BuyerVO authBuyer = (BuyerVO) session.getAttribute("authMember");
		String buyer_id = authBuyer.getBuyer_id();
//		ServiceResult result = authService.authenticate(new BuyerVO(buyer_id, mem_pass));
		ServiceResult result = null;

		String view = null;
		if (ServiceResult.OK.equals(result)) {
			BuyerVO detailMember = service.retrieveBuyer(buyer_id);

			req.setAttribute("member", detailMember);
			view = "member/mypage";
		} else {
			session.setAttribute("message", "비번 오류");
			// get방식으로 다시 여기 거쳐서 passwordform으로.
			view = "redirect:/mypage.do";
		}
		return view;

	}
}
