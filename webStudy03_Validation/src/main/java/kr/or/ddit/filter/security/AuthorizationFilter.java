package kr.or.ddit.filter.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.MemberVO;

/**
 *	이미 인증을 거치고 온 요청에 대해 
 *	현재 보호 자원에 대한 접근 허용 여부를 확인할 필터.
 */
public class AuthorizationFilter implements Filter{
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);
	private ServletContext application;
	private int statusCode = HttpServletResponse.SC_UNAUTHORIZED;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{} 필터 초기화", getClass().getName());
		String scStr =filterConfig.getInitParameter("statusCode");
		if(scStr!=null && StringUtils.isNumeric(scStr)) {
			statusCode = Integer.parseInt(scStr);
		}
		this.application = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Map<String, String[]> securedResources = 
				(Map) application.getAttribute(AuthenticationFilter.RESOURCEMAPNAME);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 1. 보호 자원에 대한 요청인지 식별
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length()).split(";")[0];
		boolean secured = securedResources.containsKey(uri);
		
		boolean pass = true;
		if(secured) {
			String[] resRoles = securedResources.get(uri);
			MemberVO authMember = (MemberVO)req.getSession().getAttribute("authMember");
			String userRole = authMember.getMem_role();
			// userRole이 포함되어 있는지 확인
			if(Arrays.binarySearch(resRoles, userRole) < 0) {
				pass = false;
			}
		}
		
		if(pass) {
			chain.doFilter(request, response);
		}else {
			// 권한이 없는 상태에서 요청을 함
			resp.sendError(statusCode,
								String.format("%s 자원에 대한 권한이 없음.", uri)); // 403
		}
	}

	@Override
	public void destroy() {
		logger.info("{} 필터 소멸", getClass().getName());
	}

}
