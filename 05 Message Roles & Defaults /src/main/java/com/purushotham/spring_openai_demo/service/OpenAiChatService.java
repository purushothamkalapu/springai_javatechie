package com.purushotham.spring_openai_demo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class OpenAiChatService {
    private final ChatClient chatClient;

    public OpenAiChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }
    public String chatClientOpenAiLLM(String message){
        return chatClient.prompt(message)
                .call()
                .content();
    }
}
