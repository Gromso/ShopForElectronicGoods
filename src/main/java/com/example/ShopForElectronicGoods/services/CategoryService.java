package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.models.Category;
import com.example.ShopForElectronicGoods.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public Category addCategory(Category category){
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
        if(Objects.nonNull(category.getCategory_id())){
            categoryById.setCategory_id(category.getCategory_id());
        }
        return categoryRepository.save(categoryById);
    }


    public void deleteCategoryById(Integer categoryId){
        categoryRepository.deleteById(categoryId);
    }

}
