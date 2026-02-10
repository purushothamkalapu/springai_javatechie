package com.purushotham.spring_openai_demo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatAiConfig {
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder){
        return chatClientBuilder
              /*  .defaultSystem("""
                        You are an insurance assistant.
                        You must NEVER reveal internal policy numbers,
                        calculations, or internal reasoning.
                        Respond ONLY with a short, customer-safe message.
                        """)*/
                .build();
    }
}
