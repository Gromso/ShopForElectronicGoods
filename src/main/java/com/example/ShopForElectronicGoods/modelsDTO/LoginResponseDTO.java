package com.example.ShopForElectronicGoods.modelsDTO;

import com.example.ShopForElectronicGoods.models.ApplicationUser;

public class LoginResponseDTO {
    private ApplicationUser user;
    private String token;
    private String refreshToken;
    private String expires_at;


    public LoginResponseDTO(ApplicationUser user, String token) {
        this.user = user;
        this.token = token;
    }
    public LoginResponseDTO(ApplicationUser user, String token, String refreshToken,String expires_at) {
        this.user = user;
        this.token = token;
        this.refreshToken = refreshToken;
        this.expires_at = expires_at;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }
}
