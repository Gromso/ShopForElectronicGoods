package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.Article;
import com.example.ShopForElectronicGoods.models.Category;
import com.example.ShopForElectronicGoods.repository.ArticleRepository;
import com.example.ShopForElectronicGoods.repository.CategoryRepository;
import com.example.ShopForElectronicGoods.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/{categoryId}")
    public Category getCategoryById(@PathVariable Integer categoryId){
        return categoryService.getCategoryById(categoryId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories (){
        try{
            List<Category> categories = categoryService.getAllCategory();
            return ResponseEntity.ok(categories);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
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

    @GetMapping("/all/{categoryId}/article")
    public ResponseEntity<List<Article>> addArticleToCart(@PathVariable  Integer categoryId){
        if(!categoryRepository.existsById(categoryId)){
            throw  new ApiRequestException("nesto nije kako treba",HttpStatus.BAD_REQUEST);
        }
        List<Article> articles = articleRepository.findByCategoryId(categoryId);
        return ResponseEntity.ok(articles);
    }

}
