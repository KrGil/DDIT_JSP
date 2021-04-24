package kr.or.ddit.listener;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.Constants;
import kr.or.ddit.vo.MemberVO;

/**
 * Application Lifecycle Listener implementation class CustomServletContextListener
 *
 */
public class CustomServletContextListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(CustomServletContextListener.class);
	private ServletContext application;
	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce)  { 
		logger.info("{} 어플리케이션 초기화 이벤트 처리", getClass().getName());
		application = sce.getServletContext();
		application.setAttribute(Constants.SESSIONCOUNTATTRNAME, 0);
		application.setAttribute(Constants.USERLISTATTRNAME, new LinkedHashSet<MemberVO>());
		application.setAttribute("cPath", application.getContextPath());
	}

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	logger.info("{} 어플리케이션 소멸 이벤트 처리", getClass().getName());
    	application.setAttribute(Constants.SESSIONCOUNTATTRNAME, 0);
    }
}
