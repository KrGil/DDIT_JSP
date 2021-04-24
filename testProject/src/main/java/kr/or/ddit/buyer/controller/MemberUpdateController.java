package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.BadRequestException;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.validator.CommonValidator;
import kr.or.ddit.validator.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;

//@WebServlet("/member/memberUpdate.do")
@Controller
public class MemberUpdateController {
	IBuyerService service = new BuyerServiceImpl();
	private void addCommandAttribute(HttpServletRequest req) {
		req.setAttribute("command", "update");
	}
	@RequestMapping("/member/memberUpdate.do")
	public String doGet(
				@ModelAttribute("buyer") BuyerVO buyer,
				
				HttpSession session,
				HttpServletRequest req, HttpServletResponse resp){
		addCommandAttribute(req);
		
		// logincheckServlet  에서....
		BuyerVO authBuyer = (BuyerVO) session.getAttribute("authBuyer");
		
		String authId = authBuyer.getBuyer_id();
		buyer = service.retrieveBuyer(authId);
		
		String view = "member/memberForm";
		// 자기자신의 정보가 필요하다
		req.setAttribute("member", buyer);
		
		// memberForm.jsp 재활용 수정으로 사용
//		req.getRequestDispatcher(view).forward(req, resp);
		return view;
	}
	
	@RequestMapping(value="/member/memberUpdate.do", method=RequestMethod.POST)
	public String doPost(
				@ModelAttribute("buyer") BuyerVO buyer,
				@RequestPart(value="mem_image", required=false) MultipartFile mem_image,
				HttpSession session,
				HttpServletRequest req, HttpServletResponse resp) throws IOException {
		addCommandAttribute(req);
		
//		req.setCharacterEncoding("utf-8");
//		1. 요청 접수
		req.setAttribute("buyer", buyer); //문제 생길까바 미리 집어넣음.
		
		// logincheckServlet  에서....
		BuyerVO authMember = (BuyerVO) session.getAttribute("authMember");
		// setid ?????
		String authId = authMember.getBuyer_id();
		buyer.setBuyer_id(authId);

		if(mem_image!=null && !mem_image.isEmpty()) {
			String mime = mem_image.getContentType();
			if(!mime.startsWith("image/")) {
				throw new BadRequestException("이미지 이외의 프로필은 처리 불가.");
			}
			byte[] mem_img = mem_image.getBytes();
//			buyer.setBuyer_img(mem_img);
		}
		// 규칙성 Mem_id와 변수명 mem_id가 같다. 그럼 reflection을 쓸 수 있다.
//		member.setMem_id(req.getParameter("mem_id"));
		
//		2. 검증( 데이터의 목적, 경로에 따라 방법이 달라져야 한다.)
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		boolean valid = new CommonValidator<BuyerVO>().validate(buyer, errors, UpdateGroup.class);
		
		String view = null;
		String message = null;

		if (valid) {
			ServiceResult result = service.modifyBuyer(buyer);
			switch (result) {
			case INVALIDPASSWORD:
				view = "member/memberForm";
				message = "비번 오류";
				break;
			case OK:
				// 업데이트 완료. request 새로 하자.
				view = "redirect:/mypage.do";
				break;
			default:
				message = "서버 오류, 잠시 후 다시 시도해주세요.";
				view = "member/memberForm";
				break;
			}
		} else {
			// 검증 불통
			view = "member/memberForm";
		}

		req.setAttribute("message", message);
		return view;
	}
}
