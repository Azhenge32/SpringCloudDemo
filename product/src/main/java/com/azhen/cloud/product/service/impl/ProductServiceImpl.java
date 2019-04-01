package com.azhen.cloud.product.service.impl;

import com.azhen.cloud.product.entity.ProductInfo;
import com.azhen.cloud.product.enums.ProductStatusEnum;
import com.azhen.cloud.product.repository.ProductInfoRepository;
import com.azhen.cloud.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
}
