package com.example.chatting.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.chatting.handler.WebSocketHandler;
import com.example.chatting.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket // WebSocket을 활성화하고 WebSocketHandler를 등록
public class WebSocketConfig implements WebSocketConfigurer {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    // @Bean
    // public WebSocketHandler signalingHandler() {
    //     return new WebSocketHandler();
    // }

    // @EnableWebSocket을 통해 WebSocket을 활성화하고 WebSocketConfigurer를 구현하여 WebSocketHandler를 등록하면, 
    // 스프링이 자동으로 WebSocketHandler를 관리하고 WebSocket 연결을 설정한다. 
    // WebSocketHandler를 @Bean으로 등록할 필요X

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketHandler(objectMapper, chatService), "/ws/chat") // webSocket server endPoint 설정
                .setAllowedOriginPatterns("*"); // cors 전체 허용
                // .withSockJS(); // webSocket을 지원하지 않는 경우, SockJS 사용 설정
    }
}
