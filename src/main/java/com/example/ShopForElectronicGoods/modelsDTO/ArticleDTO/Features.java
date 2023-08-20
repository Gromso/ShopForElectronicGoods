package com.example.ShopForElectronicGoods.modelsDTO.ArticleDTO;

public class Features {

    private Integer feature_id;
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
