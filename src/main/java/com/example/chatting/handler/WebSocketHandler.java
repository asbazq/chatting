package com.example.chatting.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler { // 필요에 따라 Text, BinaryWebSocketHandler 상속하여 사용
    // 현재 연결중인 모든 클라이언트
    private List<WebSocketSession> sessionList = new ArrayList<>();
    // WebSocketSession 은 WebSocket 이 연결될 때 생기는 연결정보를 담고있는 객체 

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
        String sender = session.getId();
        String receivedMessage = message.getPayload();

        // 받은 메시지를 다른 연결된 클라이언트에게 브로드캐스트
        for (WebSocketSession client : sessionList) {
            if (client != session) {
                client.sendMessage(new TextMessage(sender + " : " + receivedMessage));
            }
        }
    }

    

    
}
