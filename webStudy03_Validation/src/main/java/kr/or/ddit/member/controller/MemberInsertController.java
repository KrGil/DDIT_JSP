package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.validator.CommonValidator;
import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberInsertController {
	private IMemberService service = new MemberServiceImpl();
	
	@RequestMapping("/member/memberInsert.do")
	public String form()  {
		String view = "member/memberForm";
		return view;
	}

	@RequestMapping(value = "/member/memberInsert.do", method =RequestMethod.POST)
	public String process(
			@ModelAttribute("member") MemberVO member, 
			HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
//		Locale.setDefault(Locale.ENGLISH);
//		1. 요청 접수
		//ModelAttributeArgumentResolver에서 처리하게끔
		
//		2. 검증( 데이터의 목적, 경로에 따라 방법이 달라져야 한다.)
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		boolean valid = new CommonValidator<MemberVO>().validate(member, errors, InsertGroup.class);
		
		//boolean valid = validate(member, errors);
		
		String view = null;
		String message = null;

		if (valid) {
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				view = "member/memberForm02_ajax";
				message = "아이디 중복";
				break;
			case OK:
				view = "redirect:login/loginForm";
				break;
			default:
				message = "서버 오류, 잠시 후 다시 시도해주세요.";
				view = "member/memberForm02_ajax";
				break;
			}
		} else {
			// 검증 불통
			view = "member/memberForm02_ajax";
		}

		req.setAttribute("message", message);

		return view;

	}

}
