package com.example.ShopForElectronicGoods.modelsDTO.ArticleDTO;

import com.example.ShopForElectronicGoods.models.ENUMS.ArticleStatusEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ArticleEditDTO {

    private String name;
    //private Integer categoryId;
    private String excerpt;
    private String description;
    private ArticleStatusEnum status = ArticleStatusEnum.available;
    private byte is_promoted;
    private BigDecimal price;
    private Features[] features;


}
