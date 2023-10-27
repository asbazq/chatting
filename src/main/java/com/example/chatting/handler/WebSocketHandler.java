<<<<<<< HEAD
package com.example.chatting.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.chatting.model.ChatMessage;

public class WebSocketHandler extends TextWebSocketHandler {
    // 현재 연결중인 모든 클라이언트
    private List<WebSocketSession> sessionList = new ArrayList<>();

    // webSocket 연결
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        sessionList.add(session); // 접속한 모든 세션 추가

    }

    // webSocket 종료
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        sessionList.remove(session); // 접속 종료 시 세션 제거
    }


    // webSocket 통신 에러
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }


    // 양방향 데이터 통신
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        String sender = session.getHandshakeHeaders().get("name").get(0);
        for (WebSocketSession sess : sessionList) {
            sess.sendMessage(new TextMessage(message.getPayload()));
        }
    }

    

    
}
=======
package com.example.chatting.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.chatting.model.ChatMessage;

public class WebSocketHandler extends TextWebSocketHandler {
    // 현재 연결중인 모든 클라이언트
    private List<WebSocketSession> sessionList = new ArrayList<>();

    // webSocket 연결
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        sessionList.add(session); // 접속한 모든 세션 추가

    }

    // webSocket 종료
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        sessionList.remove(session); // 접속 종료 시 세션 제거
    }


    // webSocket 통신 에러
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }


    // 양방향 데이터 통신
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        String sender = session.getHandshakeHeaders().get("name").get(0);
        for (WebSocketSession sess : sessionList) {
            sess.sendMessage(new TextMessage(message.getPayload()));
        }
    }

    

    
}
>>>>>>> 22f6b2857c80c03ac08a9f108ae2554f19776474
