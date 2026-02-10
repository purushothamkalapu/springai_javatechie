package com.purushotham.spring_openai_demo.controller;

import com.purushotham.spring_openai_demo.service.MessageRolesDemoService;
import com.purushotham.spring_openai_demo.service.OpenAiChatService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OpenAiChatController {

    private final OpenAiChatService openAiChatService;
    private final MessageRolesDemoService messageRolesDemoService;

    public OpenAiChatController(OpenAiChatService openAiChatService, MessageRolesDemoService messageRolesDemoService) {
        this.openAiChatService = openAiChatService;
        this.messageRolesDemoService = messageRolesDemoService;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message){
        return openAiChatService.chatClientOpenAiLLM(message);
    }
    @GetMapping("/check/policy")
    public ChatResponse checkInsurancePolicy(@RequestParam String message){
        return messageRolesDemoService.checkPolicyInsuranceV3Policy(message);
    }
}
