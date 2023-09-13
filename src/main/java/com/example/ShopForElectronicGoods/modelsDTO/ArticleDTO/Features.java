package com.example.ShopForElectronicGoods.modelsDTO.ArticleDTO;

import jakarta.validation.constraints.*;

public class Features {

    @Min(1)
    @Max(20)
    @NotNull
    private Integer feature_id;

    @NotBlank
    @Size(min = 2, max = 128)
    private String value;

    // Konstruktor
    public Features(Integer feature_id, String value) {
        this.feature_id = feature_id;
        this.value = value;
    }

    // Getteri i setteri za polja
    public int getFeature_id() {
        return feature_id;
    }

    public void setFeature_id(Integer feature_id) {
        this.feature_id = feature_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
