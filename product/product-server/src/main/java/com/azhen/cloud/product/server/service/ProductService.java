package com.azhen.cloud.product.server.service;

import com.azhen.cloud.product.common.DecreaseStockInput;
import com.azhen.cloud.product.common.ProductInfoOutput;
import com.azhen.cloud.product.server.dataobject.ProductInfo;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    List<ProductInfoOutput> findList(List<String> productIdList);

    /**
     * 扣库存
     *
     * @param cartDTOList
     */
    void decreaseStock(List<DecreaseStockInput> cartDTOList);
}
