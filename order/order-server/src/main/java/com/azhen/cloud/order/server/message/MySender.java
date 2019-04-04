package com.azhen.cloud.order.server.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MySender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void senderOrder() {
        amqpTemplate.convertAndSend("myOrder", "computer", System.currentTimeMillis());

        amqpTemplate.convertAndSend("myOrder", "fruit", System.currentTimeMillis());
    }
}
