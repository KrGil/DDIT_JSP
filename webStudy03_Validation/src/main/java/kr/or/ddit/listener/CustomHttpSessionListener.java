package kr.or.ddit.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.Constants;

@WebListener
public class CustomHttpSessionListener implements HttpSessionListener{
	private static final Logger logger = LoggerFactory.getLogger(CustomHttpSessionListener.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		// session Id 가져오기
		logger.info("세션 생성 : {}", session.getId());
		ServletContext application = session.getServletContext();
		int sessionCount = (Integer) application.getAttribute("sessionCount");
		application.setAttribute(Constants.SESSIONCOUNTATTRNAME, sessionCount+1);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		logger.info("세션 소멸: {}", session.getId());
		ServletContext application = session.getServletContext();
		int sessionCount = (Integer) application.getAttribute("sessionCount");
		application.setAttribute(Constants.SESSIONCOUNTATTRNAME, sessionCount);
	}
}
