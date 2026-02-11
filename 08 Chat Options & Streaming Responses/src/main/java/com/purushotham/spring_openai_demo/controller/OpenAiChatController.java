package com.purushotham.spring_openai_demo.controller;

import com.purushotham.spring_openai_demo.service.MessageRolesDemoService;
import com.purushotham.spring_openai_demo.service.OpenAiChatService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.awt.*;

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
        return openAiChatService.askToAI(message);
    }
    @GetMapping(value = "/chatStream", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Flux<String> chatStream(@RequestParam String message){
        return openAiChatService.askToAIStream(message);
    }
    @GetMapping("/check/policy")
    public ChatResponse checkInsurancePolicy(@RequestParam String message){
        return messageRolesDemoService.checkPolicyInsuranceV3Policy(message);
    }
    @GetMapping("/guide")
    public String checkTechStack(@RequestParam String message){
        return messageRolesDemoService.guideMeTechStack(message);
    }
    @GetMapping("/guideWithPrompt")
    public String checkTechStackWithPrompt(@RequestParam String topic,
                                           @RequestParam String level,
                                           @RequestParam int points){
        return messageRolesDemoService.guideMeTechStackUsingPrompt(topic, level, points);
    }
}
