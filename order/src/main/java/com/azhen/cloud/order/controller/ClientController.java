package com.azhen.cloud.order.controller;


import com.azhen.cloud.order.client.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@Slf4j
public class ClientController {
    /*@Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;*/
    @Autowired
    private ProductClient productClient;
    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        // 1、第一种方式
      /*  RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8082/msg", String.class);
        return response;*/

       // 2、第二种
        /*ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort());
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url + "/msg", String.class);*/

        //String response = restTemplate.getForObject(  "http://PRODUCT/msg", String.class);
        String response = productClient.productMsg();
        log.info("response={}", response);
        return response;
    }
}
