package com.purushotham.spring_openai_demo.service;

import com.purushotham.spring_openai_demo.advisor.AuditTokenUsageAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .advisors(List.of(new SimpleLoggerAdvisor(),
                        new SafeGuardAdvisor(List.of("password", "otp", "cvv"),
                        "For security reason , we never ask such sensitive information please talk to our customer support.", 1),
                        new AuditTokenUsageAdvisor()
                        ))
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
