package com.azhen.cloud.product.service;

import com.azhen.cloud.product.entity.ProductInfo;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

}
