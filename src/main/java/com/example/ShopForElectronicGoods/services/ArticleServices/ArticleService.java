package com.example.ShopForElectronicGoods.services.ArticleServices;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.*;
import com.example.ShopForElectronicGoods.models.ENUMS.ArticleStatusEnum;
import com.example.ShopForElectronicGoods.modelsDTO.ArticleDTO.ArticleAddDTO;
import com.example.ShopForElectronicGoods.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.ShopForElectronicGoods.modelsDTO.ArticleDTO.Features;

import java.util.List;
import java.util.Objects;


@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleFeatureRepository articleFeatureRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private ArticlePriceRepository articlePriceRepository;

    public Article findArticleById(Integer articleId){
        return articleRepository.findById(articleId).orElse(null);
    }

    public List<Article> findAllArticle(){
        return articleRepository.findAll();
    }

    public Article addArticleDTO(ArticleAddDTO articleDto){
        Article article = new Article();
        article.setName(articleDto.getName());
        Category category = categoryRepository.findById(articleDto.getCategoryId()).orElseThrow(() -> new ApiRequestException("category with id" + articleDto.getCategoryId() + " not found"));
        article.setCategory(category);
        article.setExcerpt(articleDto.getExcerpt());
        article.setDescription(articleDto.getDescription());
        Article saveArticled = articleRepository.save(article);

        ArticlePrice ap = new ArticlePrice();

        ap.setArticle(saveArticled);
        ap.setPrice(articleDto.getPrice());

        articlePriceRepository.save(ap);

        for(Features f : articleDto.getFeatures()){
            Feature Feature = featureRepository.findById(f.getFeature_id()).orElseThrow(() -> new ApiRequestException(""));
            ArticleFeature afeature = new ArticleFeature();
            afeature.setArticle(saveArticled);
            afeature.setFeature(Feature);
            afeature.setValue(f.getValue());

            articleFeatureRepository.save(afeature);
        }

       return saveArticled;

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


    public List<Feature> findArticleFeatureByArticle(Integer articleId){
        if (!articleRepository.existsById(articleId)){
            throw new ApiRequestException("User with id "+ articleId + " not found", HttpStatus.NOT_FOUND);
        }
        List<Feature> articleFeatures = articleFeatureRepository.findArticleFeatureByArticleId(articleId);
        return articleFeatures;
    }
    public void deleteArticleById(Integer articleId){
        articleRepository.deleteById(articleId);
        throw new ApiRequestException("message", 1001);
    }
}
