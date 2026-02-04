package com.purushotham.spring_openai_demo.controller;

import com.purushotham.spring_openai_demo.service.OpenAiChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/openai/api")
public class OpenAiChatController {

    private final OpenAiChatService openAiChatService;

    public OpenAiChatController(OpenAiChatService openAiChatService) {
        this.openAiChatService = openAiChatService;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message){
        return openAiChatService.chatClientOpenAiLLM(message);
    }
}
