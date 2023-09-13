package com.example.ShopForElectronicGoods.modelsDTO.ArticleDTO;

import com.example.ShopForElectronicGoods.models.ENUMS.ArticleStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ArticleEditDTO {

    @NotBlank
    @Size(min = 2, max = 128)
    private String name;
    //private Integer categoryId;

    @NotBlank
    @Size(min = 2, max = 255)
    private String excerpt;
    @NotBlank
    @Size(min = 2, max = 1000)
    private String description;
    private ArticleStatusEnum status = ArticleStatusEnum.available;
    private byte is_promoted;

    @NotNull
    private BigDecimal price;
    private Features[] features;


}
