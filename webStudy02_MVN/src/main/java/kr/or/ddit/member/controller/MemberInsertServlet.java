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
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberInsert.do")
public class MemberInsertServlet extends HttpServlet {
	private IMemberService service = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/WEB-INF/views/member/memberForm.jsp";
		boolean redirect = false;
		// logic
		if (redirect) {
			// redirect는 클라이언트 사이드의 경로 302로 보내주어야하니
			resp.sendRedirect(req.getContextPath() + view);
		} else {
			req.getRequestDispatcher(view).forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
//		1. 요청 접수
		MemberVO member = new MemberVO();
		req.setAttribute("member", member); //문제 생길까바 미리 집어넣음.
		
		// 규칙성 Mem_id와 변수명 mem_id가 같다. 그럼 reflection을 쓸 수 있다.
//		member.setMem_id(req.getParameter("mem_id"));
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
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
				view = "/WEB-INF/views/member/memberForm02_ajax.jsp";
				message = "아이디 중복";
				break;
			case OK:
				view = "redirect:/login/loginForm.jsp";
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
			resp.sendRedirect(req.getContextPath() + view);
		} else {
			req.getRequestDispatcher(view).forward(req, resp);
		}

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
