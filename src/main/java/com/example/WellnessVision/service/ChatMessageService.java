package com.example.WellnessVision.service;

import com.example.WellnessVision.model.ChatMessage;
import com.example.WellnessVision.repository.ChatMessageRepository;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
// import org.springframework.boot.configurationprocessor.json.JSONException;
import org.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ChatMessageService {
    @Autowired
    public ChatMessageRepository chatMessageRepository;

    public Optional<List<ChatMessage>> GetChatWithEMAndHPFromDatabase(int hpId) throws JSONException {
        return chatMessageRepository.getAllMessagesForHPAndEM(hpId);
    }

    public void EMSaveChatWithHPInDatabase(int hpId, String chatPayload) throws JSONException {
        JSONArray jsonArray = new JSONArray(chatPayload);
        
        // chatPayload[{"sender":"other","text":"regarding event no 10 ,Can you change the description a bit , there were some spelling mistakes."},{"sender":"You","text":"okay , I corrected them"},{"sender":"other","text":"thank you"}]
        // Iterate through the array and split each object

        JSONObject jsonObject = jsonArray.getJSONObject(0);
        String sender = jsonObject.getString("sender");
        String text = jsonObject.getString("text");

        System.out.print(text);

        ChatMessage chatMessage = new ChatMessage(
                hpId,
                text,
                sender,
                LocalDateTime.now()
        );

        chatMessageRepository.save(chatMessage);


    }
}
