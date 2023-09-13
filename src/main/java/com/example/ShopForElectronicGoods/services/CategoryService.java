package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.Category;
import com.example.ShopForElectronicGoods.repository.CategoryRepository;
import com.example.ShopForElectronicGoods.services.ArticleServices.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleService articleService;


    public Category getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new  ApiRequestException("Category with id " + categoryId + " not found", HttpStatus.NOT_FOUND));
    }

    public List<Category> getAllCategory(){
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }


    public Category addCategory(Category category){


        Category laptopCategory = new Category();
        laptopCategory.setName(category.getName());
        laptopCategory.setImage_path(category.getImage_path());

        if (category.getParentCategoryId() != null) {
            Category parentCategory = categoryRepository.findById(category.getParentCategoryId()).orElse(null);
            System.out.println(parentCategory + " eee");
            category.setParent_category(parentCategory);
        }
        System.out.println(category.getParentCategoryId() + " aaaaa");

       return categoryRepository.save(category);
    }


    public Category editCategoryById(Category category, Integer categoryId){
        Category categoryById = getCategoryById(categoryId);

        if(Objects.nonNull(category.getName()) && !"".equalsIgnoreCase(category.getName())){
            categoryById.setName(category.getName());
        }
        if(Objects.nonNull(category.getImage_path()) && !"".equalsIgnoreCase(category.getImage_path())){
            categoryById.setImage_path(category.getImage_path());
        }
        return categoryRepository.save(categoryById);
    }


    public void deleteCategoryById(Integer categoryId){
        Category c = getCategoryById(categoryId);
        if(c ==  null){
            throw new ApiRequestException("Category by ID " + categoryId + " not found", HttpStatus.NOT_FOUND);
        }
                categoryRepository.deleteById(categoryId);
        throw new ApiRequestException("Category by ID " + categoryId + " deleted successfully", HttpStatus.OK);
    }

}
