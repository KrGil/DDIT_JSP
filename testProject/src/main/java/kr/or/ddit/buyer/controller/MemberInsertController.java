package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.vo.BuyerVO;

@Controller
public class MemberInsertController {
	private IBuyerService service = new BuyerServiceImpl();
	
	@RequestMapping("/member/memberInsert.do")
	public String form()  {
		String view = "member/memberForm";
		return view;
	}

	@RequestMapping(value = "/member/memberInsert.do", method =RequestMethod.POST)
	public String process(
			@ModelAttribute("buyer") BuyerVO buyer,
			@RequestPart(value="mem_image", required=false) MultipartFile mem_image,
			HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
//		Locale.setDefault(Locale.ENGLISH);
//		1. 요청 접수
		//ModelAttributeArgumentResolver에서 처리하게끔
		
		// member에 image를 바이트배열로 바꾼걸 세팅하기
		if(mem_image!=null && !mem_image.isEmpty()) {
			String mime = mem_image.getContentType();
			if(!mime.startsWith("image/")) {
				throw new BadRequestException("이미지 이외의 프로필은 처리 불가.");
			}
			byte[] mem_img = mem_image.getBytes();
//			buyer.setBuyer_img(mem_img);
		}
		
//		2. 검증( 데이터의 목적, 경로에 따라 방법이 달라져야 한다.)
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		boolean valid = new CommonValidator<BuyerVO>().validate(buyer, errors, InsertGroup.class);
		//boolean valid = validate(member, errors);
		
		String view = null;
		String message = null;

		if (valid) {
			ServiceResult result = service.createBuyer(buyer);
			switch (result) {
			case PKDUPLICATED:
				view = "member/memberForm";
				message = "아이디 중복";
				break;
			case OK:
				view = "redirect:/login/loginForm.jsp";
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
