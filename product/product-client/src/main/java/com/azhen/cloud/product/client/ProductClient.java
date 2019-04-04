package com.azhen.cloud.product.client;

import com.azhen.cloud.product.common.DecreaseStockInput;
import com.azhen.cloud.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product")
public interface ProductClient {
        @GetMapping("/msg")
        String productMsg();

        @PostMapping("/product/listForOrder")
        List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

        @PostMapping("/product/decreaseStock")
        void decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOList);
}
