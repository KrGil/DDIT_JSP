package kr.or.ddit.listener;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import kr.or.ddit.Constants;
import kr.or.ddit.vo.MemberVO;

/**
 * Application Lifecycle Listener implementation class CustomHttpSessionAttributeListener
 *
 */
//@WebListener
public class CustomHttpSessionAttributeListener implements HttpSessionAttributeListener {

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event)  { 
        if("authMember".equals(event.getName())) {
        	MemberVO authMember = (MemberVO)event.getValue();
        	ServletContext application = event.getSession().getServletContext();
        	Set<MemberVO> userList = 
        			(Set) application.getAttribute(Constants.USERLISTATTRNAME);
        	userList.add(authMember);
        }
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event)  { 
    	if("authMember".equals(event.getName())) {
        	MemberVO authMember = (MemberVO)event.getValue();
        	ServletContext application = event.getSession().getServletContext();
        	Set<MemberVO> userList = 
        			(Set) application.getAttribute(Constants.USERLISTATTRNAME);
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
