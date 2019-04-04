package com.azhen.cloud.order.server.message;

import com.azhen.cloud.order.server.utils.JsonUtil;
import com.azhen.cloud.product.common.MessageQueue;
import com.azhen.cloud.product.common.ProductInfoOutput;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProductInfoReceiver {
    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue(MessageQueue.PRODUCT_INFO))
    public void process(String message) {
        List<ProductInfoOutput> productInfoOutputList = (List<ProductInfoOutput>) JsonUtil.fromJson(message,
                new TypeReference<List<ProductInfoOutput>>(){});

        for (ProductInfoOutput output : productInfoOutputList) {
            // 保存到Redis里面
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, output.getProductId()),
                    String.valueOf(output.getProductStock()));
        }
    }
}
