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

    public List<CartArticle> getAllCartArticle(){
        List<CartArticle> cartArticles = cartArticleRepository.findAll();
        return cartArticles;
    }

   /* public CartArticle addCartArticleByCartIdAndArticleId(CartArticle cartArticle, Integer cartId, Integer articleId){
        CartArticle cartArticlee = new CartArticle();
        cartArticlee = cartRepository.findById(cartId).map(cart ->{
            cartArticle.setCart(cart);
            return cartArticleRepository.save(cartArticle);
        }).orElseThrow(() -> new ApiRequestException("cart not found", HttpStatus.NOT_FOUND));
        cartArticlee = articleRepository.findById(articleId).map(article ->{
            cartArticle.setArticle(article);
            return  cartArticleRepository.save(cartArticle);
        }).orElseThrow(() -> new ApiRequestException("article not found", HttpStatus.NOT_FOUND));

        return cartArticleRepository.save(cartArticlee);
    }*/
   public CartArticle addCartArticleByCartIdAndArticleId(CartArticle cartArticle, Integer cartId, Integer articleId) {
       Cart cart = cartRepository.findById(cartId)
               .orElseThrow(() -> new ApiRequestException("Cart not found", HttpStatus.NOT_FOUND));

       Article article = articleRepository.findById(articleId)
               .orElseThrow(() -> new ApiRequestException("Article not found", HttpStatus.NOT_FOUND));

       cartArticle.setCart(cart);
       cartArticle.setArticle(article);

       return cartArticleRepository.save(cartArticle);
   }


}
