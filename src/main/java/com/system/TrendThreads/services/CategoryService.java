package com.system.TrendThreads.services;

import com.system.TrendThreads.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> fetchAll();
    Optional<Category> findById(Integer id);
}
