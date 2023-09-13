package com.example.ShopForElectronicGoods.modelsDTO.Cart;

import com.example.ShopForElectronicGoods.modelsDTO.ArticleDTO.ArticleDTO;
import jakarta.validation.constraints.Min;

import java.util.List;

public class CartArticleDTO {

    @Min(0)
    private Integer cartArticle_id;
    @Min(0)
    private Integer cart_id;
    @Min(0)
    private Integer article_id;
    @Min(0)
    private Integer quantity;
    private ArticleDTO articles;

    public Integer getCartArticle_id() {
        return cartArticle_id;
    }

    public void setCartArticle_id(Integer cartArticle_id) {
        this.cartArticle_id = cartArticle_id;
    }

    public Integer getCart_id() {
        return cart_id;
    }

    public void setCart_id(Integer cart_id) {
        this.cart_id = cart_id;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ArticleDTO getArticles() {
        return articles;
    }

    public void setArticles(ArticleDTO articles) {
        this.articles = articles;
    }
}
