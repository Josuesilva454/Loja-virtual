package com.example.backend.repository;
import com.example.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Categoryrepository  extends JpaRepository<Category, Integer>{
    Category findByCategoryName(String categoryName);

}
