package com.purushotham.spring_openai_demo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class MultiModelChatService {
    private final ChatClient openAiChatClient;
    private final ChatClient ollamaAiChatClient;

    public MultiModelChatService(ChatClient openAiChatClient, ChatClient ollamaAiChatClient) {
        this.openAiChatClient = openAiChatClient;
        this.ollamaAiChatClient = ollamaAiChatClient;
    }
    public String chatWithOpenAi(String message){
        return openAiChatClient.prompt(message)
                .call()
                .content();
    }

    public String chatWithOllama(String message){
        return ollamaAiChatClient.prompt(message)
                .call()
                .content();
    }
}
