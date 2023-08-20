package com.example.ShopForElectronicGoods.modelsDTO.ArticleDTO;




import lombok.Data;

import java.math.BigDecimal;

@Data()
public class ArticleAddDTO {

    private String name;
    private Integer categoryId;
    private String excerpt;
    private String description;
    private BigDecimal price;
    private Features[] features;


}
