package com.azhen.cloud.order.server.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqReceiver {

    // 自动创建队列 @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    // 自动传教，Exchange和Queue绑定
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String messsage) {
        log.info(messsage);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("computer"),
            key = "computer",
            exchange = @Exchange("myOrder")
    ))
    public void processComputer(String messsage) {
        log.info(messsage);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("fruit"),
            key = "fruit",
            exchange = @Exchange("myOrder")
    ))
    public void processFruit(String messsage) {
        log.info(messsage);
    }
}
