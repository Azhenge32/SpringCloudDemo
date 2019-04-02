package com.azhen.cloud.product.server.service;



import com.azhen.cloud.product.server.dataobject.ProductCategory;

import java.util.List;


public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
