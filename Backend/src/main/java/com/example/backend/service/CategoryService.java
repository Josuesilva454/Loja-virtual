package com.example.backend.service;


import com.example.backend.model.Category;
import com.example.backend.repository.Categoryrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    Categoryrepository categoryrepository;

    public void createCategory(Category category) {
        categoryrepository.save(category);
    }

    public List<Category> listCategory() {
        return categoryrepository.findAll();
    }

    public void editCategory(int categoryID, Category updateCategory) {
        Category category = categoryrepository.findById(categoryID).get();
        category.setCategoryName(updateCategory.getCategoryName());
        category.setDescription(updateCategory.getDescription());
        category.setImageUrl(updateCategory.getImageUrl());

        categoryrepository.save(category);
    }
    public boolean findById(int categoryId) {
        return categoryrepository.findById(categoryId).isPresent();
    }
}