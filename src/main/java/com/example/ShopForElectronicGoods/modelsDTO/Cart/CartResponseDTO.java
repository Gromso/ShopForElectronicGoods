package com.example.ShopForElectronicGoods.modelsDTO.Cart;


import com.example.ShopForElectronicGoods.models.CartArticle;

import java.util.Date;
import java.util.List;

public class CartResponseDTO {

    private Integer cart_id;
    private Integer user_id;
    private Date created_at_cart;
    private List<CartArticleDTO> cartArticles;

    public Integer getCart_id() {
        return cart_id;
    }

    public void setCart_id(Integer cart_id) {
        this.cart_id = cart_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getCreated_at_cart() {
        return created_at_cart;
    }

    public void setCreated_at_cart(Date created_at_cart) {
        this.created_at_cart = created_at_cart;
    }

    public List<CartArticleDTO> getCartArticles() {
        return cartArticles;
    }

    public void setCartArticles(List<CartArticleDTO> cartArticles) {
        this.cartArticles = cartArticles;
    }
}
