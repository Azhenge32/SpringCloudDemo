package com.azhen.cloud.product.server.service.impl;


import com.azhen.cloud.product.server.dataobject.ProductCategory;
import com.azhen.cloud.product.server.repository.ProductCategoryRepository;
import com.azhen.cloud.product.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
