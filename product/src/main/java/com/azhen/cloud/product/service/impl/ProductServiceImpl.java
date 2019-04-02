package com.azhen.cloud.product.service.impl;

import com.azhen.cloud.product.dataobject.ProductInfo;
import com.azhen.cloud.product.dto.CartDTO;
import com.azhen.cloud.product.enums.ProductStatusEnum;
import com.azhen.cloud.product.enums.ResultEnum;
import com.azhen.cloud.product.exception.ProductException;
import com.azhen.cloud.product.repository.ProductInfoRepository;
import com.azhen.cloud.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoRepository.findById(cartDTO.getProductId())
                    .orElseThrow(() ->  new ProductException(ResultEnum.PRODUCT_NOT_EXIST));
            Integer result  = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}
