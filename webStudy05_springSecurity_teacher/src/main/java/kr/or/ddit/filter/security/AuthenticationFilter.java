package kr.or.ddit.filter.security;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  접근 제어를 통한 자원의 보호
 *  1. 인증(authentication) : 본인 여부를 확인하는 신원 확인 절차
 *  2. 인가(authorization) : 보호 자원에 접근할 수 있는 역할이 부여되어있는지 여부 확인하는 절차(권한 확인)
 *
 */
public class AuthenticationFilter implements Filter{
	private static final Logger logger =
			LoggerFactory.getLogger(AuthenticationFilter.class);

	public static final String RESOUCEMAPNAME = "securedResources";
	private Map<String, String[]> securedResources;
	private String loginPage;
	
	@Override
	public void init(FilterConfig filterConfig) 
			throws ServletException {
		logger.info("{} 필터 초기화", getClass().getName());
		// securedResources.properties 을 읽고, 로딩.
		securedResources = new LinkedHashMap<>();
		loginPage = filterConfig.getInitParameter("loginPage");
		filterConfig.getServletContext()
					.setAttribute(RESOUCEMAPNAME, securedResources);
		
		Properties properties = new Properties();
		String resPath = filterConfig.getInitParameter("resourcePath");
		try(
			InputStream is 
				=AuthenticationFilter.class.getResourceAsStream(resPath);
				
		){
//			properties.loadFromXML(is);
			properties.load(is);
			for(Entry<Object, Object> entry : properties.entrySet()) {
 				Object key = entry.getKey();
  				Object value = entry.getValue();
				String url = key.toString().trim();
 				String[] roles = value.toString().trim().split(",");
 				Arrays.sort(roles);
 				securedResources.put(url, roles);
				logger.info("{} : {}", url, roles);
			}
		}catch (IOException e) {
			throw new ServletException(e);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = 
				(HttpServletRequest) request;
		HttpServletResponse resp = 
				(HttpServletResponse) response;
		// 1. 보호 자원에 대한 요청인지 식별
		String uri = req.getRequestURI();
 		uri = uri.substring(req.getContextPath().length())
 								.split(";")[0];
 		boolean secured = securedResources.containsKey(uri);
		// 2. 보호 필요 자원
 		boolean pass = true;
		// 3. 인증 여부 확인
 		if(secured) {
  			Object authMember = 
  					req.getSession().getAttribute("authMember");
  			if(authMember==null) {
  				// 미인증시 loginform 으로 이동
  				pass = false;
  			}
 		}
//		2-1. 보호할 필요 없는 자원 : 통과
 		if(pass) {
 			chain.doFilter(request, response);
 		}else {
 			resp.sendRedirect(req.getContextPath() + loginPage);
 		}
		 
	}

	@Override
	public void destroy() {
		logger.info("{} 필터 소멸", getClass().getName());
	}

}










