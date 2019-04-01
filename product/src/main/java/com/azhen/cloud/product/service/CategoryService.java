package com.azhen.cloud.product.service;


import com.azhen.cloud.product.entity.ProductCategory;

import java.util.List;


public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
