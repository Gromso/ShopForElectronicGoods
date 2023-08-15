package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.models.Category;
import com.example.ShopForElectronicGoods.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{categoryId}")
    public Category getCategoryById(@PathVariable Integer categoryId){
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        try{
            Category c = categoryService.addCategory(category);
            return ResponseEntity.ok(c);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit/{categoryId}")
    public ResponseEntity<Category> editCategoryById(@RequestBody Category category, @PathVariable Integer categoryId){
        try{
            Category cache = categoryService.editCategoryById(category,categoryId);
            return ResponseEntity.ok(cache);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{categoryId}")
    public void deleteCategoryById(@PathVariable Integer categoryId){
        categoryService.deleteCategoryById(categoryId);
    }


}
