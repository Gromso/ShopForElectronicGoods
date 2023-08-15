package com.example.ShopForElectronicGoods.models.ENUMS;

public enum ArticleStatusEnum {
    available("available"),
    visible("visible"),
    hidden("hidden");

    private String displayName;

    ArticleStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
