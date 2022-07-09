package com.example.backend.controller;

import com.example.backend.common.ApiResponse;
import com.example.backend.dto.Productdto;
import com.example.backend.model.Category;
import com.example.backend.model.Product;
import com.example.backend.repository.Categoryrepository;
import com.example.backend.service.CategoryService;
import com.example.backend.service.ProductService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")

public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    Categoryrepository categoryrepository;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody Productdto productDto) {
        Optional<Category> optionalCategory = categoryrepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Categoria do produto não existe"), HttpStatus.CREATED);
        }

        productService.createProduct(productDto, optionalCategory.get());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Produto adicionado com sucesso"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Productdto>> getProducts() {
        List<Productdto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // criando uma api para editar o produto


    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId, @RequestBody Productdto productDto) throws Exception {
        Optional<Category> optionalCategory = categoryrepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Categoria do produto não existe"), HttpStatus.BAD_REQUEST);
        }

        productService.updateProduct(productDto, productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Produto atualizar com sucesso"), HttpStatus.OK);
    }
}

