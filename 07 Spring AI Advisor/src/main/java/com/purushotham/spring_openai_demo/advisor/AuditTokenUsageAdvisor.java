package com.purushotham.spring_openai_demo.advisor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;

public class AuditTokenUsageAdvisor implements CallAdvisor {
    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        Logger logger = LoggerFactory.getLogger(AuditTokenUsageAdvisor.class);
        /*
        * --> Call the next advisor LLM
        * --> Audit token usage here
        * --> extract (i/p token, o/p token , total token)
        * --> Log those details        * */
        //Call the next advisor LLM
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
        ChatResponse chatResponse = chatClientResponse.chatResponse();
        if(chatResponse!=null){
            //Adit token usage here
            Usage usage = chatResponse.getMetadata().getUsage();
            if(usage != null){
                //extract (i/p token, o/p token , total token)
                int inputTokens = usage.getPromptTokens();
                int outputTokens = usage.getCompletionTokens();
                int totalTokens = usage.getTotalTokens();
                //Log those details
                logger.info("Token Usage - Input Tokens: {}, Output Tokens: {}, Total Tokens: {}", inputTokens, outputTokens, totalTokens);
            }
        }

        return chatClientResponse;
    }

    @Override
    public String getName() {
        return "AuditTokenUsageAdvisor";
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
