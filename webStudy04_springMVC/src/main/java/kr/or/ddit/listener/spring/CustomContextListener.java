package kr.or.ddit.listener.spring;

import java.util.LinkedHashSet;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.Constants;
import kr.or.ddit.vo.MemberVO;

@Component
public class CustomContextListener {
	private static final Logger logger = LoggerFactory.getLogger(CustomContextListener.class);
	
	@EventListener(classes = ContextRefreshedEvent.class)
	public void init(ContextRefreshedEvent event) {
		WebApplicationContext root = 
				(WebApplicationContext) event.getApplicationContext();
		ServletContext application = root.getServletContext();
		application.setAttribute("cPath", application.getContextPath());
		application.setAttribute(Constants.SESSIONCOUNTATTRNAME, 0);
		application.setAttribute(Constants.USERLISTATTRNAME, new LinkedHashSet<MemberVO>());
		logger.info("컨텍스트 시작");
	}
}
