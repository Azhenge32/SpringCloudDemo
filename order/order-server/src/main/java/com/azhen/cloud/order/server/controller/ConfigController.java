package com.azhen.cloud.order.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {
    @Value("${env}")
    private String env;

    @GetMapping("/env")
    public String env() {
        return env;
    }
}
