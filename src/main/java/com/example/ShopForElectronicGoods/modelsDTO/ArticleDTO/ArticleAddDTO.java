package com.example.ShopForElectronicGoods.modelsDTO.ArticleDTO;




import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data()
public class ArticleAddDTO {

    @Min(0)
    @Max(20)
    @NotNull
    private Integer categoryId;


    @NotBlank
    @Size(min = 2, max = 128)
    private String name;


    @NotBlank
    @Size(min = 2, max = 255)
    private String excerpt;

    @NotBlank
    @Size(min = 2, max = 1000)
    private String description;

    @NotNull
    private BigDecimal price;

    private Features[] features;


}
