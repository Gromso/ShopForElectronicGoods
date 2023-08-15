package com.example.ShopForElectronicGoods.models.ENUMS;

public enum OrderStatusEnum {
    rejected("rejected"),
    accepted("accepted"),
    shipped("shipped"),
    pending("pending");

    private String displayName;

    OrderStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
