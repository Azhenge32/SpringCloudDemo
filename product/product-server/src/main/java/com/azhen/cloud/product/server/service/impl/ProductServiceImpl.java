package com.azhen.cloud.product.server.service.impl;


import com.azhen.cloud.product.common.DecreaseStockInput;
import com.azhen.cloud.product.common.ProductInfoOutput;
import com.azhen.cloud.product.server.dataobject.ProductInfo;
import com.azhen.cloud.product.server.enums.ProductStatusEnum;
import com.azhen.cloud.product.server.enums.ResultEnum;
import com.azhen.cloud.product.server.exception.ProductException;
import com.azhen.cloud.product.server.repository.ProductInfoRepository;
import com.azhen.cloud.product.server.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList).stream()
                .map(e -> {
                    ProductInfoOutput output = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, output);
                    return output;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void decreaseStock(List<DecreaseStockInput> cartDTOList) {
        for (DecreaseStockInput cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoRepository.findById(cartDTO.getProductId())
                    .orElseThrow(() -> new ProductException(ResultEnum.PRODUCT_NOT_EXIST));
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}
