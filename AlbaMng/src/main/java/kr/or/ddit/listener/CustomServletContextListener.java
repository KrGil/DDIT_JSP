package kr.or.ddit.listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.alba.service.AlbaServiceImpl;

public class CustomServletContextListener implements ServletContextListener{
	
	private static final Logger logger = LoggerFactory.getLogger(CustomServletContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		application.setAttribute("cPath", application.getContextPath());
		
		String realPath = sce.getServletContext().getRealPath("/profiles");
		File profileFolder = new File(realPath);
		if(!profileFolder.exists()) {
			profileFolder.mkdirs();
		}
		
		AlbaServiceImpl service = AlbaServiceImpl.getInstance();
		service.setProfileFolder(profileFolder);
		
		
		logger.info("{} 가 초기화", getClass().getSimpleName());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}
















