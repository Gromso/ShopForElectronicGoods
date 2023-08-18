package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.Article;
import com.example.ShopForElectronicGoods.models.Category;
import com.example.ShopForElectronicGoods.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleService articleService;


    public Category getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new  ApiRequestException("Category with id " + categoryId + " not found"));
    }

    public List<Category> getAllCategory(){
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }


    public Category addCategory(Category category){
       Category parentCategory = category.getParent_category();

// Kreirajte novu podkategoriju "Laptopovi" i postavite joj nadređenu kategoriju
        Category laptopCategory = new Category();
        laptopCategory.setName(category.getName());
        laptopCategory.setImage_path(category.getImage_path());
        laptopCategory.setParent_category(parentCategory); // Postavite parent kategoriju

// Dodajte novu podkategoriju u bazu*
       return categoryRepository.save(category);
    }

   public Set<Article> getAllArticlesForCategory(Integer categoryId) {
       Category category = getCategoryById(categoryId);
       return articleService.getAllArticlesByCategory(category);
   }

    public Category editCategoryById(Category category, Integer categoryId){
        Category categoryById = getCategoryById(categoryId);

        if(Objects.nonNull(category.getName()) && !"".equalsIgnoreCase(category.getName())){
            categoryById.setName(category.getName());
        }
        if(Objects.nonNull(category.getImage_path()) && !"".equalsIgnoreCase(category.getImage_path())){
            categoryById.setImage_path(category.getImage_path());
        }
        if(Objects.nonNull(category.getArticles())){
            categoryById.setArticles(category.getArticles());
        }
        return categoryRepository.save(categoryById);
    }


    public void deleteCategoryById(Integer categoryId){
        categoryRepository.deleteById(categoryId);
    }

}
