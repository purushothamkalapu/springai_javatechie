package com.purushotham.spring_openai_demo.controller;

import com.purushotham.spring_openai_demo.service.MultiModelChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multi-model/api")
public class MultiModelChatController {
    @Autowired
    private MultiModelChatService multiModelChatService;
    @GetMapping("/chat/openai")
    public String chatWithOpenAI(@RequestParam("message") String message){
        return multiModelChatService.chatWithOpenAi(message);
    }
    @GetMapping("/chat/ollama")
    public String chatWithOllamaAI(@RequestParam("message") String message){
        return multiModelChatService.chatWithOllama(message);
    }
    @GetMapping("/chat/googlegenai")
    public String chatWithGoogleGenAI(@RequestParam("message") String message){
        return multiModelChatService.chatWithGoogleGenAi(message);
    }
}
