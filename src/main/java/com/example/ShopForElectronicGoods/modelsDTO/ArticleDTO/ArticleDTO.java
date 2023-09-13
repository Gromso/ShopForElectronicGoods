package com.example.ShopForElectronicGoods.modelsDTO.ArticleDTO;


import com.example.ShopForElectronicGoods.models.Category;
import com.example.ShopForElectronicGoods.models.ENUMS.ArticleStatusEnum;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class ArticleDTO {

    @Min(1)
    @Max(20)
    @NotNull
    private Integer article_id;

    @Min(0)
    @NotNull
    private Integer category_id;

   // @NotBlank
    @Size(min = 1, max = 128)
    private String name;


  //  @NotBlank
    @Size(min = 2, max = 255)
    private String excerpt;

  //  @NotBlank
    @Size(min = 2, max = 1000)
    private String description;

    private ArticleStatusEnum status ;
    private int isPromoted;



    private List<ArticleFeatureDTO> articleFeatures;
    private List<FeatureDTO> features;
    private List<ArticlePriceDTO> articlePrices;
    private List<ArticlePhotosResponseDTO> photos;
    private Category category;

}
