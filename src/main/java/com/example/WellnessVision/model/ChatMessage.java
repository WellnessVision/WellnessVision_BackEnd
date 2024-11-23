package com.example.WellnessVision.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int msg_id;

    public ChatMessage() {
    }

    private int hp_id;
    @Column(length = 1000)
    private String message;
    private String msg_sender;
    private LocalDateTime msg_time;

    public int getMsg_id() {
        return msg_id;
    }

    public int getHp_id() {
        return hp_id;
    }

    public void setHp_id(int hp_id) {
        this.hp_id = hp_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsg_sender() {
        return msg_sender;
    }

    public void setMsg_sender(String msg_sender) {
        this.msg_sender = msg_sender;
    }

    public LocalDateTime getMsg_time() {
        return msg_time;
    }

    public void setMsg_time(LocalDateTime msg_time) {
        this.msg_time = msg_time;
    }

    public ChatMessage(int hp_id, String message, String msg_sender, LocalDateTime msg_time) {
        this.hp_id = hp_id;
        this.message = message;
        this.msg_sender = msg_sender;
        this.msg_time = msg_time;
    }


}