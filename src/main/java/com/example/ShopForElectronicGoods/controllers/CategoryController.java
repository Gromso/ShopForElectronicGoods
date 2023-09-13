package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.Article;
import com.example.ShopForElectronicGoods.models.Category;
import com.example.ShopForElectronicGoods.repository.ArticleRepository;
import com.example.ShopForElectronicGoods.repository.CategoryRepository;
import com.example.ShopForElectronicGoods.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/{categoryId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Category getCategoryById(@PathVariable Integer categoryId){
        return categoryService.getCategoryById(categoryId);
    }

    @GetMapping("/categories")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Category>> getAllCategories (){
        try{
            List<Category> categories = categoryService.getAllCategory();
            System.out.println(categories);
            return ResponseEntity.ok(categories);

        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category){
        try{
            Category c = categoryService.addCategory(category);
            return ResponseEntity.ok(c);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> editCategoryById(@Valid @RequestBody Category category, @PathVariable Integer categoryId){
        try{
            Category cache = categoryService.editCategoryById(category,categoryId);
            return ResponseEntity.ok(cache);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategoryById(@PathVariable Integer categoryId){

        categoryService.deleteCategoryById(categoryId);
    }

    @GetMapping("/all/{categoryId}/article")
    public ResponseEntity<List<Article>> findArticleToCategory(@PathVariable  Integer categoryId){
        if(!categoryRepository.existsById(categoryId)){
            throw  new ApiRequestException("Category by ID "+ categoryId + " not found",HttpStatus.NOT_FOUND);
        }
        List<Article> articles = articleRepository.findByCategoryId(categoryId);
        return ResponseEntity.ok(articles);
    }

}
