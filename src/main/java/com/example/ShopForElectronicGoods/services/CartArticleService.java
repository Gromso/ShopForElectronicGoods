package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.Article;
import com.example.ShopForElectronicGoods.models.Cart;
import com.example.ShopForElectronicGoods.models.CartArticle;
import com.example.ShopForElectronicGoods.modelsDTO.CartArticleDTO;
import com.example.ShopForElectronicGoods.repository.ArticleRepository;
import com.example.ShopForElectronicGoods.repository.CartArticleRepository;
import com.example.ShopForElectronicGoods.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CartArticleService {

    @Autowired
    private CartArticleRepository cartArticleRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public CartArticle getCartArticleById(Integer cartArticleId){
        return cartArticleRepository.findById(cartArticleId).orElseThrow(() ->
                new ApiRequestException("CartArticle with id " + cartArticleId + " not found", HttpStatus.NOT_FOUND));
    }



   public CartArticle updateCartArticleForQuantity(CartArticle cartArticle, Integer cartArticleId, int quantity){
       CartArticle cartArticle2 = getCartArticleById(cartArticleId);

       if(Objects.nonNull(cartArticle.getCart())){
           cartArticle2.setCart(cartArticle.getCart());
       }
       if(Objects.nonNull(cartArticle.getArticle())){
           cartArticle2.setArticle(cartArticle2.getArticle());
       }
       if(Objects.nonNull(cartArticle.getQuantity())){
           cartArticle2.setQuantity(cartArticle2.getQuantity() + quantity);
       }

      return cartArticleRepository.save(cartArticle2);
   }


}
