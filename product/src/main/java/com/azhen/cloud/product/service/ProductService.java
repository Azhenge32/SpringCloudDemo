package com.azhen.cloud.product.service;

import com.azhen.cloud.product.dataobject.ProductInfo;
import com.azhen.cloud.product.dto.CartDTO;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    List<ProductInfo> findList(List<String> productIdList);

    /**
     * 扣库存
     * @param cartDTOList
     */
    void decreaseStock(List<CartDTO> cartDTOList);
}
