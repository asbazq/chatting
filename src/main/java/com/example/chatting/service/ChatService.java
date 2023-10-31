package com.example.chatting.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.example.chatting.dto.ChatRoomDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private final ObjectMapper objectMapper;
    // 채팅방 Map은 서버에 생성된 모든 채팅방의 정보를 모아둔 구조체
    // 채팅룸의 정보 저장은 빠른구현을 위해 일단 외부 저장소를 이용하지 않고 HashMap에 저장하는 것으로 구현
    private Map<String, ChatRoomDto> chatRooms;

    // 의존성 주입이 이루어진 후 초기화를 수행
    // 해당 어노테이션은 다른 리소스에서 호출되지 않아도 수행
    // 생성자 보다 늦게 호출
    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoomDto> findAllRooms() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoomDto findRoomId(String roomId) {
        return chatRooms.get(roomId);
    }

    public ChatRoomDto createRoom(String name) {
        String randomId = UUID.randomUUID().toString();
        ChatRoomDto chatRoomDto = ChatRoomDto.builder()
                .roomId(randomId)
                .name(name)
                .build();
        chatRooms.put(randomId, chatRoomDto);
        return chatRoomDto;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
