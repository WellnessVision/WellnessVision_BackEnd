package com.example.WellnessVision.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ChatBoxVolunteerHealthProfessional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;
    private int eventId;
    private int volunteerId;
    private String sender;
    private String readingState;
    private String messageFlag;
    @Column(length = 1000)
    private String message;
    private String attachmentFlag;
    private String attachment;
    private String replyFlag;
    @Column(length = 1000)
    private String replyMessage;
    private String replyMessageAttachmentFlag;
    private LocalDateTime messageTime;
    private String editFlag;

    public ChatBoxVolunteerHealthProfessional() {
    }

    public ChatBoxVolunteerHealthProfessional(int eventId, int volunteerId, String sender, String readingState, String messageFlag, String message, String attachmentFlag, String attachment, String replyFlag, String replyMessage, String replyMessageAttachmentFlag, LocalDateTime messageTime, String editFlag) {
        this.eventId = eventId;
        this.volunteerId = volunteerId;
        this.sender = sender;
        this.readingState = readingState;
        this.messageFlag = messageFlag;
        this.message = message;
        this.attachmentFlag = attachmentFlag;
        this.attachment = attachment;
        this.replyFlag = replyFlag;
        this.replyMessage = replyMessage;
        this.replyMessageAttachmentFlag = replyMessageAttachmentFlag;
        this.messageTime = messageTime;
        this.editFlag = editFlag;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(int volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReadingState() {
        return readingState;
    }

    public void setReadingState(String readingState) {
        this.readingState = readingState;
    }

    public String getMessageFlag() {
        return messageFlag;
    }

    public void setMessageFlag(String messageFlag) {
        this.messageFlag = messageFlag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAttachmentFlag() {
        return attachmentFlag;
    }

    public void setAttachmentFlag(String attachmentFlag) {
        this.attachmentFlag = attachmentFlag;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getReplyFlag() {
        return replyFlag;
    }

    public void setReplyFlag(String replyFlag) {
        this.replyFlag = replyFlag;
    }

    public String getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }

    public String getReplyMessageAttachmentFlag() {
        return replyMessageAttachmentFlag;
    }

    public void setReplyMessageAttachmentFlag(String replyMessageAttachmentFlag) {
        this.replyMessageAttachmentFlag = replyMessageAttachmentFlag;
    }

    public LocalDateTime getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(LocalDateTime messageTime) {
        this.messageTime = messageTime;
    }

    public String getEditFlag() {
        return editFlag;
    }

    public void setEditFlag(String editFlag) {
        this.editFlag = editFlag;
    }
}
