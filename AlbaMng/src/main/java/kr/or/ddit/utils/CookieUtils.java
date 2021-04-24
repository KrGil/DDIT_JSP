package kr.or.ddit.utils;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 쿠키 생성과 쿠키 조회를 지원하는 유틸리티
 *
 */
public class CookieUtils {
	private HttpServletRequest request;
	private Map<String, Cookie> cookieMap;
	public CookieUtils(HttpServletRequest request) {
		this.request = request;
		cookieMap = new HashMap<>();
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for(Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
	}

	/**
	 * 전체 쿠키를 가진 맵 조회
	 * @return
	 */
	public Map<String, Cookie> getCookieMap() {
		return cookieMap;
	}
	
	/**
	 * 쿠키의 이름만 조회
	 * @return
	 */
	public Enumeration<String> getCookieNames() {
		final Iterator<String> keyIt = cookieMap.keySet().iterator();
		return new Enumeration<String>() {
			@Override
			public boolean hasMoreElements() {
				return keyIt.hasNext();
			}
			
			@Override
			public String nextElement() {
				return keyIt.next();
			}
		};
	}

	/**
	 * @param name
	 * @return
	 */
	public boolean containsCookie(String name) {
		return cookieMap.containsKey(name);
	}
	
	/**
	 * 쿠키 객체 조회
	 * @param name
	 * @return
	 */
	public Cookie getCookie(String name) {
		return cookieMap.get(name);
	}
	
	/**
	 * UTF-8로 디코딩된 쿠키 값 조회
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public String getCookieValue(String name) throws IOException {
		String value = null;
		if(cookieMap.containsKey(name)) {
			value = URLDecoder.decode(cookieMap.get(name).getValue(), "UTF-8");
		}
		return value;
	}

	/**
	 * @param name
	 * @param value URL encoding 방식으로 인코딩됨.
	 * @return
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value) throws IOException {
		return createCookie(name, value, null, null, -1, false, false);
	}
	/**
	 * @param name
	 * @param value
	 * @param maxAge 초단위 만료 시간
	 * @return
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value, int maxAge) throws IOException {
		Cookie cookie = createCookie(name, value);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	public static enum TextType{ PATH, DOMAIN }
	
	/**
	 * @param name
	 * @param value
	 * @param text 경로나 도메인으로 사용할 텍스트
	 * @param flag text 파라미터의 사용 속성을 결정할 타입
	 * @return
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value, 
			String text, TextType flag) throws IOException {
		if(TextType.PATH.equals(flag)) {
			return createCookie(name, value, text, null, -1, false, false);
		}else if(TextType.DOMAIN.equals(flag)) {
			return createCookie(name, value, null, text, -1, false, false);
		}else {
			throw new NullPointerException("TextType flag 는 null 일수없음.");
		}
	}
	
	/**
	 * @param name
	 * @param value
	 * @param text 경로나 도메인으로 사용할 텍스트
	 * @param flag text 파라미터의 사용 속성을 결정할 타입
	 * @param maxAge
	 * @return
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value, 
			String text, TextType flag, int maxAge) throws IOException {
		Cookie cookie = createCookie(name, value, text, flag);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	/**
	 * @param name
	 * @param value
	 * @param path 
	 * @param maxAge
	 * @return
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value, 
			String path, int maxAge) throws IOException {
		return createCookie(name, value, path, null, maxAge, false, false);
	}
	
	/**
	 * @param name
	 * @param value
	 * @param path
	 * @param domain
	 * @return
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value, 
			String path, String domain) throws IOException {
		return createCookie(name, value, path, domain, -1, false, false);
	}
	
	/**
	 * @param name
	 * @param value
	 * @param path
	 * @param domain
	 * @param maxAge
	 * @return
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value, 
			String path, String domain, int maxAge) throws IOException {
		return createCookie(name, value, path, domain, maxAge, false, false);
	}
	
	/**
	 * @param name
	 * @param value
	 * @param path
	 * @param domain
	 * @param maxAge
	 * @param httpOnly
	 * @param secure
	 * @return
	 * @throws IOException
	 */
	private static Cookie createCookie(String name, String value, 
			String path, String domain, int maxAge, boolean httpOnly, boolean secure)
	throws IOException
    {
		value = URLEncoder.encode(value, "UTF-8");
		Cookie cookie = new Cookie(name, value);
		if(path!=null && !path.isEmpty()) cookie.setPath(path);
		if(domain!=null && !domain.isEmpty()) cookie.setDomain(domain);
		if(maxAge!=-1) cookie.setMaxAge(maxAge);
		if(httpOnly) cookie.setHttpOnly(httpOnly);
		if(secure) cookie.setSecure(secure);
		return cookie;
	}
}












