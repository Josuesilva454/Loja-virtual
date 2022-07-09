package com.example.backend.controller;


import com.example.backend.common.ApiResponse;
import com.example.backend.model.Category;
import com.example.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public  ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "Criando nova Categoria"), HttpStatus.CREATED);

    }
    @GetMapping("/list")
    public List<Category> listCategory() {
       return categoryService.listCategory();


    }
    @PostMapping("/update/{categoryID}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryID") int categoryID, @RequestBody Category category) {
        System.out.print("categoria id" + categoryID);
        // Verifique se a categoria existe.
      if (!categoryService.findById(categoryID)){
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "A categoria não existem"), HttpStatus.NOT_FOUND);
        }
            // Se a categoria existir, atualize-a.
            categoryService.editCategory(categoryID, category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "atualizou a categoria"), HttpStatus.OK);

    }

    }


