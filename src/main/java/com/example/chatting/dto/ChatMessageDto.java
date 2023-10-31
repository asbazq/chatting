package com.example.chatting.dto;

import lombok.Getter;

@Getter
public class ChatMessageDto {
    public enum MessageType {
        ENTER, TALK
    }
   
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }
}

