package com.purushotham.spring_openai_demo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class OrderSupportAISupportAssistntService {
    private final ChatClient chatClient;
    @Value(("classpath:prompts/order_sytem_templet.st"))
    private Resource orderSystemPrompt;
    @Value(("classpath:prompts/order_user_templet.st"))
    private Resource orderUserPrompt;
    @Value(("classpath:prompts/order_system_policy.st"))
    private Resource orderSystemPolicyPrompt;

    public OrderSupportAISupportAssistntService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }
    public String assistWithOrderSupport(String customerName, String orderId, String customerMessage) {
        return chatClient
                .prompt()
                .system(orderSystemPrompt)
                .user(promptUserSpec -> promptUserSpec.text(
                       orderUserPrompt
                )
                        .param("customerName", customerName)
                        .param("orderId", orderId)
                        .param("customerMessage", customerMessage))
                .call()
                .content();

    }
    public String talkToAISupport(String customerName, String orderId, String customerMessage) {
        return chatClient
                .prompt()
                .system(orderSystemPolicyPrompt)
                .user(promptUserSpec -> promptUserSpec.text(
                       orderUserPrompt
                )
                        .param("customerName", customerName)
                        .param("orderId", orderId)
                        .param("customerMessage", customerMessage))
                .call()
                .content();

    }
}
