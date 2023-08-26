package com.example.ShopForElectronicGoods.modelsDTO.Cart;

import com.example.ShopForElectronicGoods.models.ApplicationUser;
import com.example.ShopForElectronicGoods.models.Cart;
import com.example.ShopForElectronicGoods.models.CartArticle;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class CartDTO {

    private Integer cartId;
    private ApplicationUser user;
    private Set<CartArticle> cartArticle;
    private Date created_at_cart;

    public static CartDTO from(Cart cart){
        CartDTO cartDto = new CartDTO();
        cartDto.setCartId(cart.getCart_id());
        cartDto.setUser(cartDto.getUser());
        cartDto.setCartArticle(cartDto.getCartArticle());
        cartDto.setCreated_at_cart(cartDto.getCreated_at_cart());
        return cartDto;
    }
}
