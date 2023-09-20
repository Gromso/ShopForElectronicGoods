package com.example.ShopForElectronicGoods.services.CartServices;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.*;
import com.example.ShopForElectronicGoods.modelsDTO.Cart.CartResponseDTO;
import com.example.ShopForElectronicGoods.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartArticleRepository cartArticleRepository;

    @Autowired
    private ArticleRepository articleRepository;


    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    public Cart getCartByCartId(Integer cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new ApiRequestException("cart by id " + cartId + " not found", HttpStatus.NOT_FOUND));

    }

    public Cart createNewCartForUser(Integer userId){
        Cart cart = new Cart();
        Cart cartUser = userRepository.findById(userId).map(user ->{
            cart.setUser(user);
            return cartRepository.save(cart);
        }).orElseThrow(() -> new ApiRequestException("save failed"));
        return cartRepository.save(cartUser);
    }


    public Cart addArticleToCart(final Integer cartId, final Integer articleId, int quantity){
        CartArticle record = cartArticleRepository.findCartArticleByCartIdAndArticleId(cartId,articleId);

        if(record == null) {
            record = new CartArticle();
            Cart cart = getCartByCartId(cartId);
            Article article = articleRepository.findById(articleId)
                    .orElseThrow(() -> new ApiRequestException("Article not found", HttpStatus.NOT_FOUND));

            record.setCart(cart);
            record.setArticle(article);
            record.setQuantity(quantity);
            cartArticleRepository.save(record);
        }else{
           updateCartArticleForQuantity(record, quantity);
        }

        return getCartById(cartId);
    }


    public void updateCartArticleForQuantity(CartArticle cartArticle, int quantity) {
        cartArticle.setQuantity(cartArticle.getQuantity() + quantity);
        cartArticleRepository.save(cartArticle);
    }

    public Cart getCartById(Integer cartId) {
        String sql = "SELECT c " +
                "FROM Cart c " +
                "LEFT JOIN user u ON c.user.userId = u.userId " +
                "LEFT JOIN cartArticle ca ON c.cart_id = ca.cart.cart_id " +
                "LEFT JOIN article a ON ca.article.article_id = a.article_id " +
                "LEFT JOIN category cat ON a.category.categoryId = cat.categoryId " +
                "WHERE c.cart_id = :cartId";

        TypedQuery<Cart> query = entityManager.createQuery(sql, Cart.class);
        query.setParameter("cartId", cartId);

        try {
            Cart record = query.getSingleResult();
            return record;
        } catch (NoResultException e) {
            throw new ApiRequestException("Cart with ID " + cartId + " not found", HttpStatus.BAD_REQUEST);
        }
    }


    public CartArticle updateQuantityForArticle(Integer cartId, Integer articleId, int quantity){
        CartArticle cartArticle = cartArticleRepository.findCartArticleByCartIdAndArticleId(cartId,articleId);
        if(cartArticle == null){
            throw new ApiRequestException("Article by ID " + articleId + " not found");
        }
        CartArticle ca = cartArticleRepository.findCartArticleByCartIdAndArticleId(cartId,articleId);
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        Article article = articleRepository.findById(articleId).orElseThrow();
        if(Objects.nonNull(cartArticle.getCart())){
            ca.setCart(cart);
        }
        if(Objects.nonNull(cartArticle.getArticle())){
            ca.setArticle(article);
        }
        ca.setQuantity(quantity);

        return cartArticleRepository.save(ca);
    }


    public void deleteCartArticleForArticle(Integer cartId, Integer articleId){
        CartArticle cartArticle = cartArticleRepository.findCartArticleByCartIdAndArticleId(cartId,articleId);
        if(cartArticle == null){
            throw new ApiRequestException("Article by ID " + articleId + " not found");
        }

         cartArticleRepository.delete(cartArticle);
    }
}
