package com.azhen.cloud.order.server.controller;

import com.azhen.cloud.order.server.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {
    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void send() {
        streamClient.output().send(MessageBuilder.withPayload(System.currentTimeMillis()).build());
    }
}
