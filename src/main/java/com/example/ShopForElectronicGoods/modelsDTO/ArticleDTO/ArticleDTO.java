package com.example.ShopForElectronicGoods.modelsDTO.ArticleDTO;


import com.example.ShopForElectronicGoods.models.Category;
import com.example.ShopForElectronicGoods.models.ENUMS.ArticleStatusEnum;

public class ArticleDTO {

    private Integer article_id;
    private String name;
   // private Integer category_id;
    private String excerpt;
    private String description;
    private ArticleStatusEnum status ;
    private int isPromoted;
    private Category category;

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArticleStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ArticleStatusEnum status) {
        this.status = status;
    }

    public int getIsPromoted() {
        return isPromoted;
    }

    public void setIsPromoted(int isPromoted) {
        this.isPromoted = isPromoted;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
