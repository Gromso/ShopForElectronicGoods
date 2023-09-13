package com.example.ShopForElectronicGoods.modelsDTO.Cart;

import com.example.ShopForElectronicGoods.models.Article;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddArticleToCartDTO {

    @NotNull
    @Min(1)
    @Max(20)
    private Integer article_id;

    @Min(0)
    private int quantity;
}
