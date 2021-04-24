package kr.or.ddit.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.Constants;
import kr.or.ddit.vo.MemberVO;

/**
 * Application Lifecycle Listener implementation class CustomHttpSessionAttributeListener
 *
 */
//@WebListener
public class CustomHttpSessionAttributeListener implements HttpSessionAttributeListener {
	private static final Logger logger = LoggerFactory.getLogger(CustomHttpSessionAttributeListener.class);
	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event)  {
    	// authMember가 맞는지 확인하기. session은 누구나 저장할 수 잇기 때문.
    	if("authMember".equals(event.getName())){
    		MemberVO authMember = (MemberVO) event.getValue();
    		// application 꺼내기
    		ServletContext application = event.getSession().getServletContext();
    		Set<MemberVO> userList= (Set) application.getAttribute(Constants.USERLISTATTRNAME);
    		userList.add(authMember);
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event)  { 
    	// authMember가 맞는지 확인하기. session은 누구나 저장할 수 잇기 때문.
    	if("authMember".equals(event.getName())){
    		MemberVO authMember = (MemberVO) event.getValue();
    		// application 꺼내기
    		ServletContext application = event.getSession().getServletContext();
    		Set<MemberVO> userList= (Set) application.getAttribute(Constants.USERLISTATTRNAME);
    		userList.remove(authMember);
    	}  
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }
	
}
