package com.example.backend.service;

import com.example.backend.dto.Productdto;
import com.example.backend.model.Category;
import com.example.backend.model.Product;
import com.example.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void createProduct(Productdto productDto, Category category) {
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setCategory(category);
        product.setPrice(productDto.getPrice());
        productRepository.save(product);

    }
    public  Productdto getProductdto(Product product) {
        Productdto productdto = new Productdto();
        productdto.setDescription(product.getDescription());
        productdto.setImageURL(product.getImageURL());
        productdto.setName(product.getName());
        productdto.setCategoryId(product.getCategory().getId());
        productdto.setPrice(product.getPrice());
        productdto.setId(product.getId());
        return productdto;
    }


    public List<Productdto> getAllProducts() {
        List<Product> allProducts  = productRepository.findAll();

        List<Productdto> productdtos  = new ArrayList<>();
        for (Product product: allProducts ){
            productdtos.add(getProductdto(product));

        }
        return productdtos;
    }

    public void updateProduct(Productdto productDto, Integer productId)throws Exception{
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (!optionalProduct.isPresent()) {

                throw new Exception("Produto não presente " + productId);

            }
            Product product = optionalProduct.get();
            product.setDescription(productDto.getDescription());
            product.setImageURL(productDto.getImageURL());
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            productRepository.save(product);



        }


    }

