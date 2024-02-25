package com.system.TrendThreads.services;

import com.system.TrendThreads.entity.Product;
import com.system.TrendThreads.pojo.ProductPojo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProductService {

    String saveProduct(ProductPojo productPojo) throws IOException;
    List<Product> fetchAll();
    Product getSingle(Integer id);
    List<Product> fetchByCategory(Integer id);
    List<Product> fetchNew();
    List<Product> trending();
    List<Product> mostPopular();
    List<Product> bestSeller();
    Map<Integer, Double> comparePrice(List<Product> products);
}
