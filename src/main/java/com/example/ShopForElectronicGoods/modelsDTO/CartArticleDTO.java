package com.example.ShopForElectronicGoods.modelsDTO;

import com.example.ShopForElectronicGoods.models.Article;
import com.example.ShopForElectronicGoods.models.Cart;
import com.example.ShopForElectronicGoods.models.CartArticle;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CartArticleDTO {

    @Min(0)
    private Integer cart_article_id;
    private Cart cart;
    private Article article;
    @Min(0)
    private int quantity;

    public static CartArticleDTO from(CartArticle cartArticle){
        CartArticleDTO caDTO = new CartArticleDTO();
        caDTO.setCart_article_id(cartArticle.getCart_article_id());
        caDTO.setCart(cartArticle.getCart());
        caDTO.setArticle(cartArticle.getArticle());
        caDTO.setQuantity(cartArticle.getQuantity());
        return caDTO;
    }

}
