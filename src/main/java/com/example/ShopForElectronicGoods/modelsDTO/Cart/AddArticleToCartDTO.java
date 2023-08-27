package com.example.ShopForElectronicGoods.modelsDTO.Cart;

import com.example.ShopForElectronicGoods.models.Article;
import lombok.Data;

@Data
public class AddArticleToCartDTO {

    private Integer article_id;
    private int quantity;
}
