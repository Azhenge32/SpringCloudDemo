package com.azhen.cloud.order.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "product")
public interface ProductClient {
        @GetMapping("/msg")
        String productMsg();
}
