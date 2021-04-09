package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
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
		
//		1. 요청 접수
		//ModelAttributeArgumentResolver에서 처리하게끔
		
//		2. 검증( 데이터의 목적, 경로에 따라 방법이 달라져야 한다.)
		Map<String, String> errors = new LinkedHashMap<String, String>();
		req.setAttribute("errors", errors);

		boolean valid = validate(member, errors);
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
			view = "views/member/memberForm02_ajax";
		}

		req.setAttribute("message", message);

		return view;

	}

	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		if (member.getMem_id() == null || member.getMem_id().isEmpty()) {
			valid = false;
			errors.put("mem_id", "회원이름 누락");
		}
		if (member.getMem_pass() == null || member.getMem_pass().isEmpty()) {
			valid = false;
			errors.put("mem_pass", "비밀번호 누락");
		}
		if (member.getMem_name() == null || member.getMem_name().isEmpty()) {
			valid = false;
			errors.put("mem_name", "이름 누락");
		}
		if (member.getMem_zip() == null || member.getMem_zip().isEmpty()) {
			valid = false;
			errors.put("mem_zip", "우편번호 누락");
		}
		if (member.getMem_add1() == null || member.getMem_add1().isEmpty()) {
			valid = false;
			errors.put("mem_add1", "주소1 누락");
		}
		if (member.getMem_add2() == null || member.getMem_add2().isEmpty()) {
			valid = false;
			errors.put("mem_add2", "주소2 누락");
		}
		if (member.getMem_mail() == null || member.getMem_mail().isEmpty()) {
			valid = false;
			errors.put("mem_mail", "이메일 누락");
		}
		return valid;
	}
}
