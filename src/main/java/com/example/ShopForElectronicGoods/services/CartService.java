package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.*;
import com.example.ShopForElectronicGoods.modelsDTO.ArticleDTO.ArticleDTO;
import com.example.ShopForElectronicGoods.modelsDTO.Cart.CartArticleDTO;
import com.example.ShopForElectronicGoods.modelsDTO.Cart.CartResponseDTO;
import com.example.ShopForElectronicGoods.repository.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartArticleRepository cartArticleRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;


    public void updateCartArticleForQuantity(CartArticle cartArticle, int quantity) {
        cartArticle.setQuantity(cartArticle.getQuantity() + quantity);
        cartArticleRepository.save(cartArticle);
    }

    public Orders getOrderByCartId(Integer cartId){
        List<Orders> orders = orderRepository.getOrderByCartId(cartId);
        if(orders.isEmpty()){
            return null;
        }else{
            return orders.get(0);
        }
    }


    public ArticleDTO getArticleDTO(Integer articleId, Integer cartArticleId){
        Article a = articleRepository.findArticleByArticleIdAndCartArticleId(articleId,cartArticleId);
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setArticle_id(a.getArticle_id());
        articleDTO.setName(a.getName());
        articleDTO.setExcerpt(a.getExcerpt());
        articleDTO.setDescription(a.getDescription());
        articleDTO.setStatus(a.getStatus());
        articleDTO.setIsPromoted(a.getIs_promoted());
        articleDTO.setCategory(a.getCategory());

        return articleDTO;
    }

    public CartResponseDTO getListCartArticles(Integer cartId, Integer userId){
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ApiRequestException("Cart with ID " + cartId + " not found"));

        List<CartArticle> cartArticles = cartArticleRepository.findCartArticleByCartId(cartId);

        if (cartArticles.isEmpty()) {
            throw new ApiRequestException("cartArticle is Empty", HttpStatus.NOT_FOUND);
        }

        // Mapiranje na DTO objekte
        List<CartArticleDTO> cartArticleDTOs = cartArticles.stream()
                .map(cartArticle -> {
                    CartArticleDTO dto = new CartArticleDTO();
                    dto.setCartArticle_id(cartArticle.getCart_article_id());
                    dto.setCart_id(cartArticle.getCart().getCart_id());
                    dto.setArticle_id(cartArticle.getArticle().getArticle_id());
                    dto.setQuantity(cartArticle.getQuantity());
                    dto.setArticles(getArticleDTO(cartArticle.getArticle().getArticle_id(),cartArticle.getCart_article_id()));
                    return dto;
                })
                .collect(Collectors.toList());

        // Kreiranje odgovarajućeg JSON odgovora
        CartResponseDTO responseDTO = new CartResponseDTO();
        responseDTO.setCart_id(cart.getCart_id());
        responseDTO.setUser_id(userId);
        responseDTO.setCreated_at_cart(cart.getCreated_at_cart());
        responseDTO.setCartArticles(cartArticleDTOs);

        return responseDTO;
    }


    public Cart addArticleToCart(final Integer cartId, final Integer articleId, int quantity){
       CartArticle record = cartArticleRepository.findCartArticleByCartIdAndArticleId(cartId,articleId);

        if(record == null) {
            record = new CartArticle();
            Cart cart = cartRepository.findById(cartId)
                    .orElseThrow(() -> new ApiRequestException("Cart not found", HttpStatus.NOT_FOUND));

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

    public Cart createNewCartForUser(Integer userId){
        Cart cart = new Cart();
        Cart cartUser = userRepository.findById(userId).map(user ->{
            cart.setUser(user);
            return cartRepository.save(cart);
        }).orElseThrow(() -> new ApiRequestException("save failed"));
        return cartRepository.save(cartUser);
    }



    public Cart getCartById(Integer cartId) {
        String sql = "SELECT c " +
                "FROM Cart c " +
                "LEFT JOIN user u ON c.user.user_id = u.user_id " +
                "LEFT JOIN cartArticle ca ON c.cart_id = ca.cart.cart_id " +
                "LEFT JOIN article a ON ca.article.article_id = a.article_id " +
                "LEFT JOIN category cat ON a.category.category_id = cat.category_id " +
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


    public List<Cart> getAllCart(){
        return cartRepository.findAll();
    }

    public Cart addCart(Cart cart,Integer userId){
        Cart cartUser = userRepository.findById(userId).map(user ->{
            cart.setUser(user);
            return cartRepository.save(cart);
        }).orElseThrow(() -> new ApiRequestException("save failed"));
        return cartRepository.save(cartUser);
    }




}
