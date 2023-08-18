package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.Article;
import com.example.ShopForElectronicGoods.models.Category;
import com.example.ShopForElectronicGoods.models.ENUMS.ArticleStatusEnum;
import com.example.ShopForElectronicGoods.repository.ArticleRepository;
import com.example.ShopForElectronicGoods.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Article findArticleById(Integer articleId){
        return articleRepository.findById(articleId).orElse(null);
    }

    public void deleteArticleById(Integer articleId){
        articleRepository.deleteById(articleId);
        throw new ApiRequestException("message", 1001);
    }

    public Article addArticle(Article article, Integer categoryId) {
        Article article1 = categoryRepository.findById(categoryId).map(category ->{
            article.setCategory(category);
            return articleRepository.save(article);
        }).orElseThrow(() -> new ApiRequestException("save failed"));
        return  articleRepository.save(article1);
    }

    public Article editArticleOrSave(Article article, Integer articleId){
        Article articleById = findArticleById(articleId);

        if(Objects.nonNull(article.getName()) && !"".equalsIgnoreCase(article.getName())){
            articleById.setName(article.getName());
        }
        if(Objects.nonNull(article.getCartArticle())){
            articleById.setCartArticle(article.getCartArticle());
        }
        if(Objects.nonNull(article.getExcerpt()) && !"".equalsIgnoreCase(article.getExcerpt())){
            articleById.setExcerpt(article.getExcerpt());
        }
        if(Objects.nonNull(article.getDescription()) && !"".equalsIgnoreCase(article.getDescription())){
            articleById.setDescription(article.getDescription());
        }
        if(Objects.nonNull(article.getStatus())){
            try {
                ArticleStatusEnum status = article.getStatus();
                articleById.setStatus(status);
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }
        }
        if(Objects.nonNull(article.getIs_promoted())){
            articleById.setIs_promoted(article.getIs_promoted());
        }
        return articleRepository.save(articleById);
    }


    public Set<Article> getAllArticlesByCategory(Category category) {
        return articleRepository.findByCategory(category);
    }
}
