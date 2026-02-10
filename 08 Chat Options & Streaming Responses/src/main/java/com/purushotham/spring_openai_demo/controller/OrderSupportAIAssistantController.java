package com.purushotham.spring_openai_demo.controller;

import com.purushotham.spring_openai_demo.service.OrderSupportAISupportAssistntService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/support")
public class OrderSupportAIAssistantController {
    private OrderSupportAISupportAssistntService orderSupportAISupportAssistntService;

    public OrderSupportAIAssistantController(OrderSupportAISupportAssistntService orderSupportAISupportAssistntService) {
        this.orderSupportAISupportAssistntService = orderSupportAISupportAssistntService;
    }
    @GetMapping("/order-support")
    public String getOrderSupportResponse(@RequestParam String customerName, @RequestParam String orderId, @RequestParam String customerMessage){
        return orderSupportAISupportAssistntService.assistWithOrderSupport(customerName, orderId, customerMessage);
    }
    @GetMapping("/order-ai-support")
    public String talkToAIOrderSupportResponse(@RequestParam String customerName, @RequestParam String orderId, @RequestParam String customerMessage){
        return orderSupportAISupportAssistntService.talkToAISupport(customerName, orderId, customerMessage);
    }
}
