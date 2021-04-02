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
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/memberDelete.do")
public class MemberDeleteServlet_me extends HttpServlet{
	IMemberService service = new MemberServiceImpl();
	private void addCommandAttribute(HttpServletRequest req) {
		req.setAttribute("command", "update");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addCommandAttribute(req);
		HttpSession session = req.getSession();
		req.setCharacterEncoding("utf-8");
		
		// session에 담긴 값 가져오기
		MemberVO member = (MemberVO) session.getAttribute("authMember");
		String authPass = member.getMem_pass();
		
		// param Pass
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
//		2. 검증( 데이터의 목적, 경로에 따라 방법이 달라져야 한다.)
		Map<String, String> errors = new LinkedHashMap<String, String>();
		req.setAttribute("errors", errors);
		
		boolean valid = validate(authPass, member.getMem_pass(), errors);
		String view = null;
		String message = null;

		if (valid) {
			ServiceResult result = service.removeMember(member);
			switch (result) {
			case OK:
				// 딜리트 완료. request 새로 하자.
				view = "redirect:/mypage.do";
				break;
			default:
				message = "서버 오류, 잠시 후 다시 시도해주세요.";
				view = "/WEB-INF/views/member/memberForm02_ajax.jsp";
				break;
			}
		} else {
			// 검증 불통
			view = "/WEB-INF/views/member/memberForm02_ajax.jsp";
		}

		req.setAttribute("message", message);

		boolean redirect = view.startsWith("redirect:");
		if (redirect) {
			view = view.substring("redirect:".length());
			resp.sendRedirect(view);
		} else {

		}

	}
	private boolean validate(String authPass, String pass, Map<String, String> errors) {
		boolean valid = true;
		if (pass == null || pass.isEmpty()) {
			valid = false;
			errors.put("mem_pass", "비밀번호 누락");
		}
		if (!pass.equals(authPass)) {
			valid = false;
			errors.put("mem_pass", "비밀번호 오기입");
		}
		
		return valid;
	}

}
