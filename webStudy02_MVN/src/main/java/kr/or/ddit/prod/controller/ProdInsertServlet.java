package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.util.BeanUtil;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodInsert.do")
public class ProdInsertServlet extends HttpServlet{
	IProdService service = ProdServiceImpl.getInstance();
	private IOthersDAO othersDAO = OthersDAOImpl.getInstance();
	
	private void addAttribute(HttpServletRequest req) {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		List<BuyerVO> buyerList =  othersDAO.selectBuyerList(null);
		
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addAttribute(req);
		
		req.getRequestDispatcher("/WEB-INF/views/prod/prodForm.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		ProdVO prod;
//		createProd(prod)
//		prod.getProd_id();
//		등록 성공시: /prodView.do로 이동.
		req.setCharacterEncoding("utf-8");
		
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);
		
		// 값 가져오기(vo에 담기 !단, name과 vo변수명이 같을 시)
		try {
			BeanUtils.populate(prod, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		// 2. 검증( 데이터의 목적, 경로에 따라 방법이 달라져야 한다.)
		// 누가 통과 못했는지, 검증결과 메시지
		Map<String, String> errors = new LinkedHashMap<String, String>();
		req.setAttribute("errors", errors);
		
		boolean valid = validate(prod, errors);
		String view = null;
		String message = null;
		// 검증 통과 후
		if (valid) {
			// sql문 실행
			ServiceResult result = service.createProd(prod);
			switch (result) {
				case OK:
					view = "redirect:/prod/prodView.do?what="+prod.getProd_id();
					break;
				default:
					message = "서버 오류, 잠시 후 다시 시도해주세요.";
					view = "/WEB-INF/views/prod/prodForm.jsp";
					break;
			}
		} else {
			// 검증 불통
			view = "/WEB-INF/views/prod/prodForm.jsp";
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
	
	private boolean validate(ProdVO prod, Map<String, String> errors) {
		boolean valid = true;
//		todo
//		if (member.getMem_mail() == null || member.getMem_mail().isEmpty()) {
//			valid = false;
//			errors.put("mem_mail", "이메일 누락");
//		}
		return valid;
	}
}
