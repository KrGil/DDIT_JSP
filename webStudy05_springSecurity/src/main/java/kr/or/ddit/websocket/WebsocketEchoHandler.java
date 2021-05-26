package kr.or.ddit.websocket;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebsocketEchoHandler extends TextWebSocketHandler{
	
//	@Inject
//	@Named("userList")
	@Resource(name = "userList")
	private List<WebSocketSession> userList;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		userList.add(session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		userList.remove(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 메시지 받기. 실제로 받은 데이터. payload는 짐덩이.
		String data = message.getPayload();
		for(WebSocketSession user : userList) {
			user.sendMessage(new TextMessage(data));
		}
	}
}
