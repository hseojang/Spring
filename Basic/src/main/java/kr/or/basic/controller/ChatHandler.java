package kr.or.basic.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;


// 웹소켓은 웹소켓 프로토콜을 이용한다
@Component
@Slf4j
public class ChatHandler extends TextWebSocketHandler implements WebSocketConfigurer {
	private static List<WebSocketSession> list = new ArrayList<WebSocketSession>();
	
	// 웹소켓은 웹소켓 세션이라는 별도의 세션을 이용함
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("## 누군가 접속");
		list.add(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String uMsg = message.getPayload();
		for (WebSocketSession webSocketSession : list) {
			webSocketSession.sendMessage(new TextMessage(session.getAcceptedProtocol() + uMsg));
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("## 누군가 떠남");
		list.remove(session);
	}
	
	// WebSocketConfigurer로 인해 설정해야 함 
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(this, "/ws-chat").setAllowedOrigins("*");
		// 핸들러 등록해주기
	}

}