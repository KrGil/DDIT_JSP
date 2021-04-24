package kr.or.ddit.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class filteringTokensFilter implements Filter {
	private String[] filteringTokens = new String[] {"말미잘", "해삼"};
	private char maskingCh = '*';
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			Enumeration<String> requestNames = req.getParameterNames();
			while(requestNames.hasMoreElements()) {
				String name = requestNames.nextElement();
				String[] requestValue = req.getParameterValues(name);
				
				String regex = String.format("(%s)", String.join("|", filteringTokens));
				Pattern pattern = Pattern.compile(regex);
				for(String value : requestValue) {
					Matcher matcher = pattern.matcher(value);
					StringBuffer result = new StringBuffer();
					while(matcher.find()) {
						// 그룹안에 있는 글자 가지고오기.
						//			matcher.group(1).replaceAll(".", maskingCh+"");
						String replace = 
								matcher.group(1).replaceAll(".", new Character(maskingCh).toString());
						matcher.appendReplacement(result, replace);
					}
					matcher.appendTail(result);
				}
				
				
			}
	//		"말미잘", "해삼", "멍게"
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
